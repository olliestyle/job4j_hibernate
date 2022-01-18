package ru.job4j.hibersamples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmAddCar {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(new CarModel("a4"));
            session.save(new CarModel("a6"));
            session.save(new CarModel("q5"));
            session.save(new CarModel("q8"));
            session.save(new CarModel("rs6"));

            CarBrand audi = new CarBrand("Audi");
            audi.addCarModel(session.load(CarModel.class, 1));
            audi.addCarModel(session.load(CarModel.class, 2));
            audi.addCarModel(session.load(CarModel.class, 3));
            audi.addCarModel(session.load(CarModel.class, 4));
            audi.addCarModel(session.load(CarModel.class, 5));

            session.save(audi);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
