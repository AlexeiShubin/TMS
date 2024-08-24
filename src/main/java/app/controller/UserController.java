package app.controller;


import app.connect.Connect;
import app.user.User;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class UserController {
    @GetMapping("/create")
    public String createPage() {
        return "create";
    }

    @PostMapping("/createUser")
    public String createUser(User user) {
        try {
            Connection connection = Connect.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employees VALUES (?, ?, ?, ?)");
            PreparedStatement lastIdStatement = connection.prepareStatement("SELECT MAX(id) FROM EMPLOYEES");


            ResultSet resultSet = lastId.executeQuery();

            int id = 0;
            while (resultSet.next()) id = resultSet.getInt("max");

            preparedStatement.setInt(1, id + 1);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.executeUpdate();

            return "createEnd";
        } catch (SQLException e) {
            return "error";
        }
    }

    @GetMapping("/get")
    public String getPage() {
        return "get";
    }

    @GetMapping("/getUser")
    public String getUser(@RequestParam(value = "id", required = false) String idStr, Model model) {

        if (idStr.isEmpty()||!NumberUtils.isCreatable(idStr)){
            return "error";
        }

        int id=Integer.parseInt(idStr);

        if (id <= 0) {
            return "error";
        }

        try {
            Connection connection = Connect.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                model.addAttribute("id", resultSet.getString("id"));
                model.addAttribute("name", resultSet.getString("name"));
                model.addAttribute("surname", resultSet.getString("surname"));
                model.addAttribute("phone", resultSet.getString("phone"));
            }

            return "getEnd";
        } catch (SQLException e) {
            return "error";
        }
    }

    @GetMapping("/change")
    public String changePage(){
        return "change";
    }

    @PostMapping("/changeUser")
    public String changeUser(@RequestParam(value = "id", required = false) String idStr, @RequestParam(value = "name", required = false) String newFirstName){

        if (idStr==null||idStr.isEmpty()||!NumberUtils.isCreatable(idStr)){
            return "error";
        }

        int id=Integer.parseInt(idStr);

        if (id <= 0) {
            return "error";
        }

        try {
            Connection connection=Connect.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE EMPLOYEES SET name = ? WHERE id = ?");
            preparedStatement.setInt(2, id);
            preparedStatement.setString(1, newFirstName);
            preparedStatement.executeUpdate();
            return "changeEnd";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/delete")
    public String deletePage(){
        return "delete";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id", required = false) String idStr, Model model){

        if (idStr==null||idStr.isEmpty()||!NumberUtils.isCreatable(idStr)){
            return "error";
        }

        int id=Integer.parseInt(idStr);

        if (id <= 0) {
            return "error";
        }

        try {
            Connection connection=Connect.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM EMPLOYEES WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            model.addAttribute("name", id);
            return "deleteEnd";
        } catch (SQLException e) {
            return "error";
        }
    }
}

