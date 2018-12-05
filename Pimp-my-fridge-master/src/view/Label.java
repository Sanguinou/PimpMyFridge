package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel{

	public Label() {

        this.setFont(new Font("Arial",Font.BOLD,30));
        this.setForeground(Color.decode("#18B4E8"));
        
    }
}
