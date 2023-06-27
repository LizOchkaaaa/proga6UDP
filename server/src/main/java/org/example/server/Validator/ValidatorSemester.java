package org.example.server.Validator;


import org.example.models.Semester;

public class ValidatorSemester extends ValidateAbstract<Semester>{

    public ValidatorSemester() {
        super("StudyGroup.Semester" , "not null");
    }

    @Override
    public Class<Semester> getType() {
        return Semester.class;
    }

    @Override
    public boolean validate(String value) {
        try {
            var valueEnum = Semester.valueOf(value);
            if(valueEnum != null) {
                return true;
            }
            return false;
        }catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean validate(String[] value) {
        return false;
    }
}
