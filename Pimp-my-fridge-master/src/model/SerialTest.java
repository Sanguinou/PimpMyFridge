package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Observable;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialTest extends Observable implements SerialPortEventListener{

	SerialPort serialPort;
	private static final String PORT_NAMES[] = {
			"COM3",
	};
	
	private BufferedReader input;
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	
	int available = 1;
	
	public float temperature;
	public float dhtT;
	public float dhtH;
	public float dewPoint;
	
	public SerialTest() {
		System.out.println("serialtest");
	}
	
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
				String inputLine = input.readLine();
				switch (available) {
				case 1 : 
					temperature = Float.parseFloat(inputLine);
					setChanged();
					break;
			
				case 2 :
					dhtT = Float.parseFloat(inputLine);
					setChanged();
					break;
					
				case 3 :
					dhtH = Float.parseFloat(inputLine);
					setChanged();
					break;
					
				case 4 : 
					dewPoint = Float.parseFloat(inputLine);
					setChanged();
					break;
					
				default :
					break;
				}
				
				available++;
				if (available > 4) {
					notifyObservers();
					available = 1;
				}
				
			}catch(Exception e){
				System.err.println(e.toString());
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
