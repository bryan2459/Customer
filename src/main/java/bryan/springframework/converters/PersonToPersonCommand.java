package bryan.springframework.converters;

import bryan.springframework.commands.PersonCommand;
import bryan.springframework.domain.Category;
import bryan.springframework.domain.Person;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonCommand implements Converter<Person, PersonCommand>{

    private final CategoryCommandToCategory categoryConverter;

    private final TransValueToTransValueCommand transValueConverter;
    private final HistoryCommandToHistory historyConverter;


    public PersonToPersonCommand(CategoryCommandToCategory categoryConverter,
             TransValueToTransValueCommand transValueConverter, HistoryCommandToHistory historyConverter) {
        this.categoryConverter = categoryConverter;
        this.transValueConverter = transValueConverter;
        this.historyConverter = historyConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public PersonCommand convert(Person source) {
        if (source == null) {
            return null;
        }
        final PersonCommand command = new PersonCommand();

        command.setId(source.getId());
        command.setName(source.getName());
        command.setAddress(source.getAddress());
        command.setEmail(source.getEmail());
        command.setMobile(source.getMobile());
        command.setDob(source.getDob());
        command.setMarital(source.getMarital());
        command.setHistory(source.getHistory());

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach((Category category) -> command.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getTransValues() != null && source.getTransValues().size() > 0){
            source.getTransValues()
                    .forEach(transValue -> command.getTransValues().add(transValueConverter.convert(transValue)));
        }

        return command;

    }
}
