package core.controller;

import core.db.DataBase;
import core.view.ModelAndView;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = DataBase.findUserById(userId);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            return jspView("redirect:/home");
        } else {
            return jspView("user/login_failed.jsp");
        }
    }
}
