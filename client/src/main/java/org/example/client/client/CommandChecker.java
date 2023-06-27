package org.example.client.client;

import org.example.Request;
import org.example.client.AvailableCommands;
import org.example.client.RequestHandler;
import org.example.client.StudyGroupFactory;

import java.util.ArrayList;


/**Class for validating commands and input*/
public class CommandChecker {

    private static AvailableCommands availableCommands;
    private ArrayList<String> arguments;


    public DataInOutStatus checkCorrectnessOfCommand(String commandName , ArrayList<String> argumentsToCommand) {
        DataInOutStatus correctnessStatus = DataInOutStatus.SUCCESSFULLY;
        MetaInfoCommand metaInfoCommand = new MetaInfoCommand();
         availableCommands = new AvailableCommands();
        /*обращаемся ко всем командам и смотрим есть ли такая команда вообще */
        //Map<String, AbstractCommand> mapCommand = metaInfoCommand.getMapOfCommand();
        /*ищем по имени */
     //   if (mapCommand.containsKey(commandName)) {
            /* создаем объект команды */

            if (availableCommands.noArgumentCommands.contains(commandName)) {
                if (argumentsToCommand.size() != 0) {
                    return DataInOutStatus.WRONGARGS;
                } if (!commandName.equals("exit")) {
                    Request request = new Request(commandName, null);
                    System.out.println(RequestHandler.getInstance().send(request));
                }
                return DataInOutStatus.SUCCESSFULLY;
            }
            /*проверяем команду , нужны ли ей дополнительные аргументы */
            if (!availableCommands.noArgumentCommands.contains(commandName)) {
                if (availableCommands.objAndNumArgumentCommand.contains(commandName) || availableCommands.scriptArgumentCommand.contains(commandName)) {
                    if (argumentsToCommand.size() == 0 || argumentsToCommand.size() != 1) {
                        OutStream.outputIntoCLI("You have wrong argument");
                        return DataInOutStatus.FAILED;
                    }
                    if (availableCommands.scriptArgumentCommand.contains(commandName)){
                        String fileName = argumentsToCommand.get(0);
                        ScriptReader.setFile(fileName);
                        try {
                            ScriptReader.execute();
                            return DataInOutStatus.SUCCESSFULLY;
                        } catch (Exception e){
                            System.out.println("exception");
                        }


                    }
                }
                        correctnessStatus = checkCorrectnessOfComplicatedCommand(commandName, argumentsToCommand);


                    if (!availableCommands.numArgumentCommands.contains(commandName)) {
                        if (correctnessStatus == DataInOutStatus.SUCCESSFULLY) {
                            if (availableCommands.objectArgumentCommands.contains(commandName)) {
                                Request request = new Request(commandName, null);
                                System.out.println(RequestHandler.getInstance().send(request, new StudyGroupFactory().createStudyGroup(arguments)));
                            }
                            else {
                                Request request = new Request(commandName, argumentsToCommand);
                                System.out.println(RequestHandler.getInstance().send(request, new StudyGroupFactory().createStudyGroup(arguments)));
                            }


                        }
                    }
                }


      //  }
        else {
            return DataInOutStatus.NOCOMMAND;
        }
        return DataInOutStatus.SUCCESSFULLY;
    }


    /*проверяем команду у которой много аргументов на правильность введения */
    private DataInOutStatus checkCorrectnessOfComplicatedCommand(String command, ArrayList<String> argumentsToCommand) {
        DataInOutStatus correctnessStatus = DataInOutStatus.SUCCESSFULLY;
        if (!availableCommands.noArgumentCommands.contains(command)) {
            if (availableCommands.numArgumentCommands.contains(command)) {
                if (argumentsToCommand.size() != 1) {
                    return DataInOutStatus.WRONGARGS;
                }
                Request request = new Request(command, argumentsToCommand);
                System.out.println(RequestHandler.getInstance().send(request));
                return DataInOutStatus.SUCCESSFULLY;
            }
            CommandDataChecker commandChecker = new CommandDataChecker();
            correctnessStatus = commandChecker.checkInputCommand(command);
            if (correctnessStatus == DataInOutStatus.SUCCESSFULLY) {
                arguments = new ArrayList<String>();
                arguments.addAll(commandChecker.getExtraArgs());
            }
            if (arguments.size() == 0) {
                return DataInOutStatus.WRONGARGS;
            }
        }
        return correctnessStatus;
    }

}
