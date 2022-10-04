package next.model;

import java.sql.Time;

public class Answer {
    private Long answerId;

    private final Long questionId;
    private final String writer;
    private final String contents;
    private final String title;

    private final Time createdDate;
    private int countOfAnswer;

    public Answer(Long questionId, String writer, String contents, String title, Time createdDate) {
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.title = title;
        this.createdDate = createdDate;
    }

    public Answer(Long questionId, String writer, String contents, String title, Time createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.title = title;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
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

    public String getTitle() {
        return title;
    }

    public Time getCreatedDate() {
        return createdDate;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }
}
