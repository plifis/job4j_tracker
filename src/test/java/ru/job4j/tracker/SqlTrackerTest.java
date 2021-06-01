package ru.job4j.tracker;

import org.junit.Test;
import org.junit.internal.runners.model.EachTestNotifier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SqlTrackerTest {
    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name"));
            assertThat(tracker.findByName("name").size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void findByName() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item("Nikita"));
            tracker.add(new Item("name"));
            assertThat(tracker.findByName("Nikita").get(0), is(item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item("Nikita"));
            String id = String.valueOf(item.getId());
            tracker.add(new Item("name"));
            tracker.add(new Item("number_two"));
            assertThat(tracker.findById(id), is(item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
           Item item =  tracker.add(new Item("name"));
            assertThat(tracker.delete(String.valueOf(item.getId())), is(true));
            assertFalse(tracker.delete(String.valueOf(item.getId())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void replaceItem() {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item oldItem =  tracker.add(new Item("Bill"));
            Item newItem = tracker.add(new Item("Lil"));
            assertThat(tracker.replace(String.valueOf(oldItem.getId()), newItem), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
