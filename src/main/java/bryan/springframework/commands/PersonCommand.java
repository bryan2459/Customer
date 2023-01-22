package bryan.springframework.commands;

import bryan.springframework.domain.Category;
import bryan.springframework.domain.History;
import bryan.springframework.domain.Marital;
import bryan.springframework.domain.TransValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;



@Setter
@Getter
@NoArgsConstructor
public class PersonCommand {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;


    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String mobile;


    private Timestamp dob;


    private Marital marital;

    private History history;
    private Set<Category> categories = new HashSet<>();
    private Set<TransValue> transValues = new HashSet<>();



}
