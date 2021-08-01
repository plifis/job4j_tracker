package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HbmTrackerTest {
    @Test
    public void addItemAndGet() {
        HbmTracker tracker = new HbmTracker();
        Item one = new Item("one", "descriptionOne");
        Item two = new Item("two", "descriptionTwo");
        tracker.add(one);
        tracker.add(two);
        assertThat(tracker.findAll().get(0), is(one));
    }

    @Test
    public void findByName() {
        HbmTracker tracker = new HbmTracker();
        Item one = new Item("one", "descriptionOne");
        Item two = new Item("two", "descriptionTwo");
        tracker.add(one);
        tracker.add(two);
        assertThat(tracker.findByName("one").get(0).getDescription(), is(one.getDescription()));
    }

    @Test
    public void findById() {
        HbmTracker tracker = new HbmTracker();
        Item one = new Item("one", "descriptionOne");
        Item two = new Item("two", "descriptionTwo");
        tracker.add(one);
        tracker.add(two);
        assertThat(tracker.findById("2").getDescription(), is(two.getDescription()));

    }

    @Test
    public void whenReplaceAndGet() {
        HbmTracker tracker = new HbmTracker();
        Item one = new Item("one", "descriptionOne");
        Item two = new Item("two", "descriptionTwo");
        tracker.add(one);
        tracker.add(two);
        Item update = new Item("update", "descriptionUpdate");
        tracker.replace("2", update);
        assertThat(tracker.findById("2"), is(update));
    }

    @Test
    public void whenAddThreeItemAndDeleteFirst() {
        HbmTracker tracker = new HbmTracker();
        Item one = new Item("one", "descriptionOne");
        Item two = new Item("two", "descriptionTwo");
        Item three = new Item("three", "descriptionThree");
        tracker.add(one);
        tracker.add(two);
        tracker.add(three);
        tracker.delete("1");
        assertThat(tracker.findAll(), is(List.of(two, three)));
    }

}