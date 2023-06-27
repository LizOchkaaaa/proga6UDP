package org.example;

import org.example.models.StudyGroup;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {

    private final String commandName;
    private final ArrayList<String> argName;
    private StudyGroup studyGroup;

    public Request(String aCommand, ArrayList<String> aArgs) {
        commandName = aCommand;
        argName = aArgs;
        studyGroup = null;

    }

    public Request addStudyGroup(StudyGroup aStudyGroup) {
        studyGroup = aStudyGroup;
        return this;
    }


    public String getCommand() {
        return commandName;
    }

    public ArrayList<String> getArg() {
        return argName;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    @Override
    public String toString() {
        return commandName + " "
                + (argName != null ? argName : "")
                + (studyGroup != null ? studyGroup : "");
    }

    public boolean isArgInt() {
        try {
            if (argName != null) {
                Integer.parseInt(String.valueOf(argName));
                return true;
            } else return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
