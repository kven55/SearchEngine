package com.Accio.SearchEngine;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    static Connection connection;

    public static Connection getConnection()
    {
        if(connection != null)
            return connection;

        String user = "root";
        String pass = "LamBo5525#";
        String dbName = "SearchEngineDb";

        return getConnection(user , pass , dbName); //function overloading
    }

    public static Connection getConnection(String user , String pass , String dbName)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName , user , pass);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return connection;
    }
}
