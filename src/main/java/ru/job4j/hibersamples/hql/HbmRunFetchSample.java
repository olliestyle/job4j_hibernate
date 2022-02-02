package ru.job4j.hibersamples.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRunFetchSample {
    public static void main(String[] args) {
        Candidate candidate = null;

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
            Candidate candidate1 = Candidate.of("Pavel", 5, "50000");
            VacancyStore vacancyStore = VacancyStore.of("HH.RU");
            session.save(vacancyStore);
            Vacancy vacancy = Vacancy.of("Junior Java", 30000);
            Vacancy vacancy1 = Vacancy.of("Middle Java", 50000);
            Vacancy vacancy2 = Vacancy.of("Senior Java", 70000);
            session.save(vacancy);
            session.save(vacancy1);
            session.save(vacancy2);
            vacancyStore.addVacancy(vacancy);
            vacancyStore.addVacancy(vacancy1);
            vacancyStore.addVacancy(vacancy2);
            candidate1.setVacancyStore(vacancyStore);
            session.save(candidate1);
            */

            /*
            candidate = session.createQuery("select c from Candidate c where c.id = :cid", Candidate.class)
                    .setParameter("cid", 6)
                    .uniqueResult();
            */

            candidate = session.createQuery("select distinct c from Candidate c "
                                            + " join fetch c.vacancyStore vs "
                                            + " join fetch vs.vacancies v "
                                            + " where c.id = :cid", Candidate.class)
                    .setParameter("cid", 6)
                    .uniqueResult();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        System.out.println(candidate);
    }
}
