package org.example.interfaces;

import org.example.models.StudyGroup;
import java.io.File;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Stack;

public interface Loadable {
    /** loads the collection from the file */
    void load(File file) throws Exception;
    /** saves the collection LinkedList<Product> products to the file*/
    boolean save(List<StudyGroup> studyGroups, File file) throws Exception;
    /** gets the initialization time */
    ZonedDateTime getInitializationTime();
    /**
     * returns loaded collection
     */
    Stack<StudyGroup> get();
}