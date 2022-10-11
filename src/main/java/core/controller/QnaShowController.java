package core.controller;

import core.view.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QnaShowController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        String questionId = request.getParameter("questionId");
        Question question = new QuestionDao().findQuestion(questionId);
        List<Answer> answerList = new AnswerDao().findAllByQuestionId(questionId);
        request.setAttribute("question", question);
        request.setAttribute("answers", answerList);
        return jspView("/qna/show.jsp");
    }
}
