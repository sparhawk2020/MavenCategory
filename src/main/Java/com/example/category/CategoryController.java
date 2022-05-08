package com.example.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"id","desc","errMsg","errorMessage"})
@RequestMapping
@Controller
public class CategoryController {

    DatabaseService service1;

    @Autowired
    DatabaseConnection connect;






    @GetMapping(path="/category")
    public String showCategorypage(ModelMap model) throws ClassNotFoundException, SQLException {


        service1 = new DatabaseService(connect.connect());


        List<Category> categorieslist = new ArrayList<Category>();

        categorieslist = service1.display();

        model.addAttribute("categorylist", categorieslist);


       //get the first record and diplay it


        model.put("id",categorieslist.get(0).getCatcode());


        model.put("desc",categorieslist.get(0).getCatdesc());


        return "category";


    }


    @GetMapping(path="/")
    public String showCategoryPage2(ModelMap model) throws ClassNotFoundException, SQLException {

        service1 = new DatabaseService(connect.connect());


        List<Category> categorieslist;

        categorieslist = service1.display();

        categorieslist.get(0).getCatcode();

        model.addAttribute("categorylist", categorieslist);


        //get the first record and diplay it


        model.put("id",categorieslist.get(0).getCatcode());


        model.put("desc",categorieslist.get(0).getCatdesc());


        return "category";


    }

    @RequestMapping(value ="/add-todo", method = RequestMethod.GET)
    public String showtodopage(){
        return "catser";
    }


    @RequestMapping(value ="/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @RequestParam String catcode, @RequestParam String catdesc) throws SQLException, ClassNotFoundException {




        if (!((service1.search(catcode)) ==null)){

            model.put("errorMessage","Record Existing");
            return "redirect:/category";

        }


        Category cc = new Category(catcode,catdesc);

        service1.add(cc);

        model.clear();
        return "redirect:/category";
    }


    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(ModelMap model,  @RequestParam(defaultValue = "") String id) throws SQLException, ClassNotFoundException {

        model.put("id", id);


        Category cc =  service1.search(id);


        model.put("id",cc.getCatcode());
        model.put("desc", cc.getCatdesc());



        return "catedit";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String showUpdate(ModelMap model,  @RequestParam String catcode, @RequestParam String catdesc) throws SQLException, ClassNotFoundException {

        //get the old catcode

        String iid = (String) model.get("id");

        Category cc = new Category(catcode,catdesc);

        service1.edit(cc,iid);

        return "redirect:/";

    }



    @RequestMapping(value ="/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(ModelMap model, @RequestParam String id) throws SQLException, ClassNotFoundException {


        service1.delete(id);


        model.clear();
        return "redirect:/";
    }


    @RequestMapping(value ="/see-todo", method = RequestMethod.GET)
    public String seetodo(ModelMap model,  @RequestParam(defaultValue = "") String id) throws SQLException, ClassNotFoundException {

        model.put("id", id);

        service1 = new DatabaseService(connect.connect());

        String iid = (String) model.get("id");

        List<Items> Itemlist;

        Itemlist = service1.display2(iid);


        if(Itemlist.size()==0){

            model.put("errorMessage","There are not items for this category ");
            return "redirect:/";
        } else {
            model.put("errorMessage"," ");
        }

        model.put("desc",iid);


        List<Items> itemlist;

        itemlist= service1.display2(iid);

        model.addAttribute("itemlist", itemlist);



        return "items";
    }


    @RequestMapping(value ="/see-todo", method = RequestMethod.POST)
    public String seetodo2(ModelMap model) throws SQLException, ClassNotFoundException {


        return "redirect:/";
    }



}
