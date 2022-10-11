package core.controller;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QnaShowController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String questionId = request.getParameter("questionId");
        Question question = new QuestionDao().findQuestion(questionId);
        List<Answer> answerList = new AnswerDao().findAllByQuestionId(questionId);
        request.setAttribute("question", question);
        request.setAttribute("answers", answerList);
        return "/qna/show.jsp";
    }
}
