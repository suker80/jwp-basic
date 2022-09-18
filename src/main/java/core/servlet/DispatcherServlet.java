package core.servlet;

import core.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMapping requestMapping;
    private final String redirectPrefix = "redirect:";

    @Override
    public void init() {
        requestMapping = new RequestMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        Controller controller = requestMapping.getController(req.getRequestURI());
        try {
            String viewName = controller.execute(req, resp);
            log.info("viewName = {} ", viewName);
            forward(viewName, req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void forward(String viewName, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (viewName.startsWith(redirectPrefix)) {
            String substring = viewName.substring(redirectPrefix.length());
            log.info("redirect : {}", substring);
            resp.sendRedirect(substring);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
            requestDispatcher.forward(req, resp);
        }
    }
}
