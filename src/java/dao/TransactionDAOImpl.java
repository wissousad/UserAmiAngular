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
public class TransactionDAOImpl implements TransactionDAO {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public void save(TransactionEntity t) {
        t = em.merge(t);
        em.persist(t);
    }

    @Transactional
    @Override
    public void update(TransactionEntity t) {
        em.merge(t);
    }

    @Transactional
    @Override
    public void delete(TransactionEntity t) {
        t = em.merge(t);
        em.remove(t);
    }

    @Transactional(readOnly = true)
    @Override
    public TransactionEntity find(Long id) {
        return em.find(TransactionEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransactionEntity> findAll() {
        Query q = em.createQuery("SELECT t FROM TransactionEntity t");
        return q.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public CompteEntity findCompte(Long id) {
        Query q = em.createQuery("SELECT t.compte FROM TransactionEntity t WHERE t.id=" + id);
        if (q.getResultList() != null) {
            List<CompteEntity> list = q.getResultList();
            CompteEntity compte = list.get(0);
            return compte;
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Long findLastId() {
        Long id = (Long)em.createNativeQuery("SELECT max(t.id) from TransactionEntity t").getSingleResult();
            return id;
    }
}
