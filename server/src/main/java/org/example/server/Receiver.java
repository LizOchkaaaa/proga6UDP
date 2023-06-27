package org.example.server;

import org.example.xmlUtils.XmlFileHandler;
import org.example.models.FormOfEducation;
import org.example.models.StudyGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import java.util.logging.Logger;

/**Execution class of all commands*/
public class Receiver {
    private Stack<StudyGroup> studyGroups;
    private final Stack<Integer> usedId;
    private int highUsedId;
    public static final Logger logger = null;

    private final LocalDateBase localDateBase;


    public Receiver(LocalDateBase localDateBase) {
        usedId = new Stack<>();
        highUsedId = 0;

        this.localDateBase = localDateBase;
        this.studyGroups = localDateBase.getMainCollection();

    }

    public Date getDateOfInitialization() {
        return localDateBase.getDateOfInitialization();
    }

    public Stack<StudyGroup> getMainCollection() {
        return localDateBase.getMainCollection();
    }

    public Date getDateOfLastChange() {
        return localDateBase.getDateOfLastChange();
    }

    public void setDateOfLastChange() {
        localDateBase.setDateOfLastChange(new Date());
    }

    public LocalDateBase getLocalDateBase() {
        return localDateBase;
    }


    public void add(StudyGroup studyGroup) {
        Integer newGroupId = studyGroup.getId();
        if (!usedId.add(newGroupId)) studyGroups.remove(this.getId(newGroupId));

        if (studyGroups.add(studyGroup)) {
            usedId.add(studyGroup.getId());
            if (highUsedId < studyGroup.getId()) highUsedId = studyGroup.getId();
        } else usedId.remove(studyGroup.getId());
    }

     public StudyGroup getId(Integer key) {
            return studyGroups.stream().filter(sg -> sg.getId().equals(key)).findAny().orElse(null);
    }

    public StudyGroup getMax(){
      return studyGroups.stream().max(StudyGroup::compareTo).orElse(null);
    }
    public String clearCollection() {
        if (getMainCollection().size() != 0) {
            getMainCollection().clear();
            setDateOfLastChange();
            return "Main collection cleared";
        } else {
            return "There is no elements in main collection";
        }
    }

    public String saveCollection() {
        try {
            if (new XmlFileHandler().save(getMainCollection(), new File("file.xml"))) {
                return "Collection saved successfully";
            } else  {
                return "Faild";
            }
        } catch (Exception e) {
            return "exception";
        }
    }


    public String show(){
        StringBuilder stringBuilder = new StringBuilder();

        for(StudyGroup studyGroup : getMainCollection()) {
            stringBuilder.append(studyGroup.toString() + "\n");
        }
        return stringBuilder.toString();
    }

//    public String addNew(ArrayList<String> arguments , Integer id) {
//        if (getMainCollection().add(new StudyGroupFactory().createStudyGroup(id,  arguments))) {
//            setDateOfLastChange();
//            return "Successfully";
//        };
//        return "Adding new StudyGroup failed.";
//    }


//    public String addMax(ArrayList<String> arguments , Integer id) {
//        StudyGroup studyGroup = new StudyGroupFactory().createStudyGroup(id,  arguments);
//        var iter = 0 ;
//        for (StudyGroup studyGroup1 : this.getMainCollection() ) {
//            if (studyGroup1.compareTo(studyGroup) > 0) {
//                iter++;
//            }
//        }
//

    public String printEnum() {
        Stack<FormOfEducation> setOfFormOfEducation = new Stack<>();
        for (StudyGroup group : getMainCollection()) {
            if (!setOfFormOfEducation.contains(group.getFormOfEducation())) {
                setOfFormOfEducation.add(group.getFormOfEducation());
            }
        }
        return setOfFormOfEducation.toString();
    }
    public boolean remove(StudyGroup group){
         return studyGroups.remove(group);
    }

    public String printEnum(ArrayList<String> arguments) {
        Stack<FormOfEducation> setOfFormOfEducation = new Stack<>();
        for (StudyGroup group : getMainCollection()) {
            if (!setOfFormOfEducation.contains(group.getFormOfEducation())) {
                setOfFormOfEducation.add(group.getFormOfEducation());
            }
        }
        return setOfFormOfEducation.toString();

    }

    public String sumOfStudentsCount() {
        int sumOfStudentsCountValue = 0;
        Stack<StudyGroup> mainCollection = this.getMainCollection();
        for (StudyGroup group : mainCollection) {
            sumOfStudentsCountValue += group.getStudentsCount();
        }
        return "Sum of all StudentsCount in Study Group = " + sumOfStudentsCountValue;
    }

    public String removeID(String idValue) {
        try {
            int id = Integer.parseInt(idValue);
            Stack<StudyGroup> mainCollection = this.getMainCollection();
            for (StudyGroup group : mainCollection) {
                if (group.getId() == id) {
                    this.remove(group);
                    setDateOfLastChange();
                    return "Successfully";
                }
            }
        } catch (NumberFormatException e) {
            return "Failed. You typed wrong id.";
        }
        return "There is no element with that id.";
    }


    public String reorder() {
        var a = getMainCollection();
        if(a.size() != 0) {
            a.sort(StudyGroup::compareTo);
        }
        else {
            return a.toString() + " " + "There are not elements";
        }
        return a.toString();
    }

    public boolean check(ArrayList<String> arguments){
        for (StudyGroup el : getMainCollection()){
            try {
                if(el.getId() == Integer.parseInt(arguments.get(0))) {
                    return true;
                }
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }
}
