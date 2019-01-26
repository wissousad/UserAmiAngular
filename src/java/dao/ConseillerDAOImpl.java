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
public class ConseillerDAOImpl implements ConseillerDAO{
    
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
    public void save(ConseillerEntity c) {
        c= em.merge(c);
        em.persist(c);
    }

    @Transactional
    @Override
    public void update(ConseillerEntity c) {
        em.merge(c);

    }

    @Transactional
    @Override
    public void delete(ConseillerEntity c) {
        c = em.merge(c);
        em.remove(c);
    }

    @Transactional(readOnly = true)
    @Override
    public ConseillerEntity find(Long id) {
        return em.find(ConseillerEntity.class, id);

    }

    @Transactional(readOnly = true)
    @Override
    public List<ConseillerEntity> findAll() {
        Query q = em.createQuery("SELECT c FROM ConseillerEntity c");
        return q.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ConseillerEntity> findByIdPass(Long id, String password) {
        Query q = em.createQuery("SELECT c FROM ConseillerEntity c WHERE c.id=" + id + " AND c.password= '" + password + "'");
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
    public List<ProfessionnelEntity> findProfessionnels(Long id) {
        Query q = em.createQuery("SELECT c.professionnels FROM ConseillerEntity c where c.id="+id );
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();

        }else{
            return null;
        }
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ParticulierEntity> findParticuliers(Long id) {
        Query q = em.createQuery("SELECT c.particuliers FROM ConseillerEntity c where c.id="+id );
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();

        }else{
            return null;
        }
    }
}
