package bryan.springframework.converters;

import bryan.springframework.commands.HistoryCommand;
import bryan.springframework.domain.History;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HistoryToHistoryCommandTest {

    public static final String BIO = "I am a good worker";
    public static final Long ID_VALUE = new Long(1L);
    HistoryToHistoryCommand converter;



    @Before
    public void setUp() throws Exception {
        converter = new HistoryToHistoryCommand();
    }

    @Test
    public void convert() {
        History history = new History();
        history.setId(ID_VALUE);
        history.setBio(BIO);

        //when
        HistoryCommand historyCommand = converter.convert(history);
        //then

        assertEquals(ID_VALUE, historyCommand.getId());
        // assertEquals(RECIPE, ingredientCommand.get);

        assertEquals(BIO, historyCommand.getBio());
    }
}