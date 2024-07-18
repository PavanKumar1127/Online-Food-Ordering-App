package annotations;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * @JsonIgnore Annotation The @JsonIgnore annotation is used in Jackson (a JSON
 *             processing library) to ignore properties during JSON
 *             serialization and deserialization. This is particularly useful in
 *             Spring Boot when working with RESTful web services to prevent
 *             certain fields from being included in the JSON response.
 */
public class JsonIgnoreAnnotation {

	@Entity
	public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String username;

		@JsonIgnore
		private String password;

		// Getters and setters
	}

//	Scenario: Preventing Recursive Serialization
//	@JsonIgnore is often used to prevent recursive serialization in bi-directional relationships. For example, consider a bidirectional relationship between Order and OrderItem.
	@Entity
	public class Order {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String orderNumber;

		@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		private List<OrderItem> orderItems;

		// Getters and setters
	}

	@Entity
	public class OrderItem {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String itemName;
		private int quantity;

		@ManyToOne
		@JoinColumn(name = "order_id")
		@JsonIgnore
		private Order order;

		// Getters and setters
	}
//In this example, the order field in OrderItem is annotated with @JsonIgnore to prevent infinite recursion during JSON serialization.
	
	
//	Practical Scenarios
//	Parent-Child Relationship: In a typical e-commerce application, Customer and Order entities can have a one-to-many relationship. A customer can have multiple orders.

//Handling Sensitive Information: In a user management system, sensitive information like passwords should not be exposed in the API response.
	
//Avoiding Circular References: In a bi-directional relationship, @JsonIgnore helps avoid circular references that can lead to stack overflow errors during JSON serialization.	

}
