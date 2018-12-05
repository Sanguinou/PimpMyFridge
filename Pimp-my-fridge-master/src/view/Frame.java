package view;

import javax.swing.JFrame;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;

	public Frame(){
		this.setVisible(true);
		this.setTitle("Pimp My Fridge - Louis.L, Antoine.D, Matthieu.B, Vincent.D");
		this.setSize(1000, 750);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
	}
}
