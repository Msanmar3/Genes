/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.controller;

import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import com.upo.genesmaven.entities.Nomenclatures;
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
public class NomenclaturesJpaController implements Serializable {
  
    public NomenclaturesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("genes_PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public NomenclaturesJpaController() {
        emf.createEntityManager();
    }

    public void create(Nomenclatures nomenclatures) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nomenclatures);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nomenclatures nomenclatures) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nomenclatures = em.merge(nomenclatures);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomenclatures.getIdNomenclature();
                if (findNomenclatures(id) == null) {
                    throw new NonexistentEntityException("The nomenclatures with id " + id + " no longer exists.");
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
            Nomenclatures nomenclatures;
            try {
                nomenclatures = em.getReference(Nomenclatures.class, id);
                nomenclatures.getIdNomenclature();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomenclatures with id " + id + " no longer exists.", enfe);
            }
            em.remove(nomenclatures);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nomenclatures> findNomenclaturesEntities() {
        return findNomenclaturesEntities(true, -1, -1);
    }

    public List<Nomenclatures> findNomenclaturesEntities(int maxResults, int firstResult) {
        return findNomenclaturesEntities(false, maxResults, firstResult);
    }

    private List<Nomenclatures> findNomenclaturesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nomenclatures.class));
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

    public Nomenclatures findNomenclatures(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nomenclatures.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomenclaturesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nomenclatures> rt = cq.from(Nomenclatures.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
