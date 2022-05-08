package com.example.category;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public interface DatabaseInterface {


    public void add(Category cat) throws ClassNotFoundException, SQLException;
    public Category edit(Category cat, String catcode) throws SQLException, ClassNotFoundException;
    public void delete(String catcode) throws SQLException;
    public List<Category> display() throws ClassNotFoundException, SQLException;

}