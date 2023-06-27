package org.example.server.Commands;

import org.example.interfaces.Execute;
import org.example.Request;
import org.example.Response;

import java.util.ArrayList;

public class ExecuteScriptCommand extends AbstractCommand implements Execute {
    private ArrayList<String> historyOfFiles = new ArrayList<>();
    private static ArrayList<String> readedCommands;
    private static Integer currentCommand;

    public ExecuteScriptCommand() {
        super("execute_script", "read and execute the script from the specified file." +
                " The script contains commands in the same form in which they are entered by the user" +
                " in interactive mode", 1 , "{file_name}" , false);
    }

    public ArrayList<String> getHistoryOfFiles() {
        return historyOfFiles;
    }

    public static Integer getCurrentCommand() {
        return currentCommand;
    }

    public static void setCurrentCommand(Integer currentCommand) {
        ExecuteScriptCommand.currentCommand = currentCommand;
    }

    public static ArrayList<String> getReadedCommands() {
        return readedCommands;
    }

    @Override
    public Response execute(Request request) {
       return null;
    }
}
