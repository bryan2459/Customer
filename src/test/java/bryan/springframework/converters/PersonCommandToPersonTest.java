package bryan.springframework.converters;

import bryan.springframework.commands.CategoryCommand;
import bryan.springframework.commands.HistoryCommand;
import bryan.springframework.commands.PersonCommand;
import bryan.springframework.commands.TransValueCommand;
import bryan.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PersonCommandToPersonTest {

    public static final Long id = 1L;
    public static final String Name = "Franky Feather";
    public static final String Address = "345 Birdeye View";
    public static final String email = "feather@gmail.com";
    public static final String mobile ="89494040";
    public static final Timestamp dob = (Timestamp.valueOf("1964-09-01 09:01:15"));
    public static final Marital marital = Marital.Single;
    public static final String bio = "Unemployed ";
    public static final Long TRANS_ID_1 = 3L;
    public static final Long TRANS_ID_2 = 4L;
    public static final Long HISTORY_ID = 9L;
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;

    /*
    private Set<Category> categories = new HashSet<>();
    private Set<TransValue> transValues = new HashSet<>();
    */

    PersonCommandToPerson converter;

    @Before
    public void setUp() throws Exception {
        converter = new PersonCommandToPerson(new CategoryCommandToCategory(),
                new TransValueCommandToTransValue(),
                new HistoryCommandToHistory());
    }

    @Test
    public void convert() {
        PersonCommand personCommand = new PersonCommand();
        personCommand.setId(id);
        personCommand.setName(Name);
        personCommand.setAddress(Address);
        personCommand.setEmail(email);
        personCommand.setMobile(mobile);
        personCommand.setDob(dob);
        personCommand.setMarital(marital);


        History history = new History();
        history.setId(HISTORY_ID);
        history.setBio(bio);
        personCommand.setHistory(history);

        Category category = new Category();
        category.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID2);
        personCommand.getCategories().add(category);
        personCommand.getCategories().add(category2);

        TransValue transValue = new TransValue();
        transValue.setId(TRANS_ID_1);

        TransValue transValue2 = new TransValue();
        transValue2.setId(TRANS_ID_2);

        personCommand.getTransValues().add(transValue);
        personCommand.getTransValues().add(transValue2);


        //when
        Person person  = converter.convert(personCommand);

        assertNotNull(person);
        assertEquals(id, person.getId());
        assertEquals(Name, person.getName());
        assertEquals(Address, person.getAddress());
        assertEquals(email, person.getEmail());
        assertEquals(mobile, person.getMobile());
        assertEquals(dob, person.getDob());
        assertEquals(marital, person.getMarital());
        assertEquals(bio, person.getHistory().getBio());


        assertEquals(2, person.getCategories().size());
        assertEquals(1, person.getTransValues().size());


    }
}