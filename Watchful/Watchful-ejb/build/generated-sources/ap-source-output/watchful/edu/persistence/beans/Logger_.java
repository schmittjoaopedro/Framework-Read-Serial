package watchful.edu.persistence.beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Logger.class)
public abstract class Logger_ {

	public static volatile SingularAttribute<Logger, Long> id;
	public static volatile SingularAttribute<Logger, String> description;
	public static volatile SingularAttribute<Logger, Date> date;

}

