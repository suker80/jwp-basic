package core.controller;

import core.db.DataBase;
import core.view.JspView;
import core.view.View;
import next.dao.UserDao;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {
    private final Logger log = LoggerFactory.getLogger(CreateUserController.class);
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        User user = new User(userId, password, name, email);
        UserDao userDao = new UserDao();
        try {
            userDao.insert(user);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        DataBase.addUser(user);
        return new JspView("redirect:/home");
    }
}
