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

public class CompteLivretDAOImpl implements CompteLivretDAO {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    @Override
    public void save(CompteLivretEntity c) {
        c = em.merge(c);
        em.persist(c);
    }

    @Transactional
    @Override
    public void update(CompteLivretEntity c) {
        em.merge(c);

    }

    @Transactional
    @Override
    public void delete(CompteLivretEntity c) {
        c = em.merge(c);
        em.remove(c);
    }

    @Transactional
    @Override
    public CompteLivretEntity find(Long id) {
        return em.find(CompteLivretEntity.class, id);

    }

    @Transactional(readOnly = true)
    @Override
    public List<CompteLivretEntity> findAll() {
        Query q = em.createQuery("SELECT c FROM CompteLivretEntity c");
        return q.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CompteLivretEntity> findAllByProfessionnel(Long id) {
        Query q = em.createQuery("select c from CompteLivretEntity c where c.professionnel=" + id);
        if (!q.getResultList().isEmpty()) {
            return q.getResultList();
        } else {
            return null;
        }
    }
}
