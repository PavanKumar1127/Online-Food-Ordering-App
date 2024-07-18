package annotations;

import java.util.List;

import jakarta.persistence.*;

/**
 * @OneToMany Annotation The @OneToMany annotation is used in JPA to define a
 *            one-to-many relationship between two entities. This means that one
 *            entity can be associated with multiple instances of another
 *            entity.
 * 
 * @OneToMany(mappedBy = "fieldName", cascade = CascadeType.ALL, fetch =
 *                     FetchType.LAZY)
 */
public class OneToManyAnnotation {
//	Parameters
//	mappedBy: Defines the field in the child entity that owns the relationship.
//	cascade: Specifies the operations that should be cascaded to the associated entities. Common options include:
//	CascadeType.ALL: All operations.
//	CascadeType.PERSIST: Persist operations.
//	CascadeType.MERGE: Merge operations.
//	CascadeType.REMOVE: Remove operations.
//	CascadeType.REFRESH: Refresh operations.
//	CascadeType.DETACH: Detach operations.

//	fetch: Defines the fetch strategy for the relationship. Common options include:
//	FetchType.EAGER: Fetch immediately.
//	FetchType.LAZY: Fetch on demand.

	@Entity
	public class Order {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String orderNumber;

		// Order has a one-to-many relationship with OrderItem.
		// mappedBy = "order" in Order indicates that the order field in OrderItem owns
		// the relationship.

		// CascadeType.ALL means all operations (persist, merge, remove, etc.) will be
		// cascaded to OrderItem.
		
		// FetchType.LAZY indicates that the orderItems list will be fetched only when
		// accessed.
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
		private Order order;

		// Getters and setters
	}
	
	@Entity
	public class Department {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;

	    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    private List<Employee> employees;

	    // Getters and setters
	}

	@Entity
	public class Employee {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;

	    @ManyToOne
	    @JoinColumn(name = "department_id")
	    private Department department;

	    // Getters and setters
	}

//	Practical Scenarios and How These Work
//	One-to-Many and Many-to-One:
//
//	Scenario: A company (Department) can have multiple employees (Employee), but each employee belongs to one department.
//	Implementation: The @OneToMany annotation is placed on the collection of employees in the Department class, while the @ManyToOne annotation is placed on the department field in the Employee class.
//	One-to-One:
//
//	Scenario: An employee (Employee) has one address (Address), and each address belongs to one employee.
//	Implementation: The @OneToOne annotation is used on both the Employee and Address classes to define the relationship.
//	Many-to-Many:
//
//	Scenario: Students (Student) can enroll in multiple courses (Course), and each course can have multiple students.
//	Implementation: The @ManyToMany annotation is used on both the Student and Course classes. The @JoinTable annotation is used to define the join table that holds the foreign keys.
}
