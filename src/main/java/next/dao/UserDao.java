package next.dao;

import next.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {
    public void insert(User user) throws SQLException {
        SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {

            @Override
            public String createQuery() {
                // language=SQL
                return "insert into USERS values ( ?,?,?,? )";
            }
        };

        selectJdbcTemplate.setPreParedStatementSetter(preparedStatement -> {
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getName());
        });
        selectJdbcTemplate.update();

    }

    public User findByUserId(String userId) {

        SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public String createQuery() {
                return "select * from users where userId= ?";
            }
        };
        selectJdbcTemplate.setPreParedStatementSetter(preparedStatement -> preparedStatement.setString(1, userId));
        selectJdbcTemplate.setRowMapper(resultSet -> {
            String id = resultSet.getString("userId");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            return new User(id, password, name, email);
        });
        return (User) selectJdbcTemplate.query().get(0);
    }

    public List<User> findAll() {
        SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {

            @Override
            public String createQuery() {
                return "SELECT * from users";
            }

        };
        selectJdbcTemplate.setRowMapper(resultSet -> {
            String userId = resultSet.getString("userId");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            return new User(userId, password, name, email);
        });
        selectJdbcTemplate.setPreParedStatementSetter(preparedStatement -> {});
        List<Object> query = selectJdbcTemplate.query();
        return query.stream().map(o -> (User) o).collect(Collectors.toList());
    }

    public void update(User user) {
        SelectJdbcTemplate selectJdbcTemplate = new SelectJdbcTemplate() {

            @Override
            public String createQuery() {
                return "update USERS U SET NAME =? , EMAIL =? , PASSWORD = ? where USERID =?";
            }
        };
        selectJdbcTemplate.setPreParedStatementSetter(preparedStatement -> {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserId());
        });
        selectJdbcTemplate.update();
    }
}
