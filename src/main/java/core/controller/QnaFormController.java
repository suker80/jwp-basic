package core.controller;

import core.view.ModelAndView;
import next.model.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QnaFormController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("/home");
        } else {
            return jspView("/qna/form.jsp");
        }
    }
}
