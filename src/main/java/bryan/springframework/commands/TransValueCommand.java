package bryan.springframework.commands;

import bryan.springframework.domain.Person;
import bryan.springframework.domain.TransType;
import bryan.springframework.domain.TransValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class TransValueCommand extends TransValue {


    private Long id;
    private Long personId;

    private Timestamp createdDate;

    //transtype

    private String description;

    private BigDecimal amount;

    private TransType transType;




}
