package core.controller;

import core.db.DataBase;
import core.view.JspView;
import core.view.View;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = DataBase.findUserById(userId);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            return new JspView("redirect:/home");
        } else {
            return new JspView("user/login_failed.jsp");
        }
    }
}
