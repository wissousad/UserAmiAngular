/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Ouissal
 */
@Entity
@DiscriminatorValue("livret")
@Cacheable(false)

public class CompteLivretEntity extends CompteEntity {

    @Column(length = 4, nullable=true)
    private Double minimum;

    public CompteLivretEntity() {
    }

    public CompteLivretEntity(Long id, Double plafond, Double solde, ProfessionnelEntity professionnel, Double minimum) {
        super(id, plafond, solde, professionnel);
        this.minimum = minimum;
    }

    public CompteLivretEntity(Long id, Double plafond, Double solde, List<ParticulierEntity> particluiers, Double minimum) {
        super(id, plafond, solde, particluiers);
        this.minimum = minimum;

    }

    public CompteLivretEntity(Double minimum, Long id, Double plafond, Double solde, List<ParticulierEntity> particluiers, List<TransactionEntity> transactions) {
        super(id, plafond, solde, particluiers, transactions);
        this.minimum = minimum;
    }

    public CompteLivretEntity(Double minimum, Long id, Double plafond, Double solde, ProfessionnelEntity professionnel, List<TransactionEntity> transactions) {
        super(id, plafond, solde, professionnel, transactions);
        this.minimum = minimum;
    }
    

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }
    @Override
    public String getType(){
        return "Livret";
    }

}
