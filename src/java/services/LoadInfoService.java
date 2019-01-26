/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CompteEntity;
import dao.ParticulierEntity;
import dao.ProfessionnelEntity;
import dao.TransactionEntity;
import java.util.List;

/**
 *
 * @author Ouissal
 */
public interface LoadInfoService {

    public List<CompteEntity> partriculierGetComptes(Long id);

    public List<CompteEntity> professionnelGetComptes(Long id);

    public List<TransactionEntity> compteGetTransactions(Long id);

    public List<ParticulierEntity> conseillerGetParticuliers(Long id);

    public List<ProfessionnelEntity> conseillerGetProfessionnels(Long id);

}
