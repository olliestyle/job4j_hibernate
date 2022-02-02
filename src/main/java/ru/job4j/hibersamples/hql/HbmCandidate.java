package ru.job4j.hibersamples.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

public class HbmCandidate {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            SessionFactory factory = new MetadataSources(registry)
                    .addAnnotatedClass(Candidate.class)
                    .buildMetadata(registry)
                    .buildSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();

            /*
            Candidate pavel = Candidate.of("Pavel", 5, "15000");
            Candidate egor = Candidate.of("Egor", 3, "13000");
            Candidate oleg = Candidate.of("Oleg", 2, "12000");

            session.save(pavel);
            session.save(egor);
            session.save(oleg);
             */

            Query query = session.createQuery("from ru.job4j.hibersamples.hql.Candidate");
            for (Object candidate: query.getResultList()) {
                System.out.println(candidate);
            }

            System.out.println();

            List<Candidate> candidates = session.createQuery("from ru.job4j.hibersamples.hql.Candidate c where c.id = :cid ")
                    .setParameter("cid", 2)
                    .getResultList();
            candidates.forEach(System.out::println);

            System.out.println();

            candidates = session.createQuery("from ru.job4j.hibersamples.hql.Candidate c where c.name = :cname ")
                    .setParameter("cname", "NEWOleg")
                    .getResultList();
            candidates.forEach(System.out::println);

            System.out.println();

            session.createQuery("update Candidate c set c.name = :newName,"
                    + " c.experience = :newExp,"
                    + " c.salary = :newSalary"
                    + " where c.id = :cid")
                    .setParameter("newName", "NEWOleg")
                    .setParameter("newExp", 7)
                    .setParameter("newSalary", "17000")
                    .setParameter("cid", 3)
                    .executeUpdate();

            session.createQuery("delete from ru.job4j.hibersamples.hql.Candidate c where c.name = :cname ")
                    .setParameter("cname", "Pavel")
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }
}
