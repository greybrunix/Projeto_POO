package view;

/**
 * UI
 */

public interface View {
    static void welcomeStart()
    {
        System.out.print("""
                ***** Bem vindo/a ao Simulador de Casas Inteligentes *****
                  *    Estara disponivel, a seguir desta mensagem,     *
                  * uma serie de menus onde a simulacao em si decorra  *
                ----------------------------------------------------------

                """);
    }

    static void welcomeCreate()
    {
        System.out.print("""
                *****    Seja bem vindo, novo utilizador    *****
                  * Criando nova simulaçao com dados inseridos *
                -------------------------------------------------

                """);
    }

    static void welcomeLoad()
    {
        System.out.print("""
               *****     Seja bem vindo de volta     *****
                 * Carregando onde ficou da ultima vez *
               -------------------------------------------

               """);
    }
    static void showError(Exception e) {
        System.out.println(e.getMessage() + "Continuando onde estava antes...");
    }
    static void exit()
    {
        System.out.println("Obrigado por fazer uso do nosso servico, ate a proxima!");
    }
    static void showDeviceAddError()
    {
        System.err.println("Erro, por favor verifique se "
            + "1) a casa existe, 2) A casa ainda nao tem contrato, "
            + "3) a divisao existe ou 4) O SmartDevice existe"
            + ". \n Por favor corrija de modo a que estas condicoes se verifiquem");
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
            case 7 -> {
                System.out.println("Insira o numero de dias que pretende avancar: ");
                System.out.print(">> ");
            }
            default -> {}
        }
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
    static void showAppPrompts(int test_int){

    }
    
}