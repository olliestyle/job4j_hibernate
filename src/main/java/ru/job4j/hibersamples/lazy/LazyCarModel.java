package ru.job4j.hibersamples.lazy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "carmodelslazy")
public class LazyCarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "carbrand_id")
    private LazyCarBrand lazyCarBrand;

    public static LazyCarModel of(String name, LazyCarBrand lazyCarBrand) {
        LazyCarModel lazyCarModel = new LazyCarModel();
        lazyCarModel.name = name;
        lazyCarModel.lazyCarBrand = lazyCarBrand;
        return lazyCarModel;
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

    public LazyCarBrand getLazyCarBrand() {
        return lazyCarBrand;
    }

    public void setLazyCarBrand(LazyCarBrand lazyCarBrand) {
        this.lazyCarBrand = lazyCarBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LazyCarModel lazyCarModel = (LazyCarModel) o;
        return id == lazyCarModel.id && Objects.equals(name, lazyCarModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "LazyCarModel{" + "id=" + id + ", name='" + name + '\'' + ", lazyCarBrand=" + lazyCarBrand + '}';
    }
}
