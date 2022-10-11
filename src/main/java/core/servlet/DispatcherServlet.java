package core.servlet;

import core.controller.Controller;
import core.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMapping requestMapping;

    @Override
    public void init() {
        requestMapping = new RequestMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        Controller controller = requestMapping.getController(req.getRequestURI());
        try {
            View execute = controller.execute(req, resp);
            execute.render(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
