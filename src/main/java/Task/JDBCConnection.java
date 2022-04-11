package Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCConnection {

    public  Connection connect() throws SQLException, ClassNotFoundException {
        ResourceBundle rb = ResourceBundle.getBundle("lesson6");
        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");
        return DriverManager.getConnection(url, user, password);
    }


}
