package view;

/*
 * Menus for UI
 */

public class ViewMenu {

    public static void startMenu() {
        System.out.print("""
                **************MENU****************
                *  1- Carregar Estado            *
                *  2- Novo Estado                *
                *  0- Fechar Programa            *
                **********************************
                """);
    }

    public static void startNewMenu(){
        System.out.print("""
                **************MENU****************
                *  1- Criar Fornecedor           *
                *  2- Criar Casa                 *
                *  3- Criar Dispositivo          *
                *  4- Adicionar Divisao          *
                *  5- Adicionar Disp. a casa     *
                *  6- Estabelecer Contrato       *
                *  7- Ligar Dispositivos         *
                *  8- Proceder no tempo          *
                *  0- Voltar a menu anterior     *
                **********************************
                """);
    }
    public static void mainMenu() {
        System.out.print("""
                **************MENU****************
                *  1- Dispositivos               *
                *  2- Casas                      *
                *  3- Fornecedores de Energia    *
                *  x- Proceder no tempo          *
                *  0- Fechar o Programa          *
                *********************************
                """);
    }

    public static void stats () {
        System.out.print("""
                **********************Estatística*********************
                *  1- Casa que mais gastou no período                *
                *  2- Comercializador com maior volume de facturação *
                *  3- Facturas emitidas por um comercializador       *
                *  4- Ordenação dos maiores consumidores de energia  *
                *     durante um período a determinar                *
                ******************************************************
                """);
    }

    public static void startDevMenu () {
        System.out.print("""
                *******SmartDevice********
                *  1- SmartBulb          *
                *  2- SmartSpeaker       *
                *  3- SmartCamera        *
                **************************
                """);
    }
}
