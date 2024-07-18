package annotations;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 
 * In Spring Boot, particularly when using Lombok, @NoArgsConstructor
 * and @AllArgsConstructor are annotations that generate constructors for your
 * classes. These annotations simplify boilerplate code in your data models by
 * automatically generating constructors.
 */
public class No_AllArgsConstructor {

	@NoArgsConstructor
	public class User {
		private Long id;
		private String name;
		private String email;

		// public User() {
//	}
		// This generated constructor allows you to create instances of User without
		// passing any arguments:
		//User user = new User();
	}


@AllArgsConstructor
public class User1 {
	private Long id;
	private String name;
	private String email;

	// public User(Long id, String name, String email) {
//	    this.id = id;
//	    this.name = name;
//	    this.email = email;
//	}
	// This allows you to create instances of User with all fields initialized:
//	User user = new User(1L, "John Doe", "john.doe@example.com");

}

}
