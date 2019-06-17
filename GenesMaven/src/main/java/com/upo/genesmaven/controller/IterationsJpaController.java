/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.controller;

import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import com.upo.genesmaven.entities.Iterations;
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
 * @author winlatop
 */
public class IterationsJpaController implements Serializable {

    public IterationsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("genes_PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public IterationsJpaController() {
        emf.createEntityManager();
    }
    
    public void create(Iterations iterations) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(iterations);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Iterations iterations) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            iterations = em.merge(iterations);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = iterations.getIdIteration();
                if (findIterations(id) == null) {
                    throw new NonexistentEntityException("The iterations with id " + id + " no longer exists.");
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
            Iterations iterations;
            try {
                iterations = em.getReference(Iterations.class, id);
                iterations.getIdIteration();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The iterations with id " + id + " no longer exists.", enfe);
            }
            em.remove(iterations);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Iterations> findIterationsEntities() {
        return findIterationsEntities(true, -1, -1);
    }

    public List<Iterations> findIterationsEntities(int maxResults, int firstResult) {
        return findIterationsEntities(false, maxResults, firstResult);
    }

    private List<Iterations> findIterationsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Iterations.class));
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

    public Iterations findIterations(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Iterations.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Iterations> findIterationsBySpecie(Integer idSpecie) {
        EntityManager em = getEntityManager();
        
         List<Iterations> listIterations = new ArrayList<>();
        try {
            listIterations.add((Iterations) em.createNamedQuery("Iterations.findByIdSpecie").setParameter("idSpecie", idSpecie).getSingleResult());
            
            return listIterations;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
       
    }
    
    public int getIterationsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Iterations> rt = cq.from(Iterations.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
