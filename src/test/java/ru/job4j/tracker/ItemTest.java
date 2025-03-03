package ru.job4j.tracker;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void whenTestCompareToItemThenAToZ() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("Nikita"));
        list.add(new Item("Alexey"));
        list.add(new Item("Tom"));
        Collections.sort(list);
        List<Item> expected = new ArrayList<>();
        expected.add(new Item("Alexey"));
        expected.add(new Item("Nikita"));
        expected.add(new Item("Tom"));
        assertEquals(list.toString(), expected.toString());
    }
    @Test
    public void whenTestCompareToItemThenZToA() {
        List<Item> list = new ArrayList<>();
        list.add(new Item("Nikita"));
        list.add(new Item("Alexey"));
        list.add(new Item("Tom"));
        Collections.sort(list, new SortItemFromZToA());
        List<Item> expected = new ArrayList<>();
        expected.add(new Item("Tom"));
        expected.add(new Item("Nikita"));
        expected.add(new Item("Alexey"));
        assertEquals(list.toString(), expected.toString());

    }
}