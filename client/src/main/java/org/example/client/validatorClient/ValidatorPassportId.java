package org.example.client.validatorClient;



public class ValidatorPassportId extends ValidateAbstract<String> {

    public ValidatorPassportId() {
        super("StudyGroup.Person.passportID" , "not null and line size >= 0");
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    @Override
    public boolean validate(String value) {
        return (value != null && value.trim().length() != 0);
    }

    @Override
    public boolean validate(String[] value) {
        return false;
    }
}