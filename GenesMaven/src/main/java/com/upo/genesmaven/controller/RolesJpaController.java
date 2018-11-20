/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.controller;

import com.upo.genesmaven.controller.exceptions.IllegalOrphanException;
import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import com.upo.genesmaven.entities.Roles;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.upo.genesmaven.entities.Users;
import java.util.ArrayList;
import java.util.List;
import com.upo.genesmaven.entities.RolesMenus;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author antonio
 */
public class RolesJpaController implements Serializable {

    public RolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Roles roles) {
        if (roles.getUsersList() == null) {
            roles.setUsersList(new ArrayList<Users>());
        }
        if (roles.getRolesMenusList() == null) {
            roles.setRolesMenusList(new ArrayList<RolesMenus>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Users> attachedUsersList = new ArrayList<Users>();
            for (Users usersListUsersToAttach : roles.getUsersList()) {
                usersListUsersToAttach = em.getReference(usersListUsersToAttach.getClass(), usersListUsersToAttach.getIdUser());
                attachedUsersList.add(usersListUsersToAttach);
            }
            roles.setUsersList(attachedUsersList);
            List<RolesMenus> attachedRolesMenusList = new ArrayList<RolesMenus>();
            for (RolesMenus rolesMenusListRolesMenusToAttach : roles.getRolesMenusList()) {
                rolesMenusListRolesMenusToAttach = em.getReference(rolesMenusListRolesMenusToAttach.getClass(), rolesMenusListRolesMenusToAttach.getIdRoleMenu());
                attachedRolesMenusList.add(rolesMenusListRolesMenusToAttach);
            }
            roles.setRolesMenusList(attachedRolesMenusList);
            em.persist(roles);
            for (Users usersListUsers : roles.getUsersList()) {
                Roles oldIdRoleOfUsersListUsers = usersListUsers.getIdRole();
                usersListUsers.setIdRole(roles);
                usersListUsers = em.merge(usersListUsers);
                if (oldIdRoleOfUsersListUsers != null) {
                    oldIdRoleOfUsersListUsers.getUsersList().remove(usersListUsers);
                    oldIdRoleOfUsersListUsers = em.merge(oldIdRoleOfUsersListUsers);
                }
            }
            for (RolesMenus rolesMenusListRolesMenus : roles.getRolesMenusList()) {
                Roles oldIdRoleOfRolesMenusListRolesMenus = rolesMenusListRolesMenus.getIdRole();
                rolesMenusListRolesMenus.setIdRole(roles);
                rolesMenusListRolesMenus = em.merge(rolesMenusListRolesMenus);
                if (oldIdRoleOfRolesMenusListRolesMenus != null) {
                    oldIdRoleOfRolesMenusListRolesMenus.getRolesMenusList().remove(rolesMenusListRolesMenus);
                    oldIdRoleOfRolesMenusListRolesMenus = em.merge(oldIdRoleOfRolesMenusListRolesMenus);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Roles roles) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles persistentRoles = em.find(Roles.class, roles.getIdRole());
            List<Users> usersListOld = persistentRoles.getUsersList();
            List<Users> usersListNew = roles.getUsersList();
            List<RolesMenus> rolesMenusListOld = persistentRoles.getRolesMenusList();
            List<RolesMenus> rolesMenusListNew = roles.getRolesMenusList();
            List<String> illegalOrphanMessages = null;
            for (Users usersListOldUsers : usersListOld) {
                if (!usersListNew.contains(usersListOldUsers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Users " + usersListOldUsers + " since its idRole field is not nullable.");
                }
            }
            for (RolesMenus rolesMenusListOldRolesMenus : rolesMenusListOld) {
                if (!rolesMenusListNew.contains(rolesMenusListOldRolesMenus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RolesMenus " + rolesMenusListOldRolesMenus + " since its idRole field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Users> attachedUsersListNew = new ArrayList<Users>();
            for (Users usersListNewUsersToAttach : usersListNew) {
                usersListNewUsersToAttach = em.getReference(usersListNewUsersToAttach.getClass(), usersListNewUsersToAttach.getIdUser());
                attachedUsersListNew.add(usersListNewUsersToAttach);
            }
            usersListNew = attachedUsersListNew;
            roles.setUsersList(usersListNew);
            List<RolesMenus> attachedRolesMenusListNew = new ArrayList<RolesMenus>();
            for (RolesMenus rolesMenusListNewRolesMenusToAttach : rolesMenusListNew) {
                rolesMenusListNewRolesMenusToAttach = em.getReference(rolesMenusListNewRolesMenusToAttach.getClass(), rolesMenusListNewRolesMenusToAttach.getIdRoleMenu());
                attachedRolesMenusListNew.add(rolesMenusListNewRolesMenusToAttach);
            }
            rolesMenusListNew = attachedRolesMenusListNew;
            roles.setRolesMenusList(rolesMenusListNew);
            roles = em.merge(roles);
            for (Users usersListNewUsers : usersListNew) {
                if (!usersListOld.contains(usersListNewUsers)) {
                    Roles oldIdRoleOfUsersListNewUsers = usersListNewUsers.getIdRole();
                    usersListNewUsers.setIdRole(roles);
                    usersListNewUsers = em.merge(usersListNewUsers);
                    if (oldIdRoleOfUsersListNewUsers != null && !oldIdRoleOfUsersListNewUsers.equals(roles)) {
                        oldIdRoleOfUsersListNewUsers.getUsersList().remove(usersListNewUsers);
                        oldIdRoleOfUsersListNewUsers = em.merge(oldIdRoleOfUsersListNewUsers);
                    }
                }
            }
            for (RolesMenus rolesMenusListNewRolesMenus : rolesMenusListNew) {
                if (!rolesMenusListOld.contains(rolesMenusListNewRolesMenus)) {
                    Roles oldIdRoleOfRolesMenusListNewRolesMenus = rolesMenusListNewRolesMenus.getIdRole();
                    rolesMenusListNewRolesMenus.setIdRole(roles);
                    rolesMenusListNewRolesMenus = em.merge(rolesMenusListNewRolesMenus);
                    if (oldIdRoleOfRolesMenusListNewRolesMenus != null && !oldIdRoleOfRolesMenusListNewRolesMenus.equals(roles)) {
                        oldIdRoleOfRolesMenusListNewRolesMenus.getRolesMenusList().remove(rolesMenusListNewRolesMenus);
                        oldIdRoleOfRolesMenusListNewRolesMenus = em.merge(oldIdRoleOfRolesMenusListNewRolesMenus);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = roles.getIdRole();
                if (findRoles(id) == null) {
                    throw new NonexistentEntityException("The roles with id " + id + " no longer exists.");
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
            Roles roles;
            try {
                roles = em.getReference(Roles.class, id);
                roles.getIdRole();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roles with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Users> usersListOrphanCheck = roles.getUsersList();
            for (Users usersListOrphanCheckUsers : usersListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Roles (" + roles + ") cannot be destroyed since the Users " + usersListOrphanCheckUsers + " in its usersList field has a non-nullable idRole field.");
            }
            List<RolesMenus> rolesMenusListOrphanCheck = roles.getRolesMenusList();
            for (RolesMenus rolesMenusListOrphanCheckRolesMenus : rolesMenusListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Roles (" + roles + ") cannot be destroyed since the RolesMenus " + rolesMenusListOrphanCheckRolesMenus + " in its rolesMenusList field has a non-nullable idRole field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(roles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Roles> findRolesEntities() {
        return findRolesEntities(true, -1, -1);
    }

    public List<Roles> findRolesEntities(int maxResults, int firstResult) {
        return findRolesEntities(false, maxResults, firstResult);
    }

    private List<Roles> findRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Roles.class));
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

    public Roles findRoles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Roles.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Roles> rt = cq.from(Roles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
