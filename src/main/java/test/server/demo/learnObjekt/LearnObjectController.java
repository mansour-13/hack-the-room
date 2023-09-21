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
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;

@RestController
public class LearnObjectController {
    private final LearnObjectRepository learnObjectRepository;

    public LearnObjectController(LearnObjectRepository learnObjectRepository) {
        this.learnObjectRepository = learnObjectRepository;
        LearnObject temp1 = new LearnObject(
                1
                ,"Waffenkammer"
                ,""
                ,Arrays.asList( "As you enter the spaceship's control room, the oxygen levels alarm starts" +
                " to sound.", "A holographic display shows the oxygen levels of five different compartments" +
                " in the spaceship.", " You need to restore and balance these levels before the oxygen runs" +
                " out.")
                ,"let reactorTemperatures = [100, 105, 110, 115, 108, 102, 98, 103, 109, 104];\nlet totalTemp = 0;\nfor(let i=0; i<reactorTemperatures.length; i++) {\n    totalTemp += reactorTemperatures[i];\n}\nlet avgTemp = totalTemp / reactorTemperatures.length;\nfor(let i=0; i<reactorTemperatures.length; i++) {\n    if(reactorTemperatures[i] > avgTemp + 5) {\n        reactorTemperatures[i] = avgTemp;\n    }\n}"
                ,"let oxygenLevels = [85, 90, 75, 80, 78]; // These are the initial oxygen levels in %\n\n// TODO: " +
                "Write a loop to calculate the average oxygen level\n\n// TODO: Write another loop to refill" +
                " any compartment below the average level to the average level\n\n// Start coding here"
                ,180
                ,"/images/learnObject1_Waffenkammer.jpg");
        learnObjectRepository.save(temp1);
        LearnObject temp2 = new LearnObject(
                2
                ,"Objekt2"
                ,""
                ,Arrays.asList("As you move to the spaceship's reactor core, you notice it is overheating."
                , " There are ten reactors in total, and each reactor's temperature is displayed on the screen."
                , " You need to stabilize the reactors by ensuring that none of them exceed the maximum safe temperature.")

                ,"let reactorTemperatures = [100, 105, 110, 115, 108, 102, 98, 103, 109, 104];\nlet totalTemp = 0;\nfor(let i=0; i<reactorTemperatures.length; i++) {\n    totalTemp += reactorTemperatures[i];\n}\nlet avgTemp = totalTemp / reactorTemperatures.length;\nfor(let i=0; i<reactorTemperatures.length; i++) {\n    if(reactorTemperatures[i] > avgTemp + 5) {\n        reactorTemperatures[i] = avgTemp;\n    }\n}"

                ,"let reactorTemperatures = [100, 105, 110, 115, 108, 102, 98, 103, 109, 104]; // Temperatures in degrees\n\n// TODO: Write a loop to calculate the average reactor temperature\n\n// TODO: Write another loop to cool down any reactor that exceeds the average temperature by more than 5 degrees\n\n// Start coding here"
                ,180
                ,"/images/picture2.jpeg");
        learnObjectRepository.save(temp2);
        LearnObject temp3 = new LearnObject(
                3
                ,"Objekt3"
                ,""
                ,Arrays.asList("You approach the spaceship's fuel chamber."
                , " The ship has multiple engines and different fuel requirements."
                , " To escape the current predicament, you must ensure each engine has the exact amount of fuel it needs without any excess.")

                ,"let engineFuel = [50, 45, 48, 47, 49, 51, 52, 48, 50, 46];\nlet totalFuel = 500;\nlet fuelPerEngine = totalFuel / engineFuel.length;\nfor(let i=0; i<engineFuel.length; i++) {\n    let excessFuel = engineFuel[i] - fuelPerEngine;\n    if(excessFuel > 0) {\n        for(let j=0; j<engineFuel.length; j++) {\n            if(engineFuel[j] < fuelPerEngine) {\n                let requiredFuel = fuelPerEngine - engineFuel[j];\n                let transferFuel = Math.min(excessFuel, requiredFuel);\n                engineFuel[i] -= transferFuel;\n                engineFuel[j] += transferFuel;\n                excessFuel -= transferFuel;\n            }\n            if(excessFuel <= 0) break;\n        }\n    }\n}"
                ,"let engineFuel = [50, 45, 48, 47, 49, 51, 52, 48, 50, 46]; // Fuel levels in liters\nlet totalFuel = 500;" +
                " // Total available fuel in liters\n\n// TODO: Write a loop to distribute the total fuel equally among all" +
                " the engines\n\n// TODO: Write another loop to check and redistribute excess fuel from engines with more " +
                "fuel to those with less\n\n// Start coding here"
                ,180
                ,"/images/picture3.jpeg");
        learnObjectRepository.save(temp3);
        LearnObject temp4 = new LearnObject(
                4
                ,"Objekt4"
                ,""
                ,Arrays.asList("The final barrier to leaving the danger zone is to activate the spaceship's thrusters in the correct sequence."
                , " The sequence is based on the power level of each thruster."
                , " You must activate them in descending order of power.")

                ,""
                ,"let thrusterPowers = [2500, 2600, 2450, 2550, 2480, 2520, 2470, 2530, 2490, 2510];" +
                "\nfor(let i=0; i<thrusterPowers.length; i++) {\n    for(let j=i+1; j<thrusterPowers.length; j++) " +
                "{\n        if(thrusterPowers[i] < thrusterPowers[j]) {\n            let temp = thrusterPowers[i]" +
                ";\n            thrusterPowers[i] = thrusterPowers[j];\n            thrusterPowers[j] = temp" +
                ";\n        }\n    }\n}\nconsole.log(thrusterPowers);"
                ,180
                ,"/images/picture4.jpeg");
        learnObjectRepository.save(temp4);
        LearnObject temp5 = new LearnObject(
                5
                ,"Objekt5"

                ,""

                ,Arrays.asList("Decode an ASCII encoded message"
                , " to find the passphrase.")

                ,"function escapeRoom() {\n    let secret = [104, 101, 108, 108, 111, 95, 119, 111, 114, 108, 100];" +
                "\n    return secret.map(char => String.fromCharCode(char)).join('');\n}\nconsole.log(escapeRoom());"

                ,"// Hint: Start here. The numbers above represent ASCII values. Convert them to characters to reveal " +
                "the passphrase.\n// The encrypted passphrase is hidden within this function.\n// Can you decode it and " +
                "find your way out?\nfunction escapeRoom() {\n    " +
                "let secret = [104, 101, 108, 108, 111, 95, 119, 111, 114, 108, 100];\n\n    " +
                "// TODO: Write a function that decodes the secret and returns the passphrase as a string.\n\n    " +
                "return \"???\";  // This should return the decoded passphrase.\n}\n\n" +
                "// Once you've solved the function, run the line below to check if you've found the correct passphrase." +
                "\nconsole.log(escapeRoom());  // If correct, this will show the passphrase."

                ,180

                ,"/images/picture5.jpeg");
        learnObjectRepository.save(temp5);

        LearnObject temp6 = new LearnObject(
                6
                ,"Object6"
                ,""
                ,Arrays.asList("The spaceship's reactor is on the brink of a catastrophic meltdown."
                ,"You find yourself in front of the main control panel, displaying the energy levels of different modules connected to the reactor core."
                ,"To safely evacuate, you need to divert excess energy from the overloaded modules to the modules that are under capacity, ensuring that no module exceeds its safety limit."
                ,"If you succeed, the reactor's self-destruction sequence will be temporarily halted, buying you just enough time to escape. Failure is not an option!")

                ,"let modulesEnergy = [150, 80, 220, 60, 175, 110, 90, 185, 130, 200];" +
                "\nlet safetyLimit = 150;\nfor(let i=0; i<modulesEnergy.length; i++) " +
                "{\n    let excessEnergy = modulesEnergy[i] - safetyLimit;\n    " +
                "if(excessEnergy > 0) {\n        for(let j=0; j<modulesEnergy.length; j++) {\n            " +
                "if(modulesEnergy[j] < safetyLimit) {\n                " +
                "let requiredEnergy = safetyLimit - modulesEnergy[j];\n                " +
                "let transferEnergy = Math.min(excessEnergy, requiredEnergy);\n                " +
                "modulesEnergy[i] -= transferEnergy;\n                modulesEnergy[j] += transferEnergy;\n                " +
                "excessEnergy -= transferEnergy;\n            }\n            if(excessEnergy <= 0) break;\n        }\n    }\n}\n" +
                "console.log(modulesEnergy);"

                ,"let modulesEnergy = [150, 80, 220, 60, 175, 110, 90, 185, 130, 200]; // Energy levels in joules\nlet safetyLimit = 150; " +
                "// Safety limit in joules\n\n// TODO: Write a loop (or multiple loops) to redistribute energy such that no module exceeds " +
                "the safety limit. Remember: only excess energy can be redistributed.\n\n// Start coding here"
                ,180
                ,"/images/picture6.jpeg");

        learnObjectRepository.save(temp6);
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
