package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];
    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    /**
     * Метод добавления заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        items[position++] = item;
        return item;
    }

    public Item[] findAll() {
        Item[] itemsWithoutNull = new Item[items.length];
        int size = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                itemsWithoutNull[size] = items[i];
                size++;
            }
        }
        itemsWithoutNull = Arrays.copyOf(itemsWithoutNull, size);
        return itemsWithoutNull;
    }

    public Item[] findByName(String key) {
        Item[] itemsName = new Item[this.items.length];
        int size = 0;
        for (int i = 0; i < position; i++) {
            if (this.items[i].getName().equals(key)) {
                itemsName[size] = this.items[i];
            }
        }
        itemsName = Arrays.copyOf(itemsName, size + 1);
        return itemsName;
    }

    public Item findById(String id) {
        int i;
        for (i = 0; i < position; i++) {
            if (this.items[i].getId().equals(id)) {
                break;
            }
        }
        return this.items[i];
    }
        /**
         * Метод генерирует уникальный ключ для заявки.
         * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
         * @return Уникальный ключ.
         */
        private String generateId () {
            Random rm = new Random();
            return String.valueOf(rm.nextLong() + System.currentTimeMillis());
        }
    }
