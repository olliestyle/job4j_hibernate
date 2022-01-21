package ru.job4j.hibersamples.manytomany;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "humans")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String info;

    private Calendar calendar;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<City> cities = new HashSet<>();

    public void addCity(City city) {
        this.cities.add(city);
    }

    public static Human of(String info) {
        Human human = new Human();
        human.info = info;
        human.calendar = Calendar.getInstance();
        return human;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Human human = (Human) o;
        return id == human.id && Objects.equals(info, human.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info);
    }

    @Override
    public String toString() {
        return "Human{" + "id=" + id + ", info='" + info + ", calendar=" + calendar + '}';
    }
}
