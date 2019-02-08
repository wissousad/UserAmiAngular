/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ouissal
 */
@Entity
@Cacheable(false)
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String date;

    @Column
    private String contenu;

    @Column
    private Double somme;
    
    
    @ManyToOne
    @JoinColumn(name="compte_fk")
    private CompteEntity compte;

    public TransactionEntity() {
    }

    public TransactionEntity(String date, String contenu, Double somme, CompteEntity compte) {
        this.date = date;
        this.contenu = contenu;
        this.somme = somme;
        this.compte = compte;
    }

    
    public TransactionEntity(Long id, String date, String contenu, Double somme, CompteEntity compte) {
        this.id = id;
        this.date = date;
        this.contenu = contenu;
        this.somme = somme;
        this.compte = compte;
    }

    public TransactionEntity(Long id, String date, String contenu, Double somme) {
        this.id = id;
        this.date = date;
        this.contenu = contenu;
        this.somme = somme;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Double getSomme() {
        return somme;
    }

    public void setSomme(Double somme) {
        this.somme = somme;
    }

    public CompteEntity getCompte() {
        return compte;
    }

    public void setCompte(CompteEntity compte) {
        this.compte = compte;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionEntity)) {
            return false;
        }
        TransactionEntity other = (TransactionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.TransactionEntity[ id=" + id + " ]";
    }
     public String toJSON()
    {
         return "{ \"id\":"+"\""+ id +"\""+",\"date\":"
                 +"\""+ date +"\""+",\"contenu\":"+"\"" + contenu +"\""+",\"somme\":"+"\"" + somme + "\"}";
    }

}
