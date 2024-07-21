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

import static servlet.ServletStart.isInitialized;

@WebServlet("/getUser")
public class ServletGet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!isInitialized) {
            getServletContext().getRequestDispatcher("/get.jsp").forward(req, resp);
            isInitialized = true;
        }

        Connection connection;
        ResultSet resultSet;

        String idStr = req.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            isInitialized=false;
            return;
        }


        if (Integer.parseInt(req.getParameter("id")) < 0) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            isInitialized=false;
        }

        try {
            connection = Connect.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE id=?");
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                req.setAttribute("id", resultSet.getString("id"));
                req.setAttribute("name", resultSet.getString("name"));
                req.setAttribute("surname", resultSet.getString("surname"));
                req.setAttribute("phone", resultSet.getString("phone"));
            }
            getServletContext().getRequestDispatcher("/endGet.jsp").forward(req, resp);
        } catch (SQLException e) {
            isInitialized=false;
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        isInitialized=false;
    }
}
