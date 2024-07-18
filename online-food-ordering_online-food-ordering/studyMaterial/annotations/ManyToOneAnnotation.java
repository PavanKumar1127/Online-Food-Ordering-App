package annotations;

import java.util.List;

import jakarta.persistence.*;

/**
 * A many-to-one relationship means that multiple instances of one entity are
 * related to a single instance of another entity. This is the inverse side of
 * the @OneToMany relationship shown above.
 */
public class ManyToOneAnnotation {

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

}
