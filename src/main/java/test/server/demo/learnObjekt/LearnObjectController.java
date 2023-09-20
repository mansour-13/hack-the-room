package test.server.demo.learnObjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import test.server.demo.GreetingResponseDTO;
import test.server.demo.user.User;

import java.sql.Time;
import java.util.Optional;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;

@RestController
public class LearnObjectController {
    private final LearnObjectRepository learnObjectRepository;

    public LearnObjectController(LearnObjectRepository learnObjectRepository) {
        this.learnObjectRepository = learnObjectRepository;
        LearnObject temp1 = new LearnObject(1,"Waffenkammer","","","","",180,"/images/learnObject1_Waffenkammer.jpg");
        learnObjectRepository.save(temp1);
/*        LearnObject temp2 = new LearnObject(1,"test1","","","","",180,"");
        learnObjectRepository.save(temp2);
        LearnObject temp3 = new LearnObject(1,"test1","","","","",180,"");
        learnObjectRepository.save(temp3);
        LearnObject temp4 = new LearnObject(1,"test1","","","","",180,"");
        learnObjectRepository.save(temp4);
        LearnObject temp5 = new LearnObject(1,"test1","","","","",180,"");
        learnObjectRepository.save(temp5);
        LearnObject temp6 = new LearnObject(1,"test1","","","","",180,"");
        learnObjectRepository.save(temp6);*/
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
