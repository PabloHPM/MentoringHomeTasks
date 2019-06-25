package homeWork2;

import lombok.extern.log4j.Log4j2;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Log4j2
public class Task6StatisticalInformation {
    private List<Integer> array;

    public Task6StatisticalInformation(int size) {
        log.info(array = new Random().ints(0, 100).limit(size).boxed().collect(Collectors.toList()));
        log.info(String.format("Arithmetic Mean: %.2f", getArithmeticMean()));
        log.info(String.format("Median: %.2f", getMedian()));
        log.info(String.format("Mode: %.2f", getMode()));
        log.info(String.format("Standard deviation: %.2f", getStandardDeviation()));
    }

    private double getArithmeticMean() {
        return array.stream().mapToDouble(Integer::doubleValue).sum() / array.size();
    }

    private double getMedian() {
        List<Integer> sortArray = new Task7BubbleSort().getSortArray(array);
        return (sortArray.size() % 2 == 0) ?
               sortArray.get(sortArray.size() / 2) + sortArray.get((sortArray.size() / 2) - 1) :
               sortArray.get(sortArray.size() / 2);
    }

    private double getMode() {
        int counter = 0;
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer aValue : array) {
            for (Integer bValue : array) {
                if (aValue.equals(bValue)) {
                    counter++;
                }
            }
            result.put(aValue, counter);
            counter = 0;
        }
        result.entrySet().forEach(item -> item.getValue());
        return Collections.max(result.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private double getStandardDeviation() {
        double a = getArithmeticMean();
        return Math.sqrt(
            array.stream().map(item -> item - a).map(item -> Math.pow(item, 2)).mapToDouble(Double::doubleValue).sum()
            / (array.size() - 1));
    }
}
