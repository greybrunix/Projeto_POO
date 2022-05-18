package controller;

import java.util.Scanner;

import model.*;
import view.*;

/**
 * Logica do Programa
 */

 public class Controller{
    private static final Scanner sc = new Scanner(System.in);
    private static final Scanner ssc = new Scanner(System.in);
    private static final FileLoadingSaving file = new FileLoadingSaving();
    private static final String save_slot = "save_slot.obj"; 
    public Controller(){
    }

    public void startApp(Model mod)
    {
        ViewMenu.startMenu();
        int test_int = sc.nextInt();
        switch(Math.abs(test_int) % 3){
            case 1 -> {
                mod = new Model();
                try{
                    mod = file.loadState(save_slot);
                } catch (Exception e) {
                    View.showError(e);
                    startApp(mod);
                    break;
                }
                View.welcomeLoad();
                app(mod);
                break;
            }
            case 2-> {
                startAppNewSys(mod);
                break;
            }
            default -> {
                View.exit();
                System.exit(1);
                break;
            }
    }
}

    public void startAppNewSys(Model mod) {
        ViewMenu.startMenuNew();
        int test_int = sc.nextInt();
        switch (Math.abs(test_int) % 8){
            case 1 -> {
                System.out.println("Insira o nome do comercializador de energia: ");
                System.out.print(">> ");
                SmartEP comerc = new SmartEP(ssc.nextLine());
                mod.addEnerg(comerc);
                startAppNewSys(mod);
                break;
            }
            case 2 -> {
                System.out.println("Insira o nome do dono e o seu NIF separados por virgulas: ");
                System.out.print(">> ");
                String[] argv = ssc.nextLine().split(",");
                SmartHouse house = new SmartHouse(argv[0], argv[1]);
                mod.addIsolHouse(house);
                startAppNewSys(mod);
                break;
            }
            case 3 -> {startAppDevMenu(mod); break;}
            case 4 -> {
                System.out.println("Insira o nome da divisao e o nome do dono da casa separado por virgulas: ");
                System.out.print(">> ");
                String[] argv = ssc.nextLine().split(",");
                mod.addRoom(argv[1], argv[0]);
                startAppNewSys(mod);
                break;
            }
            case 5 -> {
                System.out.println("Insira o ID do disp., o nome do dono e o nome do quarto: ");
                System.out.print(">> ");
                String[] argv = ssc.nextLine().split(",");
                mod.addDevToHouse(argv[0], argv[1], argv[2]);
                startAppNewSys(mod);
                break;
            }
            case 6 -> {
                System.out.println("Insira o nome do dono e o nome da empresa");
                System.out.print(">> ");
                String[] argv = ssc.nextLine().split(",");
                mod.signContract(argv[0], argv[1]);
                startAppNewSys(mod);
                break;
            }
            case 7 -> {
                System.out.println("Insira o numero de dias que pretende avancar: ");
                System.out.print(">> ");
                int num_days = sc.nextInt();
                mod.skipTime(num_days);
                app(mod);
            }
            default -> startApp(mod);
        }
    }

    public void startAppDevMenu(Model mod){
        ViewMenu.startMenuNewDev();
        int test_int = sc.nextInt();
        switch (Math.abs(test_int) % 4){
            case 1 -> {
                System.out.println("Insere o modo, o id, o tom e as dimensoes: ");
                System.out.print(">> ");
                String[] argv = ssc.nextLine().split(",");
                boolean mode = Boolean.parseBoolean(argv[0]);
                int tone = Integer.parseInt(argv[2]);
                int dim = Integer.parseInt(argv[3]);
                SmartDevice sd = new SmartBulb(mode, argv[1], tone, dim);
                mod.addIsolDevice(sd);
                startAppNewSys(mod);
                break;
            }
            case 2 -> {
                System.out.println("Insere o modo, o id, o volume, a radio, a marca e o valor base da marca: ");
                System.out.print(">> ");
                String[] argv= ssc.nextLine().split(",");
                boolean mode = Boolean.parseBoolean(argv[0]);
                int v = Integer.parseInt(argv[2]);
                double bbp = Double.parseDouble(argv[5]);
                SmartDevice sd = new SmartSpeaker(mode, argv[1], v, argv[3], argv[4], bbp);
                mod.addIsolDevice(sd);
                startAppNewSys(mod);
                break;
            }
            case 3 -> {
                System.out.println("Insere o modo, o id, a resolucao e o tamanho do video: ");
                System.out.print(">> ");
                String[] argv = ssc.nextLine().split(",");
                boolean mode = Boolean.parseBoolean(argv[0]);
                int res = Integer.parseInt(argv[2]);
                int size = Integer.parseInt(argv[3]);
                SmartDevice sd = new SmartCamera(mode, argv[1], res, size);
                mod.addIsolDevice(sd);
                startAppNewSys(mod);
                break;
            }
            default -> startAppNewSys(mod);
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
 }