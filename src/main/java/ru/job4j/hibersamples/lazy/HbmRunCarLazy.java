package ru.job4j.hibersamples.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRunCarLazy {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            List<LazyCarBrand> lazyCarBrands = session.createQuery("from LazyCarBrand").list();

            for (LazyCarBrand lazyCarBrand : lazyCarBrands) {
                for (LazyCarModel lazyCarModel : lazyCarBrand.getLazyCarModels()) {
                    System.out.println(lazyCarModel);
                }
            }

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
