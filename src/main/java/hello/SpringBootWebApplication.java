package hello;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

import hello.model.XMLReader;




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
    void init() throws IOException, SAXException, ParserConfigurationException {
//        repository.deleteAll();
//        
//        User arthur = new User("Arthur", "Dent");
//        User trillian = new User("Trillian", "McMillan");
//        User ford = new User("Ford", "Prefect");
//
//        repository.save(arthur);
//        repository.save(trillian);
//        repository.save(ford);
//    	File file = new ClassPathResource("plwordnet.xml").getFile();
    	File file = new File("/opt/plwordnet.xml");
    	XMLReader.setDocAndFactory(file);
    }

}