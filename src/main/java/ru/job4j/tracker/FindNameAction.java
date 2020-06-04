package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;

public class FindNameAction implements UserAction {
    @Override
    public String name() {
        return "Find name";
    }
    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter Name for find");
        List<Item> items = Arrays.asList(tracker.findByName(name));
            if (items.size() > 0) {
                for (Item item : items) {
                    System.out.println(String.format("%s %s", item.getId(), item.getName()));
                }
            } else {
                System.out.println(String.format("Items with name: %s not found", name));
            }
    return true;
    }
}
