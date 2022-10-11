package core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import next.dao.AnswerDao;
import next.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

public class AddAnswersController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(AddAnswersController.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String writer = request.getParameter("writer");

        String contents = request.getParameter("contents");
        Long questionId = Long.valueOf(request.getParameter("questionId"));

        Answer answer = new Answer(questionId, writer, contents, Date.valueOf(LocalDate.now()));

        log.debug("answer = {}", answer);


        Answer saved = new AnswerDao().createAnswer(answer);
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter writer1 = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            writer1.write(objectMapper.writeValueAsString(saved));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
