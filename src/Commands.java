package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Commands {

	public static void InitialMenu(Integer command) {
		Scanner sc = new Scanner(System.in);
		switch (command) {
			case 1 -> {
				System.out.println("Insira o preço da energia por kWh: ");
				SmartEP novo = new SmartEP(sc.nextInt()); //Falta método de guardar
			}
			case 2 -> {
				System.out.println("Insira os dados da seguinte forma: Nome do Cliente [space] NIF:");
				StringTokenizer token = new StringTokenizer(sc.nextLine());
				String nome_cliente = token.nextToken();
				String NIF = token.nextToken();
				SmartHouse novo = new SmartHouse(nome_cliente, NIF);
				SimuladorControl.createHouse(novo); //Falta método de guardar
			}
			case 3 -> {
				Menus.devMenu();
				Commands.Devices(sc.nextInt());
			}
			case 4 -> SmartHouse.addRoom("String"); //Falta método
			case 5 -> {
				System.out.println("Insira os dados da seguinte forma: Id do device [space] nome da divisão:");
				StringTokenizer token = new StringTokenizer(sc.nextLine());
				SmartHouse.addDeviceToRoom(.devices.get(token.nextToken()), token.nextToken());
		} //vai receber 
			case 6 -> SmartDevice.setMode(true);
			case 7 -> SmartDevice.setMode(false);
			case 8 -> {
				System.out.println("Insira quantos dias desejas passar a frente: ");
				String time = sc.nextLine();

				if (time.chars().allMatch(Character::isDigit)){
					System.out.println("Insere uma quantidade de dias válida:");
				}
				if (time == "" ){
					SimuladorControl.skipTime("1");
				}
				SimuladorControl.skipTime(time);
			} //Falta método
			case 9 -> {
				SimuladorControl.save(); //Falta método
				System.out.println("Guardado com sucesso!");
			}
			case 10 -> {
				System.out.println("Até à próxima!");
				System.exit(0);
			}
			}
            sc.close();
	}

	public static void Menu(Integer command) {
		Scanner sc = new Scanner(System.in);
		switch (command) {
			case 1 -> {
				System.out.println("Insira o preço da energia por kWh: ");
				SmartEP novo = new SmartEP(sc.nextInt()); //Falta método de guardar
			}
			case 2 -> {
				System.out.println("Insira os dados da seguinte forma: Nome do Cliente, NIF");
				StringTokenizer token = new StringTokenizer(sc.nextLine());
				String nome_cliente = token.nextToken();
				String NIF = token.nextToken();
				SmartHouse novo = new SmartHouse(nome_cliente, NIF);
				SimuladorControl.createHouse(); //Falta método de guardar
			}
			case 3 -> {
				Menus.devMenu();
				Commands.Devices(sc.nextInt());
			}
			case 4 -> {
				StringTokenizer token = new StringTokenizer(sc.nextLine());
				SmartHouse.addRoom(token.nextToken());
			}
			case 5 -> {
				System.out.println("Insira os dados da seguinte forma: Id do device [space] nome da divisão:");
				StringTokenizer token = new StringTokenizer(sc.nextLine());
				SmartHouse.addDeviceToRoom(SimuladorModel.devices.get(token.nextToken()), token.nextToken()); 
			}
			case 6 -> SmartDevice.setMode(true);
			case 7 -> SmartDevice.setMode(false);
			case 8 -> {
				System.out.println("Insira quantos dias desejas passar a frente: ");
				String time = sc.nextLine();

				if (time.chars().allMatch(Character::isDigit)){
					System.out.println("Insere uma quantidade de dias válida:");
				}
				if (time == "" ){
					SimuladorControl.skipTime("1");
				}
				SimuladorControl.skipTime(time);
			}
			case 9 -> {
				Menus.estatistica();
				Commands.estatistica(sc.nextInt());
			}
			case 10 -> {
				SimuladorControl.save();
				System.out.println("Guardado com sucesso!");
			}
			case 11 -> {
				System.out.println("Até à próxima!");
				System.exit(0);
			}
			}
            sc.close();
	}

	public static void estatistica(Integer command) { //Faltam todos os métodosScanner sc1 = new Scanner(System.in);
		switch (command) {
			case 1 -> SimuladorControl.houseMostExp();
			case 2 -> SimuladorControl.largestBill();
			case 3 -> SimuladorControl.companyBills();
			case 4 -> SimuladorControl.podiumConsumption();

			}
	}

	public static void Devices(Integer command) {
		Scanner sc = new Scanner(System.in);
		switch (command) {
			case 1 -> {
				System.out.println("Insira os dados da seguinte forma: Estado ID Tonalidade Dimensões");
				StringTokenizer token = new StringTokenizer(sc.nextLine());
				boolean mode = Boolean.parseBoolean(token.nextToken());
				String id = token.nextToken();
				int tone = Integer.parseInt(token.nextToken());
				int dimensions = Integer.parseInt(token.nextToken());
				double daily_consumption = Double.parseDouble(token.nextToken());
				SmartBulb novo = new SmartBulb(mode, id, tone, dimensions, daily_consumption);
			}
			case 2 -> {
				System.out.println("Insira os dados da seguinte forma: Estado ID Volume Rádio Marca");
				StringTokenizer token = new StringTokenizer(sc.nextLine());
				boolean mode = Boolean.parseBoolean(token.nextToken());
				String id = token.nextToken();
				int v = Integer.parseInt(token.nextToken());
				String r = token.nextToken();
				String b = token.nextToken();
				SmartSpeaker novo = new SmartSpeaker(mode, id, v, r, b);
			}
			case 3 -> {
				System.out.println("Insira os dados da seguinte forma: Estado ID Resolução");
				StringTokenizer token = new StringTokenizer(sc.nextLine());
				boolean mode = Boolean.parseBoolean(token.nextToken());
				String id = token.nextToken();
				int res = Integer.parseInt(token.nextToken());
				int size = Integer.parseInt(token.nextToken());
				double dc = Double.parseDouble(token.nextToken());
				SmartCamera novo = new SmartCamera(mode, id, res, size, dc);
			}
			default -> {
				System.out.println("Opção inválida, tente novamente!");
				Commands.Devices(sc.nextInt());
			}
		}
        sc.close();
	}
}
	
