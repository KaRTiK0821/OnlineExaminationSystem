package result;

import exam.Exam;
import users.Student;

public class Result {
    private Student student;
    private Exam exam;
    private int score;  // Store score as an integer for correct answers count

    public Result(Student student, Exam exam, int score) {
        this.student = student;
        this.exam = exam;
        this.score = score;
    }

    // Getters
    public Student getStudent() {
        return student;
    }

    public Exam getExam() {
        return exam;
    }

    public int getScore() {
        return score;
    }

    // Method to calculate grade percentage
    public double getGrade() {
        int totalQuestions = exam.getQuestions().size();
        return (double) score / totalQuestions * 100;  // Correct calculation
    }
    
    // Method to display the result
    public void displayResult() {
        double grade = getGrade();  // Get the correct grade percentage
        System.out.println("Result for " + student.getUsername() + ": " + grade + "%");
    }
}
