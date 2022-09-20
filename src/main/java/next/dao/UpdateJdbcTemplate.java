package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateJdbcTemplate {

    public  void update(UserDao userDao, User user) {
        String sql = userDao.createQueryForUpdate();
        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            userDao.setValuesForUpdate(user, preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

