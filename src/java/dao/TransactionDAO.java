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
public interface TransactionDAO {

    public void save(TransactionEntity t);

    public void update(TransactionEntity t);

    public void delete(TransactionEntity t);

    public TransactionEntity find(Long id);

    public List<TransactionEntity> findAll();

    public CompteEntity findCompte(Long id);
    
     public Long findLastId();
}
