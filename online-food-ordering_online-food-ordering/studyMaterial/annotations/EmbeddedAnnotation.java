package annotations;

import jakarta.persistence.*;

/**
 * @Embedded annotation in Spring Boot (part of the JPA specification) is used
 *           to embed a class into an entity. This annotation allows you to
 *           group multiple columns into a single embedded object, providing a
 *           way to improve code modularity and reuse.
 */
public class EmbeddedAnnotation {
//	Embeddable Class: A class annotated with @Embeddable that contains the fields you want to embed in an entity.
//	Embedded Object: An instance of the embeddable class that is used within an entity class, marked with the @Embedded annotation.
	

	@Embeddable
	public class Address {
	    private String street;
	    private String city;
	    private String state;
	    private String zipcode;

	    // Constructors, getters, and setters

	    public Address() {}

	    public Address(String street, String city, String state, String zipcode) {
	        this.street = street;
	        this.city = city;
	        this.state = state;
	        this.zipcode = zipcode;
	    }

	    public String getStreet() { return street; }
	    public void setStreet(String street) { this.street = street; }
	    public String getCity() { return city; }
	    public void setCity(String city) { this.city = city; }
	    public String getState() { return state; }
	    public void setState(String state) { this.state = state; }
	    public String getZipcode() { return zipcode; }
	    public void setZipcode(String zipcode) { this.zipcode = zipcode; }
	}


	@Entity
	public class Customer {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;

	    @Embedded
	    private Address address;

	    // Constructors, getters, and setters

	    public Customer() {}

	    public Customer(String name, Address address) {
	        this.name = name;
	        this.address = address;
	    }

	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }
	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }
	    public Address getAddress() { return address; }
	    public void setAddress(Address address) { this.address = address; }
	}

}
