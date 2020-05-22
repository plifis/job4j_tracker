//package ru.job4j.tracker;
//
//import org.junit.Test;
//
//import java.util.Scanner;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
//
//
//public class StartUITest {
//    @Test
//    public void whenAddItem() {
//        String[] answers = {"Fix PC"};
//        Input input = new StubInput(answers);
//        Tracker tracker = new Tracker();
//        StartUI.createItem(input, tracker);
//        Item created = tracker.findAll()[0];
//        Item expected = new Item("Fix PC");
//        assertThat(created.getName(), is(expected.getName()));
//    }
//    @Test
//    public void whenReplaceItem() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("new item");
//        tracker.add(item);
//        String[] answers = {
//                item.getId(), // id сохраненной заявки в объект tracker.
//                "replaced item"
//        };
//        StartUI.editItem(new StubInput(answers), tracker);
//        Item replaced = tracker.findById(item.getId());
//        assertThat(replaced.getName(), is("replaced item"));
//    }
//    @Test
//    public void whenDeleteItem() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("Name for delete");
//        tracker.add(item);
//        String [] answers = {item.getId()};
//        StartUI.itemDelete(new StubInput(answers), tracker);
//        Item deleted = tracker.findById(item.getId());
//        assertNull(null, tracker.findById(item.getId()));
//   //     assertThat(deleted, is(nullValue()));
//    }
//}
