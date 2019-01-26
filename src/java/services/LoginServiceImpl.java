/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ConseillerDAO;
import dao.ConseillerEntity;
import dao.ParticulierDAO;
import dao.ParticulierEntity;
import dao.ProfessionnelDAO;
import dao.ProfessionnelEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ouissal
 */
@Service("LoginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    ParticulierDAO particulierDAO;
    @Autowired
    ProfessionnelDAO professionnelDAO;
    @Autowired
    ConseillerDAO conseillerDAO;
    

    @Override
    public String partriculierExistGetName(Long id, String Password) {
        List<ParticulierEntity> entities = particulierDAO.findByIdPass(id, Password);
        if (entities != null) {
            return entities.get(0).getNom()+" "+entities.get(0).getPrenom();
        }
        return null;
    }

    @Override
    public String professionnelExistGetName(Long id, String Password) {
        List<ProfessionnelEntity> entities = professionnelDAO.findByIdPass(id, Password);
        if (entities != null) {
            return entities.get(0).getNom();
        }
        return null;
    }
    
    @Override
    public String partriculierGetName(Long id) {
        ParticulierEntity entity = particulierDAO.find(id);
        if (entity != null) {
            return entity.getNom()+" "+entity.getPrenom();
        }
        return null;
    }

    @Override
    public String professionnelGetName(Long id) {
        ProfessionnelEntity entity = professionnelDAO.find(id);
        if (entity != null) {
            return entity.getNom();
        }
        return null;
    }
    
     @Override
    public String conseillerExistGetName(Long id, String Password) {
        List<ConseillerEntity> entities = conseillerDAO.findByIdPass(id, Password);
        if (entities != null) {
            return entities.get(0).getNom()+" "+entities.get(0).getPrenom();
        }
        return null;
    }
    
    
    @Override
    public String conseillerGetName(Long id) {
        ConseillerEntity entity = conseillerDAO.find(id);
        if (entity != null) {
            return entity.getNom();
        }
        return null;
    }
}
