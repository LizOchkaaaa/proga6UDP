package org.example.client.validatorClient;



public class ValidateCoordinatesX extends ValidateAbstract<Double> {

    public ValidateCoordinatesX() {
        super("StudyGroup.Coordinates.x" , ">=-564");
    }

    @Override
    public Class<Double> getType() {
        return Double.class;
    }

    @Override
    public boolean validate(String value) {
        Double val;
        try {
            val = Double.parseDouble(value);
            if (val >= -564) {
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