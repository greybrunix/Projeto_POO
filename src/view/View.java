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
}