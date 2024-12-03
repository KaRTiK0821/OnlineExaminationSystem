package users;

import exam.Exam;
import exam.Question;
import result.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Student extends User {
    private ArrayList<Exam> enrolledExams;
    private ArrayList<Result> results;
    private HashMap<Exam, Integer> examResults;

    public Student(String userID, String username, String password) {
        super(userID, username, password);
        enrolledExams = new ArrayList<>();
        results = new ArrayList<>();
        examResults = new HashMap<>();
    }

    // Add a result to the student's results list
    public void addResult(Result result) {
        results.add(result);
    }

    // Get the list of results for the student
    public ArrayList<Result> getResults() {
        return results;
    }

    // View the student's profile
    @Override
    public void viewProfile() {
        System.out.println("Viewing Student Profile: " + username);
        System.out.println("User ID: " + userID);
    }

    // Edit the student's profile
    @Override
    public void editProfile(Scanner scanner) {
        System.out.println("Editing Student Profile for " + username);
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        // Assuming that username and password are unique, update them
        this.username = newUsername;
        this.password = newPassword;
        System.out.println("Profile updated successfully!");
    }

    // Enroll the student in an exam
    public void enrollInExam(Exam exam) {
        enrolledExams.add(exam);
    }

    // Method to take an exam
    public void takeExam(Scanner scanner, Exam exam) {
        if (!enrolledExams.contains(exam)) {
            System.out.println("You are not enrolled in this exam.");
            return;
        }

        System.out.println("Taking Exam: " + exam.getTitle());
        int score = 0;
        // Go through the questions in the exam and ask for answers
        for (int i = 0; i < exam.getQuestions().size(); i++) {
            Question question = exam.getQuestions().get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            System.out.print("Your answer: ");
            String studentAnswer = scanner.nextLine();
            if (question.checkAnswer(studentAnswer)) {
                score++;
            }
        }

        // Calculate the grade
        double grade = (double) score / exam.getQuestions().size() * 100;
        System.out.println("Exam completed. You scored: " + score + "/" + exam.getQuestions().size() + " (" + grade + "%)");

        // Store the result
        Result result = new Result(this, exam, grade);
        addResult(result);
        examResults.put(exam, score);
    }

    // View all enrolled exams
    public void viewEnrolledExams() {
        System.out.println("Enrolled Exams for " + username + ":");
        if (enrolledExams.isEmpty()) {
            System.out.println("No exams enrolled.");
        } else {
            for (Exam exam : enrolledExams) {
                System.out.println("Exam: " + exam.getTitle() + " (ID: " + exam.getExamID() + ")");
            }
        }
    }

    // Get a student's results for a particular exam
    public void viewExamResults() {
        System.out.println("Results for " + username + ":");
        if (results.isEmpty()) {
            System.out.println("No results available.");
        } else {
            for (Result result : results) {
                System.out.println("Exam: " + result.getExam().getTitle() + " - Score: " + result.getGrade() + "%");
            }
        }
    }
}
