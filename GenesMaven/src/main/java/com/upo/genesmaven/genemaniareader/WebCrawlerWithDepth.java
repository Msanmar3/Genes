/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.genemaniareader;

/**
 *
 * @author Mónica Sánchez Martín
 */
import java.io.File;
import java.io.FileOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WebCrawlerWithDepth {

    private File ouput;
    private String currentDir;
    private static final int MAX_DEPTH = 100;
    private HashSet<String> links;
    private List<String> ignores;
    //poner una ruta corta c:\data
//    private String urlBase="c:\\data";
    private String urlBase;
    private DBase db;
    private Connection conn;

    public WebCrawlerWithDepth(String urlBase) {
        this.urlBase = urlBase;
    }

    public WebCrawlerWithDepth() {
        links = new HashSet<>();
        //currentDir = System.getProperty("user.dir");
        currentDir = urlBase;
        //clase que se encarga de importar los datos a mysql
        db = new DBase();
        //  conn = db.connect( "jdbc:mysql://localhost:3306/genedata", "root", "artrobado");

        //lista negra de enlaces que no debe seguir el spider
        ignores = new ArrayList();
        ignores.add("http://genemania.org/data/");
        ignores.add("?C=N;O=D");
        ignores.add("?C=N;O=A");
        ignores.add("?C=M;O=A");
        ignores.add("?C=M;O=D");
        ignores.add("?C=S;O=A");
        ignores.add("?C=S;O=D");
        ignores.add("?C=D;O=A");
        ignores.add("?C=D;O=D");
        ignores.add("current_");
        ignores.add("gmt");

    }

    //descarga el fichero remoto a local
    public void downloadFileFromURL(String urlString, File destination) {
        try {
            URL website = new URL(urlString);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(destination);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // compruebo que existe el fichero remoto falla a veces
    public boolean exists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //      HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con
                    = (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //compruebo el fichero remoto este de momento no me ha fallado
    public static boolean fileExists(String URL) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();
            con.setRequestMethod("HEAD");
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //metodo estatico que descarga e importa a la bd es recursivo
    public void getPageLinks(String URL, int depth, int num, String tab) {
        //punto de parada si el link ya ha sido recorrido o llegue a la profundidad maxima
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            try {
                //saco el nombre del fichero
                String geneFile = URL.substring(URL.lastIndexOf('/') + 1);
                //ignoro las urls de la lista negra 
                if (!URL.equalsIgnoreCase(ignores.get(0)) && !ignores.contains(geneFile)) {
                    //añado el link a la lista para no repetirlo
                    links.add(URL);

                    //compruebo que el fichero exista y que sea un fichero valido
                    if (fileExists(URL) && !geneFile.isEmpty() && !geneFile.contains("_") && !geneFile.substring(geneFile.lastIndexOf('.') + 1).contains("gmt")) {
                        System.out.println(tab + ">> Depth: " + depth + " Num: " + num + " [" + URL + "]");

                        String[] geneDirPart = URL.split("/", -1);
                        String geneDir = geneDirPart[geneDirPart.length - 2];

                        //String geneOut = currentDir + "/src/main/java/es/upo/data/" + geneDir + "_" + geneFile;
                        String geneOut = currentDir + "/" + geneDir + "_" + geneFile;
                        //descargo el fichero 
                        ouput = new File(geneOut);
                        System.out.println(tab + "vv Download: " + geneOut);
                        downloadFileFromURL(URL, ouput);

                        System.out.println(tab + "|| Import to Database: " + geneOut);
                        //importo a mysql
                        //db.importData(conn, geneOut, geneDir + "_" + geneFile, geneFile);
                        //importo a mongo
                        db.importDataMongo(geneOut, geneDir + "_" + geneFile, geneFile);
                        num++;
                    }

                    //busco el siguiente enlace
                    Document document = Jsoup.connect(URL).get();
                    Elements linksOnPage = document.select("a[href]");

                    //aummento la profundida
                    depth++;
                    //llamada recursiva para el siguiente url
                    for (Element page : linksOnPage) {
                        getPageLinks(page.attr("abs:href"), depth, num, tab.concat("-"));
                    }
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
    //Mónica
    //metodo estatico que descarga e importa los ficheros identifier
    public void getPageLinksIdentifier(String URL, int depth, int num, String tab) {
        //punto de parada si el link ya ha sido recorrido o llegue a la profundidad maxima
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            try {
                //saco el nombre del fichero
                String geneFile = URL.substring(URL.lastIndexOf('/') + 1);
                //ignoro las urls de la lista negra 
                if (!URL.equalsIgnoreCase(ignores.get(0)) && !ignores.contains(geneFile) && geneFile.equalsIgnoreCase("identifier_mappings.txt")) {
                    //añado el link a la lista para no repetirlo
                    links.add(URL);

                    //compruebo que el fichero exista y que sea un fichero valido
                    if (fileExists(URL) && !geneFile.isEmpty() && !geneFile.contains("_") && !geneFile.substring(geneFile.lastIndexOf('.') + 1).contains("gmt")) {
                        System.out.println(tab + ">> Depth: " + depth + " Num: " + num + " [" + URL + "]");

                        String[] geneDirPart = URL.split("/", -1);
                        String specie = geneDirPart[geneDirPart.length - 2];

                        String geneOut = currentDir + "/" + specie + "_" + geneFile;
                        //descargo el fichero 
                        ouput = new File(geneOut);
                        System.out.println(tab + "vv Download: " + geneOut);
                        downloadFileFromURL(URL, ouput);

                        System.out.println(tab + "|| Import to Database: " + geneOut);
                        //importo a mongo los ficheros identifier
                        db.importDataMongoIdentifier(geneOut, specie);
                        num++;
                    }

                    //busco el siguiente enlace
                    Document document = Jsoup.connect(URL).get();
                    Elements linksOnPage = document.select("a[href]");

                    //aummento la profundida
                    depth++;
                    //llamada recursiva para el siguiente url
                    for (Element page : linksOnPage) {
                        getPageLinksIdentifier(page.attr("abs:href"), depth, num, tab.concat("-"));
                    }
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    public File getOuput() {
        return ouput;
    }

    public void setOuput(File ouput) {
        this.ouput = ouput;
    }

    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String currentDir) {
        this.currentDir = currentDir;
    }

    public HashSet<String> getLinks() {
        return links;
    }

    public void setLinks(HashSet<String> links) {
        this.links = links;
    }

    public List<String> getIgnores() {
        return ignores;
    }

    public void setIgnores(List<String> ignores) {
        this.ignores = ignores;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public DBase getDb() {
        return db;
    }

    public void setDb(DBase db) {
        this.db = db;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
