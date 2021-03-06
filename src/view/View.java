package view;



import model.Model;
import model.SmartHouse;

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
        System.err.println("""
                Erro, por favor verifique se: 1) A casa existe;
                 2) A casa ainda nao tem contrato assinado com Comercializador de Energia;
                 3) A divisao existe;
                 4) O dispositivo existe e ainda nao foi associado a uma casa;
                 Por favor, corrija os erros mencionados e tente de novo.
                """);
    }
    static void showInputStartMenu(int test_int)
    {
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
            }
        }
    }
    static void timeSkipPrompt() {
        System.out.print("Insira o numero de dias que pretende avancar: \n>> ");
    }

    static void showInputStartMenuDev(int test_int){
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
    }
    static void showDevAppPrompts(int test_int){
        switch (test_int){
            case 1,2,3,4,5,6,7:
                System.out.print("Insere o id do dispositivo: \n>> ");
            default: break;
        }
    }
    static void showHouseAppPrompts(int test_int){
        switch (test_int){
            case 1,2,3,4:
                System.out.print("Insere o nome do dono da casa: \n>> ");
            case 5:
                System.out.print("Insere o nome do dono da casa e o novo fornecedor: \n>> ");
            default: break;
        }
    }
    static void showProvAppPrompts(int test_int){
        if (test_int == 3){
            System.out.print("Insere o nome da empresa: \n>> ");
        }
    }

    static void showAllBills(Model mod){
        for (String comp : mod.getEnerg().keySet()){
            System.out.println(comp + ": ");
            showBills(mod, comp);
        }
    }
    static void showBills(Model mod, String comp) {
        for (SmartHouse house : mod.getEnerg().get(comp).getHouses().values())
            System.out.println("\t"+ house.getOwner() + ":\t" + mod.getEnerg().get(comp).compute(house.getOwner())*31 +"$; "
            + mod.getEnerg().get(comp).getHouses().get(house.getOwner()).totalDailyCons() +  "KWh.");
    }

    static void showErrorTimeSkip() {
        System.out.print("""
                Nao e possivel proceder se:
                1) Nao existem Comercializadores de Energia.
                2) Existem Dispositivos sem casa associada.
                3) Existem Casas sem contrato assinado com Comerc. de Energia.
                Por favor, corrija os erros mencionados e tente de novo.
                """);
    }

    static void showDate(Model mod) {
        showAllBills(mod);
        System.out.println("Today is : " + mod.getDate());
    }

    static void showInputError() {
        System.out.print("Please make sure to insert the correct amount of parameters.\n>> ");
    }

    static void showIntError() {
        System.out.print("Please make sure to insert an integer. \n>> ");
    }

    static void showDevDC(String id, double dc) {
        System.out.print("The device " + id + " is using up to " + dc + "KWh per day.\n");
    }
}