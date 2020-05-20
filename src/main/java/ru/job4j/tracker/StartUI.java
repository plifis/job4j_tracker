package ru.job4j.tracker;


import java.util.Scanner;

public class StartUI {

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            String msg = "Select";
            int select = Integer.valueOf(input.askStr(msg));
            if (select == 0) {
                System.out.println("=== Create a new Item ====");
                msg = "Enter name: ";
                String name = input.askStr(msg);
                Item item = new Item(name);
                tracker.add(item);
            } else if (select == 1) {
                Item[] items = tracker.findAll();
                for (Item item : items) {
                    System.out.println(item);
                }
            } else if (select == 2) {
                msg = "Enter Id Item's for edit";
                String id = input.askStr(msg);
                msg = "Enter new name";
                String name = input.askStr(msg);
                Item item = new Item(name);
                if (tracker.replace(id, item)) {
                    System.out.println("Done.");
                } else {
                    System.out.println("Error");
                }
            } else if (select == 3) {
                msg = "Enter Id for delete";
                String id = input.askStr(msg);
                if (tracker.delete(id)) {
                    System.out.println("Done.");
                } else {
                    System.out.println("Error");
                }
            } else if (select == 4) {
                msg = "Enter Id for find";
                String id = input.askStr(msg);
                Item item = tracker.findById(id);
                if ((item != null)) {
                    System.out.println(item);
                } else {
                    System.out.println("Id not found");
                }
            } else if (select == 5) {
                msg = "Enter Name for find";
                String name = input.askStr(msg);
                Item[] items = tracker.findByName(name);

                for (Item item : items) {
                    if (item != null) {
                        System.out.println(item);
                    } else {
                        System.out.println("Id not found");
                    }
                }
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