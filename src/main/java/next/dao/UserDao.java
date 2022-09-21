package next.dao;

import next.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void insert(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {

            @Override
            public String createQuery() {
                // language=SQL
                return "insert into USERS values ( ?,?,?,? )";
            }
        };
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getName());
        };
        jdbcTemplate.update(preparedStatementSetter);

    }

    public User findByUserId(String userId) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "select * from users where userId= ?";
            }

        };
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> preparedStatement.setString(1, userId);
        RowMapper<User> rowMapper = resultSet -> {
            String id = resultSet.getString("userId");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            return new User(id, password, name, email);
        };

        return jdbcTemplate.query(preparedStatementSetter, rowMapper).get(0);
    }

    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {

            @Override
            public String createQuery() {
                return "SELECT * from users";
            }

        };
        RowMapper<User> rowMapper = resultSet -> {
            String userId = resultSet.getString("userId");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            return new User(userId, password, name, email);
        };
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {

        };

        List<User> query = jdbcTemplate.query(preparedStatementSetter, rowMapper);
        return new ArrayList<>(query);
    }

    public void update(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {

            @Override
            public String createQuery() {
                return "update USERS U SET NAME =? , EMAIL =? , PASSWORD = ? where USERID =?";
            }
        };
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserId());
        };
        jdbcTemplate.update(preparedStatementSetter);

    }
}
