package homeWork8;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

import static homeWork8.Sex.HERMAFRODIT;

@Data
@Log4j2
public class Person {
    private String firstName;

    private String lastName;

    private int age;

    private int amountOfChildren;

    private Sex sex;

    static List<Person> personList = new ArrayList<>();

    public Person() {
    }

    public Person(String firstName, String lastName, int age, int amountOfChildren, Sex sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.amountOfChildren = amountOfChildren;
        this.sex = sex;
        personList.add(Person.this);
        checkPerson();
    }

    private void checkPerson() {
        if (sex.equals(HERMAFRODIT)) {
            log.fatal(String.format("Invalid sex %s for person: %s %s", sex.getSex(), firstName, lastName));
        }
        if (amountOfChildren < 0) {
            log.error(String.format("Invalid amount of children %d for person: %s %s", amountOfChildren, firstName,
                                   lastName));
        }
        if (age <= 0) {
            log.warn(String.format("Invalid age %d for person: %s %s", age, firstName, lastName));
        }
        log.info(toString());
    }

    public void statistics() {
        personList.forEach(item -> log.info(String.format("Person: %s %s has %d children", item.getFirstName(),
                                                          item.getLastName(), item.getAmountOfChildren())));
        long amountOfPersons = personList.stream().filter(item -> item.getAmountOfChildren() == 2).count();
        log.info(String.format("%d Person%s 2 children", amountOfPersons, (amountOfPersons == 1) ? " has" : "s have"));
    }
}
