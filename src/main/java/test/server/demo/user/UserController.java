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

        User user1 = new User(1,"Frank", "$2a$10$bIz3AKMYei4tDzm3ViUfi.orP956BME83WxszZHh9GMaqxz.lpVy2", true, "ROLE_USER", 1, 1, 600);
        User user2 = new User(2,"Sarah", "$2a$10$bIz3AKMYei4tDzm3ViUfi.orP956BME83WxszZHh9GMaqxz.lpVy2", true, "ROLE_USER", 3, 2, 555);
        User user3 = new User(3,"Angela", "$2a$10$bIz3AKMYei4tDzm3ViUfi.orP956BME83WxszZHh9GMaqxz.lpVy2", true, "ROLE_USER", 6, 3, 346);
        User user4 = new User(4,"James", "$2a$10$bIz3AKMYei4tDzm3ViUfi.orP956BME83WxszZHh9GMaqxz.lpVy2", true, "ROLE_USER", 1, 1, 125);
        User user5 = new User(5,"John", "$2a$10$bIz3AKMYei4tDzm3ViUfi.orP956BME83WxszZHh9GMaqxz.lpVy2", true, "ROLE_USER", 1, 1, 855);
        User user6 = new User(6,"Sonia", "$2a$10$bIz3AKMYei4tDzm3ViUfi.orP956BME83WxszZHh9GMaqxz.lpVy2", true, "ROLE_USER", 1, 1, 50);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);

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
    public ResponseEntity<User> setScore(@RequestBody UserDTO request) {
        System.out.println(request);
        User user = this.userRepository.findByUserName(request.getUsername()).orElseThrow();
        user.setLevelScore(request.getId_learnObject(), request.getScore());
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
