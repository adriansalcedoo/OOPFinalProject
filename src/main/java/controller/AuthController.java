
package controller;

import model.User;

import java.util.HashMap;

public class AuthController {
    private static HashMap<String, User> users = new HashMap<>();

    public boolean login(String username, String password) {
        User user = users.get(username);
        return user != null && user.checkPassword(password);
    }

    public void signup(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
            System.out.println("User created.");
        } else {
            System.out.println("User already exists.");
        }
    }
}
