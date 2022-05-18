import controller.Controller;
import model.State;
import view.View;

public class Main {
    public static final State sys = new State();
    public static Controller control = new Controller();
    public static void main(String[] args){
        View.welcomeStart();
        control.startApp(sys);
    }
}
