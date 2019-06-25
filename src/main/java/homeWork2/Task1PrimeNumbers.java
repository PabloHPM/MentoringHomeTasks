package homeWork2;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Log4j2
public class Task1PrimeNumbers {
    public Task1PrimeNumbers(int a, int b) {
        log.info(getPrimeNumbers(a,b));
    }

    private List<Integer> getPrimeNumbers(int a, int b) {
        return IntStream.range(a, b)
                .filter(i -> (int) IntStream.range(1, i + 1)
                        .filter(j -> i % j == 0)
                        .count() == 2 && i > 1)
                .boxed()
                .collect(Collectors.toList());
    }
}
