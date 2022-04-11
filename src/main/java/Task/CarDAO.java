package Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarDAO<T> implements DAO<T> {

    private final JDBCConnection jdbc;

    public CarDAO(JDBCConnection jdbc) {
        this.jdbc = jdbc;
    }

    private static final String INSERT = "INSERT INTO %s (id, name, color, price) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE %s SET name = ?, color = ?, price = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM %s WHERE id = ?";
    private static final String SELECT = "SELECT * FROM Car WHERE id = %d";


    @Override
    public void insert(Object odj) {
        String SQL = String.format(INSERT, Reflection.getTable(odj));
        try (Connection conn = jdbc.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            preparedStatement.setInt(1, Reflection.getValue("id", odj));
            preparedStatement.setString(2, Reflection.getValue("name", odj));
            preparedStatement.setString(3, Reflection.getValue("color", odj));
            preparedStatement.setInt(4, Reflection.getValue("price", odj));
            preparedStatement.executeUpdate();
            conn.commit();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object odj) {
        String SQL = String.format(UPDATE, Reflection.getTable(odj));
        try (Connection conn = jdbc.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            preparedStatement.setString(1, Reflection.getValue("name", odj));
            preparedStatement.setString(2, Reflection.getValue("color", odj));
            preparedStatement.setInt(3, Reflection.getValue("price", odj));
            preparedStatement.setInt(4, Reflection.getValue("id", odj));
            preparedStatement.executeUpdate();
            conn.commit();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object odj) {
        String SQL = String.format(DELETE, Reflection.getTable(odj));
        try (Connection conn = jdbc.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            preparedStatement.setInt(1, Reflection.getValue("id", odj));
            preparedStatement.executeUpdate();
            conn.commit();
        } catch (SQLException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public T select(Integer i) {
        String SQL = String.format(SELECT, i);
        Car car = null;
        try (Connection conn = jdbc.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            conn.setAutoCommit(false);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                car = new Car();
                car.setId(resultSet.getInt(1));
                car.setName(resultSet.getString(2));
                car.setColor(resultSet.getString(3));
                car.setPrice(resultSet.getInt(4));
            }
            resultSet.close();
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) car;
    }
}
