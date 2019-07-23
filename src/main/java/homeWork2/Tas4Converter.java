package homeWork2;

import lombok.extern.log4j.Log4j2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class Tas4Converter {
    private static String unitsArray[] =
        {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
         "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    private static String tensArray[] =
        {"zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    public Tas4Converter(int value) {
        log.info("Number: "+numberToWord(value));
    }

    public static void main(String[] args) {
        System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).concat("w"));
    }

    private String numberToWord(int value) {
        String answer = "";

        if (value == 0) {
            return "zero";
        }

        if ((value / 1000) > 0) {
            answer += numberToWord(value / 1000) + " thousand ";
            value %= 1000;
        }

        if ((value / 100) > 0) {
            answer += numberToWord(value / 100) + " hundred ";
            value %= 100;
        }

        if (value > 0) {
            if (value < 20) {
                answer += unitsArray[value];
            } else {
                answer += tensArray[value / 10];
                if ((value % 10) > 0) {
                    answer += "-" + unitsArray[value % 10];
                }
            }
        }

        return answer;
    }
}
