package ru.job4j.tracker;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "Show All Items";
    }
    @Override
    public boolean execute(Input input, Store memTracker) {
        for (Item item : memTracker.findAll()) {
            System.out.println(String.format("%s %s", item.getId(), item.getName()));
        }
        return true;
    }

}
