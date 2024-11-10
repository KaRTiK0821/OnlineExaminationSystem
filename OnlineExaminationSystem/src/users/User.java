package users;
import java.util.Scanner;
public abstract class User {
    protected String userID;
    protected String username;
    protected String password;
    
    public User(String userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
    
    public void viewProfile() {
        System.out.println("User ID: " + userID);
        System.out.println("Username: " + username);
    }
    
        // Basic edit profile functionality
    public void editProfile(Scanner scanner) {
        System.out.print("Enter new username (leave blank to keep current): ");
        String newUsername = scanner.nextLine();
        if (!newUsername.isEmpty()) {
            this.username = newUsername;
            System.out.println("Username updated.");
        }
    
        System.out.print("Enter new password (leave blank to keep current): ");
        String newPassword = scanner.nextLine();
        if (!newPassword.isEmpty()) {
            this.password = newPassword;
            System.out.println("Password updated.");
        }
    }
}