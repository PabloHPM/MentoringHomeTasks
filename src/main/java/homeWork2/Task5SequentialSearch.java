package homeWork2;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Log4j2
public class Task5SequentialSearch {
    private List<Integer> array;

    private int searchedValue;

    public Task5SequentialSearch(int searchedValue, int size) {
        this.searchedValue = searchedValue;
        log.info(array = new Random().ints(0, 100).limit(size).boxed().collect(Collectors.toList()));
        getSearchedValue(array);
    }

    private int getSearchedValue(List<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).equals(searchedValue)) {
                log.info(String.format("Position of your element: %d", i));
                return i;
            }
        }
        log.warn("No such element in array!");
        return -1;
    }
}
