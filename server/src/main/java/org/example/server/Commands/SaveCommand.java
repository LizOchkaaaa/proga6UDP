package org.example.server.Commands;

import org.example.server.Receiver;
import org.example.interfaces.Execute;
import org.example.Request;
import org.example.Response;

public class SaveCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;
    public SaveCommand(Receiver receiver) {
        super("save", "save collection to file", 0 , "" , false);
        this.receiver = receiver;
    }
    @Override
    public Response execute(Request request) {
        return new Response(receiver.saveCollection());
    }
}
