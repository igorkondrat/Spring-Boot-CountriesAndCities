package overview.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String nameCity;
    private LocalDate dateOfCreation;
    private int population;
    private String history;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Country country;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "city")
    private List<Sight> sightList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "city")
    private List<University> universityList = new ArrayList<>();

    public City(String nameCity, LocalDate dateOfCreation, int population, String history) {
        this.nameCity = nameCity;
        this.dateOfCreation = dateOfCreation;
        this.population = population;
        this.history = history;
    }

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Sight> getSightList() {
        return sightList;
    }

    public void setSightList(List<Sight> sightList) {
        this.sightList = sightList;
    }

    public List<University> getUniversityList() {
        return universityList;
    }

    public void setUniversityList(List<University> universityList) {
        this.universityList = universityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getId() == city.getId() &&
                getPopulation() == city.getPopulation() &&
                Objects.equals(getNameCity(), city.getNameCity()) &&
                Objects.equals(getDateOfCreation(), city.getDateOfCreation()) &&
                Objects.equals(getHistory(), city.getHistory()) &&
                Objects.equals(getCountry(), city.getCountry()) &&
                Objects.equals(getSightList(), city.getSightList()) &&
                Objects.equals(getUniversityList(), city.getUniversityList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameCity(), getDateOfCreation(), getPopulation(), getHistory(), getCountry(), getSightList(), getUniversityList());
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", nameCity='" + nameCity + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", population=" + population +
                ", history='" + history + '\'' +
//                ", country=" + country +
//                ", sightList=" + sightList +
//                ", universityList=" + universityList +
                '}';
    }
}
