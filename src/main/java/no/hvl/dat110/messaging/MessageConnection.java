package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
			close();
		}
	}

	public void send(Message message) {

		byte[] data = MessageUtils.encapsulate(message);
        try {
            outStream.write(data);
            outStream.flush();
        } catch (IOException e) {
            System.err.println("connection failure");
        }
    }

	public Message receive() {

		byte[] buffer = new byte[128];
        int bytesRead = 0;
        try {
            bytesRead = inStream.read(buffer);
        } catch (IOException e) {
            System.err.println("failed reading");
        }

        while (bytesRead < 128 && bytesRead != -1) {
            int currentRead = 0;
            try {
                currentRead = inStream.read(buffer, bytesRead, 128-bytesRead);
            } catch (IOException e) {
                System.err.println("failed reading");
            }
            if (currentRead != -1) {
				bytesRead += currentRead;
			}
		}

		return MessageUtils.decapsulate(buffer);
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}