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

@WebServlet("/deleteUser")
public class ServletDelete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idStr = req.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            isInitialized=false;
            return;
        }

        Connection connection;
        try {
            connection = Connect.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM EMPLOYEES WHERE id = ?");
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            preparedStatement.executeUpdate();

            req.setAttribute("name", Integer.parseInt(req.getParameter("id")));
            getServletContext().getRequestDispatcher("/endDelete.jsp").forward(req, resp);
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}