package ru.job4j.hibersamples.hql;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HbmRun {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            SessionFactory factory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();

            /*
            Student one = Student.of("Alex", 21, "Moscow");
            Student two = Student.of("Nikolay", 28, "Saint-Petersburg");
            Student three = Student.of("Nikita", 25, "Kaliningrad");

            session.save(one);
            session.save(two);
            session.save(three);
            */

            /*
            session.createQuery("delete from Student s where s.city = :city")
                    .setParameter("city", "MajorkaLand")
                    .executeUpdate();
            */

            /*
            session.createQuery("insert into Student (name, age, city) "
                    + "select s.name, s.age, s.city  "
                    + "from Student s")
                    .executeUpdate();
            */

            /*
            session.createQuery("update Student s set s.age = :newAge, s.city = :newCity where s.id = :fId")
            .setParameter("newAge", 22)
            .setParameter("newCity", "London")
            .setParameter("fId", 1)
            .executeUpdate();
            */

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
