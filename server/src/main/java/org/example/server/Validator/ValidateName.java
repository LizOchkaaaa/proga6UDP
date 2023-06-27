package org.example.server.Validator;

public class ValidateName extends ValidateAbstract<String> {

    public ValidateName() {
        super("StudyGroup.name" , "not null and line size >= 0");
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    @Override
    public boolean validate(String variable) {
        return (variable != null && variable.trim().length() != 0);
    }

    @Override
    public boolean validate(String[] value) {
        return false;
    }
}
