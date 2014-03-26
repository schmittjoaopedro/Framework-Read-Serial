package watchful.edu.persistence.beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Definable.class)
public abstract class Definable_ {

	public static volatile SingularAttribute<Definable, Long> id;
	public static volatile ListAttribute<Definable, Template> templates;
	public static volatile SingularAttribute<Definable, String> definable;
	public static volatile SingularAttribute<Definable, Date> dateUpdate;
	public static volatile SingularAttribute<Definable, String> description;
	public static volatile SingularAttribute<Definable, Date> dateCreate;

}

