package view;

import java.util.Scanner;

import model.*;

/**
 * UI
 */

public interface View {
    static void welcomeStart()
    {
        System.out.print("""
                ***** Bem vindo/a ao Simulador de Casas Inteligentes *****
                  *    Estara disponivel, a seguir desta mensagem,      *
                  * uma serie de menus onde a simulacao em si decorra   *
                ----------------------------------------------------------

                """);
    }

    static void welcomeCreate()
    {
        System.out.print("""
                *****    Seja bem vindo, novo utilizador    *****
                  * Criando nova simulacao com dados inseridos *
                -------------------------------------------------

                """);
    }

    static void welcomeLoad()
    {
        System.out.print("""
               *****     Seja bem vindo de volta     *****
                 * Carregando onde ficou da ultima vez  *
               -------------------------------------------

               """);
    }
    static void showError(Exception e) {
        System.out.println(e.getMessage() + "Continuando onde estava antes...\n");
    }
    static void exit()
    {
        System.out.println("Obrigado por fazer uso do nosso servico, ate a proxima!");
    }
    static void showDeviceAddError()
    {
        System.err.println("Erro, por favor verifique se:"
            + " 1) A casa existe;"
            + "\n 2) A casa ainda nao tem contrato assinado com Comercializador de Energia;"
            + "\n 3) A divisao existe;"
            + "\n 4) O dispositivo existe e ainda nao foi associado a uma casa;"
            + "\n Por favor, corrija os erros mencionados e tente de novo.\n");
    }
    static String[] showInputStartMenu(int test_int)
    {
        Scanner sc = new Scanner(System.in);
        switch (test_int){
            case 1 -> {
                System.out.println("Insira o nome do comercializador de energia: ");
                System.out.print(">> ");
            }
            case 2 -> {
                System.out.println("Insira o nome do dono e o seu NIF separados por virgulas: ");
                System.out.print(">> ");
            }
            case 4 -> {
                System.out.println("Insira o nome da divisao e o nome do dono da casa separado por virgulas: ");
                System.out.print(">> ");
            }
            case 5 -> {
                System.out.println("Insira o ID do disp., o nome do dono e o nome do quarto: ");
                System.out.print(">> ");
            }
            case 6 -> {
                System.out.println("Insira o nome do dono e o nome da empresa");
                System.out.print(">> ");
            }
            default -> {
                String[] array = "Hi Word!".split(" ");
                sc.close();
                return array;
            }
        }
        String[] argv = sc.nextLine().split(",");
        sc.close();
        return argv;
    }
    static int timeSkipPrompt() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o numero de dias que pretende avancar: \n>> ");
        int res = sc.nextInt();
        sc.close();
        return res;
    }

    static String[] showInputStartMenuDev(int test_int){
        Scanner sc = new Scanner(System.in);
        switch (test_int){
            case 1 -> {
                System.out.println("Insere o modo, o id, o tom e as dimensoes: ");
                System.out.print(">> ");
            }
            case 2 -> {
                System.out.println("Insere o modo, o id, o volume, a radio, a marca e o valor base da marca: ");
                System.out.print(">> ");
            }
            case 3 -> {
                System.out.println("Insere o modo, o id, a resolucao e o tamanho do video: ");
                System.out.print(">> ");
            }
            default -> {}
        }
        String[] array = sc.nextLine().split(",");
        sc.close();
        return array;
    }
    static String[] showDevAppPrompts(int test_int){
        Scanner sc = new Scanner(System.in);
        switch (test_int){
            case 1,2,3,4,5,6,7 ->{
                System.out.print("Insere o id do dispositivo: \n>> ");
            }
            default -> {}
        }
        String[] array = sc.nextLine().split(",");
        sc.close();
        return array;
    }
    static String[] showHouseAppPrompts(int test_int){
        Scanner sc = new Scanner(System.in);
        switch (test_int){
            case 1,2,3,4,5 ->{
                System.out.print("Insere o nome do dono da casa: \n>> ");
            }
            default -> {}
        }
        String[]  array = sc.nextLine().split(",");
        sc.close();
        return array;
    }
    static String[] showProvAppPrompts(int test_int){
        Scanner sc = new Scanner(System.in);
        switch(test_int){
            case 1->{
                System.out.print("Insere onome da empresa e o novo valor base de KWh:\n>> ");
            }
            case 2->{
                System.out.print("Insere o nome da empresa e o novo fator de impostos:\n>> ");
            }
            case 3->{
                System.out.print("Insere o nome da empresa:\n>> ");
            }
        }
        String[] array = sc.nextLine().split(",");
        sc.close();
        return array;
    }

    static void showBills(Model mod) {
        System.out.println("Hi");
    }

    static void showErrorTimeSkip() {
        System.out.print("Nao e possivel proceder se:\n"
            + "1) Nao existem Comercializadores de Energia.\n"
            + "2) Existem Dispositivos sem casa associada.\n"
            + "3) Existem Casas sem contrato assinado com Comerc. de Energia.\n"
            + "Por favor, corrija os erros mencionados e tente de novo.\n");
    }

    static void showDate() {
    }
}