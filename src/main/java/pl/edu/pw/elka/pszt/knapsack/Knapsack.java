package pl.edu.pw.elka.pszt.knapsack;

import lombok.AllArgsConstructor;
import pl.edu.pw.elka.pszt.knapsack.model.InputKnapsackObjects;
import pl.edu.pw.elka.pszt.knapsack.model.InputLoader;
import pl.edu.pw.elka.pszt.knapsack.model.ValidateInputKnapsackObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

@AllArgsConstructor
public class Knapsack {
    private final String inputPath, outputPath;

    public void run() throws IOException {
        InputKnapsackObjects iko = loadInput();
        validate(iko);
        String result = calculate(iko);
        saveOutput(result);
    }

    private InputKnapsackObjects loadInput() throws IOException {
        return new InputLoader(inputPath).load();
    }

    private void validate(InputKnapsackObjects iko) throws IOException {
        if (ValidateInputKnapsackObjects.checkCapacity(iko))
            throw new IOException("Error in capacity. Capacity must be integer >= 0, but is: " + iko.getKnapsackCapacity());
        if (ValidateInputKnapsackObjects.checkItems(iko))
            throw new IOException("Error in items. Value and weight for each item must be integer. All items: " +
                    Arrays.toString(iko.getKnapsackObjects().toArray()));
    }

    private String calculate(InputKnapsackObjects iko) {
        //ToDo body;
        return null;
    }

    private void saveOutput(String string) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
        writer.write(string);
        writer.close();
    }
}