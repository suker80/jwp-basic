package next.dao;

import core.jdbc.ConnectionManager;
import next.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcTemplate {

    public <T> List<T> query(PreparedStatementSetter preParedStatementSetter, RowMapper<T> rowMapper) {

        ResultSet resultSet = null;
        String sql = createQuery();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preParedStatementSetter.setValues(preparedStatement);
            List<T> list = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T object = rowMapper.mapRow(resultSet);
                list.add(object);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DataAccessException();
                }
            }
        }


    }
    public void update(PreparedStatementSetter preParedStatementSetter) {
        String sql = createQuery();
        try (Connection connection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preParedStatementSetter.setValues(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }


    public abstract String createQuery();




}
