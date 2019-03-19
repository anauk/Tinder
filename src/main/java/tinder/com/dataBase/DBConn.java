package tinder.com.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private String path = "jdbc:postgresql://localhost:5432/postgres";
    private String name = "postgres";
    private String password = "secret";
    private Connection connection;

    private Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(path, name, password);
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
