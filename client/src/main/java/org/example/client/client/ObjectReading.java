package org.example.client.client;

import org.example.client.AvailableCommands;
import org.example.client.validatorClient.ValidateAbstract;
import org.example.client.validatorClient.ValidatorManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**The class from which we get data for the collection element*/

public class ObjectReading {
    private static AvailableCommands availableCommands;
    public ArrayList<String> objread(String command , LinkedHashMap<String, String> fields) {
        availableCommands = new AvailableCommands();
        ArrayList<String> extraArgs = new ArrayList<String>();
        try {
            OutStream.outputIntoCLI("Type extra data of object");
            if (!availableCommands.noArgumentCommands.contains(command)) {
                ValidatorManager validatorManager = new ValidatorManager();
                if(!availableCommands.scriptArgumentCommand.contains(command)) {
                    int iter = 1;
                    /*идем по всем полям и ищем валидаторы */
                    while (iter < fields.keySet().size()) {
                        String field = fields.keySet().toArray()[iter].toString();
                        ValidateAbstract<?> validator = validatorManager.getValidator(field);
                        if (validator == null) {
                            iter++;
                            continue;
                        }
                        if (!field.equals("StudyGroup.id") && !field.equals("StudyGroup.creationDate")) {
                            OutStream.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                    + fields.get(field));
                        }
                        String valueOfField = InputClireader.getInputReader().nextLine().trim();

                        /*проверяем данные которые пришли на вход*/
                        if (validator.validate(valueOfField)) {
                            extraArgs.add(valueOfField);
                            iter++;
                        } else {
                            OutStream.outputIntoCLI("You've typed wrong value of field.");
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            OutStream.outputIntoCLI("\nInterrupting input stream.\n");
            extraArgs = new ArrayList<String>();
        }
        return extraArgs;
    }
}