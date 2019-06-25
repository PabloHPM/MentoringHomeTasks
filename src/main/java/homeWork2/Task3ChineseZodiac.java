package homeWork2;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Task3ChineseZodiac {
    public Task3ChineseZodiac(int year) {
        getChineseZodiac(year);
    }

    private void getChineseZodiac(int year) {
        String sentence = "According to the Chinese Calendar, you were born during";
        switch (year % 12) {
            case 0:
                log.info(String.format("%s Monkey year",sentence));
                break;
            case 1:
                log.info(String.format("%s Rooster year",sentence));
                break;
            case 2:
                log.info(String.format("%s Dog year",sentence));
                break;
            case 3:
                log.info(String.format("%s Pig year",sentence));
                break;
            case 4:
                log.info(String.format("%s Rat year",sentence));
                break;
            case 5:
                log.info(String.format("%s Ox year",sentence));
                break;
            case 6:
                log.info(String.format("%s Tiger year",sentence));
                break;
            case 7:
                log.info(String.format("%s Rabbit year",sentence));
                break;
            case 8:
                log.info(String.format("%s Dragon year",sentence));
                break;
            case 9:
                log.info(String.format("%s Snake year",sentence));
                break;
            case 10:
                log.info(String.format("%s Horse year",sentence));
                break;
            case 11:
                log.info(String.format("%s Sheep year",sentence));
                break;
            default:
                log.warn("Not valid input");
        }
    }
}
