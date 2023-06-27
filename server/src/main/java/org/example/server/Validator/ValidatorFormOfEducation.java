package org.example.server.Validator;

import org.example.models.FormOfEducation;

public class ValidatorFormOfEducation extends ValidateAbstract<FormOfEducation>{

    public ValidatorFormOfEducation() {
        super("StudyGroup.FormOfEducation" , "not null");
    }

    @Override
    public Class<FormOfEducation> getType() {
        return FormOfEducation.class;
    }

    @Override
    public boolean validate(String value) {
        try {
            var valueEnum = FormOfEducation.valueOf(value);
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
