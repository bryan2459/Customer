package bryan.springframework.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class TransValue {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp createdDate;

    //transtype

    private String description;

    private BigDecimal amount;

    @ManyToOne
    private Person person;

    @Enumerated(value = EnumType.STRING)
    private TransType transType;

    public TransValue() {
    }

    public TransValue(Person person) {
        this.person = person;
    }

    public TransValue(Timestamp createdDate, String description, BigDecimal amount, TransType transType,Person person) {
        this.createdDate = createdDate;
        this.description = description;
        this.amount = amount;
        this.transType = transType;
        this.person = person;
    }
    public TransValue(Timestamp createdDate, String description, BigDecimal amount, TransType transType) {
        this.createdDate = createdDate;
        this.description = description;
        this.amount = amount;
        this.transType = transType;

    }

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransType getTransType() {
        return transType;
    }

    public void setTransType(TransType transType) {
        this.transType = transType;
    }
}
