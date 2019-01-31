package dao;

import dao.CompteEntity;
import dao.ConseillerEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-30T22:56:08")
@StaticMetamodel(ProfessionnelEntity.class)
public class ProfessionnelEntity_ { 

    public static volatile SingularAttribute<ProfessionnelEntity, String> password;
    public static volatile ListAttribute<ProfessionnelEntity, CompteEntity> comptes;
    public static volatile SingularAttribute<ProfessionnelEntity, ConseillerEntity> conseiller;
    public static volatile SingularAttribute<ProfessionnelEntity, String> adresse;
    public static volatile SingularAttribute<ProfessionnelEntity, String> tel;
    public static volatile SingularAttribute<ProfessionnelEntity, Long> id;
    public static volatile SingularAttribute<ProfessionnelEntity, String> nom;
    public static volatile SingularAttribute<ProfessionnelEntity, Long> siret;
    public static volatile SingularAttribute<ProfessionnelEntity, String> email;

}