package org.example.exceptions;


public class CommandInterruptedException extends Exception {
    private final String enteredCommand;
    public String getEnteredCommand(){
        return enteredCommand;
    }
    public CommandInterruptedException(String enteredCommand){
        this.enteredCommand = enteredCommand;
    }
}