package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class InsertJdbcTemplate {
    public void insert(User user) {

        String sql = createQueryForInsert();
        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            setValuesForInsert(user, preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public abstract void setValuesForInsert(User user, PreparedStatement preparedStatement) throws SQLException;


    public abstract String createQueryForInsert();
}
