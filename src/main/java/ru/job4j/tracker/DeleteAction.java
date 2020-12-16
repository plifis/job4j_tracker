package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "Delete Item";
    }
    @Override
    public boolean execute(Input input, Store memTracker) {
        String id = input.askStr("Enter Id for delete");
        if (memTracker.delete(id)) {
            System.out.println("Done.");
        } else {
            System.out.println("Error");
        }
        return true;
    }
}
