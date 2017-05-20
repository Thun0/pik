package hello;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.mongodb.repository.MongoRepository;

@RepositoryRestResource(collectionResourceRel = "heroes", path = "heroes")
public interface HeroRepository extends MongoRepository<Hero, String> {

	    public Hero findByName(@Param("name") String name);
	    public Hero findById(@Param("id") String id);
	    public void delete(@Param("id") String id);
	    
	    public Hero update(@Param("id") String id, @Param("name") String new_name);
}
