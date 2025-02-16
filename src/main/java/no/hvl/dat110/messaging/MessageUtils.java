package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.Socket;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = new byte[SEGMENTSIZE];
		byte[] data = message.getData();

		byte count = 0;
		for (byte b : data) {
			if (b != 0) {
				count++;
			}
		}

		segment[0] = count;
		System.arraycopy(data, 0, segment, 1, data.length);

		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		byte[] data = new byte[segment[0]];

		System.arraycopy(segment, 1, data, 0, segment[0]);

		return new Message(data);
	}
	
}
