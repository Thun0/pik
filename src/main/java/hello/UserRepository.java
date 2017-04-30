package hello;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.mongodb.repository.MongoRepository;

@RepositoryRestResource(collectionResourceRel = "users")
public interface UserRepository extends MongoRepository<User, String> {

    public User findByFirstName(@Param("name") String name);
    public List<User> findByLastName(@Param("name") String name);
}