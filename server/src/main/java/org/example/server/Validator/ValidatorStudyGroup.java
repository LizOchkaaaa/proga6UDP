package org.example.server.Validator;

public class ValidatorStudyGroup extends ValidateAbstract<String[]>{

    public ValidatorStudyGroup() {
        super("StudyGroup" , "");
    }

    @Override
    public Class<String[]> getType() {
        return String[].class;
    }

    @Override
    public boolean validate(String value) {
        return false;
    }

    @Override
    public boolean validate(String[] value) {
        try {
            var coordinats = new ValidatorCoordinates();
            var studentsCount = new ValidatorStudentsCount();
            var formOfEducation = new ValidatorFormOfEducation();
            var semester = new ValidatorSemester();
            var person = new ValidatorPerson();

            if (new ValidateName().validate(value[0]) && coordinats.validate(value[1]) &&
                    studentsCount.validate(value[2]) && formOfEducation.validate(value[3]) &&
                    semester.validate(value[4]) && person.validate(value[5])) {
                return true;
            }
            return false;
        }catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
