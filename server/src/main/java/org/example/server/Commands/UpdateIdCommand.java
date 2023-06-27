package org.example.server.Commands;

import org.example.Request;
import org.example.Response;
import org.example.models.StudyGroup;
import org.example.interfaces.Execute;
import org.example.server.Receiver;

import java.util.ArrayList;

public class UpdateIdCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;;
    public UpdateIdCommand(Receiver receiver) {
        super("update", "update the value of the collection element whose" +
                " id is equal to the given one", 1 , "id {element}" , true);
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
        ArrayList<String> anArg = request.getArg();
        StudyGroup upgradedGroup = request.getStudyGroup();

        Object studyGroup = receiver.getId(Integer.parseInt(anArg.get(0)));

        if (studyGroup != null) receiver.remove((StudyGroup) studyGroup);
        else {
            return new Response("An object with this id does not exist!");
        }

        upgradedGroup.setId(Integer.parseInt(anArg.get(0)));
        receiver.add(upgradedGroup);

        return new Response("Object has been updated!");
    }
//        if(arguments.size()==1) {
//            if (!worker.check(arguments)) {
//                return "FAILED";
//            }
//            return "TRUE";
//        }
//        String id = arguments.remove(0);
//        try {
//            return worker.update(arguments, Integer.parseInt(id));
//        } catch (NumberFormatException e) {
//            return "Failed. Wrong Id.";
//        }




}

