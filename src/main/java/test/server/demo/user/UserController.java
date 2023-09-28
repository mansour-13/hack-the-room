package test.server.demo.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        User user2 = new User(3, "user2", "123", true, "ROLE_USER", 3, 1);
        User user3 = new User(4, "user3", "123", true, "ROLE_USER", 3, 1);
        User user4 = new User(5, "user4", "123", true, "ROLE_USER", 3, 1);
        User user5 = new User(6, "user5", "123", true, "ROLE_USER", 3, 1);
        User user6 = new User(7, "user6", "123", true, "ROLE_USER", 3, 1);
        User user7 = new User(8, "user7", "123", true, "ROLE_USER", 3, 1);
        User user8 = new User(9, "use8", "123", true, "ROLE_USER", 3, 1);
        User user9 = new User(10, "user9", "123", true, "ROLE_USER", 3, 1);
        User user10 = new User(11, "us1er1", "123", true, "ROLE_USER", 3, 1);
        User use11 = new User(12, "us2er1", "123", true, "ROLE_USER", 3, 1);
        User use12 = new User(13, "use3r1", "123", true, "ROLE_USER", 3, 1);
        User use13 = new User(14, "us4er1", "123", true, "ROLE_USER", 3, 1);
        User use14 = new User(15, "us5er1", "123", true, "ROLE_USER", 3, 1);
        User use15 = new User(16, "us6er1", "123", true, "ROLE_USER", 3, 1);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
        userRepository.save(user10);
        userRepository.save(use11);
        userRepository.save(use12);
        userRepository.save(use13);
        userRepository.save(use14);
        userRepository.save(use15);
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
            temp = temp.subList(0, 15);
        }
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @PostMapping("/score/{username}/{id_learnObject}/{score}")
    public ResponseEntity<User> setScore(@RequestBody UserDTO request) {
        System.out.println(request);
        User user = this.userRepository.findByUserName(request.getUsername()).orElseThrow();
        if (user.getLevelScore(request.getId_learnObject()) < request.getScore()) {
            user.setLevelScore(request.getId_learnObject(), request.getScore());
        }
//        user.setLevelScore(request.getId_learnObject(), request.getScore());
        this.userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/updateLife")
    public ResponseEntity<User> updateUserLife(@RequestBody User user) {
        Optional<User> existingUser = this.userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            User savedUser = existingUser.get();
            savedUser.setLife(user.getLife());
            userRepository.save(savedUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User with this username found.");
    }

    @PutMapping("/user/updateImage")
    public ResponseEntity<User> updateImage(@RequestBody User user) {
        Optional<User> existingUser = this.userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            User savedUser = existingUser.get();
            savedUser.setProfileImage(user.getProfileImage());
            userRepository.save(savedUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User with this username found.");
    }

    @PutMapping("/user/updateIdxActualLearnObject")
    public ResponseEntity<User> updateUserIdxActualLearnObject(@RequestBody User user) {
        Optional<User> existingUser = this.userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            User savedUser = existingUser.get();
            savedUser.setIdxActualLearnObject(user.getIdxActualLearnObject());
            userRepository.save(savedUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User with this username found.");
    }

}
