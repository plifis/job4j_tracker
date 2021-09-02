package ru.job4j.tracker.di;

public class StartUI {

    private Store store;
    private ConsoleInput input;

    public StartUI(Store store, ConsoleInput input) {
        this.store = store;
        this.input = input;
    }

    public void add(String value) {
        store.add(value);
    }

    public void askInput(String question) {
        input.askStr(question);
    }

    public void printStore() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}