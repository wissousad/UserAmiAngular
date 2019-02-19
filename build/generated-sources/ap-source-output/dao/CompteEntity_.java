package dao;

import dao.ParticulierEntity;
import dao.ProfessionnelEntity;
import dao.TransactionEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-19T14:52:06")
@StaticMetamodel(CompteEntity.class)
public class CompteEntity_ { 

    public static volatile SingularAttribute<CompteEntity, ProfessionnelEntity> professionnel;
    public static volatile SingularAttribute<CompteEntity, Double> plafond;
    public static volatile SingularAttribute<CompteEntity, Double> solde;
    public static volatile SingularAttribute<CompteEntity, Long> id;
    public static volatile ListAttribute<CompteEntity, ParticulierEntity> particluiers;
    public static volatile ListAttribute<CompteEntity, TransactionEntity> transactions;

}