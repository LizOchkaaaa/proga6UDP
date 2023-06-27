package org.example.server;

import org.example.models.StudyGroup;
import java.util.Date;
import java.util.Stack;

/** A class that contains a collection and data about the collection */
public class LocalDateBase {
    private Stack<StudyGroup> mainCollection;
    private Date dateOfLastChange;
    private final Date dateOfInitialization;

    public LocalDateBase(Stack<StudyGroup> mainCollection) {
        this.mainCollection = mainCollection;
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
    }

    public Stack<StudyGroup> getMainCollection() {
        return mainCollection;
    }

    public void setMainCollection(Stack<StudyGroup> mainCollection) {
        this.mainCollection = mainCollection;
    }

    public Date getDateOfLastChange() {
        return dateOfLastChange;
    }

    public Date getDateOfInitialization() {
        return dateOfInitialization;
    }

    public void setDateOfLastChange(Date date){
        this.dateOfLastChange = new Date();
    }

}
