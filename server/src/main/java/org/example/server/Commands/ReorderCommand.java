package org.example.server.Commands;

import org.example.server.Receiver;
import org.example.interfaces.Execute;
import org.example.Request;
import org.example.Response;

public class ReorderCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public ReorderCommand(Receiver receiver) {
        super("reorder", "sort the collection in reverse order", 0 , "" , false);
        this.receiver = receiver;
    }
    @Override
    public Response execute(Request request) {
        return new Response(receiver.reorder().toString());
    }
}
