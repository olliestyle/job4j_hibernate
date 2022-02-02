package ru.job4j.hibersamples.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int experience;

    private String salary;

    @OneToOne(fetch = FetchType.LAZY)
    private VacancyStore vacancyStore;

    public static Candidate of(String name, int experience, String salary) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.experience = experience;
        candidate.salary = salary;
        return candidate;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public VacancyStore getVacancyStore() {
        return vacancyStore;
    }

    public void setVacancyStore(VacancyStore vacancyStore) {
        this.vacancyStore = vacancyStore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id && experience == candidate.experience
                && Objects.equals(name, candidate.name) && Objects.equals(salary, candidate.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary);
    }

    @Override
    public String toString() {
        return "Candidate{" + "id=" + id + ", name='" + name + '\'' + ", experience=" + experience
                + ", salary='" + salary + '\'' + ", vacancyStore=" + vacancyStore + '}';
    }
}
