import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        outContent.reset(); // Clear output stream before each test
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void assertOutputMatches(String expected) {
        assertEquals(expected.replace("\r\n", "\n").trim(), outContent.toString().replace("\r\n", "\n").trim());
    }

    @Test
    void testNormalCaseAddAndServe() {
        String input = "1\nAlice\n1\nBob\n2\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});

        String expectedOutput = """

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Alice has been added to the waiting list.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Bob has been added to the waiting list.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Alice has been served.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Exiting...
                """;

        assertOutputMatches(expectedOutput);
    }

    @Test
    void testNormalCaseCheckSizeAndDisplay() {
        String input = "1\nAlice\n1\nBob\n4\n5\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});

        String expectedOutput = """

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Alice has been added to the waiting list.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Bob has been added to the waiting list.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Waiting list size: 2

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Waiting List:
                1. Alice
                2. Bob

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Exiting...
                """;
        assertOutputMatches(expectedOutput);
    }

    @Test
    void testNormalCaseCheckIfInList() {
        String input = "1\nAlice\n3\nAlice\n3\nBob\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});

        String expectedOutput = """

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Alice has been added to the waiting list.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Alice is in the waiting list.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Bob is not in the waiting list.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Exiting...
                """;
        assertOutputMatches(expectedOutput);
    }

    @Test
    void testEdgeCaseAddDuplicate() {
        String input = "1\nAlice\n1\nAlice\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});

        String expectedOutput = """

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Alice has been added to the waiting list.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Alice is already in the waiting list.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Exiting...
                """;
        assertOutputMatches(expectedOutput);
    }

    @Test
    void testEdgeCaseServeEmptyList() {
        String input = "2\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});

        String expectedOutput = """

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: The waiting list is empty.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Exiting...
                """;
        assertOutputMatches(expectedOutput);
    }

    @Test
    void testEdgeCaseInvalidChoice() {
        String input = "7\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});

        String expectedOutput = """

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Invalid choice. Please try again.

                Waiting List Manager Menu:
                1. Add Person
                2. Serve Person
                3. Check if Person is in List
                4. Waiting List Size
                5. Display Waiting List
                6. Exit
                Enter your choice: Exiting...
                """;
        assertOutputMatches(expectedOutput);
    }
}