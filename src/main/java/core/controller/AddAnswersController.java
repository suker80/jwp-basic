package core.controller;

import core.view.JsonView;
import core.view.View;
import next.dao.AnswerDao;
import next.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

public class AddAnswersController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(AddAnswersController.class);


    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        String writer = request.getParameter("writer");

        String contents = request.getParameter("contents");
        Long questionId = Long.valueOf(request.getParameter("questionId"));

        Answer answer = new Answer(questionId, writer, contents, Date.valueOf(LocalDate.now()));

        log.debug("answer = {}", answer);


        Answer saved = new AnswerDao().createAnswer(answer);
        request.setAttribute("answer", saved);
        return new JsonView();
    }
}
