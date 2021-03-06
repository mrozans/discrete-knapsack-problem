package pl.edu.pw.elka.pszt.knapsack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class KnapsackDataLoaderTest {
    private final String PATH = "src/test/resources/pl.edu.pw.elka.pszt.knapsack.model/inputLoader/";

    String loadFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine())
            stringBuilder.append(scanner.nextLine()).append("\n");
        scanner.close();
        return stringBuilder.toString();
    }

    @Nested
    class CorrectInput {
        @Test
        @DisplayName("4 items")
        void test1() throws IOException {
            String path = PATH + "correctInput/test1.txt";
            test(loadFile(path).split("\n"), new KnapsackDataLoader(path).load());
        }

        @Test
        @DisplayName("no items")
        void test2() throws IOException {
            String path = PATH + "correctInput/test2.txt";
            test(loadFile(path).split("\n"), new KnapsackDataLoader(path).load());
        }

        @Test
        @DisplayName("1 item")
        void test3() throws IOException {
            String path = PATH + "correctInput/test3.txt";
            test(loadFile(path).split("\n"), new KnapsackDataLoader(path).load());
        }

        void test(String[] split, KnapsackObjects knapsackObjects) {
            assertEquals(Long.parseLong(split[0]), knapsackObjects.getKnapsackCapacity());
            assertEquals(split.length - 1, knapsackObjects.getItems().size());
            List<Item> items = knapsackObjects.getItems();
            for (int i = 0; i < items.size(); i++) {
                Item e = items.get(i);
                String[] lineValues = split[i + 1].split(" ");
                assertEquals(Long.parseLong(lineValues[0]), e.getVolume());
                assertEquals(Long.parseLong(lineValues[1]), e.getValue());
            }
        }
    }

    @Nested
    class Exceptions {
        @Test
        @DisplayName("no capacity")
        void test1() {
            test("Capacity must be number, but found: 1 01", PATH + "exceptions/test1.txt");
        }

        @Test
        @DisplayName("one value in items line")
        void test2() {
            test("Line must contain two numbers delimited with space, but found 101", PATH + "exceptions/test2.txt");
        }

        @Test
        @DisplayName("empty line")
        void test3() {
            test("Line must contain two numbers delimited with space, but found ", PATH + "exceptions/test3.txt");
        }

        @Test
        @DisplayName("3 values in line")
        void test4() {
            test("Line must contain two numbers delimited with space, but found 1 123 2", PATH + "exceptions/test4.txt");
        }

        @Test
        @DisplayName("non numeric value")
        void test5() {
            test("volume must be number, but found: 1a", PATH + "exceptions/test5.txt");
        }

        @Test
        @DisplayName("non numeric volume")
        void test6() {
            test("value must be number, but found: b", PATH + "exceptions/test6.txt");
        }

        @Test
        @DisplayName("double in volume")
        void test7() {
            test("value must be Long, but found: 123.2", PATH + "exceptions/test7.txt");
        }

        @Test
        @DisplayName("double in volume")
        void test8() {
            test("Capacity must be Long, but found: 10.1", PATH + "exceptions/test8.txt");
        }

        private void test(String expected, String path) {
            try {
                new KnapsackDataLoader(path).load();
            } catch (IOException e) {
                assertEquals(expected, e.getMessage());
                return;
            }
            fail();
        }
    }
}