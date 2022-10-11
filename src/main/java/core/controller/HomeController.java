package core.controller;

import core.view.ModelAndView;
import next.dao.QuestionDao;
import next.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
        UserDao userDao = new UserDao();
        QuestionDao questionDao = new QuestionDao();
        req.setAttribute("users", userDao.findAll());
        req.setAttribute("questions", questionDao.findAll());
        return  jspView("home.jsp");
    }
}