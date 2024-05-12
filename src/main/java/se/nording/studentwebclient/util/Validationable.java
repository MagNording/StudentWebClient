package se.nording.studentwebclient.util;

public interface Validationable {

    String getValidString();

    String readString(String prompt);

    String getAnyString();

    Long getValidLong(String prompt);

    int getValidInt(String prompt);

    int getValidIntegerInput(String prompt, int min, int max);

    String getValidEmail();

    String getValidPhone(String prompt);

}
