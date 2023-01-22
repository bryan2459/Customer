package bryan.springframework.converters;

import bryan.springframework.commands.PersonCommand;
import bryan.springframework.domain.Category;
import bryan.springframework.domain.Person;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;;

@Component
public class PersonCommandToPerson implements Converter<PersonCommand, Person> {

    private final CategoryCommandToCategory categoryConverter;

    private final TransValueCommandToTransValue transValueConverter;
    private final HistoryCommandToHistory historyConverter;

    public PersonCommandToPerson(CategoryCommandToCategory categoryConverter,
                      TransValueCommandToTransValue transValueConverter, HistoryCommandToHistory historyConverter) {
        this.transValueConverter = transValueConverter;
        this.historyConverter = historyConverter;
        this.categoryConverter = categoryConverter;

    }
    @Synchronized
    @Nullable
    @Override
    public Person convert(PersonCommand source) {
        if (source == null) {
            return null;
        }
        final Person person = new Person();
        person.setId(source.getId());
        person.setName(source.getName());
        person.setAddress(source.getAddress());
        person.setEmail(source.getEmail());
        person.setMobile(source.getMobile());
        person.setDob(source.getDob());
        person.setMarital(source.getMarital());
        person.setHistory(source.getHistory());

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach(( Category category) -> person.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getTransValues() != null && source.getTransValues().size() > 0){
            source.getTransValues()
                    .forEach(transValue -> person.getTransValues().add(transValueConverter.convert(transValue)));
        }

        /*
            private String Name;
            private String Address;
            private String email;
            private String mobile;
            private Timestamp dob;
            private Marital marital;
            private History history;
            private Set<Category> categories = new HashSet<>();
            private Set<TransValue> transValues = new HashSet<>();


         */

        return person;

    }
}
