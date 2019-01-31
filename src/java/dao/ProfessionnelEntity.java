/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Ouissal
 */
@Entity
@Cacheable(false)
public class ProfessionnelEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nom;
    @Column
    private String password;
    @Column(unique = true)
    private String email;
    @Column
    private String adresse;
    @Column(unique = true)
    private String tel;
    @Column(unique = true)
    private Long siret;

    @OneToMany(mappedBy = "professionnel")
    private List<CompteEntity> comptes = new ArrayList<CompteEntity>();

    @ManyToOne
    @JoinColumn(name = "conseiller_fk")
    ConseillerEntity conseiller;

    public ProfessionnelEntity() {
    }

    public ProfessionnelEntity(Long id, String nom, String password, String email, String adresse, String tel, Long siret, ArrayList<CompteEntity> comptes) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.siret = siret;
        this.comptes = comptes;
    }

    public ProfessionnelEntity(Long id, String nom, String password, String email, String adresse, String tel, Long siret, ConseillerEntity conseiller) {
        this.id = id;
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.adresse = adresse;
        this.tel = tel;
        this.siret = siret;
        this.conseiller = conseiller;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Long getSiret() {
        return siret;
    }

    public void setSiret(Long siret) {
        this.siret = siret;
    }

    public List<CompteEntity> getComptes() {
        return comptes;
    }

    public void setComptes(List<CompteEntity> comptes) {
        this.comptes = comptes;
    }

    public ConseillerEntity getConseiller() {
        return conseiller;
    }

    public void setConseiller(ConseillerEntity conseiller) {
        this.conseiller = conseiller;
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
        if (!(object instanceof ProfessionnelEntity)) {
            return false;
        }
        ProfessionnelEntity other = (ProfessionnelEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.ProfessionnelEntity[ id=" + id + " ]";
    }
 public String toJSON()
    {
         return "{ \"numero_compte\":"+"\""+ id +"\""+",\"password\":"
                 +"\""+ password +"\""+",\"nom\":"+"\"" + nom +"\"}";
    }
     public String loginPassToJSON()
    {
        return "{ \"numero_compte\":"+"\""+id+"\""+",\"password\":"+"\""+password+"\""+"}";
    }
}
