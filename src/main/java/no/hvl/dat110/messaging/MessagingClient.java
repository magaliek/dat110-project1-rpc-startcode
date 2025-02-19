package no.hvl.dat110.messaging;


import java.io.IOException;
import java.net.Socket;


public class MessagingClient {

	// name/IP address of the messaging server
	private String server;

	// server port on which the messaging server is listening
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// setup of a messaging connection to a messaging server
	public MessageConnection connect () {

		// client-side socket for underlying TCP connection to messaging server
        Socket clientSocket = null;
        try {
            clientSocket = new Socket(server, port);
        } catch (IOException e) {
            System.err.println("connection failed");
			return null;
        }


        return new MessageConnection(clientSocket);
	}
}
