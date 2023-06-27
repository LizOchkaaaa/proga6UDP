package org.example.server.Validator;

public class ValidatorPersonname extends ValidateAbstract<String>{

    public ValidatorPersonname() {
        super("StudyGroup.Person.name" , "not null and line size >= 0");
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    @Override
    public boolean validate(String variable) {
        return variable != null && variable.trim().length() != 0;
    }

    @Override
    public boolean validate(String[] value) {
        return false;
    }
}
