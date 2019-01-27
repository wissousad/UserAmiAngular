package dao;

import dao.CompteEntity;
import dao.ConseillerEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-27T00:10:23")
@StaticMetamodel(ParticulierEntity.class)
public class ParticulierEntity_ { 

    public static volatile SingularAttribute<ParticulierEntity, String> password;
    public static volatile ListAttribute<ParticulierEntity, CompteEntity> comptes;
    public static volatile SingularAttribute<ParticulierEntity, ConseillerEntity> conseiller;
    public static volatile SingularAttribute<ParticulierEntity, String> adresse;
    public static volatile SingularAttribute<ParticulierEntity, String> tel;
    public static volatile SingularAttribute<ParticulierEntity, Long> id;
    public static volatile SingularAttribute<ParticulierEntity, String> nom;
    public static volatile SingularAttribute<ParticulierEntity, String> prenom;
    public static volatile SingularAttribute<ParticulierEntity, String> email;

}