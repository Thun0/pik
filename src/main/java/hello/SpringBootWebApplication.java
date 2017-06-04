package hello;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;



@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {

	@Autowired
	private UserRepository repository; 
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
    
    @PostConstruct
    void init() {
        repository.deleteAll();
        
        User arthur = new User("Arthur", "Dent");
        User trillian = new User("Trillian", "McMillan");
        User ford = new User("Ford", "Prefect");

        repository.save(arthur);
        repository.save(trillian);
        repository.save(ford);
    }

}