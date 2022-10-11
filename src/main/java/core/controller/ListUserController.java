package core.controller;

import core.db.DataBase;
import core.view.JspView;
import core.view.View;
import next.model.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        if(!UserSessionUtils.isLogined(request.getSession())){
            return new JspView("redirect:/users/loginForm");
        }
        request.setAttribute("users", DataBase.findAll());
        return new JspView("/user/list.jsp");
    }
}
