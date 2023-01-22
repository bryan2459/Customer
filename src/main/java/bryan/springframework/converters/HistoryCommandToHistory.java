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
public class HistoryCommandToHistory implements Converter<HistoryCommand, History> {

    @Synchronized
    @Nullable
    @Override
    public History convert(HistoryCommand source) {
        if(source == null) {
            return null;
        }

        final History history = new History();
        history.setId(source.getId());
        history.setBio(source.getBio());
        return history;
    }
}
