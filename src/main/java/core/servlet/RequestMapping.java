package core.servlet;

import core.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private Map<String, Controller> controllerMap = new HashMap<>();

    public RequestMapping() {
        controllerMap.put("/user/login", new LoginController());
        controllerMap.put("/user/create", new CreateUserController());
        controllerMap.put("forward", new ForwardController());
        controllerMap.put("/user/list", new ListUserController());
        controllerMap.put("/user/logout", new LogoutController());
    }

    public Controller getController(String requestURI) {
        Controller controller = controllerMap.get(requestURI);
        if (controller == null) {
            Controller forward = controllerMap.get("forward");
            return forward;
        } else {
            return controller;
        }
    }



}
