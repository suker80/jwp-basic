package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void insert(User user) throws SQLException {
        InsertJdbcTemplate jdbcTemplate = new InsertJdbcTemplate();
        jdbcTemplate.insert(this, user);
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
            }

            return user;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<User> findAll() {

        String sql = "SELECT userId, password, name, email FROM USERS";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {

            ArrayList<User> usersList = new ArrayList<>();
            while (resultSet.next()) {
                String userId = resultSet.getString("userId");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                User user = new User(userId, password, name, email);
                usersList.add(user);
            }

            return usersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        UpdateJdbcTemplate jdbcTemplate = new UpdateJdbcTemplate();
        jdbcTemplate.update(this, user);
    }


    public String createQueryForInsert() {
        return "insert into USERS values ( ?,?,?,? )";
    }

    public String createQueryForUpdate() {
        return "update USERS U SET NAME =? , EMAIL =? , PASSWORD = ? where USERID =?";

    }

    public void setValuesForInsert(User user, PreparedStatement pstmt) {
        try {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void setValuesForUpdate(User user, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4, user.getUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
