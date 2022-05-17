package src.MVC;
import src.classes.*;
import src.classes.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Commands {

    static Scanner sc = new Scanner(System.in);

    public static void InitialMenu(Integer command) {
        Scanner sc = new Scanner(System.in);
        switch (command) {
            case 1 -> {
                System.out.println("Insira o preço da energia por kWh: ");
                EnergyProvider novo = new EnergyProvider(sc.nextInt()); //Falta método de guardar
            }
            case 2 -> {
                System.out.println("Insira os dados da seguinte forma: Nome do Cliente, NIF");
                StringTokenizer token = new StringTokenizer(sc.nextLine());
                String nome_cliente = token.nextToken();
                String NIF = token.nextToken();
                SmartHouse novo = new SmartHouse(nome_cliente, NIF);
                SmartHouse.CreateHouse(); //Falta método de guardar
            }
            case 3 -> {
                Menus.SmartDevices();
                Commands.SmartDevices(sc.nextInt());
            }
            case 4 -> SmartHouse.AddDivision(); //Falta método
            case 5 -> SmartHouse.AddDevice(); //Falta método
            case 6 -> SmartDevice.setMode(true);
            case 7 -> SmartDevice.setMode(false);
            case 8 -> SimuladorControl.SkipTime(); //Falta método
            case 9 -> {
                SimuladorControl.Save(); //Falta método
                System.out.println("Guardado com sucesso!");
            }
            case 10 -> {
                System.out.println("Até à próxima!");
                System.exit(0);
            }
            }
    }

    public static void Menu(Integer command) {
        Scanner sc = new Scanner(System.in);
        switch (command) {
            case 1 -> {
                System.out.println("Insira o preço da energia por kWh: ");
                EnergyProvider novo = new EnergyProvider(sc.nextInt()); //Falta método de guardar
            }
            case 2 -> {
                System.out.println("Insira os dados da seguinte forma: Nome do Cliente, NIF");
                StringTokenizer token = new StringTokenizer(sc.nextLine());
                String nome_cliente = token.nextToken();
                String NIF = token.nextToken();
                SmartHouse novo = new SmartHouse(nome_cliente, NIF);
                SmartHouse.CreateHouse(); //Falta método de guardar
            }
            case 3 -> {
                Menus.SmartDevices();
                Commands.SmartDevices(sc.nextInt());
            }
            case 4 -> SmartHouse.AddDivision(); //Falta método
            case 5 -> SmartHouse.AddDevice(); //Falta método
            case 6 -> SmartDevice.setMode(true);
            case 7 -> SmartDevice.setMode(false);
            case 8 -> {
                SimuladorControl.SkipTime(); //Falta método
            }
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

    public static void Statistics(Integer command) { //Faltam todos os métodosScanner sc1 = new Scanner(System.in);
        switch (command) {
            case 1 -> SimuladorControl.HouseExpensive();
            case 2 -> SimuladorControl.BiggestFacture();
            case 3 -> SimuladorControl.CompanyFactures();
            case 4 -> SimuladorControl.BiggestConsumers();
            }
    }

    public static void SmartDevices(Integer command) {
        Scanner sc = new Scanner(System.in);
        switch (command) {
            case 1 -> {
                System.out.println("Insira os dados da seguinte forma: Estado, ID, Tonalidade, Dimensões");
                StringTokenizer token = new StringTokenizer(sc.nextLine(), ",");
                boolean mode = Boolean.parseBoolean(token.nextToken());
                String id = token.nextToken();
                int tone = Integer.parseInt(token.nextToken());
                int dimensions = Integer.parseInt(token.nextToken());
                double daily_consumption = Double.parseDouble(token.nextToken());
                SmartBulb novo = new SmartBulb(mode, id, tone, dimensions, daily_consumption);
                SmartHouse.AddDevice(); //FALTA TROCAR PARA O CORRETO
            }
            case 2 -> {
                System.out.println("Insira os dados da seguinte forma: Estado, ID, Volume, Rádio, Marca Coluna");
                StringTokenizer token = new StringTokenizer(sc.nextLine(), ",");
                boolean mode = Boolean.parseBoolean(token.nextToken());
                String id = token.nextToken();
                int v = Integer.parseInt(token.nextToken());
                String r = token.nextToken();
                String b = token.nextToken();
                SmartSpeaker novo = new SmartSpeaker(mode, id, v, r, b);
                SmartHouse.AddDevice(); //FALTA TROCAR PARA O CORRETO
            }
            case 3 -> {
                System.out.println("Insira os dados da seguinte forma: Estado, ID, Resolução, ");
                StringTokenizer token = new StringTokenizer(sc.nextLine());
                boolean mode = Boolean.parseBoolean(token.nextToken());
                String id = token.nextToken();
                int res = Integer.parseInt(token.nextToken());
                int size = Integer.parseInt(token.nextToken());
                double dc = Double.parseDouble(token.nextToken());
                SmartCamera novo = new SmartCamera(mode, id, res, size, dc);
                SmartHouse.AddDevice(); //FALTA TROCAR PARA O CORRETO
            }
            default -> {
                System.out.println("Opção inválida, tente novamente!");
                Commands.SmartDevices(sc.nextInt());
            }
        }
    }

 }
