package test.server.demo.learnObjekt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import test.server.demo.GreetingResponseDTO;
import test.server.demo.user.User;

import java.util.Optional;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;

@RestController
public class LearnObjectController {
    private final LearnObjectRepository learnObjectRepository;

    public LearnObjectController(LearnObjectRepository learnObjectRepository) {
        this.learnObjectRepository = learnObjectRepository;
    }

    @GetMapping("/level/{id}")
    public ResponseEntity<LearnObject> getLearnObjerctById(@PathVariable int id) {
        Optional<LearnObject> temp =  this.learnObjectRepository.findById(id);
        if (temp.isPresent()){
            return new ResponseEntity<>(temp.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No meme under this id found.");
    }
}
