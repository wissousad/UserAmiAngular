/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CompteDAO;
import dao.CompteEntity;
import dao.CompteLivretDAO;
import dao.CompteLivretEntity;
import dao.TransactionDAO;
import dao.TransactionEntity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ouissal
 */
@Service("VirementService")
public class VirementServiceImpl implements VirementService {

    @Autowired
    CompteDAO compteDAO;
    @Autowired
    CompteLivretDAO compteLivretDAO;
    @Autowired
    TransactionDAO transactionDAO;

    @Override
    public Double CompteGetSolde(Long id) {
        CompteEntity compte = compteDAO.find(id);
        if (compte != null) {
            Double solde = compte.getSolde();
            return solde;
        }
        return null;
    }

    @Override
    public Double CompteGetPlafond(Long id) {
        CompteEntity compte = compteDAO.find(id);
        if (compte.getPlafond() != null) {
            Double plafond = compte.getPlafond();
            return plafond;
        }
        return null;
    }

    @Override
    public String CompteGetType(Long id) {
        CompteEntity compte = compteDAO.find(id);
        if (compte.getType() != null) {

            return compte.getType();
        }
        return null;
    }

    @Override
    public Double CompteGetMinimum(Long id) {
        CompteLivretEntity compte = compteLivretDAO.find(id);
        if (compte.getType().equals("Livret")) {
            Double minimum = compte.getMinimum();
            return minimum;
        }
        return null;
    }

    @Override
    public void insertTransactions(Long idTr, Long idSender, Long idReceiver, double montant) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        CompteEntity s = compteDAO.find(idSender);
        CompteEntity r = compteDAO.find(idReceiver);
        String date = new Date() + "";
        TransactionEntity tr1 = new TransactionEntity(idTr, date, "Virement", -montant, s);
        transactionDAO.save(tr1);
        TransactionEntity tr2 = new TransactionEntity(idTr + 1, date, "Versement", montant, r);
        transactionDAO.save(tr2);
    }

    @Override
    public void insertIntoComptes(Long idSender, Long idReceiver, double montant) {
        CompteEntity s = compteDAO.find(idSender);
        CompteEntity r = compteDAO.find(idReceiver);
        Double soldeS = s.getSolde() - montant;
        System.out.println("*************************soldeS" + soldeS);
        s.setSolde(soldeS);
        System.out.println("*************************soldeS après set" + s.getSolde());
        Double soldeR = r.getSolde() + montant;
        r.setSolde(soldeR);
        compteDAO.update(r);
        compteDAO.update(s);

    }

    @Override
    public void insertIntoComptesAndTransaction(Long idSender, Long idReceiver, double montant, Long idTr) {
        CompteEntity s = compteDAO.find(idSender);
        CompteEntity r = compteDAO.find(idReceiver);
        Double soldeS = s.getSolde() - montant;
        System.out.println("*************************soldeS" + soldeS);
        s.setSolde(soldeS);
        System.out.println("*************************soldeS après set" + s.getSolde());
        Double soldeR = r.getSolde() + montant;
        r.setSolde(soldeR);
        compteDAO.update(r);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = new Date() + "";
        TransactionEntity tr1 = new TransactionEntity(idTr, date, "Virement", -montant, s);
        transactionDAO.save(tr1);
        compteDAO.update(s);
        TransactionEntity tr2 = new TransactionEntity(idTr + 1, date, "Versement", montant, r);
        transactionDAO.save(tr2);

    }

    @Override
    public Long lastId() {
        if (transactionDAO.findLastId() != null) {
            return transactionDAO.findLastId();
        } else {
            return 1L;
        }
    }
}
