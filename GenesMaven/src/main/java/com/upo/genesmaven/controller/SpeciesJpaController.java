/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.controller;

import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import com.upo.genesmaven.entities.Species;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Mónica Sánchez Martín
 */
public class SpeciesJpaController implements Serializable {

    public SpeciesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("genes_PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public SpeciesJpaController() {
        emf.createEntityManager();
    }
    
    public void create(Species species) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(species);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Species species) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            species = em.merge(species);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = species.getIdSpecie();
                if (findSpecies(id) == null) {
                    throw new NonexistentEntityException("The species with id " + id + " no longer exists.");
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
            Species species;
            try {
                species = em.getReference(Species.class, id);
                species.getIdSpecie();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The species with id " + id + " no longer exists.", enfe);
            }
            em.remove(species);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Species> findSpeciesEntities() {
        return findSpeciesEntities(true, -1, -1);
    }

    public List<Species> findSpeciesEntities(int maxResults, int firstResult) {
        return findSpeciesEntities(false, maxResults, firstResult);
    }

    private List<Species> findSpeciesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Species.class));
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

    public Species findSpecies(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Species.class, id);
        } finally {
            em.close();
        }
    }

    public int getSpeciesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Species> rt = cq.from(Species.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
