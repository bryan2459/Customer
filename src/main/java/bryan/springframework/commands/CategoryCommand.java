package bryan.springframework.commands;

import bryan.springframework.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor

public class CategoryCommand {


    private Long id;

    private String description;




}
