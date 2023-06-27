package org.example.server.Commands;

import org.example.xmlUtils.XmlFileHandler;
import org.example.server.Receiver;

import java.io.File;
import java.util.logging.Logger;

public class ExitSaver implements Runnable  {
    private final XmlFileHandler xmlFileHandler;
    private final Receiver receiver;
    final Logger logger = Logger.getLogger(ExitSaver.class.getCanonicalName());


    public ExitSaver(XmlFileHandler xmlFileHandler, Receiver receiver) {
        this.xmlFileHandler = xmlFileHandler;
        this.receiver = receiver;
    }

    @Override
    public void run() {
        logger.info("The client is disconnected from the server");
        try {
            xmlFileHandler.save(receiver.getMainCollection(), new File("file.xml"));
        } catch (Exception e) {
            logger.info("exception");
        }
    }
}
