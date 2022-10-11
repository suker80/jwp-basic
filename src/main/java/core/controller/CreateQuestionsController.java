package core.controller;


import core.view.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.time.LocalTime;

public class CreateQuestionsController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        String writer = request.getParameter("writer");
        String title = request.getParameter("title");
        String content = request.getParameter("contents");
        new QuestionDao().createQuestion(new Question(writer, content, title, Time.valueOf(LocalTime.now()), 0));


        return jspView("redirect:/home");
    }

}
