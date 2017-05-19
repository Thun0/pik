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
	private HeroRepository repository; 
	
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

        Hero h1 = new Hero("Mr. Nice");
        Hero h2 = new Hero("Narco");
        Hero h3 = new Hero("Ford");

        repository.save(h1);
        repository.save(h2);
        repository.save(h3);
    }

}