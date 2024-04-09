package application;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Authenticator {
    public boolean authenticate(String email, String password) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("users.txt"));
            for (String line : lines) {
                String[] parts = line.split(", ");
                String emailPart = parts[1].split(": ")[1];
                String passwordPart = parts[2].split(": ")[1];
                
                if (email.equals(emailPart) && password.equals(passwordPart)) {
                    return true; // Exact match found
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
        return false;
    }
}
