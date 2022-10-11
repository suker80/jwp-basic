package core.controller;

import core.view.JspView;
import core.view.View;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QnaShowController implements Controller {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        String questionId = request.getParameter("questionId");
        Question question = new QuestionDao().findQuestion(questionId);
        List<Answer> answerList = new AnswerDao().findAllByQuestionId(questionId);
        request.setAttribute("question", question);
        request.setAttribute("answers", answerList);
        return new JspView("/qna/show.jsp");
    }
}
