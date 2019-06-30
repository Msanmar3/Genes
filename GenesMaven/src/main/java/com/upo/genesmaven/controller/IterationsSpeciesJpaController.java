/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.controller;

import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import com.upo.genesmaven.entities.Iterations;
import com.upo.genesmaven.entities.IterationsSpecies;
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
 * @author Mónica Sánchez
 */
public class IterationsSpeciesJpaController implements Serializable {

    public IterationsSpeciesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("genes_PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public IterationsSpeciesJpaController() {
        emf.createEntityManager();
    }
    
    public void create(IterationsSpecies iterationsSpecies) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(iterationsSpecies);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IterationsSpecies iterationsSpecies) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            iterationsSpecies = em.merge(iterationsSpecies);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = iterationsSpecies.getIdIterationSpecie();
                if (findIterationsSpecies(id) == null) {
                    throw new NonexistentEntityException("The iterationsSpecies with id " + id + " no longer exists.");
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
            IterationsSpecies iterationsSpecies;
            try {
                iterationsSpecies = em.getReference(IterationsSpecies.class, id);
                iterationsSpecies.getIdIterationSpecie();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The iterationsSpecies with id " + id + " no longer exists.", enfe);
            }
            em.remove(iterationsSpecies);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IterationsSpecies> findIterationsSpeciesEntities() {
        return findIterationsSpeciesEntities(true, -1, -1);
    }

    public List<IterationsSpecies> findIterationsSpeciesEntities(int maxResults, int firstResult) {
        return findIterationsSpeciesEntities(false, maxResults, firstResult);
    }

    private List<IterationsSpecies> findIterationsSpeciesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IterationsSpecies.class));
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

    public IterationsSpecies findIterationsSpecies(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IterationsSpecies.class, id);
        } finally {
            em.close();
        }
    }

    public List<IterationsSpecies> findIterationsBySpecie(Integer idSpecie) {
        EntityManager em = getEntityManager();
        
         List<IterationsSpecies> listIterations = new ArrayList<>();
        try {
            listIterations.add((IterationsSpecies) em.createNamedQuery("IterationsSpecies.findByIdSpecie").setParameter("idSpecie", idSpecie).getSingleResult());
            
            return listIterations;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
       
    }
    
    public List<Iterations> findIterationsBySpecieSQL(Integer idSpecie, Integer idAuthor) {
        EntityManager em = getEntityManager();
        
        List<Iterations> listIterations = new ArrayList<>();
         
        try {
            Query q = em.createNativeQuery("Select i.* from iterations i , iterations_species ie "
                    + "Where i.id_iteration = ie.id_iteration "
                    + "AND ie.id_specie= '"+idSpecie+"' "
                    + "AND i.id_author= '"+idAuthor+"'", Iterations.class);
            listIterations = q.getResultList();
//            System.out.println("aux!!!" + aux);
            return listIterations;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
       
    }
     
    public int getIterationsSpeciesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IterationsSpecies> rt = cq.from(IterationsSpecies.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
