package ru.job4j.tracker;

import org.junit.Test;

import java.util.*;

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
        assertEquals(list, expected);
    }
    @Test
    public void whenTestCompareToItemThenZToA() {
        List<Item> list = Arrays.asList(
                new Item("Nikita"),
                new Item("Alexey"),
                new Item("Tom")

        );

        list.sort(new SortItemFromZToA());
        List<Item> expected = new ArrayList<>();
        expected.add(new Item("Tom"));
        expected.add(new Item("Nikita"));
        expected.add(new Item("Alexey"));
        assertEquals(list, expected);

    }
}