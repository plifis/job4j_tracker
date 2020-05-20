package ru.job4j.tracker;


public class StartUI {
    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }
    public static void showAllItem(Input input, Tracker tracker) {
        Item[] items = tracker.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
    }
    public static void editItem(Input input, Tracker tracker) {
        String id = input.askStr("Enter Id Item's for edit");
        String name = input.askStr("Enter new name");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            System.out.println("Done.");
        } else {
            System.out.println("Error");
        }
    }
    public static void itemDelete(Input input, Tracker tracker) {
        String id = input.askStr("Enter Id for delete");
        if (tracker.delete(id)) {
            System.out.println("Done.");
        } else {
            System.out.println("Error");
        }
    }
    public static void idFind(Input input, Tracker tracker) {
        String id = input.askStr("Enter Id for find");
        Item item = tracker.findById(id);
        if ((item != null)) {
            System.out.println(item);
        } else {
            System.out.println("Id not found");
        }
    }
    public static void nameFind(Input input, Tracker tracker) {
        String name = input.askStr("Enter Name for find");
        Item[] items = tracker.findByName(name);
        for (Item item : items) {
            if (item != null) {
                System.out.println(item);
            } else {
                System.out.println("Id not found");
            }
        }
    }
    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = Integer.valueOf(input.askStr("Select"));
            if (select == 0) {
            StartUI.createItem(input, tracker);
            } else if (select == 1) {
              StartUI.showAllItem(input, tracker);
            } else if (select == 2) {
                StartUI.showAllItem(input, tracker);
            } else if (select == 3) {
                StartUI.itemDelete(input, tracker);
            } else if (select == 4) {
                StartUI.idFind(input, tracker);
            } else if (select == 5) {
               StartUI.nameFind(input, tracker);
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu." + System.lineSeparator()
                + "0. Add new Item" + System.lineSeparator()
                + "1. Show all items" + System.lineSeparator()
                + "2. Edit item" + System.lineSeparator()
                + "3. Delete item" + System.lineSeparator()
                + "4. Find item by Id" + System.lineSeparator()
                + "5. Find items by name" + System.lineSeparator()
                + "6. Exit Program" + System.lineSeparator()
                + "Select:");
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}