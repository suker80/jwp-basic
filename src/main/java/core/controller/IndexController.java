package core.controller;

import core.view.JspView;
import core.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController implements Controller {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        return new JspView("redirect:/home");
    }
}
