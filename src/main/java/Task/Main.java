package Task;

import liquibase.pro.packaged.T;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CarDAO<T> CarDAO = new CarDAO<>(new JDBCConnection());

/*        CarDAO.insert(new Car(1, "BMW", "RED", 1000));
        CarDAO.insert(new Car(2, "LADA", "BLUE", 500));
        CarDAO.insert(new Car(3, "GEELY", "YELLOW", 100));

        CarDAO.update(new Car(1, "merc", "orange", 1200));*/
        System.out.println(CarDAO.select(3));

    }
}
