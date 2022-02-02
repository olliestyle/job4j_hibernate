package ru.job4j.hibersamples.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private int salary;

    public static Vacancy of(String description, int salary) {
        Vacancy vacancy = new Vacancy();
        vacancy.description = description;
        vacancy.salary = salary;
        return vacancy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id && salary == vacancy.salary
                && Objects.equals(description, vacancy.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, salary);
    }

    @Override
    public String toString() {
        return "Vacancy{" + "id=" + id + ", description='" + description + '\'' + ", salary=" + salary + '}';
    }
}
