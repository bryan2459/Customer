package bryan.springframework.converters;

import bryan.springframework.commands.TransValueCommand;
import bryan.springframework.domain.Person;
import bryan.springframework.domain.TransValue;
import lombok.Synchronized;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;


@Component
public class TransValueCommandToTransValue implements Converter<TransValueCommand, TransValue> {

    @Override
    @Synchronized
    @Nullable
    public TransValue convert(TransValueCommand source) {
        if (source == null) {
            return null;
        }


    /*
    public TransValue convert(TransValue source) {
        if (source == null) {
            return null;
        }
  */


        final TransValue transValue = new TransValue();
        transValue.setId(source.getId());

        if(source.getPersonId() != null){
            Person person = new Person();
            person.setId(source.getPersonId());
            transValue.setPerson(person);
            person.getTransValues(transValue);
        }


      //  transValue.setId(source.getId());
        transValue.setAmount(source.getAmount());
        transValue.setDescription(source.getDescription());
        transValue.setCreatedDate(source.getCreatedDate());
        return transValue;
    }

    public TransValue convert(TransValue transValue) {
        return null;
    }
}
