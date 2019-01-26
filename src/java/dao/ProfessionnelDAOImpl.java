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
public class ProfessionnelDAOImpl implements ProfessionnelDAO {

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
    public void save(ProfessionnelEntity p) {
        p = em.merge(p);
        em.persist(p);
    }

    @Transactional
    @Override
    public void update(ProfessionnelEntity p) {
        em.merge(p);

    }

    @Transactional
    @Override
    public void delete(ProfessionnelEntity p) {
        p = em.merge(p);
        em.remove(p);
    }

    @Transactional(readOnly = true)
    @Override
    public ProfessionnelEntity find(Long id) {
        return em.find(ProfessionnelEntity.class, id);

    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfessionnelEntity> findAll() {
        Query q = em.createQuery("SELECT p FROM ProfessionnelEntity p");
        return q.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfessionnelEntity> findByIdPass(Long id, String password) {
        Query q = em.createQuery("SELECT p FROM ProfessionnelEntity p WHERE p.id=" + id + " AND p.password= '" + password + "'");
        System.out.println("/////////////////////////////////id=" + id);
        System.out.println("/////////////////////////////////pass=" + password);
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();

        } else {
            return null;
        }
    }
    @Transactional(readOnly = true)
    @Override
    public List<CompteEntity> findComptes(Long id) {
        Query q = em.createQuery("SELECT p.comptes FROM ProfessionnelEntity p WHERE p.id=" + id );
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();

        }else{
            return null;
        }
    }

}
