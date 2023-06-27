package org.example;

import org.example.client.RequestHandler;
import org.example.client.client.InputClireader;
import org.example.client.client.ObjectReading;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainClient {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                getRequestHandlerProperties(scanner, InetAddress.getLocalHost());
                RequestHandler.getInstance().setSocketStatus(true);
                System.out.println(RequestHandler.getInstance().getInformation());

                ObjectReading objectReading = new ObjectReading();
                InputClireader.openStream();
                break;
//                if (objectReading.objread(AbstractCommand )) return;
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getEntryInformation(){
        return "Hi";
    }

    private static boolean requestTypeOfAddress(Scanner scanner) {
        String answer;
        do{
            System.out.println("Do you want to specify the address of the remote host?" + "[\"y\", \"n\"]:");
            answer = scanner.nextLine();
        }while (!answer.equals("y") && !answer.equals("n"));
        return answer.equals("y");
    }

    private static int getPort(Scanner scanner){
        String arg;
        Pattern remoteHostPortPattern = Pattern.compile("^\\s*(\\d{1,5})\\s*");

        do {
            System.out.print("\nPlease, enter remote host port(1-65535): ");
            arg = scanner.nextLine();
        } while (!(remoteHostPortPattern.matcher(arg).find() && (Integer.parseInt(arg.trim()) < 65536)
                && (Integer.parseInt(arg.trim()) > 0)));

        return Integer.parseInt(arg.trim());
    }
    private static void getRequestHandlerProperties(Scanner scanner, InetAddress localHost) {
        InetAddress remoteHostAddress = localHost;
        if(requestTypeOfAddress(scanner)) {
            String arg;
            Pattern hostAddress = Pattern.compile("^\\s*([\\w.]+)\\s*");

            do{
                System.out.print("Please, enter remote host address! (Example: 5.18.215.199): ");
                arg = scanner.nextLine();
            } while (!hostAddress.matcher(arg).find());
                try {
                    remoteHostAddress = InetAddress.getByName(arg.trim());
                } catch (UnknownHostException e) {
                    System.out.println(
                            "\nThe program could not find the server by the specified address!\n " +
                                    "The default address(localhost) will be used!");;
                }

        }
        RequestHandler.getInstance().setRemoteHostSocketAddress(
                new InetSocketAddress(remoteHostAddress, getPort(scanner)));
    }

}