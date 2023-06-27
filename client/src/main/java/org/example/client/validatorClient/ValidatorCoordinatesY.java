package org.example.client.validatorClient;



public class ValidatorCoordinatesY extends ValidateAbstract<Integer> {

    public ValidatorCoordinatesY() {
        super("StudyGroup.Coordinates.y" , "<=441");
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }

    @Override
    public boolean validate(String value) {
        Integer val;
        try {
            val = Integer.parseInt(value);
            if (val <= 441) {
                return true;
            }

            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean validate(String[] value) {
        return false;
    }
}
