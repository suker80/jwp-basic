package core.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JsonView implements View {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HashMap<String, Object> model = new HashMap<>();


    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        String s = objectMapper.writeValueAsString(model);
        response.getWriter().print(s);
    }


}
