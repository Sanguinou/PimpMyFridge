package controller;

import java.util.Enumeration;
import gnu.io.*;

public class CommPort {
	
	Enumeration enumComm;
	CommPortIdentifier serialPortId;
	
	
	public CommPort(){
		findPort();
	}
	
	//Method using Rxtx lib to get COM Port
	void findPort(){
		enumComm = CommPortIdentifier.getPortIdentifiers();
		while(enumComm.hasMoreElements()) {
			serialPortId = (CommPortIdentifier)enumComm.nextElement();
			if(serialPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println(serialPortId.getName());
			}
		}
	}
}
