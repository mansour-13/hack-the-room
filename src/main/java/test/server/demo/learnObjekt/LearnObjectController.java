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
                1,
                "Waffenkammer",
                "",
                Arrays.asList(
                        "Your task is to implement a Java method that calculates the factorial of a number.",
                        "As a hint, the factorial of 0 is 1.",
                        "For any other positive integer n, the factorial of n is n multiplied by the factorial of (n-1)."
                ),
                "public class FactorialCalculator {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(factorial(5));\n" +
                        "        }\n" +
                        "        public static int factorial(int n) {\n" +
                        "            if (n == 0) {\n" +
                        "                return 1;\n" +
                        "                } else {\n" +
                        "                    return n * factorial(n - 1);\n" +
                        "                    }\n" +
                        "                }\n" +
                        "}",
                "public class FactorialCalculator {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        // TODO: Test your factorial method.\n" +
                        "        // Call the factorial method with a few test numbers and print the results.\n" +
                        "        // Example:\n" +
                        "        // System.out.println(factorial(5)); // Expected output: 120\n\n" +
                        "        // start coding here\n" +
                        "    }\n" +
                        "\n" +
                        "    public static int factorial(int n) {\n" +
                        "        // TODO: Implement this method using recursion.\n" +
                        "        // Remember: \n" +
                        "        // - factorial(0) = 1\n" +
                        "        // - factorial(n) = n * factorial(n-1) for n > 0\n\n" +
                        "        // start coding here\n" +
                        "        return 0; // Change this to return the correct value\n" +
                        "    }\n" +
                        "}",
                180,
                "/images/learnObject1_Waffenkammer.jpg");
        learnObjectRepository.save(temp1);
        LearnObject temp2 = new LearnObject(
                2,
                "Grade Averager",
                "",
                Arrays.asList(
                        "You are a teacher and have just finished grading your students.",
                        "You want to get an average of all the grades to see how the class performed overall.",
                        "Write a Java program to calculate the average."
                ),
                "public class GradeAverager {\n" +
                        "    \n" +
                        "    public static void main(String[] args) {\n" +
                        "        List<Integer> grades = Arrays.asList(85, 90, 78, 92, 88, 76, 95, 87);\n" +
                        "        double average = calculateAverage(grades);\n" +
                        "        System.out.println(average);\n" +
                        "        } \n" +
                        "        \n" +
                        "    public static double calculateAverage(List<Integer> grades) {\n" +
                        "        int sum = 0;\n" +
                        "        for (int grade : grades) {\n" +
                        "            sum += grade; \n" +
                        "        } \n" +
                        "        return (double) sum / grades.size(); \n" +
                        "        \n" +
                        "    } \n" +
                        "    \n" +
                        "}",
                "import java.util.Arrays;\n" +
                "public class GradeAverager {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        List<Integer> grades = Arrays.asList(85, 90, 78, 92, 88, 76, 95, 87);\n" +
                        "        // TODO: Calculate the average of grades and print it.\n" +
                        "        // Use the calculateAverage method to do this.\n\n" +
                        "        // start coding here\n" +
                        "    }\n" +
                        "\n" +
                        "    public static double calculateAverage(List<Integer> grades) {\n" +
                        "        int sum = 0;\n" +
                        "        // TODO: Write a loop to sum up the grades.\n\n" +
                        "        // start coding here\n" +
                        "        return (double) sum / grades.size();  // This calculates the average\n" +
                        "    }\n" +
                        "}",
                        180,
                        "/images/picture2.jpeg");
        learnObjectRepository.save(temp2);
        LearnObject temp3 = new LearnObject(
                3,
                "SpaceStation Sequencer",
                "",
                Arrays.asList(
                        "As you move deeper into the spacestation, you discover a locked door. A holographic interface appears in front of you, prompting you to solve a sequence to gain entry.",
                        "The sequence seems to follow the Fibonacci pattern: 0, 1, 1, 2, 3, 5, 8, ...",
                        "You need to provide the next number in the sequence to proceed. You decide to write a recursive function to find any nth number in the Fibonacci sequence."
                ),
                "public class FibonacciCalculator {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(fibonacci(7)); // Expected output: 13\n" +
                        "    }\n" +
                        "\n" +
                        "    public static int fibonacci(int n) {\n" +
                        "        if (n <= 1) return n;\n" +
                        "        return fibonacci(n-1) + fibonacci(n-2);\n" +
                        "    }\n" +
                        "}",
                "public class FibonacciCalculator {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        // TODO: Test your fibonacci method.\n" +
                        "        // Call the fibonacci method with a few test numbers and print the results.\n" +
                        "        // Example:\n" +
                        "        // System.out.println(fibonacci(7)); // Expected output: 13\n\n" +
                        "        // start coding here\n" +
                        "    }\n" +
                        "\n" +
                        "    public static int fibonacci(int n) {\n" +
                        "        // TODO: Implement this method using recursion.\n" +
                        "        // Remember: \n" +
                        "        // - fibonacci(0) = 0\n" +
                        "        // - fibonacci(1) = 1\n" +
                        "        // - fibonacci(n) = fibonacci(n-1) + fibonacci(n-2) for n > 1\n\n" +
                        "        // start coding here\n" +
                        "        return 0; // Change this to return the correct value\n" +
                        "    }\n" +
                        "}",
                210,
                "/images/picture3.jpeg");
        learnObjectRepository.save(temp3);
        LearnObject temp4 = new LearnObject(
                4,
                "SpaceStation Array Multiplier",
                "",
                Arrays.asList(
                        "You've reached the engineering deck of the spacestation. Another holographic interface stands in your way, displaying a grid of numbers, reminiscent of an array.",
                        "To bypass this system, it prompts you: 'Multiply every element in this array by a given factor'.",
                        "To quickly get through, you decide to write a method that multiplies each element of an integer array by a given factor."
                ),
                "public class ArrayMultiplier {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        int[] arr = {2, 4, 6, 8};\n" +
                        "        int factor = 3;\n" +
                        "        int[] result = multiplyArray(arr, factor);\n" +
                        "        for(int num : result) {\n" +
                        "            System.out.print(num + \" \"); // Expected output: 6 12 18 24 \n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    public static int[] multiplyArray(int[] arr, int factor) {\n" +
                        "        for(int i = 0; i < arr.length; i++) {\n" +
                        "            arr[i] *= factor;\n" +
                        "        }\n" +
                        "        return arr;\n" +
                        "    }\n" +
                        "}",
                "public class ArrayMultiplier {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        // TODO: Test your multiplyArray method.\n" +
                        "        // Multiply each element of an integer array by a given factor and print the results.\n" +
                        "        // Example:\n" +
                        "        // int[] arr = {2, 4, 6, 8};\n" +
                        "        // int factor = 3;\n" +
                        "        // Expected output: 6 12 18 24\n\n" +
                        "        // start coding here\n" +
                        "    }\n" +
                        "\n" +
                        "    public static int[] multiplyArray(int[] arr, int factor) {\n" +
                        "        // TODO: Implement this method.\n" +
                        "        // Each element in the array should be multiplied by the factor.\n\n" +
                        "        // start coding here\n" +
                        "        return arr; // Change this to return the modified array\n" +
                        "    }\n" +
                        "}",
                250,
                "/images/picture4.jpeg");
        learnObjectRepository.save(temp4);
        LearnObject temp5 = new LearnObject(
                5,
                "SpaceStation Fuel Calculator",
                "",
                Arrays.asList(
                        "After bypassing the engineering deck, you arrive at the fuel chamber of the spacestation. A digital meter indicates the total amount of fuel and the number of engines that need to be fed.",
                        "A voice echoes: 'To power up the engines evenly, determine how much fuel each engine should receive'.",
                        "You quickly decide to write a method to divide the total fuel evenly among the engines."
                ),
                "public class FuelCalculator {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        int totalFuel = 1000;\n" +
                        "        int numberOfEngines = 4;\n" +
                        "        int fuelPerEngine = divideFuel(totalFuel, numberOfEngines);\n" +
                        "        System.out.println(fuelPerEngine); // Expected output: 250\n" +
                        "    }\n" +
                        "\n" +
                        "    public static int divideFuel(int totalFuel, int numberOfEngines) {\n" +
                        "        return totalFuel / numberOfEngines;\n" +
                        "    }\n" +
                        "}",
                "public class FuelCalculator {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        // TODO: Test your divideFuel method.\n" +
                        "        // Divide the total fuel evenly among the engines and print the results.\n" +
                        "        // Example:\n" +
                        "        // int totalFuel = 1000;\n" +
                        "        // int numberOfEngines = 4;\n" +
                        "        // Expected output: 250\n\n" +
                        "        // start coding here\n" +
                        "    }\n" +
                        "\n" +
                        "    public static int divideFuel(int totalFuel, int numberOfEngines) {\n" +
                        "        // TODO: Implement this method.\n" +
                        "        // The method should divide the totalFuel evenly among the numberOfEngines.\n\n" +
                        "        // start coding here\n" +
                        "        return 0; // Change this to return the correct value\n" +
                        "    }\n" +
                        "}",
                270,
                "/images/picture5.jpeg");
        learnObjectRepository.save(temp5);

        LearnObject temp6 = new LearnObject(
                6,
                "SpaceStation Storage Checker",
                "",
                Arrays.asList(
                        "On your journey through the spacestation, you stumble upon a row of storage units. Each storage unit has a numeric code and only units with an odd code are accessible.",
                        "To verify if a storage unit is accessible, the station's computer system prompts you to check if the code of a storage unit is odd.",
                        "You decide to write a method to determine if a given storage unit's code is odd using the modulus operator."
                ),
                "public class StorageChecker {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        int unitCode = 105;\n" +
                        "        boolean isOdd = isCodeOdd(unitCode);\n" +
                        "        System.out.println(isOdd); // Expected output: true\n" +
                        "    }\n" +
                        "\n" +
                        "    public static boolean isCodeOdd(int unitCode) {\n" +
                        "        return unitCode % 2 != 0;\n" +
                        "    }\n" +
                        "}",
                "public class StorageChecker {\n" +
                        "\n" +
                        "    public static void main(String[] args) {\n" +
                        "        // TODO: Test your isCodeOdd method.\n" +
                        "        // Verify if the provided storage unit code is odd and print the results.\n" +
                        "        // Example:\n" +
                        "        // int unitCode = 105;\n" +
                        "        // Expected output: true\n\n" +
                        "        // start coding here\n" +
                        "    }\n" +
                        "\n" +
                        "    public static boolean isCodeOdd(int unitCode) {\n" +
                        "        // TODO: Implement this method.\n" +
                        "        // The method should return true if the code is odd and false otherwise.\n\n" +
                        "        // start coding here\n" +
                        "        return false; // Change this to return the correct value\n" +
                        "    }\n" +
                        "}",
                320,
                "/images/picture6.jpeg");
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
