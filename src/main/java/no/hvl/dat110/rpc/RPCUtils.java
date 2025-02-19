package no.hvl.dat110.rpc;

import java.nio.charset.StandardCharsets;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = new byte[1+payload.length];
		rpcmsg[0] = rpcid;

		System.arraycopy(payload, 0, rpcmsg, 1, payload.length);
		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = new byte[rpcmsg.length -1];

		System.arraycopy(rpcmsg, 1, payload, 0, rpcmsg.length-1);
		
		return payload;
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {

        return str.getBytes(StandardCharsets.UTF_8);
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {

        return new String(data, StandardCharsets.UTF_8);
	}
	
	public static byte[] marshallVoid() {
		
		return new byte[0];
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		
		if (data.length != 0) {
			throw new RuntimeException("unexpected response for void method");
		}
		
	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {

		byte[] encoded = new byte[4];

		encoded[0] = (byte) ((x >> 24) & 0xFF);
		encoded[1] = (byte) ((x >> 16) & 0xFF);
		encoded[2] = (byte) ((x >> 8) & 0xFF);
		encoded[3] = (byte) (x & 0xFF);

		return encoded;

	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {

        return (data[0] & 0xff) << 24 |
                (data[1] & 0xff) << 16 |
                (data[2] & 0xff) << 8 |
                (data[3] & 0xff);
		
	}
}
