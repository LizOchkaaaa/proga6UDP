package org.example.server;

import org.example.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class AcceptingConnections {

    private final Deliver deliver;
    private final DatagramChannel datagramChannel;
    private byte[] buffer;
    private InetSocketAddress address;
    final Logger logger = Logger.getLogger(Receiver.class.getCanonicalName());


    public AcceptingConnections(Deliver aDeliver, DatagramChannel aDatagramChannel, InetSocketAddress aAddress) {

        datagramChannel = aDatagramChannel;
        deliver = aDeliver;
        address = aAddress;
        buffer = new byte[4096];
    }

    public void start() {


        try {
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                do{
                    address = (InetSocketAddress) datagramChannel.receive(byteBuffer);
                }while (address == null);
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer));
                Request request = (Request) ois.readObject();
                logger.info("Server received command: " + request.toString());
                deliver.answer(request, datagramChannel, address);
            }
            } catch (IOException e) {
                logger.info("Some problem's with network!");
            }catch (ClassNotFoundException e){
                logger.info("Client sent outdated request!");
            } catch (ExecutionException | InterruptedException e) {
                logger.info("Problem!");
            }

    }
}
