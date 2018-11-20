/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.controller;

import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.upo.genesmaven.entities.Menus;
import com.upo.genesmaven.entities.Roles;
import com.upo.genesmaven.entities.RolesMenus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author antonio
 */
public class RolesMenusJpaController implements Serializable {

    public RolesMenusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("genes_PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public RolesMenusJpaController() {
        emf.createEntityManager();
    }
    
    

    public void create(RolesMenus rolesMenus) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Menus idMenu = rolesMenus.getIdMenu();
            if (idMenu != null) {
                idMenu = em.getReference(idMenu.getClass(), idMenu.getIdMenu());
                rolesMenus.setIdMenu(idMenu);
            }
            Roles idRole = rolesMenus.getIdRole();
            if (idRole != null) {
                idRole = em.getReference(idRole.getClass(), idRole.getIdRole());
                rolesMenus.setIdRole(idRole);
            }
            em.persist(rolesMenus);
            if (idMenu != null) {
                idMenu.getRolesMenusList().add(rolesMenus);
                idMenu = em.merge(idMenu);
            }
            if (idRole != null) {
                idRole.getRolesMenusList().add(rolesMenus);
                idRole = em.merge(idRole);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RolesMenus rolesMenus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolesMenus persistentRolesMenus = em.find(RolesMenus.class, rolesMenus.getIdRoleMenu());
            Menus idMenuOld = persistentRolesMenus.getIdMenu();
            Menus idMenuNew = rolesMenus.getIdMenu();
            Roles idRoleOld = persistentRolesMenus.getIdRole();
            Roles idRoleNew = rolesMenus.getIdRole();
            if (idMenuNew != null) {
                idMenuNew = em.getReference(idMenuNew.getClass(), idMenuNew.getIdMenu());
                rolesMenus.setIdMenu(idMenuNew);
            }
            if (idRoleNew != null) {
                idRoleNew = em.getReference(idRoleNew.getClass(), idRoleNew.getIdRole());
                rolesMenus.setIdRole(idRoleNew);
            }
            rolesMenus = em.merge(rolesMenus);
            if (idMenuOld != null && !idMenuOld.equals(idMenuNew)) {
                idMenuOld.getRolesMenusList().remove(rolesMenus);
                idMenuOld = em.merge(idMenuOld);
            }
            if (idMenuNew != null && !idMenuNew.equals(idMenuOld)) {
                idMenuNew.getRolesMenusList().add(rolesMenus);
                idMenuNew = em.merge(idMenuNew);
            }
            if (idRoleOld != null && !idRoleOld.equals(idRoleNew)) {
                idRoleOld.getRolesMenusList().remove(rolesMenus);
                idRoleOld = em.merge(idRoleOld);
            }
            if (idRoleNew != null && !idRoleNew.equals(idRoleOld)) {
                idRoleNew.getRolesMenusList().add(rolesMenus);
                idRoleNew = em.merge(idRoleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rolesMenus.getIdRoleMenu();
                if (findRolesMenus(id) == null) {
                    throw new NonexistentEntityException("The rolesMenus with id " + id + " no longer exists.");
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
            RolesMenus rolesMenus;
            try {
                rolesMenus = em.getReference(RolesMenus.class, id);
                rolesMenus.getIdRoleMenu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolesMenus with id " + id + " no longer exists.", enfe);
            }
            Menus idMenu = rolesMenus.getIdMenu();
            if (idMenu != null) {
                idMenu.getRolesMenusList().remove(rolesMenus);
                idMenu = em.merge(idMenu);
            }
            Roles idRole = rolesMenus.getIdRole();
            if (idRole != null) {
                idRole.getRolesMenusList().remove(rolesMenus);
                idRole = em.merge(idRole);
            }
            em.remove(rolesMenus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RolesMenus> findRolesMenusEntities() {
        return findRolesMenusEntities(true, -1, -1);
    }

    public List<RolesMenus> findRolesMenusEntities(int maxResults, int firstResult) {
        return findRolesMenusEntities(false, maxResults, firstResult);
    }

    private List<RolesMenus> findRolesMenusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RolesMenus.class));
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

    public RolesMenus findRolesMenus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RolesMenus.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolesMenusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RolesMenus> rt = cq.from(RolesMenus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public List<RolesMenus> findRolesMenuByIdRol(Roles idRole) {
        EntityManager em = getEntityManager();
        try {
            return (List<RolesMenus>) em.createNamedQuery("RolesMenus.findByIdRole").setParameter("idRole", idRole).getResultList();
        } finally {
            em.close();
        }

    }
}
