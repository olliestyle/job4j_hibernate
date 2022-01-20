package ru.job4j.hibersamples.lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carbrandslazy")
public class LazyCarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "lazyCarBrand")
    private List<LazyCarModel> lazyCarModels = new ArrayList<>();

    public static LazyCarBrand of(String name) {
        LazyCarBrand lazyCarBrand = new LazyCarBrand();
        lazyCarBrand.name = name;
        return lazyCarBrand;
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

    public List<LazyCarModel> getLazyCarModels() {
        return lazyCarModels;
    }

    public void setLazyCarModels(List<LazyCarModel> lazyCarModels) {
        this.lazyCarModels = lazyCarModels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LazyCarBrand lazyCarBrand = (LazyCarBrand) o;
        return id == lazyCarBrand.id && Objects.equals(name, lazyCarBrand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CarBrand{" + "id=" + id + ", name='" + name;
    }
}
