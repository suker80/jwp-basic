package core.controller;

import core.view.ModelAndView;
import next.dao.AnswerDao;
import next.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

public class AddAnswersController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AddAnswersController.class);


    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        String writer = request.getParameter("writer");

        String contents = request.getParameter("contents");
        Long questionId = Long.valueOf(request.getParameter("questionId"));

        Answer answer = new Answer(questionId, writer, contents, Date.valueOf(LocalDate.now()));

        log.debug("answer = {}", answer);


        Answer saved = new AnswerDao().createAnswer(answer);
        ModelAndView modelAndView = jsonView();
        modelAndView.addObject("answer", saved);
        return modelAndView;
    }
}
