package org.example.server.Commands;

import org.example.Request;
import org.example.Response;
import org.example.interfaces.Execute;
import org.example.server.Receiver;

public class ClearCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public ClearCommand(Receiver receiver) {
        super("clear", "clear the collection", 0 , "" , false);
        this.receiver = receiver;
    }


    @Override
    public Response execute(Request request) {
        receiver.clearCollection();
        return new Response("Clear");
    }
}
