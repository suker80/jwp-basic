package next.model;

import java.sql.Date;

public class Answer {
    private Long answerId;

    private final Long questionId;
    private final String writer;
    private final String contents;

    private final Date createdDate;

    public Answer(Long answerId, Long questionId, String writer, String contents, Date createdDate) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public Answer(Long questionId, String writer, String contents, Date createdDate) {
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

}
