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
public interface ProfessionnelDAO {

    public void save(ProfessionnelEntity p);

    public void update(ProfessionnelEntity p);

    public void delete(ProfessionnelEntity p);

    public ProfessionnelEntity find(Long id);

    public List<ProfessionnelEntity> findAll();

    public List<ProfessionnelEntity> findByIdPass(Long id, String password);

    public List<CompteEntity> findComptes(Long id);
    
    
}
