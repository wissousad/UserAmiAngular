/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Ouissal
 */
@Entity
@Cacheable(false)
public class ConseillerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nom;
    @Column
    private String password;
    @Column
    String prenom;
    
    @OneToMany(mappedBy = "conseiller")
    private List<ProfessionnelEntity> professionnels;
    
     @OneToMany(mappedBy = "conseiller")
    private List<ParticulierEntity> particuliers;

    public ConseillerEntity() {
    }

    public ConseillerEntity(Long id, String nom, String password, String prenom) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.prenom = prenom;
    }
    
//    public ConseillerEntity(Long id, String nom, String password, String prenom, List<CompteEntity> comptes) {
//        this.id = id;
//        this.nom = nom;
//        this.password = password;
//        this.prenom = prenom;
//        this.comptes = comptes;
//    }
    

    public ConseillerEntity(Long id, String nom, String password, String prenom, List<ProfessionnelEntity> professionnels, List<ParticulierEntity> particuliers) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.prenom = prenom;
        this.professionnels = professionnels;
        this.particuliers = particuliers;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

//    public List<CompteEntity> getComptes() {
//        return comptes;
//    }
//
//    public void setComptes(List<CompteEntity> comptes) {
//        this.comptes = comptes;
//    }

    public List<ProfessionnelEntity> getProfessionnels() {
        return professionnels;
    }

    public void setProfessionnels(List<ProfessionnelEntity> professionnels) {
        this.professionnels = professionnels;
    }

    public List<ParticulierEntity> getParticuliers() {
        return particuliers;
    }

    public void setParticuliers(List<ParticulierEntity> particuliers) {
        this.particuliers = particuliers;
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
        if (!(object instanceof ConseillerEntity)) {
            return false;
        }
        ConseillerEntity other = (ConseillerEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.ConseillerEntity[ id=" + id + " ]";
    }

}
