package test.server.demo.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;

        User user1 = new User(2, "user1", "123", true, "ROLE_USER", 3, 1);
        userRepository.save(user1);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = this.userRepository.findByUserName(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User under this username found.");
    }

    @GetMapping("/score")
    public ResponseEntity<List<User>> findAllByOrderByScore() {
        List<User> temp = this.userRepository.findAllByOrderByScore();
        Collections.reverse(temp);
        if (temp.size() > 15) {
            temp = temp.subList(0, 14);
        }
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    @PostMapping("/score/{username}/{id_learnObject}/{score}")
    public void setScore(@PathVariable String username, @PathVariable int id_learnObject, @PathVariable int score) {
        User user = this.userRepository.findByUserName(username).orElseThrow();
        user.setLevelScore(id_learnObject, score);
        this.userRepository.save(user);
    }

}
