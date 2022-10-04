package next.dao;

import next.model.Question;

import java.sql.Time;
import java.util.List;

public class QuestionDao {

    public void createQuestion(Question question) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "insert into QUESTIONS ( WRITER, TITLE, CONTENTS, CREATEDDATE, COUNTOFANSWER) values(?,?,?,?,?)";
            }
        };

        jdbcTemplate.update(preparedStatement -> {
            preparedStatement.setString(1, question.getWriter());
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getContents());
            preparedStatement.setTime(4, question.getCreatedDate());
            preparedStatement.setInt(5, question.getCountOfAnswer());
        });
    }

    public List<Question> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "select * from QUESTIONS";
            }
        };

        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
        };
        RowMapper<Question> rowMapper = (resultSet -> {
            Long questionId = resultSet.getLong("questionId");
            String writer = resultSet.getString("writer");
            String contents = resultSet.getString("contents");
            String title = resultSet.getString("title");
            Time createddate = resultSet.getTime("createddate");
            int countofanswer = resultSet.getInt("COUNTOFANSWER");
            return new Question(questionId, writer, contents, title, createddate, countofanswer);
        });


        return jdbcTemplate.query(preparedStatementSetter, rowMapper);
    }

}
