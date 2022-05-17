package src.MVC;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * UI
 */

public class SimuladorView {
    public static void main(String args[]) {
        boolean over = false;
        SimuladorControl controlo = new SimuladorControl();
        SimuladorModel modelo = new SimuladorModel();
        Scanner number = new Scanner(System.in);
        Scanner command = new Scanner(System.in);
        StringTokenizer commandToken = new StringTokenizer(command.toString());

        Menus.InitialMenu();
        Commands.InitialMenu(number.nextInt());

        while (!over) {
            Menus.Menu();
            Commands.Menu(number.nextInt());
        }
        number.close();
        command.close();
    }
}