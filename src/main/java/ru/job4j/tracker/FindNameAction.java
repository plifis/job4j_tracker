package ru.job4j.tracker;

public class FindNameAction implements UserAction {
    @Override
    public String name() {
        return "Find name";
    }
    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter Name for find");
        Item[] items = tracker.findByName(name);
            if (items.length > 0) {
                for (Item item : items) {
                    System.out.println(item);
                }
            } else {
                System.out.println(String.format("Items with name: %s not found", name));
            }
    return true;
    }
}
