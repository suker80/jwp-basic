package next.model;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static boolean isLogined(HttpSession session) {

        User user = (User) session.getAttribute("user");
        return user != null;
    }
}
