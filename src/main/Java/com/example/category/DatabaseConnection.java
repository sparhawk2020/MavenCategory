package com.example.category;

import org.springframework.stereotype.Service;

import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DatabaseConnection
{

    public java.sql.Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");



        java.sql.Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");




        return con1;

    }

}
