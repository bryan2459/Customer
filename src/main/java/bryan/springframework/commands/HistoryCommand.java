package bryan.springframework.commands;

import bryan.springframework.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
public class HistoryCommand {


    private Long id;

    private String bio;


}
