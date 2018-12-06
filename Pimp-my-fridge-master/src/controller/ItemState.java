package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ItemState implements ItemListener {
	
	private String item;
	private int consigne = 0;
	
	public void itemStateChanged(ItemEvent e) {
		
		System.out.println("event trigger on : " + e.getItem());
		
		item = (String) e.getItem();
		
		switch (item) {
		case "19°C" :
			consigne = 19;
			
			break;
		case "18°C" :
			consigne = 18;
			System.out.println(consigne);
			break;
		case "17°C" :
			consigne = 17;
			break;
		case "16°C" :
			consigne = 16;
			break;
		case "15°C" :
			consigne =15;
			break;
		default:
			break;
		}
	}

}
