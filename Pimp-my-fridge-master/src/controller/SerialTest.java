package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialTest implements SerialPortEventListener{

	SerialPort serialPort;
	private static final String PORT_NAMES[] = {
			"COM10",
	};
	
	private BufferedReader input;
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	
	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		//port available
			while(portEnum.hasMoreElements()) {
				CommPortIdentifier currPortId = (CommPortIdentifier)portEnum.nextElement();
				for(String portName: PORT_NAMES) {
					if(currPortId.getName().equals(portName)) {
						portId = currPortId;
						break;
					}
				}
			}
			if(portId == null) {
				System.out.println("Could not find COM port");
				return;
			}
			try {
				serialPort = (SerialPort)portId.open(this.getClass().getName(),TIME_OUT);
				serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_MARK);
				
				input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
				output = serialPort.getOutputStream();
				
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
			}catch(Exception e){
				System.err.println(e.toString());
			}
		}
	//event onclose
	public synchronized void close(){
		if(serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}
	
	//Arduino search method implements by rxtx
	@Override
	public synchronized void serialEvent(SerialPortEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine = null;
				if(input.ready()) {
					inputLine = input.readLine();
					System.out.println(inputLine);
				}
			}catch(Exception e){
				//System.err.println(e.toString());
			}
		}
	}
	//data send method
    public synchronized void sendData(String data){
        try {
            output.write(data.getBytes());
            output.flush();
        }
        catch(Exception e){
            //System.out.println(e.toString());
        }
    }
}
