package tinder.com.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private String path = "jdbc:mysql://danit.cukm9c6zpjo8.us-west-2.rds.amazonaws.com:3306/fs5";
    private String username = "fs5_user";
    private String password = "bArceloNa";
    private Connection connection;

    private Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(path, username, password);
    }

    public Connection connection() {
        if (connection == null) {
            try {
                connection = connect();
            } catch (SQLException e) {
                throw new IllegalStateException("DbConnection went wrong ", e);
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        return this.connection;
    }
}
