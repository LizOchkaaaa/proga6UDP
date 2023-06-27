package org.example.client.client;


import org.example.client.FieldFetcher;

import java.util.LinkedHashMap;


/**A class that contains the fields of the main class and commands*/
public class MetaInfoCommand {
    private  static LinkedHashMap<String, String> fields;


    /*взяли все команды из Invoker*/
    public MetaInfoCommand() {
 //       mapOfCommand = Invoker.getCommandsMap();
        fields = new FieldFetcher().fetchFields();
    }

//    public HashMap<String, AbstractCommand> getMapOfCommand() {
//        return mapOfCommand;
//    }

    public static LinkedHashMap<String, String> getFields() {
        return fields;
    }
}
