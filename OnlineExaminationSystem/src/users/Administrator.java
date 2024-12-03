package users;
import java.util.Scanner;
import exam.Exam;

public class Administrator extends User {

    public Administrator(String userID, String username, String password) {
        super(userID, username, password);
    }

    @Override
    public void viewProfile() {
        System.out.println("Viewing Administrator Profile: " + username);
    }

    @Override
    public void editProfile(Scanner scanner) {
        System.out.println("Editing Administrator Profile");
        super.editProfile(scanner);
    }

    public void createExam(String title, String subject) {
        // Logic for creating an exam
    }
}
