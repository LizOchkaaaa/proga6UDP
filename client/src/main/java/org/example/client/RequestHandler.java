package org.example.client;

import org.example.Request;
import org.example.models.StudyGroup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;

public class RequestHandler {
    private static RequestHandler instance;
    private SocketAddress socketAddress;
    private boolean socketStatus;

    public static RequestHandler getInstance(){
        if(instance == null) instance = new RequestHandler();
        return instance;
    }

    private RequestHandler() {
    }

    public String send(Request request) {
        try {
            ClientWorker clientWorker = new ClientWorker(socketAddress);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(request);
            return clientWorker.sendRequest(byteArrayOutputStream.toByteArray());
        }  catch (IOException e) {
            return "Request can not be serialized, call programmer";
        }
    }

    public String send(Request request , StudyGroup studyGroup){
        if(studyGroup != null) {
            request.addStudyGroup(studyGroup);
            return send(request);
        }
        return "Study group isn't exist";
    }

    public void setRemoteHostSocketAddress(SocketAddress aSocketAddress){
        socketAddress = aSocketAddress;
    }

    public String getInformation(){
        return "Connection\n" +  "remote host address: " + socketAddress;
    }

    public void setSocketStatus(boolean b) {
        socketStatus = b;
    }

    public boolean getSocketStatus(){
        return socketStatus;
    }
}
