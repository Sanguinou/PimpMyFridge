import java.awt.EventQueue;

import controller.Controller;
import model.SerialTest;
import view.View;

public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					SerialTest model = new SerialTest();
			        View view = new View();
			        Controller controller = new Controller(model, view);
			        model.addObserver(view);
			        model.initialize();
					
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}
			
		});
	}
}
