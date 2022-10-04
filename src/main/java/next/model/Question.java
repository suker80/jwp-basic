package next.model;

import java.sql.Time;

public class Question {

    private Long questionId;
    private final String writer;
    private final String contents;


    private final String title;

    private final Time createdDate;
    private final int countOfAnswer;

    public Question(String writer, String contents, String title, Time createdDate, int countOfAnswer) {
        this.writer = writer;
        this.contents = contents;
        this.title = title;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public Question(Long questionId, String writer, String contents, String title, Time createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.title = title;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
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
