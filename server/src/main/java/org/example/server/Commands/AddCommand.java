package org.example.server.Commands;

import org.example.Request;
import org.example.Response;
import org.example.models.StudyGroup;
import org.example.interfaces.Execute;
import org.example.server.Receiver;

public class AddCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public AddCommand(Receiver receiver) {
        super("add", "add a new element to the collection" , 0 , "{element}" , true);
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
//        String id = String.valueOf(UniqueId.getNewId());
//        return worker.addNew(request.getArg(), Integer.parseInt(id));
        StudyGroup studyGroup = request.getStudyGroup();
        receiver.add(studyGroup);
        return new Response(("Study group has been added!"));
    }

}
