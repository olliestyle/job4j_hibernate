package ru.job4j.hibersamples.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmManyToMany {
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

            Author pushkin = Author.of("Pushkin");
            Author martin = Author.of("Martin");
            Author tolstoy = Author.of("Tolstoy");

            Book first = Book.of("PushkinMartinBook");
            Book second = Book.of("MartinTolstoyBook");
            pushkin.getBooks().add(first);
            martin.getBooks().add(first);
            martin.getBooks().add(second);
            tolstoy.getBooks().add(second);

            session.save(first);
            session.save(second);
            session.save(pushkin);
            session.save(martin);
            session.save(tolstoy);

            Author toDelete = session.get(Author.class, martin.getId());

            session.remove(toDelete);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
