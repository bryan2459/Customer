package bryan.springframework.Services.CustomerService;

import bryan.springframework.commands.PersonCommand;
import bryan.springframework.domain.Person;

import java.util.Set;

public interface CustomerService {

    Set<Person> getCustomers();

    Object findById(Long valueOf);

    PersonCommand findCommandById(Long l);

    PersonCommand savePersonCommand(PersonCommand command);

    void deleteById(Long idToDelete);


}
