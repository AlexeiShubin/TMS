import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SaveRequest extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        req.setAttribute("name", name);
        req.setAttribute("surname", surname);
        req.setAttribute("age", age);
        if (name.isEmpty() || surname.isEmpty() || age.isEmpty()) {
            getServletContext().getRequestDispatcher("/incorrectform.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/userdata.jsp").forward(req, resp);
        }
    }
}
