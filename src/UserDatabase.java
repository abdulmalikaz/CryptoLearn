import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private Map<String, User> users = new HashMap<>();

    public boolean addUser(String username, String passwordHash) {
        if (users.containsKey(username)) {
            return false; // User already exists
        }

        users.put(username, new User(username, passwordHash));
        return true;
    }

    public User getUser(String username) {
        return users.get(username);
    }
}
