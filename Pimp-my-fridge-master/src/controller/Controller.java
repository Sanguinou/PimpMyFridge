package controller;

import model.Model;
import model.SerialTest;
import view.View;

public class Controller {

	private CommPort commport;
    private View view;
    private SerialTest model;
	
	public Controller(SerialTest model, View view) {
		this.model = model;
		// TODO Auto-generated constructor stub
		CommPort commPort = new CommPort();
		SerialTest serialTest = new SerialTest();
		
		serialTest.initialize();
		Thread MainThread=new Thread() {
			public void run(){
				try {
					Thread.sleep(1000000);
				}catch(InterruptedException ie) {
					
				}
			}
		};
		MainThread.start();
		
		Thread Ard_Java = new Thread() {
			public void run() {
				while(true){
					serialTest.sendData("test");
					try {
						Thread.sleep(1000000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Ard_Java.start();
		
		System.out.println("Controller started");
	}
}
