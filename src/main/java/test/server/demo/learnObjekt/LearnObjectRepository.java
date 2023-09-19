package test.server.demo.learnObjekt;

import org.springframework.data.jpa.repository.JpaRepository;
import test.server.demo.user.User;

import java.util.Optional;

public interface LearnObjectRepository extends JpaRepository<LearnObject, Integer> {
    Optional<LearnObject> findById(String id);

}
