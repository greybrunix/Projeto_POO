import controller.Controller;
import model.Model;
import view.View;

public class Main {
    public static final Model mod = new Model();
    public static Controller control = new Controller();
    public static void main(String[] args){
        View.welcomeStart();
        control.startApp(mod);
    }
}
