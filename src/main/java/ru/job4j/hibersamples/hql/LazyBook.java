package ru.job4j.hibersamples.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lazybooks")
public class LazyBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String publishingHouse;

    public static LazyBook of(String name, String publishingHouse) {
        LazyBook b = new LazyBook();
        b.name = name;
        b.publishingHouse = publishingHouse;
        return b;
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

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LazyBook lazyBook = (LazyBook) o;
        return id == lazyBook.id && Objects.equals(name, lazyBook.name) && Objects.equals(publishingHouse, lazyBook.publishingHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publishingHouse);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", publishingHouse='" + publishingHouse + '\'' + '}';
    }
}
