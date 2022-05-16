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

        System.out.printf("**************MENU**************\n"
                + "*  1- Criar Fornecedor          *\n"
                + "*  2- Criar Casa                *\n"
                + "*  3- Criar Dispositivo         *\n"
                + "*  4- Adicionar Divisao         *\n"
                + "*  5- Adicionar Disp. a Casa    *\n"
                + "*  6- Ligar Dispositivo         *\n"
                + "*  7- Desligar Dispositivo      *\n"
                + "*  8- Avançar no Tempo          *\n"
                + "*  9- Guardar                   *\n"
                + "* 10- Fechar                    *\n");
        number.nextInt();

        while (!over) {
            System.out.printf("**************MENU**************\n"
                    + "*  1- Criar Fornecedor          *\n"
                    + "*  2- Criar Casa                *\n"
                    + "*  3- Criar Dispositivo         *\n"
                    + "*  4- Adicionar Divisao         *\n"
                    + "*  5- Adicionar Disp. a Casa    *\n"
                    + "*  6- Ligar Dispositivo         *\n"
                    + "*  7- Desligar Dispositivo      *\n"
                    + "*  8- Avançar no Tempo          *\n"
                    + "*  9- Abrir Menu Estatistica    *\n"
                    + "* 10- Guardar                   *\n"
                    + "* 11- Fechar                    *\n");
            number.nextInt();
            commandToken.equals(commandToken);
        }
        number.close();
        command.close();
    }
}