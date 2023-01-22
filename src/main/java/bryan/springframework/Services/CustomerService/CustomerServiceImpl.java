package bryan.springframework.Services.CustomerService;

import bryan.springframework.commands.PersonCommand;
import bryan.springframework.converters.PersonCommandToPerson;
import bryan.springframework.converters.PersonToPersonCommand;
import bryan.springframework.domain.Person;
import bryan.springframework.exceptions.NotFoundException;
import bryan.springframework.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final PersonRepository personRepository;
    private final PersonCommandToPerson personCommandToPerson;
    private final PersonToPersonCommand personToPersonCommand;



    public CustomerServiceImpl(PersonRepository personRepository, PersonCommandToPerson personCommandToPerson, PersonToPersonCommand personToPersonCommand) {
        this.personRepository = personRepository;
        this.personCommandToPerson = personCommandToPerson;
        this.personToPersonCommand = personToPersonCommand;
    }

    @Override
    public Set<Person> getCustomers() {

       Set<Person> customerSet = new HashSet<>();

       personRepository.findAll().iterator().forEachRemaining(customerSet::add);

        return customerSet;
    }

    @Override
    public Person findById(Long l) {

        Optional<Person> personOptional =  personRepository.findById(l);

        if (!personOptional.isPresent()) {

            throw new NotFoundException("Customer Not Found. The customer ID: "+l.toString());
        }

        return personOptional.get();
    }

    public <S extends Person> S save(S s) {
        return personRepository.save(s);
    }

    public <S extends Person> Iterable<S> saveAll(Iterable<S> iterable) {
        return personRepository.saveAll(iterable);
    }

    public boolean existsById(Long aLong) {
        return personRepository.existsById(aLong);
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    public Iterable<Person> findAllById(Iterable<Long> iterable) {
        return personRepository.findAllById(iterable);
    }

    public long count() {
        return personRepository.count();
    }

    public void deleteById(Long aLong) {
        personRepository.deleteById(aLong);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }

    public void deleteAll(Iterable<? extends Person> iterable) {
        personRepository.deleteAll(iterable);
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

    @Override
    @Transactional
    public PersonCommand findCommandById(Long l) {
        return personToPersonCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public PersonCommand savePersonCommand(PersonCommand command) {
        Person detachedPerson = personCommandToPerson.convert(command);
        System.out.println("SavePersonCommand");

        Person savedPerson = personRepository.save(detachedPerson);
        log.debug("Saved PersonId:" + savedPerson.getId());
        return personToPersonCommand.convert(savedPerson);

    }



}
