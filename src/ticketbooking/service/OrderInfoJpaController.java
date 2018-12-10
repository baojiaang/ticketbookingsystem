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
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ticketbooking.entity.OrderInfo;
import ticketbooking.service.exceptions.NonexistentEntityException;

/**
 *
 * @author baojiaang
 */
public class OrderInfoJpaController implements Serializable {

    public OrderInfoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrderInfo orderInfo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(orderInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrderInfo orderInfo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            orderInfo = em.merge(orderInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orderInfo.getId();
                if (findOrderInfo(id) == null) {
                    throw new NonexistentEntityException("The orderInfo with id " + id + " no longer exists.");
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
            OrderInfo orderInfo;
            try {
                orderInfo = em.getReference(OrderInfo.class, id);
                orderInfo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderInfo with id " + id + " no longer exists.", enfe);
            }
            em.remove(orderInfo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrderInfo> findOrderInfoEntities() {
        return findOrderInfoEntities(true, -1, -1);
    }

    public List<OrderInfo> findOrderInfoEntities(int maxResults, int firstResult) {
        return findOrderInfoEntities(false, maxResults, firstResult);
    }

    private List<OrderInfo> findOrderInfoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrderInfo.class));
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

    public OrderInfo findOrderInfo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderInfo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderInfoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderInfo> rt = cq.from(OrderInfo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) {
        
    }
    
}
