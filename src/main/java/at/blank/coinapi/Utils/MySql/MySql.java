package at.blank.coinapi.Utils.MySql;


import at.blank.coinapi.Coinapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySql {
    public static String host;
    public static String port;
    public static String database;
    public static String username;
    public static String password;
    public static Connection connection;

    public static boolean isConnected() {
        if (connection == null) {
            return false;
        }
        try {
            return !connection.isClosed();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static void update(String qry) {
        if (isConnected()) {
            try {
                connection.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Coinapi.getMySQL().connect();
            update(qry);
        }
    }

    public static ResultSet getResult(String qry) {
        if (isConnected()) {
            try {
                return connection.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                createTablesIfNotExit();

            } catch (SQLException throwables2) {
                throwables2.printStackTrace();
                return;
            }

        }
    }

    public void execute(String cmd) {
        try {
            connection.createStatement().executeUpdate(cmd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet executeQuery(String cmd) {
        try {
            return connection.createStatement().executeQuery(cmd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void createTablesIfNotExit() {
        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS coins(UUID VARCHAR(100), Balance DOUBLE)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS taller(UUID VARCHAR(100), Balance DOUBLE)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS rangUpgrade(UUID VARCHAR(100), upgrades BIGINT(100))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS bannedplayers(Spielername VARCHAR(100), UUID VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS mutedplayers(Spielername VARCHAR(100), UUID VARCHAR(100), Ende VARCHAR(100), Grund VARCHAR(100))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS clans(Owner VARCHAR(100), Name VARCHAR(100), Tag VARCHAR(100), Members LONGTEXT, Moderators LONGTEXT, Balance BIGINT(100), Plot VARCHAR(100))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
