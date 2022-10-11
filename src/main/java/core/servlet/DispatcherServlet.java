package core.servlet;

import core.controller.Controller;
import core.view.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            ModelAndView mv = controller.execute(req, resp);
            mv.getView().render(mv.getModel(), req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
