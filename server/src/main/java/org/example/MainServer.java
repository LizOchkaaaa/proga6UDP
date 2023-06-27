package org.example;

import org.example.xmlUtils.XmlFileHandler;
import org.example.models.StudyGroup;
import org.example.server.server.*;
import org.example.server.server.commands.ExitSaver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainServer {
    public static final Logger logger = Logger.getLogger(MainServer.class.getCanonicalName());
    private static InetSocketAddress address;

    public static void main(String[] args) throws IOException{
        logger.info("Connect with server");
        try (Scanner scanner = new Scanner(System.in)) {
            DatagramChannel server = DatagramChannel.open();
            server.configureBlocking(false);
            int Port = 43414;
            logger.info("Server listening port "+ Port);
            address = new InetSocketAddress(Port);
            server.bind(address);
            FileNameListener fileNameListener = new FileNameListener(logger);
            fileNameListener.listener();
            fileNameListener.reader();
            UniqueId id = new UniqueId();
            if (fileNameListener.getGroups() != null) {
                for (StudyGroup group : fileNameListener.getGroups()) {
                    if (UniqueId.getCurrentID() < group.getId()) {
                        id.setId(group.getId());
                    }
                }
            }
            LocalDateBase localDateBase = new LocalDateBase(fileNameListener.getGroups());
            Receiver receiver = new Receiver(localDateBase);
            Invoker invoker = new Invoker(receiver);
            Deliver deliver = new Deliver(invoker, id);
            AcceptingConnections connect = new AcceptingConnections(deliver, server, address);
            XmlFileHandler xmlFileHandler = new XmlFileHandler();
            Runtime.getRuntime().addShutdownHook(new Thread(new ExitSaver(xmlFileHandler,receiver)));

            connect.start();
        }
    }

//    private static int getPort(Scanner scanner) {
//        String arg;
//        Pattern remoteHostPortPattern = Pattern.compile("^\\s*(\\d{1,5})\\s*");
//        do {
//            System.out.print("Please, enter remote host port(1-65535): ");
//            arg = scanner.nextLine();
//        } while (!(remoteHostPortPattern.matcher(arg).find() && (Integer.parseInt(arg.trim()) < 65536)
//                && (Integer.parseInt(arg.trim()) > 0)));
//        return Integer.parseInt(arg.trim());
//    }

//    public static DatagramSocket getDatagramSocket(Scanner scanner){
//        while (true) {
//            try {
//                return new DatagramSocket(getPort(scanner));
//            }catch (SocketException e){
//                System.out.println("Socket could not bind to the specified local port");
//            }
//        }
//    }
}
