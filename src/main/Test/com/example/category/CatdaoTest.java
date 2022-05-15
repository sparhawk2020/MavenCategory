package com.example.category;

import groovy.transform.NamedParam;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class CatdaoTest {


    @Mock
    Catdao cdao;

    @InjectMocks
    Catdao dao;

    @Mock
    private Category category;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;
  /*  @Before
    public void setUp() {
        //catimpl = spy(new Catdao());
        ReflectionTestUtils.setField(catimpl, "jdbcTemplate", jdbcTemplate);


    }*/



    @Before
    public void init(){

        MockitoAnnotations.openMocks(this);

    }


    @Test
    void addData() {

        category.setCatcode("101");
        category.setCatdesc("Toys");

        cdao.insertData(category);
        Mockito.verify(cdao, times(1)).insertData(category);


    }

    @Test
    void deleteData() {

        final String QUERY = "delete from category where catcode = ?";

        //Category cc = new Category();
        category.setCatcode("102");


        when(this.jdbcTemplate.update(QUERY, category.getCatcode())).thenReturn(1);

      //  assertTrue(this.cdao.deleteData("102");
        //        user.setID("other-ID");
        //        assertFalse(this.usersService.delete(user));

    }



    @Test
    void editData() {



    }

    @Test
    void display() throws SQLException, ClassNotFoundException {


 /*       Category cat = new Category();

        cat.setCatcode("101");
        cat.setCatdesc("Toys");
*/







        Mockito.when(jdbcTemplate.query(
                        ArgumentMatchers.anyString(), ArgumentMatchers.any(RowMapper.class)))
                .thenAnswer((invocation) -> {

                    RowMapper<Category> rowMapper = (RowMapper<Category>) invocation.getArgument(1);
                    ResultSet rs = Mockito.mock(ResultSet.class);

                    // Mock ResultSet to return two rows.
                    Mockito.when(rs.getString(ArgumentMatchers.eq("catcode")))
                            .thenReturn("506", "400");
                    Mockito.when(rs.getString(ArgumentMatchers.eq("catdesc")))
                            .thenReturn("Jim Carrey", "John Travolta");

                    List<Category> cats = new ArrayList<>();
                    cats.add(rowMapper.mapRow(rs, 0));
                    cats.add(rowMapper.mapRow(rs, 1));

                    return cats;
                });

        List<Category> cca = cdao.display();




        // Assert First Row
        assertFirstUser(cca.get(0));


    }

    public void assertFirstUser(Category cc) {
        Assert.assertEquals(String.valueOf("506"), cc.getCatcode());
        Assert.assertEquals("Jim Carrey", cc.getCatcode());

    }


    @Test
    void getcatbyid() throws SQLException {





        category.setCatcode("101");
        category.setCatdesc("Toys");

        Category r = new Category();

        r.setCatcode("101");
        r.setCatdesc("Toys");


        when(rs.first()).thenReturn(true);
        when(rs.getString(1)).thenReturn(r.getCatcode());
        when(rs.getString(2)).thenReturn(r.getCatdesc());
        when(stmt.executeQuery()).thenReturn(rs);

        cdao.insertData(r);

        Optional<Category> k = cdao.getcatbyid("101");


        assertEquals(category,k);

    }



    @Test
    void getitem() {

        JdbcTemplate mockTemplate = Mockito.mock(JdbcTemplate.class);

        List<Map<String, Object>> mockResult = new ArrayList<>();

        Mockito.when(mockTemplate.queryForList(Mockito.anyString(), ArgumentMatchers.<Object>any())).thenReturn(mockResult);
        // Alternatively:
        // when(mockTemplate.queryForList(anyString(), Mockito.<Object>any())).thenReturn(mockResult);

        String query = "SELECT * from items where catcode = ?";
        Object[] params = new Object[]{1};

       // List<Map<String, Object>> returnedResult = mockTemplate.queryForList(query, params);

        //List<Map<String, Object>> returnedResult = dao.queryForList(query, params);

//        Assert.assertThat(returnedResult, CoreMatchers.sameInstance(mockResult));

    }
}