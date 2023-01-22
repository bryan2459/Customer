package bryan.springframework.bootstrap;

import bryan.springframework.domain.*;
import bryan.springframework.repositories.CategoryRepository;
import bryan.springframework.repositories.PersonRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Component
public class PersonBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final PersonRepository personRepository;
    private final CategoryRepository categoryRepository;

    public PersonBootstrap(PersonRepository personRepository, CategoryRepository categoryRepository) {
        this.personRepository = personRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
             personRepository.saveAll(getPersons());
    }

    private List<Person> getPersons(){

        System.out.println("Starting bootstrap");

        List<Person> persons = new ArrayList<>(2);

        Person person1 = new Person();
        person1.setName("Andrew Jackson");
        person1.setAddress("10 Mocking Bird lane");
        person1.setEmail("jack@gmail.com");
        person1.setMobile("948199191");
        person1.setDob(Timestamp.valueOf("1964-09-01 09:01:15"));
        person1.setMarital(Marital.Single);


        Person person2 = new Person();
        person2.setName("Wildo Bonifide");
        person2.setAddress("22 Christoper Cross");
        person2.setEmail("wildo@gmail.com");
        person2.setMobile("95612341");
        person2.setDob(Timestamp.valueOf("1978-02-21 09:01:15"));
        person2.setMarital(Marital.Married);

        Person person3 = new Person();
        person3.setName("Hilary Clinton");
        person3.setAddress("56 James Street");
        person3.setEmail("hilary@gmail.com");
        person3.setMobile("0405202341");
        person3.setDob(Timestamp.valueOf("1967-11-14 09:01:15"));
        person3.setMarital(Marital.Married);

       // person1.getHistory().add(new History("Bio Tech Engineer"));

        History person1Hist = new History();
        person1Hist.setBio("Andrew is an excellent addition to any team.\n" +
                "He has the will to succeed in any situation and remains calm while others are losing their cool.\n" +
                "He knows more computer languages and has good problem solving skills.\n" +
                "Andrew comes highly recommended.\n");

        person1.setHistory(person1Hist);
        Date date = new Date();

        Optional<Category> level1 = categoryRepository.findByDescription(">50Kand<100K");
        Optional<Category> level2 = categoryRepository.findByDescription(">101Kand<150K");
        Optional<Category> level3 = categoryRepository.findByDescription(">151Kand<200K");
        Optional<Category> level4 = categoryRepository.findByDescription(">201Kand<300K");

        Category level1Category = level1.get();
        Category level2Category = level2.get();


        person1.getCategories().add(level2Category);
        person1.getTransValues().add(new TransValue(new Timestamp(date.getTime()),"Puma soccer boot",new BigDecimal(62), TransType.Debit,person1));
        person1.getTransValues().add(new TransValue(new Timestamp(date.getTime()),"Payment",new BigDecimal(62), TransType.Credit,person1));
        person1.addTrans(new TransValue(new Timestamp(date.getTime()),"Woolworths",new BigDecimal(267), TransType.Debit));

        History person2Hist = new History();
        person2Hist.setBio("Not available\n");
        person2.setHistory(person2Hist);

        History person3Hist = new History();
        person3Hist.setBio("Not available\n");
        person3.setHistory(person3Hist);

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        return persons;

    }
}
