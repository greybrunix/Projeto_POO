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
    private static final String file_name = "save_slot.bin";
    public Controller(){
    }

    public void startApp(Model mod)
    {
        boolean start = true;
        while (start){
        ViewMenu.startMenu();
        int test_int = sc.nextInt();
        switch(Math.abs(test_int) % 3){
            case 1 -> {
            try{
                mod = mod.loadState(file_name);
                View.welcomeLoad();
                start = false;
                app(mod);
            } catch (Exception e) {
                View.showError(e);
            }
            }
            case 2-> {
            boolean creating = true;
            while (creating){
                ViewMenu.startMenuNew();
                test_int = Math.abs(sc.nextInt()) % 8;
                View.showInputStartMenu(test_int);
                switch (test_int){
                    case 1 -> {
                    SmartEP comerc = new SmartEP(ssc.nextLine());
                    mod.addEnerg(comerc);
                    }
                    case 2 -> {
                    String[] argv = ssc.nextLine().split(",");
                    SmartHouse house = new SmartHouse(argv[0], argv[1]);
                    mod.addIsolHouse(house);
                    }
                    case 3 -> {
                    ViewMenu.startMenuNewDev();
                    test_int = Math.abs(sc.nextInt()) % 4;
                    View.showInputStartMenuDev(test_int);
                    switch (test_int){
                        case 1 -> {
                        String[] argv = ssc.nextLine().split(",");
                        boolean mode = Boolean.parseBoolean(argv[0]);
                        int tone = Integer.parseInt(argv[2]);
                        int dim = Integer.parseInt(argv[3]);
                        SmartDevice sd = new SmartBulb(mode, argv[1], tone, dim);
                        mod.addIsolDevice(sd);
                        }
                        case 2 -> {
                        String[] argv= ssc.nextLine().split(",");
                        boolean mode = Boolean.parseBoolean(argv[0]);
                        int v = Integer.parseInt(argv[2]);
                        double bbp = Double.parseDouble(argv[5]);
                        SmartDevice sd = new SmartSpeaker(mode, argv[1], v, argv[3], argv[4], bbp);
                        mod.addIsolDevice(sd);
                        }
                        case 3 -> {
                        String[] argv = ssc.nextLine().split(",");
                        boolean mode = Boolean.parseBoolean(argv[0]);
                        int res = Integer.parseInt(argv[2]);
                        int size = Integer.parseInt(argv[3]);
                        SmartDevice sd = new SmartCamera(mode, argv[1], res, size);
                        mod.addIsolDevice(sd);
                        }
                        default -> {}
                    }
                    }
                    case 4 -> {
                    String[] argv = ssc.nextLine().split(",");
                    mod.addRoom(argv[1], argv[0]);
                    }
                    case 5 -> {
                    String[] argv = ssc.nextLine().split(",");
                    mod.addDevToHouse(argv[0], argv[1], argv[2]);
                    }
                    case 6 -> {
                    String[] argv = ssc.nextLine().split(",");
                    mod.signContract(argv[0], argv[1]);
                    }
                    case 7 -> {
                    int num_days = sc.nextInt();
                    if (!mod.getHouse().isEmpty() || !mod.getDevs().isEmpty() || mod.getEnerg().isEmpty()){
                        System.out.println("Error check if housessdfsd");
                    }
                    else{
                        mod.skipTime(num_days);
                        View.welcomeCreate();
                        creating = false;
                        start = false;
                        app(mod);
                    }
                    }
                    default -> creating = false;
                }
            }
            }
            default -> {
            View.exit();
            System.exit(1);
            }
        }
        }
    }

    public void app(Model mod) {
        ViewMenu.mainMenu();
        int test_int = sc.nextInt() % 8;
        View.showAppPrompts(test_int);
        switch (test_int) {
            case 1 -> {}
            case 2 -> {}
            case 3 -> {}
            case 4 -> {}
            case 5 -> {}
            case 6 -> {}
            case 7 -> save(mod);
            default -> {
                View.exit();
                System.exit(1);
            }
        }
    }

    public void stats(Model sys){
    }
    public void save(Model mod){
        try{
            mod.save(file_name);
        } catch (Exception e){
            View.showError(e);
        }
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