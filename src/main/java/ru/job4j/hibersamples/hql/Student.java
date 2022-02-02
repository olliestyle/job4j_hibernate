package ru.job4j.hibersamples.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    private String city;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    public static Student of(String name, int age, String city) {
        Student student = new Student();
        student.age = age;
        student.name = name;
        student.city = city;
        return student;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return id == student.id && age == student.age
                && Objects.equals(name, student.name) && Objects.equals(city, student.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, city);
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age
                + ", city='" + city + '\'' + ", account=" + account + '}';
    }
}
