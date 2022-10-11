package core.controller;

import core.db.DataBase;
import core.view.JspView;
import core.view.View;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"),
                request.getParameter("email"));
        DataBase.updateUser(user.getUserId(), user);
        return new JspView("redirect:/home");
    }
}
