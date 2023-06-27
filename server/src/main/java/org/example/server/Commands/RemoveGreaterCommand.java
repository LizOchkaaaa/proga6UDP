package org.example.server.Commands;

import org.example.Request;
import org.example.Response;
import org.example.models.StudyGroup;
import org.example.interfaces.Execute;
import org.example.server.Receiver;

public class RemoveGreaterCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public RemoveGreaterCommand(Receiver receiver) {
        super("remove_greater", "remove from the collection all elements greater than the given", 0 , "{element}", true);
        this.receiver = receiver;

    }
    @Override
    public Response execute(Request request){
        StudyGroup studyGroup = request.getStudyGroup();
        if (receiver.getMax() != null && studyGroup.compareTo(receiver.getMax()) <= 0) {

            return new Response("No");
        }

        receiver.remove(studyGroup);
        return new Response("Remove");
    }
        //String id = arguments.remove(0);
        //return worker.removeGreater(arguments , Integer.parseInt(id));
}
