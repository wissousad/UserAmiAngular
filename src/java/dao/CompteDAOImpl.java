/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ouissal
 */
@Repository

public class CompteDAOImpl implements CompteDAO {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    @Override
    public void save(CompteEntity c) {
        c = em.merge(c);
        em.persist(c);
    }

    @Transactional
    @Override
    public void update(CompteEntity c) {
        em.getEntityManagerFactory().getCache().evictAll();
        em.merge(c);
    }

    @Transactional
    @Override
    public void delete(CompteEntity c) {
        c = em.merge(c);
        em.remove(c);
    }

    @Transactional(readOnly = true)
    @Override
    public CompteEntity find(Long id) {
        return em.find(CompteEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CompteEntity> findAll() {
        Query q = em.createQuery("SELECT c FROM CompteEntity c");
        return q.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CompteEntity> findAllByProfessionnel(Long id) {
        Query q = em.createQuery("select c from CompteEntity c where c.professionnel=" + id);
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransactionEntity> findTransactions(Long id) {
        Query q = em.createQuery("SELECT c.transactions FROM CompteEntity c WHERE c.id=" + id);
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();

        } else {
            return null;
        }
    }

}
