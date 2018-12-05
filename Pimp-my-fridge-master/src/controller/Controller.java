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
		this.view = view;

		//CommPort commPort = new CommPort();
		
		System.out.println("Controller started");
	}
}
