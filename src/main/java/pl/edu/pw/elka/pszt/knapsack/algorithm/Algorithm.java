package pl.edu.pw.elka.pszt.knapsack.algorithm;

import pl.edu.pw.elka.pszt.knapsack.algorithm.genetic.population.Population;

import java.util.List;

public interface Algorithm {
    String calculate() throws CloneNotSupportedException;

    List<Population> getOldPopulations();
}
