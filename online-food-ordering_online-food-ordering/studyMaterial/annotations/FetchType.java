package annotations;

import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class FetchType {

	// 1. FetchType.LAZY
	// By default, the @OneToMany relationship is fetched lazily. This means that
	// the associated entities are not loaded from the database until they are
	// accessed in the code.

	@Entity
	public class Parent {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String name;

		// @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade =
		// CascadeType.ALL)
		private List<Child> children;

		// Getters and setters
	}

	@Entity
	public class Child {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String name;

		@ManyToOne
		@JoinColumn(name = "parent_id")
		private Parent parent;

		// Getters and setters
	}
//This is useful when the related entities are large or not always needed, as it improves performance by delaying the loading of the collection until it is explicitly accessed.

	// FetchType.EAGER
	// When the fetch type is set to EAGER, the associated entities are loaded from
	// the database immediately along with the parent entity.
	@Entity
	public class Parent1 {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    //@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	    private List<Child> children;

	    // Getters and setters
	}

	@Entity
	public class Child1 {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    @ManyToOne
	    @JoinColumn(name = "parent_id")
	    private Parent parent;

	    // Getters and setters
	}
//This is useful when you always need the related entities along with the parent entity. However, it can impact performance if the related entities are large or many in number.

}
