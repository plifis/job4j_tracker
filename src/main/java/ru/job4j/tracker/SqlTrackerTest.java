package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SqlTrackerTest {

    @Test
    public void findByName() {
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        Item item = new Item("Nikita");
        item.setId(tracker.add(item).getId());
        List<Item> expected  = Collections.singletonList(item);
        assertEquals(expected, tracker.findByName("Nikita"));
        tracker.delete(item.getId());
    }

    @Test
    public void findById() {
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        Item expected = new Item("Roman");
        expected.setId("3");
        assertEquals(expected, tracker.findById("3"));
    }

    @Test
    public void findAll() {
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        List<Item> exp = Arrays.asList( new Item("Roman"),
                                        new Item("Alexey"),
                                        new Item("Ivan"));
        exp.get(0).setId("3");
        exp.get(1).setId("2");
        exp.get(2).setId("1");
        assertThat(tracker.findAll(), is(exp));
    }

    @Test
    public void whenDelete1of4ThenReturn3() {
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        Item item = new Item("Luke");
        tracker.add(item);
        List<Item> idList = tracker.findByName(item.getName());
        String id = idList.get(0).getId();
        assertThat(tracker.delete(id), is(true));
        assertNull(tracker.findById(id));
    }

    @Test
    public void whenReplaceBillThenLil() {
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        Item item = new Item("Bill");
        tracker.add(item);
        List<Item> idList = tracker.findByName(item.getName());
        String id = idList.get(0).getId();
        Item expItem = new Item("Lil");
        expItem.setId(id);
        tracker.replace(id, expItem);
        assertEquals(expItem.getName(), tracker.findById(id).getName());
        tracker.delete(id);
    }
}