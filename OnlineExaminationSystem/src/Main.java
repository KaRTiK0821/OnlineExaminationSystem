import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import users.*;
import exam.*;
import result.*;

public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Exam> exams = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Online Examination System!");

        while (true) {
            if (loggedInUser == null) {
                System.out.println("\nMain Menu:");
                System.out.println("1. Register as a Student");
                System.out.println("2. Register as an Administrator");
                System.out.println("3. Login");
                System.out.println("4. Exit");
                System.out.print("Please enter your choice: ");
                
                int choice = -1;
                boolean validChoice = false;

                // Error handling for invalid input
                while (!validChoice) {
                    try {
                        choice = scanner.nextInt();  // Attempt to read an integer input
                        scanner.nextLine();  // Consume newline character
                        if (choice < 1 || choice > 4) {
                            System.out.println("Invalid choice. Please enter a valid option between 1 and 4.");
                        } else {
                            validChoice = true;  // Exit the loop if input is valid
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine();  // Clear the buffer
                    }
                }

                switch (choice) {
                    case 1:
                        registerStudent(scanner);
                        break;
                    case 2:
                        registerAdministrator(scanner);
                        break;
                    case 3:
                        login(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting the system. Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } else if (loggedInUser instanceof Student) {
                studentMenu(scanner);
            } else if (loggedInUser instanceof Administrator) {
                adminMenu(scanner);
            }
        }
    }
    

    private static void registerStudent(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String userID = "STU" + (users.size() + 1);

        Student student = new Student(userID, username, password);
        users.add(student);
        System.out.println("Student registered successfully with User ID: " + userID);
    }

    private static void registerAdministrator(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String userID = "ADM" + (users.size() + 1);

        Administrator admin = new Administrator(userID, username, password);
        users.add(admin);
        System.out.println("Administrator registered successfully with User ID: " + userID);
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.verifyPassword(password)) {
                loggedInUser = user;
                System.out.println("Login successful! Welcome, " + username + "!");
                return;
            }
        }
        System.out.println("Invalid credentials. Please try again.");
    }

    private static void studentMenu(Scanner scanner) {
        System.out.println("\nStudent Menu:");
        System.out.println("1. View Profile");
        System.out.println("2. Edit Profile");
        System.out.println("3. View Available Exams");
        System.out.println("4. Take an Exam");
        System.out.println("5. View Results");
        System.out.println("6. Logout");
        System.out.print("Please enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                loggedInUser.viewProfile();
                break;
            case 2:
                loggedInUser.editProfile(scanner);
                break;
            case 3:
                viewAvailableExams();
                break;
            case 4:
                takeExam(scanner);
                break;
            case 5:
                viewResults();
                break;
            case 6:
                System.out.println("Logging out...");
                loggedInUser = null;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void adminMenu(Scanner scanner) {
        System.out.println("\nAdministrator Menu:");
        System.out.println("1. View Profile");
        System.out.println("2. Edit Profile");
        System.out.println("3. Create an Exam");
        System.out.println("4. Delete an Exam");
        System.out.println("5. Add Questions to an Exam");
        System.out.println("6. Remove Questions from an Exam");
        System.out.println("7. View All Results");
        System.out.println("8. Logout");
        System.out.print("Please enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                loggedInUser.viewProfile();
                break;
            case 2:
                loggedInUser.editProfile(scanner);
                break;
            case 3:
                createExam(scanner);
                break;
            case 4:
                deleteExam(scanner);
                break;
            case 5:
                addQuestionToExam(scanner);
                break;
            case 6:
                removeQuestionFromExam(scanner);
                break;
            case 7:
                viewAllResults();
                break;
            case 8:
                System.out.println("Logging out...");
                loggedInUser = null;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void viewAvailableExams() {
        System.out.println("Available Exams:");
        for (Exam exam : exams) {
            System.out.println("Exam ID: " + exam.getExamID() + ", Title: " + exam.getTitle());
        }
    }

    private static void takeExam(Scanner scanner) {
        // Step 1: Display available exams
        System.out.println("Available Exams:");
        for (int i = 0; i < exams.size(); i++) {
            Exam exam = exams.get(i);
            System.out.println((i + 1) + ". " + exam.getTitle());
        }
    
        // Step 2: Ask the student to choose an exam
        System.out.print("Select the exam you want to take (enter the number): ");
        int examChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        if (examChoice < 1 || examChoice > exams.size()) {
            System.out.println("Invalid choice. Returning to the main menu.");
            return;
        }
    
        // Get the selected exam
        Exam selectedExam = exams.get(examChoice - 1);
    
        // Step 3: Ask questions and collect answers
        int score = 0;  // Track the score for the exam
        for (int i = 0; i < selectedExam.getQuestions().size(); i++) {
            Question question = selectedExam.getQuestions().get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            System.out.print("Your answer: ");
            String studentAnswer = scanner.nextLine();
    
            // Check if the student's answer is correct
            if (question.checkAnswer(studentAnswer)) {
                score++;
            }
        }
    
        // Step 4: Show the result
        double grade = (double) score / selectedExam.getQuestions().size() * 100;  // Correct calculation
        System.out.println("Exam completed. You scored: " + score + "/" + selectedExam.getQuestions().size() + " (" + grade + "%)");
    
        // Optionally, store the result (e.g., in a Result object)
        Result result = new Result((Student) loggedInUser, selectedExam, score);  // Pass the score
        ((Student) loggedInUser).addResult(result);
    }
    

    private static void viewResults() {
        Student student = (Student) loggedInUser;
        
        // Check if the student has any results
        if (student.getResults().isEmpty()) {
            System.out.println("You have not taken any exams yet.");
            return;
        }
        
        System.out.println("Your Exam Results:");
        
        // Loop through all results and display them
        for (Result result : student.getResults()) {
            Exam exam = result.getExam();
            double grade = result.getGrade();
            System.out.println("Exam: " + exam.getTitle());
            System.out.println("Grade: " + grade + "%");
            System.out.println("------------------------------------------------");
        }
    }

    private static void createExam(Scanner scanner) {
        System.out.print("Enter exam title: ");
        String title = scanner.nextLine();
        String examID = "EXM" + (exams.size() + 1);  // Generate a unique exam ID
    
        Exam exam = new Exam(examID, title);
        exams.add(exam);
    
        System.out.println("Exam created with ID: " + examID);
    
        // Option to add questions
        while (true) {
            System.out.println("\nWould you like to add a question to this exam? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                break;
            }
    
            System.out.print("Enter the question text: ");
            String questionText = scanner.nextLine();
            System.out.print("Enter the correct answer: ");
            String correctAnswer = scanner.nextLine();
    
            Question question = new Question(questionText, correctAnswer);
            exam.addQuestion(question);
    
            System.out.println("Question added to the exam.");
        }
    
        System.out.println("Exam creation complete.");
    }

    private static void deleteExam(Scanner scanner) {
        // Display the list of all exams
        System.out.println("Available Exams to Delete:");
        for (int i = 0; i < exams.size(); i++) {
            Exam exam = exams.get(i);
            System.out.println((i + 1) + ". " + exam.getTitle() + " (ID: " + exam.getExamID() + ")");
        }
    
        // Prompt admin to select an exam
        System.out.print("Enter the number of the exam you want to delete (or 0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
    
        if (choice == 0) {
            System.out.println("Delete operation cancelled.");
            return;
        }
    
        // Validate the choice and delete the exam
        if (choice > 0 && choice <= exams.size()) {
            Exam examToDelete = exams.get(choice - 1);
            exams.remove(examToDelete);
            System.out.println("Exam '" + examToDelete.getTitle() + "' has been deleted successfully.");
        } else {
            System.out.println("Invalid choice. Returning to the admin menu.");
        }
    }
    

    private static void addQuestionToExam(Scanner scanner) {
        System.out.print("Enter exam ID to add questions to: ");
        String examID = scanner.nextLine();
        Exam selectedExam = null;
    
        // Find the exam by its ID
        for (Exam exam : exams) {
            if (exam.getExamID().equals(examID)) {
                selectedExam = exam;
                break;
            }
        }
    
        if (selectedExam == null) {
            System.out.println("Exam not found with the given ID.");
            return;
        }
    
        // Prompt to start adding questions to the selected exam
        while (true) {
            System.out.println("\nWould you like to add a question to this exam? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                break;  // Exit the loop if the answer is "no"
            }
    
            System.out.print("Enter the question text: ");
            String questionText = scanner.nextLine();
            System.out.print("Enter the correct answer: ");
            String correctAnswer = scanner.nextLine();
    
            // Create a new question and add it to the exam
            Question question = new Question(questionText, correctAnswer);
            selectedExam.addQuestion(question);
    
            System.out.println("Question added to the exam.");
    
            // Ask if the administrator wants to add another question
            System.out.println("\nWould you like to add another question? (yes/no)");
            String continueResponse = scanner.nextLine();
            if (continueResponse.equalsIgnoreCase("no")) {
                break;  // Exit the loop if they don't want to add more questions
            }
        }
    
        System.out.println("Finished adding questions to the exam.");
    }
    
    
    

    private static void removeQuestionFromExam(Scanner scanner) {
        // Display the list of all exams
        System.out.println("Available Exams to Remove Questions From:");
        for (int i = 0; i < exams.size(); i++) {
            Exam exam = exams.get(i);
            System.out.println((i + 1) + ". " + exam.getTitle() + " (ID: " + exam.getExamID() + ")");
        }
    
        // Prompt admin to select an exam
        System.out.print("Enter the number of the exam you want to remove questions from (or 0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
    
        if (choice == 0) {
            System.out.println("Removing questions operation cancelled.");
            return;
        }
    
        // Validate the choice and proceed
        if (choice > 0 && choice <= exams.size()) {
            Exam selectedExam = exams.get(choice - 1);
    
            // Display all questions in the selected exam
            System.out.println("\nQuestions in Exam: " + selectedExam.getTitle());
            ArrayList<Question> questions = selectedExam.getQuestions();
            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                System.out.println((i + 1) + ". " + question.getQuestionText());
            }
    
            // Prompt admin to select the question to remove
            System.out.print("Enter the number of the question you want to remove (or 0 to cancel): ");
            int questionChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
    
            if (questionChoice == 0) {
                System.out.println("Operation cancelled. Returning to admin menu.");
                return;
            }
    
            // Validate the question choice and remove it
            if (questionChoice > 0 && questionChoice <= questions.size()) {
                Question selectedQuestion = questions.get(questionChoice - 1);
                selectedExam.removeQuestion(selectedQuestion);
                System.out.println("Question removed successfully from exam: " + selectedExam.getTitle());
            } else {
                System.out.println("Invalid question choice. Returning to admin menu.");
            }
        } else {
            System.out.println("Invalid exam choice. Returning to admin menu.");
        }
    }
    

    private static void viewAllResults() {
        System.out.println("\nAll Student Results:");
    
        // Iterate through all users and check if they are students
        for (User user : users) {
            if (user instanceof Student) {
                Student student = (Student) user;
                ArrayList<Result> results = student.getResults();  // Get the student's results
    
                if (results.isEmpty()) {
                    System.out.println("Student " + student.getUsername() + " has no results.");
                } else {
                    System.out.println("Results for Student: " + student.getUsername());
                    for (Result result : results) {
                        Exam exam = result.getExam();
                        double score = result.getScore();
                        double totalScore = exam.getQuestions().size();
                        System.out.println("Exam: " + exam.getTitle() + " | Score: " + score + "/" + totalScore + " (" + result.getGrade() + "%)");
                    }
                }
                System.out.println();  // Print an empty line for better readability
            }
        }
    }
    
}