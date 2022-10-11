package core.controller;


import core.view.JspView;
import core.view.View;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.time.LocalTime;

public class CreateQuestionsController implements Controller {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        String writer = request.getParameter("writer");
        String title = request.getParameter("title");
        String content = request.getParameter("contents");
        new QuestionDao().createQuestion(new Question(writer, content, title, Time.valueOf(LocalTime.now()), 0));


        return new JspView("redirect:/home");
    }

}
