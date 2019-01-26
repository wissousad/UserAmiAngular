/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CompteDAO;
import dao.CompteEntity;
import dao.ConseillerDAO;
import dao.ConseillerEntity;
import dao.ParticulierDAO;
import dao.ParticulierEntity;
import dao.ProfessionnelDAO;
import dao.ProfessionnelEntity;
import dao.TransactionEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ouissal
 */
@Service("LoadInfoService")
public class LoadInfoServiceImpl implements LoadInfoService {

    @Autowired
    ParticulierDAO particulierDao;
    @Autowired
    ProfessionnelDAO professionnelDAO;
    @Autowired
    CompteDAO compteDAO;
    @Autowired
    ConseillerDAO conseillerDAO;

    @Override
    public List<CompteEntity> partriculierGetComptes(Long id) {
        List<CompteEntity> entities = particulierDao.findComptes(id);
        if (entities != null) {
            return entities;
        }
        return null;
    }

    @Override
    public List<CompteEntity> professionnelGetComptes(Long id) {
        List<CompteEntity> entities = professionnelDAO.findComptes(id);
        if (entities != null) {
            return entities;
        }
        return null;
    }


    @Override
    public List<TransactionEntity> compteGetTransactions(Long id) {
        List<TransactionEntity> entities = compteDAO.findTransactions(id);
        if (entities != null) {
            return entities;
        }
        return null;
    }

     @Override
    public List<ParticulierEntity> conseillerGetParticuliers(Long id) {
        List<ParticulierEntity> entities = conseillerDAO.findParticuliers(id);
        if (entities != null) {
            return entities;
        }
        return null;
    }
     @Override
    public List<ProfessionnelEntity> conseillerGetProfessionnels(Long id) {
        List<ProfessionnelEntity> entities = conseillerDAO.findProfessionnels(id);
        if (entities != null) {
            return entities;
        }
        return null;
    }
    
}
