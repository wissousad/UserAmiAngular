package dao;

import dao.CompteEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-27T00:10:23")
@StaticMetamodel(TransactionEntity.class)
public class TransactionEntity_ { 

    public static volatile SingularAttribute<TransactionEntity, String> date;
    public static volatile SingularAttribute<TransactionEntity, Double> somme;
    public static volatile SingularAttribute<TransactionEntity, Long> id;
    public static volatile SingularAttribute<TransactionEntity, String> contenu;
    public static volatile SingularAttribute<TransactionEntity, CompteEntity> compte;

}