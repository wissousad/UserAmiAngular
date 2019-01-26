/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Ouissal
 */
@Entity
@Cacheable(false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("courant")
public class CompteEntity implements Serializable {

    protected static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column
    protected Double plafond;

    @Column
    protected Double solde;

    @JoinTable(
            name = "Compte_Particulier",
            joinColumns = @JoinColumn(name = "id_CompteEntity"),
            inverseJoinColumns = @JoinColumn(name = "id_ParticulierEntity")
    )
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected List<ParticulierEntity> particluiers;

    @ManyToOne
    @JoinColumn(name = "professionnel_fk")
    ProfessionnelEntity professionnel;
    
    @OneToMany(mappedBy = "compte")
    protected List<TransactionEntity> transactions;
    
//    @ManyToOne
//    @JoinColumn(name = "conseiller_fk")
//    ConseillerEntity conseiller;
    
    
    public CompteEntity() {
    }

    public CompteEntity(Long id, Double plafond, Double solde, ProfessionnelEntity professionnel) {
        this.id = id;
        this.plafond = plafond;
        this.solde = solde;
        this.professionnel=professionnel;
    }

    public CompteEntity(Long id, Double plafond, Double solde, List<ParticulierEntity> particluiers) {
        this.id = id;
        this.plafond = plafond;
        this.solde = solde;
        this.particluiers = particluiers;
    }

    public CompteEntity(Long id, Double plafond, Double solde, List<ParticulierEntity> particluiers,List<TransactionEntity> transactions) {
        this.id = id;
        this.plafond = plafond;
        this.solde = solde;
        this.particluiers = particluiers;
        this.transactions = transactions;
    }

    public CompteEntity(Long id, Double plafond, Double solde, ProfessionnelEntity professionnel, List<TransactionEntity> transactions) {
        this.id = id;
        this.plafond = plafond;
        this.solde = solde;
        this.professionnel = professionnel;
        this.transactions = transactions;
    }

//    public CompteEntity(Long id, Double plafond, Double solde, List<ParticulierEntity> particluiers, List<TransactionEntity> transactions, ConseillerEntity conseiller) {
//        this.id = id;
//        this.plafond = plafond;
//        this.solde = solde;
//        this.particluiers = particluiers;
//        this.transactions = transactions;
//        this.conseiller = conseiller;
//    }
//
//    public CompteEntity(Long id, Double plafond, Double solde, ProfessionnelEntity professionnel, List<TransactionEntity> transactions, ConseillerEntity conseiller) {
//        this.id = id;
//        this.plafond = plafond;
//        this.solde = solde;
//        this.professionnel = professionnel;
//        this.transactions = transactions;
//        this.conseiller = conseiller;
//    }
//
//    public CompteEntity(Long id, Double plafond, Double solde, List<ParticulierEntity> particluiers, ConseillerEntity conseiller) {
//        this.id = id;
//        this.plafond = plafond;
//        this.solde = solde;
//        this.particluiers = particluiers;
//        this.conseiller = conseiller;
//    }
//
//    public CompteEntity(Long id, Double plafond, Double solde, ProfessionnelEntity professionnel, ConseillerEntity conseiller) {
//        this.id = id;
//        this.plafond = plafond;
//        this.solde = solde;
//        this.professionnel = professionnel;
//        this.conseiller = conseiller;
//    }
    
    

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPlafond() {
        return plafond;
    }

    public void setPlafond(Double plafond) {
        this.plafond = plafond;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public List<ParticulierEntity> getParticluiers() {
        return particluiers;
    }

    public void setParticluiers(List<ParticulierEntity> particluiers) {
        this.particluiers = particluiers;
    }

    public ProfessionnelEntity getProfessionnel() {
        return professionnel;
    }

    public void setProfessionnel(ProfessionnelEntity professionnel) {
        this.professionnel = professionnel;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
    
    public String getType(){
        return "Courant";
    }

//    public ConseillerEntity getConseiller() {
//        return conseiller;
//    }
//
//    public void setConseiller(ConseillerEntity conseiller) {
//        this.conseiller = conseiller;
//    }

   
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteEntity)) {
            return false;
        }
        CompteEntity other = (CompteEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.CompteEntity[ id=" + id + " ]";
    }
    public String toJSON()
    {
         return "{ \"id\":"+"\""+ id +"\""+",\"plafond\":"
                 +"\""+ plafond +"\""+",\"solde\":"+"\"" + plafond +"\"}";
    }
    
   
}
