package org.example.server.Commands;

import org.example.server.Receiver;
import org.example.interfaces.Execute;
import org.example.Request;
import org.example.Response;

public class SumOfStudentsCountCommand extends AbstractCommand implements Execute {
    private final Receiver receiver;

    public SumOfStudentsCountCommand(Receiver receiver) {
        super("sum_of_students_count", "display the sum of the values of the studentsCount" +
                " field for all elements of the collection",  0 , "" , false);
        this.receiver = receiver;
    }

    @Override
    public Response execute(Request request) {
        return new Response(receiver.sumOfStudentsCount());
    }
}
