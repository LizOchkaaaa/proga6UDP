package org.example.client.factores;

import org.example.models.*;
import java.util.ArrayList;

public class StudyGroupFactory {
    public StudyGroup createStudyGroup(ArrayList<String> args) {
        String name = args.get(0);
        String[] coordinatesvalues = {args.get(1), args.get(2)};
        String[] person = {args.get(6), args.get(7), args.get(8), args.get(9), args.get(10)};
        Integer countStudent = Integer.parseInt(args.get(3));
        FormOfEducation formOfEducation = FormOfEducation.valueOf(args.get(4));
        Coordinates coordinates = new CoordinatesFactory().createCoordinates(coordinatesvalues);
        Person newPerson = new PersonFactory().createPerson(person);

        Semester semester = null;
        if (!args.get(5).equals("")) {
            semester = Semester.valueOf(args.get(5));
        }

        StudyGroup newStudyGroup = new StudyGroup(0 , name ,newPerson, semester, formOfEducation, coordinates, countStudent);
        return newStudyGroup;
    }
}
