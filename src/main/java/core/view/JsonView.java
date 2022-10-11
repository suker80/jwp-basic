package core.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JsonView implements View {
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        String s = objectMapper.writeValueAsString(createModel(request));
        response.getWriter().write(s);

    }

    private Map<String, Object> createModel(HttpServletRequest request) {
        Enumeration<String> names = request.getAttributeNames();
        Map<String, Object> model = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            model.put(name, request.getAttribute(name));
        }
        return model;
    }

}
