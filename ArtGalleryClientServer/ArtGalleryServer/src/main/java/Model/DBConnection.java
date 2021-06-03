package Model;


import java.io.IOException;
import java.io.Serializable;
import java.sql.*;

public class DBConnection {
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/galerie";
    private static final String uid = "root";
    private static final String pw = "";


    private static DBConnection singleInstance = new DBConnection();

    private DBConnection() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance(){
        return singleInstance;
    }


    private static Connection createConnection() throws IOException, SQLException {
        return DriverManager.getConnection(url,uid,pw);
    }

    public static Connection getConnection() throws IOException, SQLException {
        return singleInstance.createConnection();
    }


    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}