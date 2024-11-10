package exam;

import java.util.ArrayList;

public class Exam {
    private String examID;
    private String title;
    private ArrayList<Question> questions;

    public Exam(String examID, String title) {
        this.examID = examID;
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getExamID() {
        return examID;
    }

    public String getTitle() {
        return title;
    }



    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
