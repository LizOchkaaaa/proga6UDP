package org.example.server.Commands;

import org.example.Request;
import org.example.Response;
import org.example.models.StudyGroup;
import org.example.interfaces.Execute;
import org.example.server.Receiver;

public class AddIfMaxCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public AddIfMaxCommand(Receiver receiver) {
        super("add_if_max", "add a new element to the collection if its value is greater than the value of the largest element in this collection", 0 , "{element}" , true);
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
        StudyGroup studyGroup = request.getStudyGroup();
        if (receiver.getMax() != null && studyGroup.compareTo(receiver.getMax()) <= 0) {
            return new Response("Add max");
        }
        receiver.add(studyGroup);
        return new Response("Add max");
    }
        //String id = arguments.remove(0);
        //return worker.addMax(arguments, Integer.parseInt(id));
    }
