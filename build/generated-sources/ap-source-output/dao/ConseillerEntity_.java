package dao;

import dao.ParticulierEntity;
import dao.ProfessionnelEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-19T14:52:06")
@StaticMetamodel(ConseillerEntity.class)
public class ConseillerEntity_ { 

    public static volatile SingularAttribute<ConseillerEntity, String> password;
    public static volatile ListAttribute<ConseillerEntity, ParticulierEntity> particuliers;
    public static volatile ListAttribute<ConseillerEntity, ProfessionnelEntity> professionnels;
    public static volatile SingularAttribute<ConseillerEntity, Long> id;
    public static volatile SingularAttribute<ConseillerEntity, String> nom;
    public static volatile SingularAttribute<ConseillerEntity, String> prenom;

}