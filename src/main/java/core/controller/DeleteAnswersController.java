package core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import next.dao.AnswerDao;
import next.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteAnswersController implements Controller {
    Logger log = LoggerFactory.getLogger(DeleteAnswersController.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answerId = request.getParameter("answerId");
        log.info("answerId = {} ", answerId);
        new AnswerDao().deleteAnswer(answerId);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.print(objectMapper.writeValueAsString(new Result(true)));

        return null;
    }

}
