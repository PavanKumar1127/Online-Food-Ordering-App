package annotations;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * In Spring Boot, the @Entity annotation is used in conjunction with JPA (Java
 * Persistence API) to map Java classes to database tables. This annotation
 * indicates that a particular class is an entity and should be mapped to a
 * database table.
 */
public class EntityAnnotation {

//	Detailed Explanation of @Entity
//	What is @Entity?
//	The @Entity annotation is a JPA annotation that specifies that the class is an entity and is mapped to a database table.
//	It is placed above the class definition.
//	Entities in JPA are plain old Java objects (POJOs) that represent data that can be persisted to a database.

//	How Does @Entity Work?
//	When a class is annotated with @Entity, JPA will manage it as a persistent entity.
//	This class will have a corresponding table in the database.
//	Each instance of the class corresponds to a row in the table.
//	The fields of the class represent the columns in the table.

	@Entity
	@Table(name = "employees")
	public class Employee {
		
//		The Employee class is annotated with @Entity, making it a JPA entity.
//		The @Table(name = "employees") annotation specifies the name of the table in the database. If not specified, the table name defaults to the class name.
//		The @Id annotation indicates the primary key of the entity.
//		The @GeneratedValue annotation specifies the generation strategy for the primary key. Here, GenerationType.IDENTITY is used, which means the database will automatically generate the primary key.

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String firstName;
		private String lastName;
		private String email;

		// Getters and Setters
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	}
}
