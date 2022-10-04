package next.dao;

import next.model.Answer;

public class AnswerDao {
    public void createAnswer(Answer answer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public String createQuery() {
                return "insert into answer values(?,?,?,?,?)";
            }
        };

        jdbcTemplate.update(preparedStatement -> {
            preparedStatement.setString(1, answer.getWriter());
            preparedStatement.setString(2, answer.getTitle());
            preparedStatement.setString(3, answer.getContents());
            preparedStatement.setTime(4, answer.getCreatedDate());
            preparedStatement.setLong(5, answer.getQuestionId());

        });
    }
}

