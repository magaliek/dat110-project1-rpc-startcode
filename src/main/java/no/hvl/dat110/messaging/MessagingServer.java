package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class MessagingServer {

	// server-side socket for accepting incoming TCP connections
	private ServerSocket welcomeSocket;

	public MessagingServer(int port) {

		try {

			this.welcomeSocket = new ServerSocket(port);

		} catch (IOException ex) {

			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// accept an incoming connection from a client
	public MessageConnection accept() {

		Socket clientReceived;

        try {
            clientReceived = this.welcomeSocket.accept();
        } catch (IOException e) {
            System.err.println("connection failed");
			return null;
        }

        return new MessageConnection(clientReceived);

	}

	public void stop() {

		if (welcomeSocket != null) {

			try {
				welcomeSocket.close();
			} catch (IOException ex) {

				System.out.println("Messaging server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
