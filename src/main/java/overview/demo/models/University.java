package overview.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String nameUniversity;
    private String direction;
    private String ownership;
    private String formOfTraining;
    private LocalDate dateOfCreation;
    private String street;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private City city;

    public University(String nameUniversity, String direction, String ownership, String formOfTraining, LocalDate dateOfCreation, String street) {
        this.nameUniversity = nameUniversity;
        this.direction = direction;
        this.ownership = ownership;
        this.formOfTraining = formOfTraining;
        this.dateOfCreation = dateOfCreation;
        this.street = street;
    }

    public University() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUniversity() {
        return nameUniversity;
    }

    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getFormOfTraining() {
        return formOfTraining;
    }

    public void setFormOfTraining(String formOfTraining) {
        this.formOfTraining = formOfTraining;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof University)) return false;
        University that = (University) o;
        return getId() == that.getId() &&
                Objects.equals(getNameUniversity(), that.getNameUniversity()) &&
                Objects.equals(getDirection(), that.getDirection()) &&
                Objects.equals(getOwnership(), that.getOwnership()) &&
                Objects.equals(getFormOfTraining(), that.getFormOfTraining()) &&
                Objects.equals(getDateOfCreation(), that.getDateOfCreation()) &&
                Objects.equals(getStreet(), that.getStreet()) &&
                Objects.equals(getCity(), that.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameUniversity(), getDirection(), getOwnership(), getFormOfTraining(), getDateOfCreation(), getStreet(), getCity());
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", nameUniversity='" + nameUniversity + '\'' +
                ", direction='" + direction + '\'' +
                ", ownership='" + ownership + '\'' +
                ", formOfTraining='" + formOfTraining + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", street='" + street + '\'' +
//                ", city=" + city +
                '}';
    }
}
