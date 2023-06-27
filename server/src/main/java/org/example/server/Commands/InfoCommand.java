package org.example.server.Commands;

import org.example.server.Receiver;
import org.example.interfaces.Execute;
import org.example.Request;
import org.example.Response;


public class InfoCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public InfoCommand(Receiver receiver) {
        super("info", "print information about the collection to the standard output stream (initialization date, number of elements, etc)" , 0 , "" , false);
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size of collection - " + receiver.getMainCollection().size() + "\n");
        stringBuilder.append("Data initialization - " + receiver.getDateOfInitialization() + "\n");
        stringBuilder.append("Data of last change - " + receiver.getDateOfLastChange());
        return new Response(stringBuilder.toString());
    }
}
