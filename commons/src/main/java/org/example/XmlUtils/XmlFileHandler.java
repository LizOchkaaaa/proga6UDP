package org.example.xmlUtils;

import org.example.interfaces.Loading;
import org.example.exceptions.FileLoadingException;
import org.example.exceptions.NoAccessToFileException;
import org.example.models.StudyGroup;
import org.example.models.StudyGroups;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Stack;

public class XmlFileHandler implements Loading.Loadable {

    private Stack<StudyGroup> groups;
    private ZonedDateTime initializationDate;
    /**
     * Class which check file is existed, and can be readable and writeable.
     */
    private boolean checkPermissions(File file) {
        if (!file.canRead()) {
            System.out.println("File cannot be read from. You should have this permission.");
            return false;
        }
        if (!file.canWrite()) {
            System.out.println("File cannot be written to. You should have this permission.");
            return false;
        }
        return true;
    }

    /**
     * Method for saving (marshaling) java collection to XML-file and updating hash of file
     */
    @Override
    public boolean save(Stack<StudyGroup> groups, File file) throws Exception {
        for (StudyGroup sg : groups) {
            System.out.println(sg.toString());
        }

        try {
            if (groups.size() == 0){
                new FileWriter(file, false).close();
                return true;
            }
            var fileWriter = new FileWriter(file);
            var groupsXml = new StudyGroups();
            groupsXml.setStudyGroups(groups);
            JAXBContext jaxbContext = JAXBContext.newInstance(StudyGroups.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Marshal the persons list in file
            jaxbMarshaller.marshal(groupsXml, fileWriter);
            fileWriter.close();
        } catch (JAXBException | IOException jaxbException) {
            System.err.print(jaxbException.getMessage());
            return false;
        }
        return true;
    }
   @Override
    public ZonedDateTime getInitializationTime() {
        return null;
    }

   @Override
    public Stack<StudyGroup> get() {
        return groups;
    }

   // @Override
    public void load(File xmlfile) throws NoAccessToFileException, FileLoadingException {
        try {
            var fileCreated = xmlfile.createNewFile();
            if (!checkPermissions(xmlfile)) {
                throw new NoAccessToFileException(xmlfile);
            }
            initializationDate = ZonedDateTime.now();
            if (!xmlfile.exists()) {
                System.out.println("0 groups were downloaded");
                groups = new Stack<>();
                return;
            }
            final QName qName = new QName("studyGroup");
            // create xml event reader for input stream
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            var fileReader = new FileReader(xmlfile);
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(fileReader);
            // initialize jaxb
            JAXBContext context = JAXBContext.newInstance(StudyGroups.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            groups = unmarshaller.unmarshal(xmlEventReader, StudyGroups.class).getValue().getStudyGroups();
            System.out.println(groups.toString());
            Collections.sort(groups);
            System.out.println("loaded " + "groups: " + groups.size());
        } catch (Exception jaxbException) {
            groups = new Stack<>();
            System.out.println("Troubles with file.");
        }
    }



}
