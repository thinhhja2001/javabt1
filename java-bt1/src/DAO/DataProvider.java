package DAO;

import java.sql.*;

public class DataProvider {
    static String url = "jdbc:postgresql://localhost:5432/postgres";
    static String username = "postgres";
    static String password = "talavua5122001";

    public DataProvider() {
    }

    /**
     * This method is used in execute the SELECT query in database
     *
     * @param query represent for the query you want to execute
     *              Print stacktrace if there is an error executing the query
     * @return list of data in ResultSet
     */
    public static ResultSet executeQuery(String query) {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used in execute the INSERT, UPDATE, DELETE query in database
     *
     * @param query represent for the query you want to execute
     *              Print stacktrace if there is an error executing the query
     * @return 1 if the query is executed successfully
     * 0 if the query is fail to be executed
     */
    public static int executeNonQuery(String query) {
        int result;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
            return result;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            result = 0;
        }
        return result;
    }
}
