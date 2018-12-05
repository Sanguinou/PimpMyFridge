import controller.Controller;
import model.Model;
import model.SerialTest;
import view.View;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SerialTest model = new SerialTest();
        View view = new View();
        Controller controller = new Controller(model, view);
        model.addObserver(view);
	}
}
