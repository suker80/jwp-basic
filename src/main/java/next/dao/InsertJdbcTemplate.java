package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertJdbcTemplate {
    public  void insert(UserDao userDao, User user) {
        String sql = userDao.createQueryForInsert();
        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            userDao.setValuesForInsert(user, preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
