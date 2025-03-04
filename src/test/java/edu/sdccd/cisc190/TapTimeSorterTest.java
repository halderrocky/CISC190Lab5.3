package edu.sdccd.cisc190;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
class LapTimeSorterTest {
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        assertTrue(LapTimeSorter.EXTEND_ARRAY_INCREMENT <= 5);

        LapTimeSorter.add(0, "Alice", 1.12f);
        LapTimeSorter.add(5, "Bob", 1.15f);
        LapTimeSorter.add(2, "Carl", 1.25f);
        LapTimeSorter.add(4, "Dave", 1.13f);
        LapTimeSorter.add(3, "Elza", 1.77f);
        LapTimeSorter.add(1, "Frank", 1.43f);
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testSortArraysByLapTime() {
        LapTimeSorter.printArrays();
        LapTimeSorter.sortArraysByLapTime();
        LapTimeSorter.printArrays();
        String expectedOutput =
                "-------------------------------------------------" + System.lineSeparator() +
                        "| Name                             | Lap Time   |" + System.lineSeparator() +
                        "-------------------------------------------------" + System.lineSeparator() +
                        "| Alice                            | 1.12       |" + System.lineSeparator() +
                        "| Frank                            | 1.43       |" + System.lineSeparator() +
                        "| Carl                             | 1.25       |" + System.lineSeparator() +
                        "| Elza                             | 1.77       |" + System.lineSeparator() +
                        "| Dave                             | 1.13       |" + System.lineSeparator() +
                        "| Bob                              | 1.15       |" + System.lineSeparator() +
                        "-------------------------------------------------" + System.lineSeparator() +
                        "-------------------------------------------------" + System.lineSeparator() +
                        "| Name                             | Lap Time   |" + System.lineSeparator() +
                        "-------------------------------------------------" + System.lineSeparator() +
                        "| Alice                            | 1.12       |" + System.lineSeparator() +
                        "| Dave                             | 1.13       |" + System.lineSeparator() +
                        "| Bob                              | 1.15       |" + System.lineSeparator() +
                        "| Carl                             | 1.25       |" + System.lineSeparator() +
                        "| Frank                            | 1.43       |" + System.lineSeparator() +
                        "| Elza                             | 1.77       |" + System.lineSeparator() +
                        "-------------------------------------------------" +  System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}

