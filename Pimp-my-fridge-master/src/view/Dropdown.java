package view;

import java.awt.Dimension;

import javax.swing.JComboBox;

import controller.ItemState;

public class Dropdown extends JComboBox{

	private int tab[] = {19, 18, 17, 16, 15};
	
	public Dropdown(){
		for(int i = 0; i < 5; i++) {
			this.addItem(tab[i] + "°C");
			
			this.addItemListener(new ItemState());
		}        
	}
}
