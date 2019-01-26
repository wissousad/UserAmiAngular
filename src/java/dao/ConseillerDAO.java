/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Ouissal
 */
public interface ConseillerDAO {

    public void save(ConseillerEntity c);

    public void update(ConseillerEntity c);

    public void delete(ConseillerEntity c);

    public ConseillerEntity find(Long id);

    public List<ConseillerEntity> findAll();

    public List<ConseillerEntity> findByIdPass(Long id, String password);

    public List<ProfessionnelEntity> findProfessionnels(Long id);
    
    public List<ParticulierEntity> findParticuliers(Long id) ;
}
