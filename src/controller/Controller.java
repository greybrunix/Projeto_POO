package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    public void startApp(State sys)
    {
        ViewMenu.startMenu();
        switch(sc.nextInt() % 3){
            case 1 -> {
                sys = new State();
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

    public void startAppNewSys(State sys) {
        ViewMenu.startNewMenu();
        switch (sc.nextInt()){
            case 1 -> {

            }

            default -> startApp(sys);
        }
    }

    public void app(State sys) {
    }

    public void stats(State sys){
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