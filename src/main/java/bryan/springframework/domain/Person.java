package bryan.springframework.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String Name;
    private String Address;
    private String email;
    private String mobile;
    private Timestamp dob;



    @Enumerated(value = EnumType.STRING)
    private Marital marital;


    @OneToOne(cascade = CascadeType.ALL)
    private History history;

    @ManyToMany
    @JoinTable(name = "person_category",
          joinColumns = @JoinColumn(name = "person_id"),
          inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person" )
    private Set<TransValue> transValues = new HashSet<>();

    public Person() {
    }


    public Set<TransValue> getTransValues() {

        return transValues;
    }

    public TransValue getTransValues(TransValue transValues) {

        return transValues;
    }

    public void setTransValues(Set<TransValue> transValues) {
        this.transValues = transValues;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Timestamp getDob() {
        return dob;
    }

    public void setDob(Timestamp dob) {
        this.dob = dob;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Marital getMarital() {
        return marital;
    }

    public void setMarital(Marital marital) {
        this.marital = marital;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Person addTrans(TransValue transValue)
    {
        System.out.println("Personid:" +transValue.getId());
        transValue.setPerson(this);
        this.transValues.add(transValue);
        return this;


    }
}
