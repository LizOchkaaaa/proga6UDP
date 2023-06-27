package org.example.client.validatorClient;



public class ValidatorPerson extends ValidateAbstract<String[]> {

    public ValidatorPerson() {
        super("StudyGroup.Person" , "");
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
            var nameValidator = new ValidatorPersonname();
            var weightValidator = new ValidatorWeight();
            var hairValidate = new ValidatorHairColor();

            if (nameValidator.validate(value[0]) && new ValidatorBirthday().validate(value[1]) &&
                    weightValidator.validate(value[2]) && new ValidatorPassportId().validate(value[3])
                    && hairValidate.validate(value[4])) {
                return true;
            }
            return false;
        }catch (IndexOutOfBoundsException e) {
            return false;
        }

    }
}

