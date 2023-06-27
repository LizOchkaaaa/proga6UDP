package org.example.server;


import org.example.xmlUtils.XmlFileHandler;
import org.example.exceptions.FileLoadingException;
import org.example.exceptions.NoAccessToFileException;
import org.example.models.StudyGroup;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.logging.Logger;

/**A class that opens and reads a file*/
public class FileNameListener {
    private String fileName;
    private Stack<StudyGroup> groups;
    private final Logger logger;

    public FileNameListener(Logger logger) {
        this.logger = logger;
    }

    public String getFileName(){
        return fileName;
    }

    public DataInOutStatus listener(){
        logger.info("Type name of file.");
        try {
            this.fileName = InputServerReader.getInputReader().nextLine();
        }catch (NoSuchElementException e) {
            logger.info("No data");
            return DataInOutStatus.FAILED;
        }
        return DataInOutStatus.SUCCESSFULLY;
    }

    public DataInOutStatus reader(){
        try {
            File file1 = new File(fileName);
            XmlFileHandler read  = new XmlFileHandler();
            read.load(file1);
            groups = read.get();
        } catch (FileLoadingException | NoAccessToFileException e) {
            return DataInOutStatus.FAILED;
        }
        return DataInOutStatus.SUCCESSFULLY;
    }
    public Stack<StudyGroup> getGroups() {
        return groups;
    }
}
