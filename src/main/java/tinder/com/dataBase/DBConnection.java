package tinder.com.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String path = "jdbc:postgres://kjgjkuhhmgaerl:96d311194d5419faf690958792ea2dbf672826e3377783239fb4330126002cf9@ec2-54-246-92-116.eu-west-1.compute.amazonaws.com:5432/dauap40l27k3ma";
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
