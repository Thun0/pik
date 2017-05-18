package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/user")
final class UserController {
 
    private final UserRepository repository;
 
    @Autowired
    UserController(UserRepository repo) {
        this.repository = repo;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    User createUser(@RequestBody User usr) {
        repository.save(usr);
        return usr;
    }
 
 
    @RequestMapping(method = RequestMethod.GET)
    List<User> getAllUsers() {
        return repository.findAll();
    }
    

    @RequestMapping(value = "/search/byFirstName/{firstName}", method = RequestMethod.GET)
    User getPersonByFirstName(@PathVariable String firstName) {
        return repository.findByFirstName(firstName);
    }
}