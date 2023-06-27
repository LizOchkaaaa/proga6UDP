package org.example.server;

import org.example.Request;
import org.example.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

/**
 * Class for send answer
 */
public class Deliver {
    private final Invoker invoker;
    private final UniqueId id;
    private byte[] buffer;
    private final Logger logger = Logger.getLogger(Deliver.class.getCanonicalName());


    public Deliver(Invoker invoker, UniqueId id) {
        this.invoker = invoker;
        this.id = id;
    }

    public void answer(Request request , DatagramChannel aDatagramChannel, InetSocketAddress aSocketAddress) throws IOException, ExecutionException, InterruptedException {
        Response response = invoker.execute(id.getNewId(request));


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outObj = new ObjectOutputStream(byteArrayOutputStream);
        outObj.writeObject(response);

        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        aDatagramChannel.send(buffer, aSocketAddress);
        logger.info("Server send an answer!");

    }
}
