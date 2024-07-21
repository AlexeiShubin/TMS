package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ServletStart extends HttpServlet {

    public static boolean isInitialized=false;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        isInitialized=false;
        getServletContext().getRequestDispatcher("webapp/index.jsp").forward(req, resp);
    }
}
