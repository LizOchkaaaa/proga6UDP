package org.example.server.Commands;

import org.example.server.Receiver;
import org.example.interfaces.Execute;
import org.example.models.StudyGroup;
import org.example.Request;
import org.example.Response;

import java.util.*;

public class PrintFieldAscendingStudentsCountCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public PrintFieldAscendingStudentsCountCommand(Receiver receiver) {
        super("print_field_ascending_students_count", "display the values of the students count" +
                " field of all elements in ascending order", 0 , "" , false);
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
            StringBuilder execution = new StringBuilder();
            Comparator<StudyGroup> field = Comparator.comparingInt(StudyGroup::getStudentsCount).reversed();
            Stack<StudyGroup> mainCollection = receiver.getMainCollection();
            StudyGroup[] arrayGroups = new StudyGroup[mainCollection.size()];
            int iter = 0;
            for (StudyGroup studyGroup1 : mainCollection) {
                arrayGroups[iter] = studyGroup1;
                iter++;
            }
            Arrays.sort(arrayGroups, field);
            for (StudyGroup group : arrayGroups) {
                execution.append(group.getStudentsCount() + " ");
            }
            return new Response(execution.toString());
        }
    }
