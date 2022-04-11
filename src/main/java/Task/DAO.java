package Task;

import java.sql.SQLException;

public interface DAO<T>  {

    void insert(Object odj) throws SQLException, IllegalAccessException, ClassNotFoundException;

    void update(Object odj) throws SQLException, IllegalAccessException, ClassNotFoundException;

    void delete(Object odj) throws SQLException, IllegalAccessException, ClassNotFoundException;

    T select(Integer i) throws SQLException, ClassNotFoundException;
}
