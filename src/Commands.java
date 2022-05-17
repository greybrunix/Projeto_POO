package src.MVC;
import src.classes.*;
import src.classes.*;

import java.awt.*;
import java.util.Scanner;


public class Commands {

    static Scanner sc = new Scanner(System.in);

    public static void InitialMenu(Integer command) {
        switch (command) {
            case 1 -> {
                EnergyProvider novo = new EnergyProvider();
            }
            case 2 -> {
                SmartHouse novo = new SmartHouse();
            }
            case 3 -> {
                Menus.SmartDevices();
            }
            case 4 -> SmartHouse.AddDivision();
            case 5 -> SmartHouse.AddDevice();
            case 6 -> SmartDevice.setMode(true);
            case 7 -> SmartDevice.setMode(false);
            case 8 -> SimuladorControl.SkipTime();
            case 9 -> {
                SimuladorControl.Save();
                System.out.println("Guardado com sucesso!");
            }
            case 10 -> {
                System.out.println("Até à próxima!");
                System.exit(0);
            }
            }
    }

    public static void Menu(Integer command) {
        switch (command) {
            case 1 -> {
                EnergyProvider novo = new EnergyProvider();
            }
            case 2 -> {
                SmartHouse novo = new SmartHouse();
            }
            case 3 -> {
                Menus.SmartDevices();
                Commands.SmartDevices(sc.nextInt());
            }
            case 4 -> SmartHouse.AddDivision();
            case 5 -> SmartHouse.AddDevice();
            case 6 -> SmartDevice.setMode(true);
            case 7 -> SmartDevice.setMode(false);
            case 8 -> SimuladorControl.SkipTime();
            case 9 -> {
                Menus.Estatistica();
                Commands.Statistics(sc.nextInt());
            }
            case 10 -> {
                SimuladorControl.Save();
                System.out.println("Guardado com sucesso!");
            }
            case 11 -> {
                System.out.println("Até à próxima!");
                System.exit(0);
            }
            }
    }

    public static void Statistics(Integer command) {
        switch (command) {
            case 1 -> SimuladorControl.HouseExpensive();
            case 2 -> SimuladorControl.BiggestFacture();
            case 3 -> SimuladorControl.CompanyFactures();
            case 4 -> SimuladorControl.BiggestConsumers();
            }
    }

    public static void SmartDevices(Integer command) {
        switch (command) {
            case 1 -> {
                SmartBulb novo = new SmartBulb();
            }
            case 2 -> {
                SmartSpeaker novo = new SmartSpeaker();
            }
            case 3 -> {
                SmartCamera novo = new SmartCamera();
            }
        }
    }

 }
