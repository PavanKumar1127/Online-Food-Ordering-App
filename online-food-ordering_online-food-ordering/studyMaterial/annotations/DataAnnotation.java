package annotations;

import java.util.Objects;

import lombok.Data;

/**
 * @Data annotation in Lombok is a powerful and convenient way to reduce
 *       boilerplate code in Java. It combines several useful annotations into
 *       one, automatically generating common methods like getters, setters,
 *       toString(), equals(), hashCode(), and requiredArgsConstructor. This is
 *       particularly useful in simplifying the code for simple data carrier
 *       classes.
 */
public class DataAnnotation {
//	Key Concepts
//	Getters and Setters: Generates getter and setter methods for all non-final fields.
//	toString() Method: Creates a toString() method that includes all fields.
//	equals() and hashCode() Methods: Generates equals() and hashCode() methods, considering all non-transient fields.
//	Constructors: Generates a constructor that initializes all final fields and fields with constraints such as @NonNull.


	public class Person {
	    private String name;
	    private int age;

	    public Person(String name, int age) {
	        this.name = name;
	        this.age = age;
	    }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }
	    public int getAge() { return age; }
	    public void setAge(int age) { this.age = age; }

	    @Override
	    public String toString() {
	        return "Person{" +
	               "name='" + name + '\'' +
	               ", age=" + age +
	               '}';
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Person person = (Person) o;
	        return age == person.age && Objects.equals(name, person.name);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(name, age);
	    }
	}
	
	
	@Data
	public class Person1 {
	    private String name;
	    private int age;
	}

}
