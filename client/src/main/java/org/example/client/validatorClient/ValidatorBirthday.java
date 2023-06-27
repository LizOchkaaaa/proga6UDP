package org.example.client.validatorClient;


import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ValidatorBirthday extends ValidateAbstract<ZonedDateTime> {

    public ValidatorBirthday() {
        super("StudyGroup.Person.birthday" , "not null");
    }

    @Override
    public Class<ZonedDateTime> getType() {
        return ZonedDateTime.class;
    }

    @Override
    public boolean validate(String variable) {
        try {
            String[] args = variable.split("-");
            ZonedDateTime zdtWithZoneOffset = ZonedDateTime.of(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), 0, 0, 0, 0, ZoneId.systemDefault());
            return variable != null && args.length==3;
        } catch (IndexOutOfBoundsException | DateTimeException | NullPointerException | NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean validate(String[] value) {
        return false;
    }
}