/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Ouissal
 */
public interface VirementService {

     public Double CompteGetSolde(Long id);

    public Double CompteGetPlafond(Long id);

    public String CompteGetType(Long id);

    public Double CompteGetMinimum(Long id);

     public void insertTransactions(Long idTr,Long idSender, Long idReceiver, double montant);

    public void insertIntoComptes(Long idSender, Long idReceiver, double montant);

    public Long lastId();
    public void insertIntoComptesAndTransaction(Long idSender, Long idReceiver, double montant, Long idTr);
     public boolean receiverExists(Long id);
    
}
