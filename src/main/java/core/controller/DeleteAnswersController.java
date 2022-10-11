package core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.view.JsonView;
import core.view.View;
import next.dao.AnswerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAnswersController implements Controller {
    Logger log = LoggerFactory.getLogger(DeleteAnswersController.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answerId = request.getParameter("answerId");
        log.info("answerId = {} ", answerId);
        new AnswerDao().deleteAnswer(answerId);
        request.setAttribute("status",true);

        return new JsonView();
    }

}
