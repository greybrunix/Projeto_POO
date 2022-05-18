package controller;

import java.util.Scanner;
import model.*;
import view.*;

/**
 * Logica do Programa
 */

 public class Controller{
    private static final Scanner sc = new Scanner(System.in);
    private static final FileLoadingSaving file = new FileLoadingSaving();
    private static final String save_slot = "save_slot.obj"; 
    public Controller(){
    }

    public void startApp(Model sys)
    {
        ViewMenu.startMenu();
        int test_int = sc.nextInt();
        switch(Math.abs(test_int) % 3){
            case 1 -> {
                sys = new Model();
                try{
                    sys = file.loadState(save_slot);
                } catch (Exception e) {
                    View.showError(e);
                    startApp(sys);
                    break;
                }
                View.welcomeLoad();
                app(sys);
                break;
            }
            case 2-> {
                startAppNewSys(sys);
                break;
            }
            default -> {
                View.exit();
                System.exit(1);
                break;
            }
    }
}

    public void startAppNewSys(Model sys) {
        ViewMenu.startMenuNew();
        int test_int = sc.nextInt();
        switch (Math.abs(test_int) % 9){
            case 1 -> {
                System.out.println("Insira os dados do Fornecedor separado por espaços: ");
                System.out.print(">> ");
                String data = sc.nextLine();
                data.split(" ");

            }

            default -> startApp(sys);
        }
    }

    public void app(Model sys) {
    }

    public void stats(Model sys){
    }

    public void houseMostExp(String name) {
    }
    public void largestBill() {
    }
    public void companyBills() {
    }
    public void podiumConsumption() {
    }
    public void skipTime(String dias) {
    }
    public void createHouse() {
    }
 }