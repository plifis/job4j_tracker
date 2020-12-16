package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MemTracker {
    /**
     * Массив для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();
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
        items.add(item);
        return item;
    }

    /**
     * Метод замены заявки в массиве с сохранением идентфиикатора
     * @param id идентификатор заявки, которую требуется заменить
     * @param item заявка, которую требуется добавить на место заменённой
     * @return возвращается истинну, если замена удачна
     */

    public boolean replace(String id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            item.setId(id);
            this.items.set(index, item);
        }
        return rsl;
    }
    /**
     * Метод удаляет заявку из массива и смещает его влево
     * @param id идентификатор заявки
     * @return возвращается true, если удаление успешно
     */
    public boolean delete(String id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            items.remove(index);
//            System.arraycopy(items, index++, items, index, position - index);
 //           position--;
        }
        return rsl;
    }

    public List<Item> findAll() {
        return items;
    }

    public List<Item> findByName(String key) {
        List<Item> itemsName = new ArrayList<Item>(); //Item[this.items.size()];
//        int size = 0;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getName().equals(key)) {
                itemsName.add(this.items.get(i));
//                size++;
            }
        }
//        itemsName = Arrays.copyOf(itemsName, size);
        return itemsName;
    }

    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }
        /**
         * Метод генерирует уникальный ключ для заявки.
         * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
         * @return Уникальный ключ.
         */
        private String generateId() {
            Random rm = new Random();
            return String.valueOf(rm.nextLong() + System.currentTimeMillis());
        }
    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }    }
