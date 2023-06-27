package org.example.client.validatorClient;


import org.example.models.Color;

public class ValidatorHairColor extends ValidateAbstract<Color> {

    public ValidatorHairColor() {
        super("StudyGroup.Person.Color" , "not null");
    }

    @Override
    public Class<Color> getType() {
        return Color.class;
    }

    @Override
    public boolean validate(String value) {
        try {
            var valueEnum = Color.valueOf(value);
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
