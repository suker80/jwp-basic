package core.db;

import com.google.common.collect.Maps;
import next.model.User;

import java.util.Collection;
import java.util.Map;

public class DataBase {
    private static final Map<String, User> users = Maps.newHashMap();

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static void updateUser(String userId, User user) {
        users.replace(userId, user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static Collection<User> findAll() {
        return users.values();
    }
}
