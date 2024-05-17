package FTbackend.finance.controller;

import FTbackend.finance.business.service.UserService;
import FTbackend.finance.data.domain.User;
import FTbackend.finance.data.domain.Calculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerNewUser(user.getUsername(), user.getEmail(), user.getPassword());
        Map<String, Object> response = new HashMap<>();
        response.put("id", registeredUser.getId());
        response.put("username", registeredUser.getUsername());
        response.put("email", registeredUser.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            Map<String, Object> response = userService.loginUser(username, password);
            System.out.println("Login response: " + response);  // Debug log
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());  // Debug log
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Login failed", "message", e.getMessage()));
        }
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            List<Calculation> calculations = userService.getUserCalculations(id);
            if (user != null) {
                Map<String, Object> result = Map.of(
                        "id", user.getId(),
                        "username", user.getUsername(),
                        "email", user.getEmail(),
                        "calculations", calculations
                );
                System.out.println("Profile fetched: " + result);
                return ResponseEntity.ok(result);
            } else {
                System.out.println("User not found for ID: " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            System.out.println("Error retrieving user profile: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
