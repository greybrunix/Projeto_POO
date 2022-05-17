package src.view;
import java.util.Scanner;
import java.util.StringTokenizer;

import src.control.Commands;
import src.control.SimuladorControl;
import src.model.SimuladorModel;


/**
 * UI
 */

public class View {
    public static void main(String args[]) {
        boolean over = false;
        SimuladorControl controlo = new SimuladorControl();
        SimuladorModel modelo = new SimuladorModel();
        Scanner number = new Scanner(System.in);
        Scanner command = new Scanner(System.in);
        StringTokenizer commandToken = new StringTokenizer(command.toString());

        ViewMenu.InitialMenu();
        Commands.InitialMenu(number.nextInt());

        while (!over) {
            ViewMenu.Menu();
            Commands.Menu(number.nextInt());
        }
        number.close();
        command.close();
    }
}