/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.service;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ticketbooking.entity.UserInfo;
import ticketbooking.service.exceptions.NonexistentEntityException;

/**
 *
 * @author baojiaang
 */
public class UserInfoJpaController implements Serializable {

    public UserInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserInfo userInfo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(userInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserInfo userInfo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            userInfo = em.merge(userInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userInfo.getId();
                if (findUserInfo(id) == null) {
                    throw new NonexistentEntityException("The userInfo with id " + id + " no longer exists.");
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
            UserInfo userInfo;
            try {
                userInfo = em.getReference(UserInfo.class, id);
                userInfo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userInfo with id " + id + " no longer exists.", enfe);
            }
            em.remove(userInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserInfo> findUserInfoEntities() {
        return findUserInfoEntities(true, -1, -1);
    }

    public List<UserInfo> findUserInfoEntities(int maxResults, int firstResult) {
        return findUserInfoEntities(false, maxResults, firstResult);
    }

    private List<UserInfo> findUserInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserInfo.class));
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

    public UserInfo findUserInfo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserInfo> rt = cq.from(UserInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public UserInfo checkLogin(String username,String password){
        EntityManager em = getEntityManager();
        UserInfo ui=null;
        try{
            Query query=em.createNativeQuery("select * from user_info where username=? and password =?",UserInfo.class);
            query.setParameter(1, username);
            query.setParameter(2, password);
            ui=(UserInfo) query.getSingleResult();
            return ui;
        }
        catch(Exception e){
            return null;
        }
        finally{
            em.close();
        }
    }
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TicketBookingSystemPU");   
        UserInfoJpaController uijc=new UserInfoJpaController(factory);
      //  System.out.println(uijc.findUserInfo(2));
      UserInfo ui=uijc.checkLogin("baojiaang", "12345");
        if(ui==null){
            System.out.println("null");
        }
    }
    
}
