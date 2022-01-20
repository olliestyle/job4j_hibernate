package ru.job4j.hibersamples.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 *         Т.к. у нас отношение one to many, то грузить many
 *         при запросе (list = session.createQuery("from Category").list();) хибер сразу не будет и получится,
 *         что вне сессии вложенный объект не будет доступен.
 *         Можно добавить к аннотации  fetch = FetchType.EAGER, но это в крайнем случае.
 *         Лучше:
 *         1. Поместить вызов метода, который получает доступ
 *          к ассоциированному объекту в поле действия сессии с которой он ассоциирован.
 *         2. Изменить стратегию загрузки связанных сущностей прямо в запросе к БД -
 *         list = session.createQuery(
 *                     "select distinct c from Category c join fetch c.tasks"
 *             ).list();
 */

public class HbmRunLazy {
    public static void main(String[] args) {
        List<Category> categoryList = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            SessionFactory factory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            categoryList = session.createQuery("select distinct c from Category c join fetch c.tasks").list();
            for (Category category: categoryList) {
                for (Task task: category.getTasks()) {
                    System.out.println(task);
                }
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Category category: categoryList) {
            for (Task task: category.getTasks()) {
                System.out.println(task);
            }
        }
    }
}
