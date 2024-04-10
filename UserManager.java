import java.util.HashMap;

public class UserManager {

    private HashMap<String, String> users;

    public UserManager() {
        this.users = new HashMap<>();
    }

    public void addUser(String email, String password) {
        users.put(email, password);
    }

    public boolean isValidUser(String email, String password) {
        return users.containsKey(email) && users.get(email).equals(password);
    }
}
