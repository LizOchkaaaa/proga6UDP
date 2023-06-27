package org.example;

import org.example.models.StudyGroup;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Stack;

public class Response implements Serializable {
    private String information;
    private StudyGroup studyGroup;
    private Stack<StudyGroup> collection;



    public Response(StudyGroup aStudyGroup) {
        studyGroup = aStudyGroup;
    }

    public Response(String anInformation){
         information = anInformation;
    }

    public Response(Stack<StudyGroup> aCollection) {
        collection = aCollection;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public Stack<StudyGroup> getCollection() {
        return collection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (information != null)
            sb.append(information);

        if (studyGroup != null)
            sb.append(studyGroup).append("\n");

        if (collection != null)
            collection.stream().sorted(Comparator.comparing(StudyGroup::getCoordinates)).
                    forEach(sg -> sb.append(sg).append("\n"));
        return sb.toString();
    }
}
