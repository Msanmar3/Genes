/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upo.genesmaven.controller;

import com.upo.genesmaven.controller.exceptions.IllegalOrphanException;
import com.upo.genesmaven.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.upo.genesmaven.entities.Roles;
import com.upo.genesmaven.entities.Files;
import com.upo.genesmaven.entities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author antonio
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("genes_PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UsersJpaController() {
        emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getFilesList() == null) {
            users.setFilesList(new ArrayList<Files>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles idRole = users.getIdRole();
            if (idRole != null) {
                idRole = em.getReference(idRole.getClass(), idRole.getIdRole());
                users.setIdRole(idRole);
            }
            List<Files> attachedFilesList = new ArrayList<Files>();
            for (Files filesListFilesToAttach : users.getFilesList()) {
                filesListFilesToAttach = em.getReference(filesListFilesToAttach.getClass(), filesListFilesToAttach.getIdFile());
                attachedFilesList.add(filesListFilesToAttach);
            }
            users.setFilesList(attachedFilesList);
            em.persist(users);
            if (idRole != null) {
                idRole.getUsersList().add(users);
                idRole = em.merge(idRole);
            }
            for (Files filesListFiles : users.getFilesList()) {
                Users oldIdUserOfFilesListFiles = filesListFiles.getIdUser();
                filesListFiles.setIdUser(users);
                filesListFiles = em.merge(filesListFiles);
                if (oldIdUserOfFilesListFiles != null) {
                    oldIdUserOfFilesListFiles.getFilesList().remove(filesListFiles);
                    oldIdUserOfFilesListFiles = em.merge(oldIdUserOfFilesListFiles);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getIdUser());
            Roles idRoleOld = persistentUsers.getIdRole();
            Roles idRoleNew = users.getIdRole();
            List<Files> filesListOld = persistentUsers.getFilesList();
            List<Files> filesListNew = users.getFilesList();
            List<String> illegalOrphanMessages = null;
            for (Files filesListOldFiles : filesListOld) {
                if (!filesListNew.contains(filesListOldFiles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Files " + filesListOldFiles + " since its idUser field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idRoleNew != null) {
                idRoleNew = em.getReference(idRoleNew.getClass(), idRoleNew.getIdRole());
                users.setIdRole(idRoleNew);
            }
            List<Files> attachedFilesListNew = new ArrayList<Files>();
            for (Files filesListNewFilesToAttach : filesListNew) {
                filesListNewFilesToAttach = em.getReference(filesListNewFilesToAttach.getClass(), filesListNewFilesToAttach.getIdFile());
                attachedFilesListNew.add(filesListNewFilesToAttach);
            }
            filesListNew = attachedFilesListNew;
            users.setFilesList(filesListNew);
            users = em.merge(users);
            if (idRoleOld != null && !idRoleOld.equals(idRoleNew)) {
                idRoleOld.getUsersList().remove(users);
                idRoleOld = em.merge(idRoleOld);
            }
            if (idRoleNew != null && !idRoleNew.equals(idRoleOld)) {
                idRoleNew.getUsersList().add(users);
                idRoleNew = em.merge(idRoleNew);
            }
            for (Files filesListNewFiles : filesListNew) {
                if (!filesListOld.contains(filesListNewFiles)) {
                    Users oldIdUserOfFilesListNewFiles = filesListNewFiles.getIdUser();
                    filesListNewFiles.setIdUser(users);
                    filesListNewFiles = em.merge(filesListNewFiles);
                    if (oldIdUserOfFilesListNewFiles != null && !oldIdUserOfFilesListNewFiles.equals(users)) {
                        oldIdUserOfFilesListNewFiles.getFilesList().remove(filesListNewFiles);
                        oldIdUserOfFilesListNewFiles = em.merge(oldIdUserOfFilesListNewFiles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getIdUser();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Files> filesListOrphanCheck = users.getFilesList();
            for (Files filesListOrphanCheckFiles : filesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the Files " + filesListOrphanCheckFiles + " in its filesList field has a non-nullable idUser field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Roles idRole = users.getIdRole();
            if (idRole != null) {
                idRole.getUsersList().remove(users);
                idRole = em.merge(idRole);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Users findUsersLogin(String user, String password) {
        EntityManager em = getEntityManager();
        try {
            return (Users) em.createNamedQuery("Users.findUserLogin").setParameter("email", user).setParameter("password", password).getSingleResult();
        } finally {
            em.close();
        }

    }
}
