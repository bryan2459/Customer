package bryan.springframework.Services.CustomerService;

import bryan.springframework.commands.PersonCommand;
import bryan.springframework.converters.PersonCommandToPerson;
import bryan.springframework.converters.PersonToPersonCommand;
import bryan.springframework.domain.Person;
import bryan.springframework.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    CustomerService customerService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonCommandToPerson personCommandToPerson;

    @Autowired
    PersonToPersonCommand personToPersonCommand;

    @Transactional
    @Test
    public void savePersonCommand() {

        //given
        Iterable<Person> persons = personRepository.findAll();
        Person testPerson = persons.iterator().next();
        PersonCommand testPersonCommand = personToPersonCommand.convert(testPerson);

        //when
        testPersonCommand.setName(NEW_DESCRIPTION);
        PersonCommand savedPersonCommand = customerService.savePersonCommand(testPersonCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedPersonCommand.getName());
        assertEquals(testPerson.getId(), savedPersonCommand.getId());
        assertEquals(testPerson.getCategories().size(), savedPersonCommand.getCategories().size());
        assertEquals(testPerson.getTransValues().size(), savedPersonCommand.getTransValues().size());



    }
}