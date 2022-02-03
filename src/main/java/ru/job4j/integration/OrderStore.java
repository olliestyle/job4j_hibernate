package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderStore {
    private final BasicDataSource pool;

    public OrderStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Order save(Order order) {
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO orders (name, description, created) VALUES (?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, order.getName());
                ps.setString(2, order.getDescription());
                ps.setObject(3, order.getCreated());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getInt("id"));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public Collection<Order> findAll() {
        List<Order> toReturn = new ArrayList<>();
        try (Connection connection = pool.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    toReturn.add(new Order(rs.getInt("id"),
                                            rs.getString("name"),
                                            rs.getString("description"),
                                            rs.getTimestamp("created")));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public Order findById(int id) {
        Order toReturn = null;
        try (Connection connection = pool.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders where id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                toReturn = new Order(rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getTimestamp("created"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public Order findByName(String name) {
        Order toReturn = null;
        try (Connection connection = pool.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                toReturn = new Order(rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("description"),
                                    rs.getTimestamp("created"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public boolean update(Order update) {
        int rows = 0;
        try (Connection connection = pool.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "UPDATE orders set name = ?, description = ? where id = ?")) {
            ps.setString(1, update.getName());
            ps.setString(2, update.getDescription());
            ps.setInt(3, update.getId());
            rows = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows != 0;
    }

}
