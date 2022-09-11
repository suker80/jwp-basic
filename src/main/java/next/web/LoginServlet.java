package next.web;

import core.db.DataBase;
import next.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User userById = DataBase.findUserById(userId);
        if (userById != null && userById.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", userById);
            resp.sendRedirect("/index.jsp");
        } else {
            resp.sendRedirect("/user/login_failed.jsp");
        }
    }
}
