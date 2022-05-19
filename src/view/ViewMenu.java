package view;

/*
 * Menus for UI
 */

public interface ViewMenu {

    static void startMenu() {
        System.out.print("""
                ************NOVA SESSAO***********
                *  1- Carregar Estado            *
                *  2- Novo Estado                *
                *  0- Fechar Programa            *
                **********************************"""
                + "\n>> ");
    }

    static void startMenuNew(){
        System.out.print("""
                ***********CRIAR SESSAO***********
                *  1- Criar Fornecedor           *
                *  2- Criar Casa                 *
                *  3- Criar Dispositivo          *
                *  4- Adicionar Divisao          *
                *  5- Adicionar Disp. a casa     *
                *  6- Estabelecer Contrato       *
                *  7- Proceder no tempo          *
                *  0- Voltar ao menu anterior    *
                **********************************"""
                + "\n>> ");
    }
    static void startMenuNewDev() {
        System.out.print("""
                ********CRIAR DISPOSITIVO*********
                *  1- SmartBulb                  *
                *  2- SmartSpeaker               *
                *  3- SmartCamera                *
                *  0- Voltar ao menu anterior    *
                **********************************"""
                + "\n>> ");
    }

    static void mainMenu() {
        System.out.print("""
                **********MENU PRINCIPAL**********
                *  1- Dispositivos               *
                *  2- Casas                      *
                *  3- Fornecedores de Energia    *
                *  4- Estatisticas               *
                *  5- Mostrar faturas            *
                *  6- Proceder no tempo          *
                *  7- Guardar Estado             *
                *  0- Fechar o Programa          *
                **********************************"""
                + "\n>> ");
    }
    static void mainMenuDev(){
        System.out.print("""
                *********DISPOSITIVOS*************
                *  1- Ligar um disp.             *
                *  2- Desligar um disp.          *
                *  3- Aumentar Volume (Speaker)  *
                *  4- Diminuir Volume            *
                *  5- Alterar Tom (Lampada)      *
                *  6- Obter consumo diario       *
                *  0- Voltar ao menu anterior    *
                **********************************"""
                + "\n>> ");
    }
    static void mainMenuHouse(){
        System.out.print("""
                *************CASAS****************
                *  1- Ligar tudo                 *
                *  2- Ligar tudo em div.         *
                *  3- Desligar tudo              *
                *  4- Desligar tudo em div.      *
                *  5- Alterar Contrato           *
                *  0- Voltar ao menu anterior    *
                **********************************"""
                + "\n>> ");
    }
    static void mainMenuComer(){
        System.out.print("""
                **********************************
                *  1- Alterar valor base         *
                *  2- Alterar fator de impostos  *
                *  0- Voltar ao menu anterior    *
                **********************************"""
                + "\n>> ");
    }
    static void mainMenuStats() {
        System.out.print("""
                **********************Estatistica*********************
                *  1- Casa que mais gastou no período                *
                *  2- Comercializador com maior volume de facturação *
                *  3- Facturas emitidas por um comercializador       *
                *  4- Ordenação dos maiores consumidores de energia  *
                *     durante um período a determinar                *
                *  0- Voltar ao menu anterior                        *
                ******************************************************"""
                + "\n>> ");
    }
}
