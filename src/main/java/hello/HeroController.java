package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/hero")
final class HeroController {
 
    private final HeroRepository repository;
 
    @Autowired
    HeroController(HeroRepository repo) {
        this.repository = repo;
    }
 
    @RequestMapping(method = RequestMethod.POST)
    Hero createHero(@RequestBody Hero hero) {
        repository.save(hero);
        return hero;
    }
 
 
    @RequestMapping(method = RequestMethod.GET)
    List<Hero> getAllHeroes() {
        return repository.findAll();
    }
    

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    Hero getHeroByName(@PathVariable String name) {
        return repository.findByName(name);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteHero(@PathVariable String id) {
        repository.delete(id);
    }
}