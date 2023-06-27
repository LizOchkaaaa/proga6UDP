package org.example.client;

import org.example.annotation.Complex;
import org.example.annotation.Date;
import org.example.models.StudyGroup;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public class FieldFetcher {
    /**
     * A class that collects all fields into an array
     * fetchFields method
     */
    public LinkedHashMap<String, String> fetchFields() {
        var resultHashMap = new LinkedHashMap<String, String>();
        Field[] studyGroupClass = StudyGroup.class.getDeclaredFields();
        for (Field studyGroupField : studyGroupClass) {
            if (studyGroupField.isAnnotationPresent(Complex.class)) {
                resultHashMap.putAll(this.fetchFromField(studyGroupField, "StudyGroup", new StringBuilder()));
            } else {
                if(studyGroupField.isAnnotationPresent(Date.class)){
                    resultHashMap.put(studyGroupField.getDeclaringClass().getSimpleName() + "." + studyGroupField.getName(),
                            studyGroupField.getType().getSimpleName() + " Format: YYYY-MM-DD");

                }
                else {
                    resultHashMap.put(studyGroupField.getDeclaringClass().getSimpleName() + "." + studyGroupField.getName(),
                            studyGroupField.getType().getSimpleName());
                }

            }
        }
        return resultHashMap;
    }

    public LinkedHashMap<String, String> fetchFromField(Field fetchField, String keyValue, StringBuilder enumValues) {
        var resultHashMap = new LinkedHashMap<String, String>();
        Field[] fetchedClass = fetchField.getType().getDeclaredFields();
        keyValue += "." + fetchField.getType().getSimpleName();
        for (Field fetchedFieldInClass : fetchedClass) {
            if (fetchedFieldInClass.isAnnotationPresent(Complex.class)) {
                resultHashMap.putAll(this.fetchFromField(fetchedFieldInClass, keyValue, enumValues));
            } else {
                if (fetchedFieldInClass.getType().isEnum()) {
                    enumValues.append(fetchedFieldInClass.getName() + ", ");
                } else {
                    if (fetchedFieldInClass.getName().equals("ENUM$VALUES")
                            || fetchedFieldInClass.getName().equals("$VALUES")) {
                        resultHashMap.put(keyValue,
                                "Enum. Values: " + enumValues.delete(enumValues.length() - 2, enumValues.length()).toString());
                    } else {
                        resultHashMap.put(keyValue + "." + fetchedFieldInClass.getName(),
                                fetchedFieldInClass.getType().getSimpleName());
                    }
                }
            }
        }
        return resultHashMap;
    }
}