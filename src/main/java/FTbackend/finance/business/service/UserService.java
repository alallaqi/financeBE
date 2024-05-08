package FTbackend.finance.business.service;

import FTbackend.finance.data.domain.User;
import FTbackend.finance.data.repository.UserRepository;
import FTbackend.finance.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User registerNewUser(String username, String email, String password) {
        userRepository.findByUsername(username)
                .or(() -> userRepository.findByEmail(email))
                .ifPresent(s -> {
                    throw new IllegalArgumentException("User already exists");
                });

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true, true, true, true,
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    public Map<String, Object> loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        if (passwordEncoder.matches(password, user.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtUtil.generateToken(user.getUsername()));
            String token = jwtUtil.generateToken(user.getUsername());
            response.put("token", token);
            response.put("user", Map.of("id", user.getId(), "username", user.getUsername(), "email", user.getEmail()));
            return response;
        } else {
            throw new IllegalArgumentException("Incorrect username or password");
        }
    }
}
