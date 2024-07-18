package annotations;

import java.util.*;

import jakarta.persistence.*;

/**
 * @ElementCollection annotation in Spring Boot (specifically in JPA/Hibernate)
 *                    is used to define a collection of basic types or
 *                    embeddable objects. It allows you to store collections
 *                    like lists, sets, or maps of simple values or embeddable
 *                    objects in a separate table, rather than as an entity in
 *                    its own right.
 */
public class ElementCollectionAnnotation {
	
//	Scenarios Where @ElementCollection is Used
//	Storing Collections of Basic Types: When you need to store a collection of primitive or simple data types (e.g., a list of phone numbers, a set of tags).
//	Storing Embeddable Types: When you have a collection of objects that are not entities themselves but can be embedded in an entity (e.g., addresses, attributes).
//	Simplifying the Database Schema: When you want to avoid creating a separate entity for a simple collection and instead store it directly in the parent entity’s table.



	@Entity
	public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    @ElementCollection
	    @CollectionTable(name = "user_phone_numbers")
	    @Column(name = "phone_number")
	    private List<String> phoneNumbers = new ArrayList<>();

	    // Getters and setters
	}

//	Storing a Set of Embeddable Objects
//	First, define the embeddable class:

	@Embeddable
	public class Address {
	    private String street;
	    private String city;
	    private String state;
	    private String zipcode;

	    // Getters and setters
	}
//	Then, use it in an entity:
	
	@Entity
	public class User1 {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    @ElementCollection
	    @CollectionTable(name = "user_addresses", joinColumns = @JoinColumn(name = "user_id"))
	    private Set<Address> addresses = new HashSet<>();

	    // Getters and setters
	}

//	 Using Maps with @ElementCollection
//	 If you want to store a map where the key is a string and the value is a string:
	
	@Entity
	public class User2 {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    @ElementCollection
	    @CollectionTable(name = "user_attributes")
	    @MapKeyColumn(name = "attribute_name")
	    @Column(name = "attribute_value")
	    private Map<String, String> attributes = new HashMap<>();

	    // Getters and setters
	}
}
