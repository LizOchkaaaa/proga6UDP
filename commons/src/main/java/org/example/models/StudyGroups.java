package org.example.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Stack;

@XmlRootElement(name = "studyGroups")
@XmlAccessorType(XmlAccessType.NONE)
public class StudyGroups {

    @XmlElement(name = "studyGroup")
    private Stack<StudyGroup> groups;

    public Stack<StudyGroup> getStudyGroups() {
        return groups;
    }

    public void setStudyGroups(Stack<StudyGroup> groups) {
        this.groups = groups;
    }

    public StudyGroups() {
    }

}
