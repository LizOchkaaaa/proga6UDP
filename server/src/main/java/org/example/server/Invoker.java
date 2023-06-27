package org.example.server;

import org.example.Request;
import org.example.Response;
import org.example.server.Commands.AbstractCommand;
import org.example.server.Commands.*;

import java.util.HashMap;

public class Invoker {
    private static Receiver receiver;

    private static HashMap<String, AbstractCommand> commandsMap;


    public Invoker(Receiver receiver) {
        this.receiver = receiver;

        commandsMap = new HashMap<>();
        var helpCommand = new HelpCommand(receiver, commandsMap.values());
        var infoCommand = new InfoCommand(receiver);

        var clearCommand = new ClearCommand(receiver);
        var saveCommand = new SaveCommand(receiver);
        var showCommand = new ShowCommand(receiver);
        var addCommand = new AddCommand(receiver);
        var updateCommand = new UpdateIdCommand(receiver);
        var removeCommand = new RemoveByIdCommand(receiver);
        var executeCommand = new ExecuteScriptCommand();
        var addMaxCommand = new AddIfMaxCommand(receiver);
        var remove1Command = new RemoveGreaterCommand(receiver);
        var reorderCommand = new ReorderCommand(receiver);
        var sumOfStudentsCountCommand = new SumOfStudentsCountCommand(receiver);
        var printCommand = new PrintUniqueFormOfEducationCommand(receiver);
        var printFieldCommand = new PrintFieldAscendingStudentsCountCommand(receiver);

        commandsMap.put("help", helpCommand);
        commandsMap.put("info", infoCommand);
        commandsMap.put("clear", clearCommand);
        commandsMap.put("save", saveCommand);
        commandsMap.put("show", showCommand);
        commandsMap.put("add", addCommand);
        commandsMap.put("update", updateCommand);
        commandsMap.put("remove_by_id", removeCommand);
        commandsMap.put("execute_script", executeCommand);
        commandsMap.put("add_if_max", addMaxCommand);
        commandsMap.put("remove_greater", remove1Command);
        commandsMap.put("reorder", reorderCommand);
        commandsMap.put("sum_of_students_count", sumOfStudentsCountCommand);
        commandsMap.put("print_unique_form_of_education", printCommand);
        commandsMap.put("print_field_ascending_students_count", printFieldCommand);
    }


    public  Response execute(Request request) {
        String command = request.getCommand();
        return commandsMap.get(command).execute(request);
    }

    public static HashMap<String, AbstractCommand> getCommandsMap(){
        return commandsMap;
    }
}
