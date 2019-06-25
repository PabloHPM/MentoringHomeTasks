package homeWork9;

public enum Sex {
    MALE("Male"), FEMALE("Female"), HERMAFRODIT("Hermafrodit");

    private final String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
