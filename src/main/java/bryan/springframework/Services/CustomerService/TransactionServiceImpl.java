package bryan.springframework.Services.CustomerService;

import bryan.springframework.commands.TransValueCommand;
import bryan.springframework.converters.TransValueCommandToTransValue;
import bryan.springframework.converters.TransValueToTransValueCommand;
import bryan.springframework.domain.Person;
import bryan.springframework.domain.TransValue;
import bryan.springframework.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService  {

    private final TransValueToTransValueCommand transValueToTransValueCommand;
    private final TransValueCommandToTransValue transValueCommandToTransValue;
    private final PersonRepository personRepository;

    public TransactionServiceImpl(TransValueToTransValueCommand transValueToTransValueCommand,
                                  TransValueCommandToTransValue transValueCommandToTransValue, PersonRepository personRepository) {
        this.transValueToTransValueCommand = transValueToTransValueCommand;
        this.transValueCommandToTransValue = transValueCommandToTransValue;
        this.personRepository = personRepository;
    }


    @Override
    public TransValueCommand findByPersonIdAndTransactionId(Long personId, Long transactionId)
    {
        System.out.println("TransValueCommand: "+ personId+" "+ transactionId);

        Optional<Person> personOptional = personRepository.findById(personId);

        if (!personOptional.isPresent()){
            //todo impl error handling
            log.error("customer id not found. Id: " + personId);
        }


        Person person = personOptional.get();

        Optional<TransValueCommand> transValueCommandOptional = person.getTransValues().stream()
                .filter(transValue -> transValue.getId().equals(transactionId))
                .map( transValue -> transValueToTransValueCommand.convert(transValue)).findFirst();

        if(!transValueCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Transaction id not found: " + transactionId);
        }

        return transValueCommandOptional.get();
    }

    @Override
    @Transactional
    public TransValueCommand saveTransCommand(TransValueCommand command) {
        Optional<Person> personOptional = personRepository.findById(command.getPersonId());
        System.out.println("TransactionServiceimpl: "+ command.getId());
        System.out.println("Serviceimpl personid: "+ command.getPersonId());



        if(!personOptional.isPresent()){

            //todo toss error if not found!
            log.error("Person not found for id: " + command.getPersonId());
            System.out.println("id:"+command.getId());
            return new TransValueCommand();
        } else {
            Person person = personOptional.get();

            Optional<TransValue> transValueOptional = person
                    .getTransValues()
                    .stream()
                    .filter(transValue -> transValue.getId().equals(command.getId()))
                    .findFirst();

            if (transValueOptional.isPresent()) {
                TransValue transValueFound = transValueOptional.get();
                transValueFound.setDescription(command.getDescription());
                transValueFound.setAmount(command.getAmount());
                transValueFound.setTransType(command.getTransType());
                transValueFound.setCreatedDate(command.getCreatedDate());
            } else {
                //add new Ingredient
                System.out.println("customer id not found. Id: " + command.getPersonId());
                person.addTrans(transValueCommandToTransValue.convert(command));
            }

            Person savedPerson = personRepository.save(person);

            //to do check for fail
            return transValueToTransValueCommand.convert(savedPerson.getTransValues().stream()
                    .filter(personTransValues -> personTransValues.getId().equals(command.getId()))
                    .findFirst()
                    .get());

        }
    }
}
