package org.example.client;

import org.example.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class ResponseHandler {

    private static ResponseHandler instance;

    private ResponseHandler() {}


    public static ResponseHandler getInstance(){
        if(instance == null) instance = new ResponseHandler();
        return instance;
    }

    public String receive(ByteBuffer buffer) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
            Response response = (Response) inputStream.readObject();
            return response.toString();
        } catch (InvalidClassException e){
            return "FAILD";
        } catch (ClassNotFoundException | IOException  e) {
            return "FAILD";
        }
    }

    public String receive(String information){
        return information;
    }
}

