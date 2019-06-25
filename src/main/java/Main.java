import com.github.javafaker.Faker;
import homeWork1.QuadraticEquation;
import homeWork2.Task1PrimeNumbers;
import homeWork2.Task3ChineseZodiac;
import homeWork2.Task5SequentialSearch;
import homeWork2.Task6StatisticalInformation;
import homeWork2.Task7BubbleSort;
import homeWork9.Person;
import homeWork9.Sex;
import lombok.extern.log4j.Log4j2;

import java.util.Random;

import static homeWork9.Sex.FEMALE;
import static homeWork9.Sex.HERMAFRODIT;
import static homeWork9.Sex.MALE;

@Log4j2
public class Main {
    public static void main(String[] args) {
        new Main().getTask(2);
    }

    private void getTask(int number) {
        switch (number) {
            case 1:
                new QuadraticEquation(1, 5, 3);
                break;
            case 2:
                new Task1PrimeNumbers(0, 50);
                new Task3ChineseZodiac(2000);
                new Task5SequentialSearch(3, 10);
                new Task6StatisticalInformation(10);
                new Task7BubbleSort(10);
                break;
            case 9:
                personGenerator();
                break;
            default:
                log.warn("You enter not valid task number!");
        }
    }

    private void personGenerator() {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            new Person(faker.name().firstName(), faker.name().lastName(), new Random().nextInt(150) - 50,
                       new Random().nextInt(20) - 10, rnd());
        }
        new Person().statistics();
    }

    private Sex rnd() {
        int rndValue = new Random().nextInt(100);
        if (rndValue % 2 == 0 && rndValue < 33) {
            return MALE;
        } else if (rndValue < 66) {
            return FEMALE;
        } else {
            return HERMAFRODIT;
        }
    }
}
