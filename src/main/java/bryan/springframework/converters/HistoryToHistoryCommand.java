package bryan.springframework.converters;


import bryan.springframework.commands.HistoryCommand;
import bryan.springframework.domain.History;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class HistoryToHistoryCommand implements Converter<History, HistoryCommand>{

    @Synchronized
    @Nullable
    @Override
    public HistoryCommand convert(History source) {
        if (source == null) {
            return null;
        }

        final HistoryCommand historyCommand = new HistoryCommand();
        historyCommand.setId(source.getId());
        historyCommand.setBio( source.getBio());
        return historyCommand;
    }
}
