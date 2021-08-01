package ru.job4j.tracker;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void init() {
    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.beginTransaction();
        Item result = session.get(Item.class, item.getId());
        session.close();
        return result;
    }

    @Override
    public boolean replace(String id, Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item temp = session.get(Item.class, Integer.parseInt(id));
        if (temp == null) {
            return false;
        }
        item.setId(Integer.parseInt(id));
        session.merge(item);
        session.getTransaction().commit();
        session.close();
            return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item del = new Item();
        del.setId(Integer.parseInt(id));
        session.delete(del);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("FROM ru.job4j.tracker.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("FROM ru.job4j.tracker.Item i WHERE i.name = :name")
                .setParameter("name", key).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Item findById(String id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, Integer.parseInt(id));
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}