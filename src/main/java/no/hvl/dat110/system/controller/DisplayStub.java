package no.hvl.dat110.system.controller;

import no.hvl.dat110.messaging.MessageUtils;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {

		// implement marshalling, call and unmarshalling for write RPC method
		byte[] marshalledMessage = RPCUtils.marshallString(message);
		byte[] results = rpcclient.call((byte) Common.WRITE_RPCID, marshalledMessage);
		RPCUtils.unmarshallVoid(results);
	}
}
