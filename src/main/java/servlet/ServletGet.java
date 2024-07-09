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
import java.sql.SQLException;

@WebServlet("/getUser")
public class ServletGet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection;
        ResultSet resultSet;
        int id=Integer.parseInt(req.getParameter("id"));
        if (id<0){
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }

        try {
            connection= Connect.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM employees WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                req.setAttribute("id", resultSet.getString("id"));
                req.setAttribute("name", resultSet.getString("name"));
                req.setAttribute("surname", resultSet.getString("surname"));
                req.setAttribute("phone", resultSet.getString("phone"));
            }
            getServletContext().getRequestDispatcher("/endGet.jsp").forward(req, resp);
        } catch (SQLException e) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
