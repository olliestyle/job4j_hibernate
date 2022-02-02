package ru.job4j.hibersamples.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmFetchRun {
    public static void main(String[] args) {
        Student rsl = null;

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            SessionFactory factory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();

            rsl = session.createQuery(
                    "select distinct st from Student st "
                            + "join fetch st.account a "
                            + "join fetch a.lazyBooks b "
                            + "where st.id = :sId", Student.class
            ).setParameter("sId", 9).uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(rsl);
    }
}
