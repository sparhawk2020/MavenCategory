package com.example.category;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SessionAttributes({"id","desc","errMsg","errorMessage"})
@RequestMapping
@Controller
public class CategoryController {

     @Autowired
     Catdao dao;



    @GetMapping(path="/category")
    public String showCategorypage(ModelMap model) throws ClassNotFoundException, SQLException {

        List<Category> list = dao.display();
        model.addAttribute("categorylist", list);

        model.put("id",list.get(0).getCatcode());


        model.put("desc",list.get(0).getCatdesc());


        return "category";


    }


    @GetMapping(path="/")
    public String showCategoryPage2(ModelMap model) throws ClassNotFoundException, SQLException {

        List<Category> list = dao.display();
        model.addAttribute("categorylist", list);

        model.put("id",list.get(0).getCatcode());

        model.put("desc",list.get(0).getCatdesc());

        return "category";


    }

    @GetMapping(path="/add-todo")
    public String showtodopage(){


        return "catser";
    }



    @PostMapping(path="/add-todo")
    public String addTodo(ModelMap model, @RequestParam String catcode, @RequestParam String catdesc) throws SQLException, ClassNotFoundException {


        Optional<Category> x = dao.getcatbyid(catcode);

        if(x.isEmpty()==false){

            model.put("errorMessage","Record Existing");
            return "redirect:/category";


        }



         Category cc = new Category();

         cc.setCatcode(catcode);
         cc.setCatdesc(catdesc);

         dao.insertData(cc);


        model.addAttribute("cc", cc);


        return "redirect:/category";
    }


    @GetMapping(path="/update-todo")
    public String showUpdateTodoPage(ModelMap model,  @RequestParam(defaultValue = "") String id) throws SQLException, ClassNotFoundException {

        model.put("id", id);

        Optional<Category>  cc =  dao.getcatbyid(id);




        model.put("id",cc.get().getCatcode());
        model.put("desc", cc.get().getCatdesc());


        return "catedit";
    }

    //@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    @PostMapping(path="/update-todo")
    public String showUpdate(ModelMap model,  @RequestParam String catcode, @RequestParam String catdesc) throws SQLException, ClassNotFoundException {

        String iid = (String) model.get("id");

        Category cc = new Category();

        cc.setCatcode(catcode);
        cc.setCatdesc(catdesc);

        dao.EditData(cc, iid);



        return "redirect:/";

    }



    //@RequestMapping(value ="/delete-todo", method = RequestMethod.GET)

    @GetMapping(path="/delete-todo")
    public String deleteTodo(ModelMap model, @RequestParam String id) throws SQLException, ClassNotFoundException {



        dao.deleteData(id);

        model.clear();
        return "redirect:/";
    }



    @GetMapping(path="/see-todo")
    public String seetodo(ModelMap model,  @RequestParam(defaultValue = "") String id) throws SQLException, ClassNotFoundException {





        List<Map<String, Object>> x= dao.getitem(id);

        if(x.size()==0){

            model.put("errorMessage","No items in this category ");
            return "redirect:/category";


        }



        model.addAttribute("itemlist", x);



        return "items";
    }


    @PostMapping(path="/see-todo")
    public String seetodo2(ModelMap model) throws SQLException, ClassNotFoundException {


        return "redirect:/";
    }



}
