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

import static servlet.ServletStart.isInitialized;

@WebServlet("/changeUser")
public class ServletChange extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!isInitialized) {
            getServletContext().getRequestDispatcher("/change.jsp").forward(req, resp);
            isInitialized = true;
        }

        String idStr = req.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            isInitialized=false;
            return;
        }

        String newFistName = req.getParameter("name");
        Connection connection;

        try {
            connection = Connect.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE EMPLOYEES SET name = ? WHERE id = ?");
            preparedStatement.setString(1, newFistName);
            preparedStatement.setInt(2, Integer.parseInt(req.getParameter("id")));
            preparedStatement.executeUpdate();

            getServletContext().getRequestDispatcher("/endChange.jsp").forward(req, resp);
        } catch (Exception e) {
            isInitialized=false;
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
        isInitialized=false;
    }
}