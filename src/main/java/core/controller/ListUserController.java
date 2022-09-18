package core.controller;

import core.db.DataBase;
import next.model.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(!UserSessionUtils.isLogined(request.getSession())){
            return "redirect:/users/loginForm";
        }
        request.setAttribute("users", DataBase.findAll());
        return "/user/list.jsp";
    }
}
