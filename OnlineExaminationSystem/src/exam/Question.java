package exam;

public class Question {
    private String questionText;
    private String correctAnswer;

    public Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean checkAnswer(String studentAnswer) {
        return studentAnswer.equalsIgnoreCase(correctAnswer);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}