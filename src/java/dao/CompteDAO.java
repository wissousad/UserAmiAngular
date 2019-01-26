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
public interface CompteDAO {

    public void save(CompteEntity c);

    public void update(CompteEntity c);

    public void delete(CompteEntity c);

    public CompteEntity find(Long id);

    public List<CompteEntity> findAll();

    public List<CompteEntity> findAllByProfessionnel(Long id);

    public List<TransactionEntity> findTransactions(Long id);

}
