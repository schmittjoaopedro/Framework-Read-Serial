package watchful.edu.persistence.beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Template.class)
public abstract class Template_ {

	public static volatile SingularAttribute<Template, Long> id;
	public static volatile SingularAttribute<Template, Definable> definable;
	public static volatile SingularAttribute<Template, Date> dateUpdate;
	public static volatile SingularAttribute<Template, String> description;
	public static volatile SingularAttribute<Template, Date> dateCreate;

}

