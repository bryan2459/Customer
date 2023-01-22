package bryan.springframework.repositories;

import bryan.springframework.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {
}
