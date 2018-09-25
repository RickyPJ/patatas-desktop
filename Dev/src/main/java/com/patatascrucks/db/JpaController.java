/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.patatascrucks.db;

import com.patatascrucks.db.exceptions.NonexistentEntityException;
import com.patatascrucks.db.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Ricardo
 * @param <K>
 * @param <T>
 */
public class JpaController<K, T> implements Serializable, AutoCloseable {

    private EntityManagerFactory emf = null;
    private EntityManager em = null;
    private Class<T> entityClass;

    public JpaController() {
        emf = Persistence.createEntityManagerFactory("PatatasHQ_PU");
        em = emf.createEntityManager();
    }

    public void create(T entity) throws PreexistingEntityException, Exception {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (exist(entity)) {
                throw new PreexistingEntityException("Entity " + entity + " already exists.", ex);
            }
            throw ex;
        }
    }

    public void update(T entity) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (!exist(entity)) {
                    throw new NonexistentEntityException("The entity " + entity + " no longer exists.");
                }
            }
            throw ex;
        }
    }

    public void remove(T entity) throws NonexistentEntityException {
        em.getTransaction().begin();
        if (!exist(entity)) {
            throw new NonexistentEntityException("The entity  " + entity + " no longer exists.");
        }
        em.remove(em.merge(entity));
        em.getTransaction().commit();
    }

    public List<T> findAll() {
        return em.createQuery(em.getCriteriaBuilder().createQuery(entityClass)).getResultList();
    }

    public List<T> findAll(java.util.function.Predicate<T> query) {
        return em.createQuery(em.getCriteriaBuilder().createQuery(entityClass)).getResultList()
                .stream()
                .filter(query)
                .collect(Collectors.toList());
    }
    
    public T findById(K id) {
        return em.find(entityClass, id);
    }

    public Boolean exist(T entity) {
        return em.contains(entity);
    }
    
    public int getCount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void close() throws Exception {
        if (emf != null) {
            if (em != null) {
                em.close();
            }
            emf.close();
        }
    }
}
