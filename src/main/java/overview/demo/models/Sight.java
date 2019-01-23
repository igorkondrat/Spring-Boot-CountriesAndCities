package overview.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;

@Entity
public class Sight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String nameSight;
    private String type;
    private int minMoney;
    private String description;
    private String file;
    @Column(columnDefinition = "LONGTEXT")
    private ArrayList<String> comments = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private City city;

    public Sight(String nameSight, String type, int minMoney, String description, String file) {
        this.nameSight = nameSight;
        this.type = type;
        this.minMoney = minMoney;
        this.description = description;
        this.file=file;
    }

    public Sight() {
    }

    public int getId() {
        return id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComment(String comment) {
        this.comments.add(comment);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSight() {
        return nameSight;
    }

    public void setNameSight(String nameSight) {
        this.nameSight = nameSight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof Sight)) return false;
        Sight sight = (Sight) o;
        return getId() == sight.getId() &&
                getMinMoney() == sight.getMinMoney() &&
                Objects.equals(getNameSight(), sight.getNameSight()) &&
                Objects.equals(getType(), sight.getType()) &&
                Objects.equals(getDescription(), sight.getDescription()) &&
                Objects.equals(getCity(), sight.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameSight(), getType(), getMinMoney(), getDescription(), getCity());
    }

    @Override
    public String toString() {
        return "Sight{" +
                "id=" + id +
                ", nameSight='" + nameSight + '\'' +
                ", type='" + type + '\'' +
                ", minMoney=" + minMoney +
                ", description='" + description + '\'' +
                ", file='" + file + '\'' +
//                ", city=" + city +
                '}';
    }
}
