/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.controller;

import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import com.upo.genesmaven.entities.Authors;
import com.upo.genesmaven.entities.AuthorsYear;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class AuthorsJpaController implements Serializable {

    public AuthorsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("genes_PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public AuthorsJpaController() {
        emf.createEntityManager();
    }

    public void create(Authors authors) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(authors);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Authors authors) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            authors = em.merge(authors);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = authors.getIdAuthor();
                if (findAuthors(id) == null) {
                    throw new NonexistentEntityException("The authors with id " + id + " no longer exists.");
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
            Authors authors;
            try {
                authors = em.getReference(Authors.class, id);
                authors.getIdAuthor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The authors with id " + id + " no longer exists.", enfe);
            }
            em.remove(authors);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Authors> findAuthorsEntities() {
        return findAuthorsEntities(true, -1, -1);
    }

    public List<Authors> findAuthorsEntities(int maxResults, int firstResult) {
        return findAuthorsEntities(false, maxResults, firstResult);
    }

    private List<Authors> findAuthorsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Authors.class));
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

    public Authors findAuthors(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Authors.class, id);
        } finally {
            em.close();
        }
    }

    public Authors findAuthorByIteration(Integer idIteration) {
        EntityManager em = getEntityManager();
        try {
            return (Authors) em.createNamedQuery("Authors.findByIteration").setParameter("idIteration", idIteration).getResultList();
        } finally {
            em.close();
        }
    }

    public List<AuthorsYear> findAuthorBySpecie(Integer idSpecie) {
        EntityManager em = getEntityManager();
        
        List<Object[]> list = new ArrayList<>();
         
        try {
            //TODO Ordenar por author
            Query q = em.createNativeQuery("Select a.id_author, a.name_author, i.year "
                    + "from iterations i , iterations_species ie, authors a "
                    + "Where a.id_author = i.id_author "
                    + "AND i.id_iteration = ie.id_iteration "
                    + "AND ie.id_specie= '"+idSpecie+"' "
                    + "ORDER BY a.name_author");
            list = q.getResultList();
//            System.out.println("aux!!!" + aux);
            List<AuthorsYear> listaAuthors= new ArrayList();
            for (Object[] a : list) {
                AuthorsYear aux = new AuthorsYear((Integer)a[0], (String)a[1], (String)a[2]);
                listaAuthors.add(aux);
            }
            return listaAuthors;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getAuthorsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Authors> rt = cq.from(Authors.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
