package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {

    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = Integer.valueOf(scanner.nextLine());
            if (select == 0) {
                System.out.println("=== Create a new Item ====");
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.add(item);
            } else if (select == 1) {
                Item[] items = tracker.findAll();
                for (Item item : items) {
                    System.out.println(item.toString());
                }
            } else if (select == 2) {
                System.out.println("Enter Id Item's for edit");
                String id = scanner.nextLine();
                System.out.println("Enter new name");
                String name = scanner.nextLine();
                Item item = new Item(name);
                if (tracker.replace(id, item)) {
                    System.out.println("Done.");
                } else {
                    System.out.println("Error");
                }
            } else if (select == 3) {
                System.out.println("Enter Id for delete");
                String id = scanner.nextLine();
                if (tracker.delete(id)) {
                    System.out.println("Done.");
                } else {
                    System.out.println("Error");
                }
            } else if (select == 4) {
                System.out.println("Enter Id for find");
                String id = scanner.nextLine();
                Item item = tracker.findById(id);
                System.out.println(item.toString());
            } else if (select == 5) {
                System.out.println("Enter Name for find");
                String name = scanner.nextLine();
                Item[] items = tracker.findByName(name);
                for (Item item : items) {
                    System.out.println(item.toString());
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
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}