import controller.Controller;
import model.FileLoadingSaving;
import model.State;

public class Main {
    public static final State sys = new State();
    public static Controller control = new Controller();
    public static void main(String[] args){
        control.app(sys);
    }
}