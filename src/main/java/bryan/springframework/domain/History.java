package bryan.springframework.domain;

import javax.persistence.*;

@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
   @Lob
    private String bio;

    public History() {
        this.id = id;
    }


    @OneToOne
    private Person person;

    public History(String bio) {
        this.bio = bio;
    }

    public Long getId() {
         return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return  bio;
    }
}
