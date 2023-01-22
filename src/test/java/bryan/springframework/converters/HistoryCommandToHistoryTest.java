package bryan.springframework.converters;

import bryan.springframework.commands.HistoryCommand;
import bryan.springframework.domain.History;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HistoryCommandToHistoryTest {
    public static final String BIO = "I am a good worker";
    public static final Long ID_VALUE = new Long(1L);
    HistoryCommandToHistory converter;

    @Before
    public void setUp() throws Exception {
        converter = new HistoryCommandToHistory();
    }

    @Test
    public void convert() {
        HistoryCommand command = new HistoryCommand();
        command.setId(ID_VALUE);
        command.setBio("I am a good worker");

        //when
        History history = converter.convert(command);

        //then
        assertNotNull(history);

        assertEquals(ID_VALUE, history.getId());

        assertEquals(BIO, history.getBio());

    }
}