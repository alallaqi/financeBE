package FTbackend.finance.controller;

import FTbackend.finance.data.domain.User;
import FTbackend.finance.business.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import FTbackend.finance.data.domain.User;
import FTbackend.finance.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerNewUser(user.getUsername(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok(registeredUser);
    }

    // Endpoint for login will be handled by Spring Security
}