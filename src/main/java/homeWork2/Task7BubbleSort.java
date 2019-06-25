package homeWork2;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Log4j2
public class Task7BubbleSort {
    public Task7BubbleSort() {
    }

    public Task7BubbleSort(int size) {
        log.info(getSortArray(new Random().ints(0, 100).limit(size).boxed().collect(Collectors.toList())));
    }

    List<Integer> getSortArray(List<Integer> array) {
        boolean flag;
        do {
            flag = false;
            for (int i = 0; i < array.size() - 1; i++) {
                if (array.get(i) > array.get(i + 1)) {
                    int temp = array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    flag = true;
                }
            }
        } while (flag);
        return array;
    }
}
