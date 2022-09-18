package core.controller;

import core.db.DataBase;
import next.model.User;
import next.model.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = DataBase.findUserById(userId);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            return "redirect:/index.jsp";
        } else {
            return "/login_failed.jsp";
        }
    }
}
