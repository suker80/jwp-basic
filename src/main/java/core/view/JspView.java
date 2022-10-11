package core.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspView implements View {
    private String viewName;
    private final Logger log = LoggerFactory.getLogger(JspView.class);

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String redirectPrefix = "redirect:";
        if (viewName.startsWith(redirectPrefix)) {
            String substring = viewName.substring(redirectPrefix.length());
            log.info("redirect : {}", substring);
            response.sendRedirect(substring);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
            requestDispatcher.forward(request, response);
        }
    }

    public JspView(String viewName) {
        this.viewName = viewName;
    }
}
