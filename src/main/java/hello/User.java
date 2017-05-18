package hello;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User {

    @Id
    private String id;

    private String firstName;
    private String lastName;

    public User() {}

    @PersistenceConstructor
    public User(String firstName, String lastName) {
    	super();
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
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

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

	public void update(String fname, String lname) {
		this.firstName = fname;
		this.lastName = lname;
	}

}