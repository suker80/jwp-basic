package next.dao;

import core.jdbc.KeyHolder;
import next.model.Answer;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.*;
import java.util.List;

public class AnswerDao {
    public Answer createAnswer(Answer answer) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "insert into answers(writer,contents,createddate,questionId) values(?,?,?,?)";
            }
        };
        PreparedStatementCreator psc = con -> {
            PreparedStatement preparedStatement = con.prepareStatement(jdbcTemplate.createQuery());
            preparedStatement.setString(1, answer.getWriter());
            preparedStatement.setString(2, answer.getContents());
            preparedStatement.setDate(3, answer.getCreatedDate());
            preparedStatement.setLong(4, answer.getQuestionId());
            return preparedStatement;
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        return findById(keyHolder.getId());
    }

    private Answer findById(long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "select * from answers where answerId = ?";
            }
        };
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> preparedStatement.setLong(1, id);
        RowMapper<Answer> rowMapper = resultSet -> {
            long answerId = resultSet.getLong("answerid");
            String writer = resultSet.getString("writer");
            String contents = resultSet.getString("contents");
            Date createdDate = resultSet.getDate("createdDate");
            long questionId = resultSet.getLong("questionId");
            return new Answer(answerId, questionId, writer, contents, createdDate);
        };
        return jdbcTemplate.queryObject(preparedStatementSetter, rowMapper);
    }

    public void deleteAnswer(String answerId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "delete from ANSWERS where answerId = ?";
            }

        };
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> preparedStatement.setInt(1, Integer.parseInt(answerId));
        jdbcTemplate.update(preparedStatementSetter);

    }

    public List<Answer> findAllByQuestionId(String questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "select * from answers where questionId = ?";
            }
        };
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> preparedStatement.setLong(1, Long.parseLong(questionId));
        RowMapper<Answer> rowMapper = resultSet -> {
            long answerId = resultSet.getLong("answerid");
            String writer = resultSet.getString("writer");
            String contents = resultSet.getString("contents");
            Date createdDate = resultSet.getDate("createdDate");
            return new Answer(answerId, Long.parseLong(questionId), writer, contents, createdDate);

        };
        return jdbcTemplate.query(preparedStatementSetter, rowMapper);
    }

}

