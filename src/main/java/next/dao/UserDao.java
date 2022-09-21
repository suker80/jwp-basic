package next.dao;

import next.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {
    public void insert(User user) throws SQLException {
        SelectJdbcTemplate SelectJdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, user.getUserId());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getName());

            }

            @Override
            protected Object mapRow(ResultSet resultSet) {
                return null;
            }

            @Override
            public String createQuery() {
                // language=SQL
                return "insert into USERS values ( ?,?,?,? )";
            }
        };
        SelectJdbcTemplate.update();

    }

    public User findByUserId(String userId) {

        SelectJdbcTemplate SelectJdbcTemplate = new SelectJdbcTemplate() {
            @Override
            protected Object mapRow(ResultSet resultSet) throws SQLException {
                String userId = resultSet.getString("userId");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                return new User(userId, password, name, email);
            }

            @Override
            public String createQuery() {
                return "select * from users where userId= ?";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, userId);

            }
        };
        return (User) SelectJdbcTemplate.query().get(0);
    }

    public List<User> findAll() {
        SelectJdbcTemplate SelectJdbcTemplate = new SelectJdbcTemplate() {
            @Override
            protected Object mapRow(ResultSet resultSet) throws SQLException {
                String userId = resultSet.getString("userId");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                return new User(userId, password, name, email);
            }

            @Override
            public String createQuery() {
                return "SELECT * from users";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) {
            }
        };
        List<Object> query = SelectJdbcTemplate.query();
        return query.stream().map(o -> (User) o).collect(Collectors.toList());
    }

    public void update(User user) {
        SelectJdbcTemplate SelectJdbcTemplate = new SelectJdbcTemplate() {
            @Override
            protected Object mapRow(ResultSet resultSet) {
                return null;
            }

            @Override
            public String createQuery() {
                return "update USERS U SET NAME =? , EMAIL =? , PASSWORD = ? where USERID =?";
            }

            @Override
            public void setValues(PreparedStatement preparedStatement) {


                try {
                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setString(2, user.getEmail());
                    preparedStatement.setString(3, user.getPassword());
                    preparedStatement.setString(4, user.getUserId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };
        SelectJdbcTemplate.update();
    }
}
