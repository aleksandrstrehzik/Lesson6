package Task;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class CarDAOTest {

    JDBCConnection jdbs = new JDBCConnection();
    CarDAO carDAO = new CarDAO<>(jdbs);

    private static final String INSERT = "INSERT INTO %s (id, name, color, price) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE %s SET name = ?, color = ?, price = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM %s WHERE id = ?";
    private static final String SELECT = "SELECT * FROM Car WHERE id = %d";

    @BeforeAll
    public void cleanTable(){
        try (Connection conn = jdbs.connect();
        PreparedStatement preparedStatement = conn.prepareStatement("TRUNCATE TABLE Car")) {
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    @Test
    public void insert() {
        Car expected = new Car(1, "LADA", "WHITE", 100);
        carDAO.insert(expected);
        Object actual = carDAO.select(1);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Car MA3 = new Car(2, "MA3", "GREY", 15000);
        carDAO.update(MA3);
        Car KAMA3 = new Car(2, "KAMA3", "GREY", 17000);
        carDAO.update(KAMA3);
        Object actual = carDAO.select(2);
        assertNotEquals(MA3, actual);
    }

    @Test
    public void delete() {
        Car car = new Car(3, "OPEL", "RED", 2000);
        carDAO.delete(car);
        Object select = carDAO.select(3);
        assertNull(select);
    }

    @Test
    public void select() {
        Car expected = new Car(4, "BMW", "YELLOW", 5000);
        carDAO.insert(expected);
        Object select = carDAO.select(4);
        assertEquals(expected, select);
    }
}