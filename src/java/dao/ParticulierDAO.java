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
public interface ParticulierDAO {

    public void save(ParticulierEntity p);

    public void update(ParticulierEntity p);

    public void delete(ParticulierEntity p);

    public ParticulierEntity find(Long id);

    public List<ParticulierEntity> findAll();

    public List<ParticulierEntity> findByIdPass(Long id, String password);
    
    public List<CompteEntity> findComptes(Long id);

}
