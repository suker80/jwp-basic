package core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/home";
    }
}
