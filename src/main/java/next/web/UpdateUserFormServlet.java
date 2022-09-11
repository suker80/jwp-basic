package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = (User) req.getSession().getAttribute("user");

        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (loginUser == null || !loginUser.equals(user)) {
            resp.sendRedirect("/index.jsp");
        } else {
            req.setAttribute("user", user);
            RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
            rd.forward(req, resp);
        }

    }
}
