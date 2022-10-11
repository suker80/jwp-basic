package core.controller;

import core.db.DataBase;
import core.view.ModelAndView;
import next.model.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/users/loginForm");
        }
        request.setAttribute("users", DataBase.findAll());
        return jspView("/user/list.jsp");
    }
}
