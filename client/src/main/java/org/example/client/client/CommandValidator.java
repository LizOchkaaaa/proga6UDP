package org.example.client.client;

import java.util.ArrayList;

/**Class to separate name and arguments for a command*/
public class CommandValidator {
    private String commandName;
    private ArrayList<String> commandArguments = new ArrayList<>();
    private String args = null;

    public DataInOutStatus validate(String inputData) {
        String[] splitedInputData = inputData.split(" ");
        commandName = splitedInputData[0];
        for (int i = 1; i < splitedInputData.length; i++) {
            commandArguments.add(splitedInputData[i]);
        }
        return new CommandChecker().checkCorrectnessOfCommand(commandName , commandArguments);
    }


    public String[] splitter(String inputData) {
        String[] splittedInputData = inputData.split(" ");
        commandName = splittedInputData[0];
        for (int i = 1; i < splittedInputData.length; i++) {
            args += splittedInputData[i];
        }
        return new String[] {commandName, args};
    }
    public ArrayList<String> getCommandArguments(){
        return commandArguments;
    }
}
