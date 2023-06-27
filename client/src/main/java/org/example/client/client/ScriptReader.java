package org.example.client.client;

import java.util.ArrayList;

public class ScriptReader {
    private static ArrayList<String> historyOfFiles = new ArrayList<>();
    private static ArrayList<String> readedCommands = new ArrayList<String>();
    private static Integer currentCommand;
    private static String file;


    public static void setFile(String file){
        ScriptReader.file = file;
    }
    public static void execute() {
        StringBuilder execution = new StringBuilder();
        if (historyOfFiles.contains(file)) {
            historyOfFiles = new ArrayList<>();
            OutStream.outputIntoCLI(execution.append("Recursion was detected in your files").toString());

        } else {
            historyOfFiles.add(file);
            currentCommand = 0;
            readedCommands = new FileReader().readFile(file);
            int iter = 0;

            if (readedCommands.size() != 0) {
                while (iter < readedCommands.size()) {
                    String commandLine = readedCommands.get(iter);
                    if (new CommandValidator().validate(commandLine) != DataInOutStatus.SUCCESSFULLY) {
                        OutStream.outputIntoCLI(execution.append("Check correctness of commands in your script '" + file
                                + "'. Failed.\nSome commands can be completed.").toString());
                    }
                    currentCommand++;
                    iter = currentCommand;
                }
            } else {
                OutStream.outputIntoCLI(execution.append("There are some errors with file '" + file + "'. Try again.").toString());
            }
        }
    }
}
