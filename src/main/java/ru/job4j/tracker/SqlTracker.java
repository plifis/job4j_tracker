package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    /**
     * Метод инициализирует переменную подключения информацией для подклюения
     * url, user, password
     */

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    /**
     * Метод добавляет задачу
     * @param item задача для добавления(идентификатор, имя)
     * @return добавленая задача
     */

    @Override
    public Item add(Item item) {
        String strQuery = "INSERT INTO items (name) values (?) ";
       try (PreparedStatement statement = cn.prepareStatement(strQuery, Statement.RETURN_GENERATED_KEYS)) {
           statement.setString(1, item.getName());
           statement.executeUpdate();
           ResultSet set = statement.getGeneratedKeys();
           set.next();
           item.setId(set.getString(1));
       } catch (Exception e) {
           e.printStackTrace();
       }
        return item;
    }

    /**
     * Обновление описание задачи в БД
     * @param id идентификатор обновляемой задача
     * @param item задача НА которую требуется заменить
     * @return результат добавления задача (истина\ложь), зависит от возвращаемого значения в statement.executeUpdate()
     */

    @Override
    public boolean replace(String id, Item item) {
        String strQuery = "UPDATE items SET name = ? WHERE id = ?";
        boolean result = false;
        try (PreparedStatement statement = cn.prepareStatement(strQuery)) {
            statement.setString(1, item.getName());
            statement.setInt(2, Integer.parseInt(id));
            int i = statement.executeUpdate();
            if (i > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Удаление задачи по идентификатору
     * @param id идентификатор удаляемой задачи
     * @return результат добавления задача (истина\ложь)
     */

    @Override
    public boolean delete(String id) {
        String strQuery = "DELETE FROM items WHERE id =  ?";
        boolean result = false;
        try (PreparedStatement statement = cn.prepareStatement(strQuery)) {
            statement.setInt(1, Integer.parseInt(id));
            int i = statement.executeUpdate();
            if (i > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Показать все имеющиеся в БД задачи
     * @return список задач (идентификатор, описание)
     */

    @Override
    public List<Item> findAll() {
        StringBuilder builder = new StringBuilder("SELECT * FROM items");
        return this.findList(builder.toString());
    }

    /**
     * Список задач по описанию
     * @param key описание (имя) задачи
     * @return список найденных задач
     */

    @Override
    public List<Item> findByName(String key) {
        StringBuilder builder = new StringBuilder("SELECT * FROM items WHERE name = '" + key + "'");
        return this.findList(builder.toString());
    }

    /**
     * Поиск задачи по идентификатору
     * @param id идентификатор задачи
     * @return задача (идентификатор, описание)
     */

    @Override
    public Item findById(String id) {
        StringBuilder builder = new StringBuilder("SELECT * FROM items WHERE id = '" + id + "'");
        Item item = null;
        try (PreparedStatement statement = cn.prepareStatement(builder.toString())) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                item = new Item(result.getString("name"));
                item.setId(result.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Вспомогательный метод для поиска по готовому запросу
     * @param query готовый запрос SQL в виде String
     * @return список найденных задач
     */

    private List<Item> findList(String query) {
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Item item = new Item(result.getString("name"));
                item.setId(result.getString("id"));
                itemList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }
}