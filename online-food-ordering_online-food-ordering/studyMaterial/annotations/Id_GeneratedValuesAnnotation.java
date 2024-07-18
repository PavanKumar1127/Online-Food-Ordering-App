package annotations;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * In Spring Boot, when working with databases through JPA (Java Persistence
 * API), the @Id and @GeneratedValue annotations are used to specify the primary
 * key of an entity and how it should be generated. These annotations are
 * crucial for defining the identity and unique identifier of entity instances.
 */
public class Id_GeneratedValuesAnnotation {
	
//	@Id Annotation
//	Purpose:
//
//	The @Id annotation is used to mark a field in an entity as the primary key.
//	Usage:
//
//	This annotation is placed above the field that should serve as the primary key for the entity. It indicates that this field is unique and can be used to identify each record in the database.

	@Entity
	public class User {
	    @Id
	    private Long id;
	    
	    // other fields, getters, and setters
	}

//	@GeneratedValue Annotation
//	Purpose:
//
//	The @GeneratedValue annotation is used to specify how the primary key should be automatically generated.
//	Attributes:
//
//	strategy: Defines the primary key generation strategy.
//	generator: Specifies the name of the primary key generator to use (optional).
//	Generation Strategies:
//
//	GenerationType.AUTO: Default strategy. The persistence provider (e.g., Hibernate) will choose an appropriate strategy.
//	GenerationType.IDENTITY: Uses an auto-increment column in the database.
//	GenerationType.SEQUENCE: Uses a database sequence to generate unique values.
//	GenerationType.TABLE: Uses a table to simulate a sequence (less common).
	
	@Entity
	public class User1 {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    
//	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
//	    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)

//	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
//	    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
	    
	    private Long id;
	    
	    // other fields, getters, and setters
	}

}
