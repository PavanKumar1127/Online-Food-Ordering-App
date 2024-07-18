package annotations;

import java.util.List;

import jakarta.persistence.*;

/**
 * A many-to-many relationship means that multiple instances of one entity are
 * related to multiple instances of another entity.
 */
public class OneToOneAnnotation {

	// Example
//	Consider an example where a Student can enroll in multiple Courses and a Course can have multiple Students.
	@Entity
	public class Student {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;

	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "student_course",
	        joinColumns = @JoinColumn(name = "student_id"),
	        inverseJoinColumns = @JoinColumn(name = "course_id")
	    )
	    private List<Course> courses;

	    // Getters and setters
	}

	@Entity
	public class Course {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String title;

	    @ManyToMany(mappedBy = "courses")
	    private List<Student> students;

	    // Getters and setters
	}


}
