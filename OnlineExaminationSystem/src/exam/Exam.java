package exam;

import java.util.ArrayList;
import java.util.Collections;


public class Exam {
    private String examID;
    private String title;
    private ArrayList<Question> questions;
    private ArrayList<Question> questionPool;
    private int numQuestionsToDisplay;

    public Exam(String examID, String title) {
        this.examID = examID;
        this.title = title;
        this.questions = new ArrayList<>();
        this.questionPool = new ArrayList<>();
        this.numQuestionsToDisplay = 5;
    }

    public String getExamID() {
        return examID;
    }

    public String getTitle() {
        return title;
    }



    public void addQuestion(Question question) {
        questions.add(question);
        questionPool.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
    public ArrayList<Question> getRandomQuestions() {
        Collections.shuffle(questionPool);
        return new ArrayList<>(questionPool.subList(0,Math.min(numQuestionsToDisplay, questionPool.size())));
    }
}
