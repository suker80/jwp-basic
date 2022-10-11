package core.controller;

import core.view.JspView;
import core.view.View;
import next.dao.QuestionDao;
import next.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        UserDao userDao = new UserDao();
        QuestionDao questionDao = new QuestionDao();
        req.setAttribute("users", userDao.findAll());
        req.setAttribute("questions", questionDao.findAll());
        return new JspView("home.jsp");
    }
}