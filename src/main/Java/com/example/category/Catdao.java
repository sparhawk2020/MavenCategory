package com.example.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Service
@Repository
public class Catdao {

@Autowired
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


   /* public void insertData(final Category category) {
        String query = "insert into category values(?,?)";
        template.execute(query, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setString(1, category.getCatcode());
                ps.setString(2, category.getCatdesc());
                return ps.execute();
            }
        });
        System.out.println("Data inserted Successfully");

    }*/


    public int deleteData (String cat) {
        return template.update("delete from category where catcode= ?", cat);
    }

    /*public void deleteData(final String category) {
        String query = "Delete from category where catcode = ?";
        template.execute(query, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setString(1, category);
                return ps.execute();
            }
        });
        System.out.println("Data deleted Successfully");

    }*/



    public int EditData(final Category category, String cat) {
        return template.update("update category set catcode= ?, catdesc = ? where catcode = ?", category.getCatcode(), category.getCatdesc(),cat);
    }

   /* public void EditData(final Category category, String cat) {
        String query = "update category set catcode= ?, catdesc = ? where catcode = ?";
        template.execute(query, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setString(1, category.getCatcode());
                ps.setString(2, category.getCatdesc());
                ps.setString(3,cat);
                return ps.execute();
            }
        });
        System.out.println("Data edited Successfully");

    }*/

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




    private Items mapUserResult2(final ResultSet rs) throws SQLException {
        Items e = new Items();
        e.getItemcode(rs.getString(1));
        e.getItemdesc(rs.getString(2));


        return e;
    }

    //<editor-fold desc="Description">
  /*  public Category getcatById(String cat) {
        String sql = "select * from category where catcode=?";
        //   return template.queryForObject(sql, new Object[]{cat},new BeanPropertyRowMapper<Category>(Category.class));

        List<String> strLst = template.query(sql, new RowMapper<String>() {
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        });

        return null;
    }*/
    //</editor-fold>








}

