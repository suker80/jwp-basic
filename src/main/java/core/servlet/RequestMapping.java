package core.servlet;

import core.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private final Map<String, Controller> controllerMap = new HashMap<>();

    public RequestMapping() {
        controllerMap.put("/users/login", new LoginController());
        controllerMap.put("/users/create", new CreateUserController());
        controllerMap.put("forward", new ForwardController());
        controllerMap.put("/users/list", new ListUserController());
        controllerMap.put("/user/logout", new LogoutController());
        controllerMap.put("/qna", new CreateQuestionsController());
        controllerMap.put("/", new HomeController());
        controllerMap.put("/index.jsp", new HomeController());
        controllerMap.put("/qna/form", new ForwardController());
    }

    public Controller getController(String requestURI) {
        return controllerMap.get(requestURI);
    }


}
