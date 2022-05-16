package com.example.category;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Test2 {
    @Mock
    JdbcTemplate mockJdbcTemplate;

    Catdao dao;

    @Mock
    private RowMapper<Category> rowMapper;

    String catcode;
    String catdesc;

    @Before
    public void init() {
        dao= new Catdao(mockJdbcTemplate);

         catcode = "1234567";
         catdesc = "Toys";

    }


    @Test
    public void Addcat() {


        Category toys = new Category();
        toys.setCatcode(catcode);
        toys.setCatdesc(catdesc);


       when(mockJdbcTemplate.update(anyString(), anyString(), anyString())).thenReturn(1);


       dao.insertData(toys);


        ArgumentCaptor<Object> varArgs = ArgumentCaptor.forClass(Object.class);

        ArgumentCaptor<String> strArg = ArgumentCaptor.forClass(String.class);

        //Verify update method was called and capture args
        verify(mockJdbcTemplate).update(strArg.capture(),varArgs.capture(),varArgs.capture());



        //Verify 1st dynamic argument was the catcode
        assertEquals(catcode, varArgs.getAllValues().get(0));

        assertEquals(catdesc, varArgs.getAllValues().get(1));


    }


     @Test
    public void editcat() {

        String catcode2 = "1234567";
        String catdesc2 = "Cleaning";

         Category clean = new Category();

         clean.setCatcode(catcode2);
         clean.setCatdesc(catdesc2);

         when(mockJdbcTemplate.update(anyString(), anyString(), anyString(), anyString())).thenReturn(1);


         dao.EditData(clean,catcode);


        ArgumentCaptor<Object> varArgs2 = ArgumentCaptor.forClass(Object.class);

        ArgumentCaptor<String> strArg2 = ArgumentCaptor.forClass(String.class);

        //Verify update method was called and capture args
        verify(mockJdbcTemplate).update(strArg2.capture(),varArgs2.capture(),varArgs2.capture(), varArgs2.capture());



        //Verify 1st dynamic argument was the catcode
        assertEquals(catcode2, varArgs2.getAllValues().get(0));



    }


    @Test
    public void display() throws SQLException, ClassNotFoundException {

       Category f = new Category();

       f.setCatcode("101");
       f.setCatdesc("School");

       List<Category> ll = new ArrayList<Category>();

       ll.add(f);

        when(mockJdbcTemplate.query(anyString(),any(RowMapper.class))).thenReturn(ll);

        List<Category> ss1 = dao.display();

        assertEquals(1,ss1.size() );

    }


    @Test
    public void itemtest(){

        List<Map<String, Object>> listBeforeGroup = new ArrayList<Map<String, Object>>();

        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("catcode", "101");
        m1.put("itemcode", "111");
        m1.put("itemdesc", "Planner");

        listBeforeGroup.add(m1);
        when(mockJdbcTemplate.queryForList(anyString(), anyString())).thenReturn(listBeforeGroup);

        List<Map<String, Object>> ss1 = dao.getitem("101");

        assertEquals(listBeforeGroup,ss1);

    }


    @Test
    public void findbyid2(){


        List<Map<String, Object>> listBeforeGroup = new ArrayList<Map<String, Object>>();

        Map<String, Object> m1 = new HashMap<String, Object>();
        m1.put("catcode", "101");
        m1.put("catdesc", "Planner");

        listBeforeGroup.add(m1);
        when(mockJdbcTemplate.queryForList(anyString(), anyString())).thenReturn(listBeforeGroup);

        List<Map<String, Object>> ss1 = dao.getcat("101");

        assertEquals(listBeforeGroup,ss1);

    }



    @Test
    public void delete1(){


        when(mockJdbcTemplate.update(anyString(), anyString())).thenReturn(1);

        dao.deleteData("101");


        verify(mockJdbcTemplate, times(1)).update(anyString(), anyString());

        ArgumentCaptor<Object> varArgs3 = ArgumentCaptor.forClass(Object.class);

        ArgumentCaptor<String> strArg3 = ArgumentCaptor.forClass(String.class);

        //Verify update method was called and capture args
        verify(mockJdbcTemplate).update(strArg3.capture(),varArgs3.capture());



        //Verify 1st dynamic argument was the catcode
        assertEquals("101", varArgs3.getAllValues().get(0));


    }

}