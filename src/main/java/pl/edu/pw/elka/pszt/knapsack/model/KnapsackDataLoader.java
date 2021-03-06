package pl.edu.pw.elka.pszt.knapsack.model;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;

@AllArgsConstructor
public class KnapsackDataLoader extends FileGetter {
    private final String inputPath;

    public KnapsackObjects load() throws IOException {
        String inputData = getDataFromFile(inputPath);
        return setInputKnapsackObjects(inputData);
    }

    private KnapsackObjects setInputKnapsackObjects(String inputData) throws IOException {
        String[] lines = inputData.split("\n");
        if (lines.length == 0)
            throw new IOException("Data in input file can't be empty");
        KnapsackObjects knapsackObjects = new KnapsackObjects(getCapacity(lines[0]));
        for (int i = 1; i < lines.length; i++) {
            knapsackObjects.add(getKnapsackObject(lines[i]));
        }
        return knapsackObjects;
    }

    private Long getCapacity(String capacity) throws IOException {
        if (nonNumber(capacity))
            throw new IOException(String.format("Capacity must be number, but found: %s", capacity));
        if (nonLong(capacity))
            throw new IOException(String.format("Capacity must be Long, but found: %s", capacity));
        return Long.parseLong(capacity);
    }

    private Item getKnapsackObject(String line) throws IOException {
        String[] strings = line.split(" ");
        if (strings.length != 2)
            throw new IOException(String.format("Line must contain two numbers delimited with space, but found %s",
                    line));
        if (nonNumber(strings[0]))
            throw new IOException(String.format("volume must be number, but found: %s", strings[0]));
        if (nonLong(strings[0]))
            throw new IOException(String.format("volume must be Long, but found: %s", strings[0]));
        if (nonNumber(strings[1]))
            throw new IOException(String.format("value must be number, but found: %s", strings[1]));
        if (nonLong(strings[1]))
            throw new IOException(String.format("value must be Long, but found: %s", strings[1]));
        return new Item(Long.parseLong(strings[0]), Long.parseLong(strings[1]));
    }

    private boolean nonLong(String string) {
        try {
            Long.parseLong(string);
        } catch (NumberFormatException | NullPointerException exception) {
            return true;
        }
        return false;
    }

    private boolean nonNumber(String string) {
        return !NumberUtils.isParsable(string);
    }
}
