package hello;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="heroes")
public class Hero {

    @Id
    private String id;

    private String name;

    public Hero() {}

    @PersistenceConstructor
    public Hero(String name) {
    	super();
        this.name = name;
    }
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
    	this.id = id;
    }

    public String getName() {
    	return name;
    }

    public void setName(String name) {
    	this.name = name;
    }
    

    @Override
    public String toString() {
        return String.format(
                "Hero[id=%s, name='%s']",
                this.id, this.name);
    }

	public void update(String name) {
		this.name = name;
	}

}