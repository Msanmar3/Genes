/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.controller;

import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import com.upo.genesmaven.entities.Files;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.upo.genesmaven.entities.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class FilesJpaController implements Serializable {

    public FilesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("genes_PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FilesJpaController() {
        emf.createEntityManager();
    }

    public void create(Files files) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users idUser = files.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                files.setIdUser(idUser);
            }
            em.persist(files);
            if (idUser != null) {
                idUser.getFilesList().add(files);
                idUser = em.merge(idUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Files files) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Files persistentFiles = em.find(Files.class, files.getIdFile());
            Users idUserOld = persistentFiles.getIdUser();
            Users idUserNew = files.getIdUser();
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                files.setIdUser(idUserNew);
            }
            files = em.merge(files);
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getFilesList().remove(files);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getFilesList().add(files);
                idUserNew = em.merge(idUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = files.getIdFile();
                if (findFiles(id) == null) {
                    throw new NonexistentEntityException("The files with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Files files;
            try {
                files = em.getReference(Files.class, id);
                files.getIdFile();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The files with id " + id + " no longer exists.", enfe);
            }
            Users idUser = files.getIdUser();
            if (idUser != null) {
                idUser.getFilesList().remove(files);
                idUser = em.merge(idUser);
            }
            em.remove(files);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Files> findFilesEntities() {
        return findFilesEntities(true, -1, -1);
    }

    public List<Files> findFilesEntities(int maxResults, int firstResult) {
        return findFilesEntities(false, maxResults, firstResult);
    }

    private List<Files> findFilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Files.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Files findFiles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Files.class, id);
        } finally {
            em.close();
        }
    }

    public int getFilesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Files> rt = cq.from(Files.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
