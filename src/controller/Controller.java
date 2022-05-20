package controller;

import java.util.Scanner;

import model.*;
import view.*;

/**
 * Logica do Programa
 */

 public class Controller{
    private static final Scanner sc = new Scanner(System.in);
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
                //e.printStackTrace();
            }
            }
            case 2-> {
            boolean creating = true;
            while (creating){
                ViewMenu.startMenuNew();
                test_int = Math.abs(sc.nextInt()) % 8;
                String[] argv = View.showInputStartMenu(test_int);
                switch (test_int){
                    case 1 -> {
                    SmartEP comerc = new SmartEP(argv[0]);
                    mod.addEnerg(comerc);
                    }
                    case 2 -> {
                    SmartHouse house = new SmartHouse(argv[0], argv[1]);
                    mod.addIsolHouse(house);
                    }
                    case 3 -> {
                    ViewMenu.startMenuNewDev();
                    test_int = Math.abs(sc.nextInt()) % 4;
                    View.showInputStartMenuDev(test_int);
                    switch (test_int){
                        case 1 -> {
                        boolean mode = Boolean.parseBoolean(argv[0]);
                        int tone = Integer.parseInt(argv[2]) % 3 + 1;
                        int dim = Integer.parseInt(argv[3]);
                        SmartDevice sd = new SmartBulb(mode, argv[1], tone, dim);
                        mod.addIsolDevice(sd);
                        }
                        case 2 -> {
                        boolean mode = Boolean.parseBoolean(argv[0]);
                        int v = Integer.parseInt(argv[2]);
                        double bbp = Double.parseDouble(argv[5]);
                        SmartDevice sd = new SmartSpeaker(mode, argv[1], v, argv[3], argv[4], bbp);
                        mod.addIsolDevice(sd);
                        }
                        case 3 -> {
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
                    mod.addRoom(argv[1], argv[0]);
                    }
                    case 5 -> {
                    mod.addDevToHouse(argv[0], argv[1], argv[2]);
                    }
                    case 6 -> {
                    mod.signContract(argv[0], argv[1]);
                    }
                    case 7 -> {
                    int num_days = View.timeSkipPrompt();
                    if (!mod.getHouse().isEmpty() || !mod.getDevs().isEmpty() || mod.getEnerg().isEmpty()){
                        View.showErrorTimeSkip();
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
        while (true){
        View.showDate();
        ViewMenu.mainMenu();
        int test_int = sc.nextInt() % 8;
        switch (test_int) {
            case 1 -> {
            ViewMenu.mainMenuDev();
            test_int = sc.nextInt() % 8;
            String[] argv = View.showDevAppPrompts(test_int);
            switch (test_int){
                case 1 ->{mod.turnOnDev(argv[0]);}
                case 2 ->{mod.turnOffDev(argv[0]);}
                case 3 ->{mod.incVol(argv[0]);}
                case 4 ->{mod.decVol(argv[0]);}
                case 5 ->{mod.incTone(argv[0]);}
                case 6 ->{mod.decTone(argv[0]);}
                case 7 ->{mod.getDevDC(argv[0]);}
                default -> {}
            }
            }
            case 2 -> {
            ViewMenu.mainMenuHouse();
            test_int = sc.nextInt() % 6;
            String[] argv = View.showHouseAppPrompts(test_int);
            switch (test_int){
                case 1-> {mod.setAllOn(argv[0]);}
                case 2-> {mod.setAllOnDiv(argv[0],argv[1]);}
                case 3-> {mod.setAllOff(argv[0]);}
                case 4-> {mod.setAllOffDiv(argv[0]);}
                case 5-> {mod.changeContract(argv[0],argv[1]);}
                default -> {}
            }
            }
            case 3 -> {
            ViewMenu.mainMenuComer();
            test_int = sc.nextInt() % 4;
            String[] argv = View.showProvAppPrompts(test_int);
            switch (test_int){
                case 1->{int value = Integer.parseInt(argv[1]); mod.changeBaseValue(argv[0], value);}
                case 2->{double tax = Double.parseDouble(argv[1]); mod.changeTaxFactor(argv[0],tax);}
                case 3->{mod.changeFormula(argv[0]);}
                default->{}
            }
            }
            case 4 -> {
            ViewMenu.mainMenuStats();
            test_int = sc.nextInt() % 5;

            }
            case 5 -> {
            View.showBills(mod);
            }
            case 6 -> {
            View.timeSkipPrompt();
            int days = sc.nextInt();
            mod.skipTime(days);
            }
            case 7 -> {save(mod);}
            default -> {
                View.exit();
                System.exit(1);
            }
        }
        }
    }

    public void save(Model mod){
        try{
            mod.save(file_name);
        } catch (Exception e){
            View.showError(e);
            //e.printStackTrace();
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