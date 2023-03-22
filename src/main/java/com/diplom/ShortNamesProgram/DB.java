package com.diplom.ShortNamesProgram;

import java.sql.*;

public class DB {

    private  final String HOST = "localhost";
    private  final String PORT = "6033";
    private  final String DB_NAME = "app_db";
    private  final String LOGIN = "db_user";
    private  final String PASS = "db_user_pass";


    private Connection dbConn = null;
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public void isConnected() throws SQLException, ClassNotFoundException {
        dbConn = getDbConnection();
        System.out.println(dbConn.isValid(1000));
    }


    public void addToBase(String short_Name, String long_Name) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO `shorts` (`short_name`, `long_name`) VALUES (?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, short_Name);
        prSt.setString(2, long_Name);
        prSt.executeUpdate();
    }
    public void createTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName
                + " (id INT AUTO_INCREMENT PRIMARY KEY, short_name VARCHAR(30), long_name VARCHAR(150))" +
                " ENGINE=MYISAM";

        Statement statement = dbConn.createStatement();
        statement.executeUpdate(sql);
    }
    public boolean getShort(String shortName) throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM `shorts` WHERE `short_name`= ? ";


        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, shortName);

        ResultSet res = prSt.executeQuery();
        return res.next();

    }
    public ResultSet getShorts() throws SQLException, ClassNotFoundException{
        String sql = "SELECT * FROM `shorts`";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        ResultSet res = prSt.executeQuery();
        return res;

    }

}


