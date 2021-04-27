package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "Edit Item";
    }
    @Override
    public boolean execute(Input input, Store memTracker) {
        int id = input.askInt("Enter Id Item's for edit");
        String name = input.askStr("Enter new name");
        Item item = new Item(name);
        if (memTracker.replace(String.valueOf(id), item)) {
            System.out.println("Done");
        } else {
            System.out.println("Error");
        }
        return true;
    }
}
