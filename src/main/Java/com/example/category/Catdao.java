package com.example.category;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Service
@Repository
public class Catdao {

//@Autowired
JdbcTemplate template;

    public Catdao(JdbcTemplate template) {
        this.template = template;
    }

    /*public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }*/





    public int insertData(final Category category) {
        return template.update("insert into category values(?,?)", category.getCatcode(), category.getCatdesc());
    }



    public int deleteData (String cat) {
        return template.update("delete from category where catcode= ?", cat);
    }





    public int EditData(final Category category, String cat) {
        return template.update("update category set catcode= ?, catdesc = ? where catcode = ?", category.getCatcode(), category.getCatdesc(),cat);
    }



    public List<Category> display() throws ClassNotFoundException, SQLException {
        //create an array list that will contain the data recovered

        return template.query("select * from category", (RowMapper) (rs, row) -> {
            Category c = new Category();
            c.setCatcode(rs.getString(1));
            c.setCatdesc(rs.getString(2));
            return c;

        });


    }







    public Optional<Category> getcatbyid(String cat) {
        try {
            return template.queryForObject("SELECT * from category where catcode = ?", new Object[] { cat },
                    (rs, rowNum) -> Optional.of(mapUserResult1(rs)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    private Category mapUserResult1(final ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setCatcode(rs.getString(1));
        c.setCatdesc(rs.getString(2));


        return c;
    }






    public List<Map<String, Object>> getitem (String cat){
        return template.queryForList("SELECT * from items where catcode = ?", cat);
    }

    public List<Map<String, Object>> getcat (String cat){
        return template.queryForList("SELECT * from category where catcode = ?", cat);
    }




    private Items mapUserResult2(final ResultSet rs) throws SQLException {
        Items e = new Items();
        e.getItemcode(rs.getString(1));
        e.getItemdesc(rs.getString(2));


        return e;
    }

    public void setTemplate(JdbcTemplate template) {
    }









}

