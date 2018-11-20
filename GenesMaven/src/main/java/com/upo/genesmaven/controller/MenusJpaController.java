/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.controller;

import com.upo.genesmaven.controller.exceptions.IllegalOrphanException;
import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import com.upo.genesmaven.entities.Menus;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.upo.genesmaven.entities.RolesMenus;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author antonio
 */
public class MenusJpaController implements Serializable {

    public MenusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Menus menus) {
        if (menus.getRolesMenusList() == null) {
            menus.setRolesMenusList(new ArrayList<RolesMenus>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<RolesMenus> attachedRolesMenusList = new ArrayList<RolesMenus>();
            for (RolesMenus rolesMenusListRolesMenusToAttach : menus.getRolesMenusList()) {
                rolesMenusListRolesMenusToAttach = em.getReference(rolesMenusListRolesMenusToAttach.getClass(), rolesMenusListRolesMenusToAttach.getIdRoleMenu());
                attachedRolesMenusList.add(rolesMenusListRolesMenusToAttach);
            }
            menus.setRolesMenusList(attachedRolesMenusList);
            em.persist(menus);
            for (RolesMenus rolesMenusListRolesMenus : menus.getRolesMenusList()) {
                Menus oldIdMenuOfRolesMenusListRolesMenus = rolesMenusListRolesMenus.getIdMenu();
                rolesMenusListRolesMenus.setIdMenu(menus);
                rolesMenusListRolesMenus = em.merge(rolesMenusListRolesMenus);
                if (oldIdMenuOfRolesMenusListRolesMenus != null) {
                    oldIdMenuOfRolesMenusListRolesMenus.getRolesMenusList().remove(rolesMenusListRolesMenus);
                    oldIdMenuOfRolesMenusListRolesMenus = em.merge(oldIdMenuOfRolesMenusListRolesMenus);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Menus menus) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Menus persistentMenus = em.find(Menus.class, menus.getIdMenu());
            List<RolesMenus> rolesMenusListOld = persistentMenus.getRolesMenusList();
            List<RolesMenus> rolesMenusListNew = menus.getRolesMenusList();
            List<String> illegalOrphanMessages = null;
            for (RolesMenus rolesMenusListOldRolesMenus : rolesMenusListOld) {
                if (!rolesMenusListNew.contains(rolesMenusListOldRolesMenus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RolesMenus " + rolesMenusListOldRolesMenus + " since its idMenu field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<RolesMenus> attachedRolesMenusListNew = new ArrayList<RolesMenus>();
            for (RolesMenus rolesMenusListNewRolesMenusToAttach : rolesMenusListNew) {
                rolesMenusListNewRolesMenusToAttach = em.getReference(rolesMenusListNewRolesMenusToAttach.getClass(), rolesMenusListNewRolesMenusToAttach.getIdRoleMenu());
                attachedRolesMenusListNew.add(rolesMenusListNewRolesMenusToAttach);
            }
            rolesMenusListNew = attachedRolesMenusListNew;
            menus.setRolesMenusList(rolesMenusListNew);
            menus = em.merge(menus);
            for (RolesMenus rolesMenusListNewRolesMenus : rolesMenusListNew) {
                if (!rolesMenusListOld.contains(rolesMenusListNewRolesMenus)) {
                    Menus oldIdMenuOfRolesMenusListNewRolesMenus = rolesMenusListNewRolesMenus.getIdMenu();
                    rolesMenusListNewRolesMenus.setIdMenu(menus);
                    rolesMenusListNewRolesMenus = em.merge(rolesMenusListNewRolesMenus);
                    if (oldIdMenuOfRolesMenusListNewRolesMenus != null && !oldIdMenuOfRolesMenusListNewRolesMenus.equals(menus)) {
                        oldIdMenuOfRolesMenusListNewRolesMenus.getRolesMenusList().remove(rolesMenusListNewRolesMenus);
                        oldIdMenuOfRolesMenusListNewRolesMenus = em.merge(oldIdMenuOfRolesMenusListNewRolesMenus);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = menus.getIdMenu();
                if (findMenus(id) == null) {
                    throw new NonexistentEntityException("The menus with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Menus menus;
            try {
                menus = em.getReference(Menus.class, id);
                menus.getIdMenu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The menus with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RolesMenus> rolesMenusListOrphanCheck = menus.getRolesMenusList();
            for (RolesMenus rolesMenusListOrphanCheckRolesMenus : rolesMenusListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Menus (" + menus + ") cannot be destroyed since the RolesMenus " + rolesMenusListOrphanCheckRolesMenus + " in its rolesMenusList field has a non-nullable idMenu field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(menus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Menus> findMenusEntities() {
        return findMenusEntities(true, -1, -1);
    }

    public List<Menus> findMenusEntities(int maxResults, int firstResult) {
        return findMenusEntities(false, maxResults, firstResult);
    }

    private List<Menus> findMenusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Menus.class));
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

    public Menus findMenus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Menus.class, id);
        } finally {
            em.close();
        }
    }

    public int getMenusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Menus> rt = cq.from(Menus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
