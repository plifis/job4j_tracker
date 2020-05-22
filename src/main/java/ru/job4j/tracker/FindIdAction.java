package ru.job4j.tracker;

public class FindIdAction implements UserAction {
    @Override
    public String name() {
        return "Find Id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Enter Id for find");
        Item item = tracker.findById(id);
        if ((item != null)) {
            System.out.println(item);
        } else {
            System.out.println("Id not found");
        }
    return true;
    }
}
