package bryan.springframework.converters;

import bryan.springframework.commands.TransValueCommand;
import bryan.springframework.domain.TransValue;
import org.springframework.stereotype.Component;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TransValueToTransValueCommand implements Converter<TransValue, TransValueCommand> {
    @Synchronized
    @Nullable
    @Override
    public TransValueCommand convert(TransValue source) {
        if (source == null) {
            return null;
        }

        TransValueCommand transValueCommand = new TransValueCommand();
        transValueCommand.setId(source.getId());
        if (source.getPerson() != null) {
            transValueCommand.setPersonId(source.getPerson().getId());
        }

        /*
        final TransValueCommand transValueCommand = new TransValueCommand();
        transValueCommand.setId(source.getId());
         */
        transValueCommand.setAmount(source.getAmount());
        transValueCommand.setTransType(source.getTransType());
        transValueCommand.setDescription(source.getDescription());
        transValueCommand.setCreatedDate(source.getCreatedDate());
        return transValueCommand;

    }

}
