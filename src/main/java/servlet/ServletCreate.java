package servlet;

import database.Connect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static servlet.ServletStart.isInitialized;

@WebServlet("/createUser")
public class ServletCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Connection connection;
        ResultSet resultSet;
        int id = 0;
        try {
            connection = Connect.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employees VALUES (?, ?, ?, ?)");
            PreparedStatement maxID = connection.prepareStatement("SELECT MAX(id) FROM EMPLOYEES");

            resultSet = maxID.executeQuery();

            while (resultSet.next()) id = resultSet.getInt("max");

            preparedStatement.setInt(1, id + 1);
            preparedStatement.setString(2, req.getParameter("name"));
            preparedStatement.setString(3, req.getParameter("surname"));
            preparedStatement.setString(4, req.getParameter("phone"));
            preparedStatement.executeUpdate();
            getServletContext().getRequestDispatcher("/endCreate.jsp").forward(req, resp);

        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
