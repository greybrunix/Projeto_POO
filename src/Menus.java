package src;

/*
 * Menus for UI
 */

public class Menus {

    public static void initialMenu () {
        System.out.print("""
                **************MENU***************
                *  1- Criar Fornecedor          *
                *  2- Criar Casa                *
                *  3- Criar Dispositivo         *
                *  4- Adicionar Divisao         *
                *  5- Adicionar Disp. a Casa    *
                *  6- Ligar Dispositivo         *
                *  7- Desligar Dispositivo      *
                *  8- Avançar no Tempo          *
                *  9- Guardar                   *
                * 10- Fechar                    *
                *********************************
                """);
    }

    public static void menu () {
        System.out.print("""
                **************MENU***************
                *  1- Criar Fornecedor          *
                *  2- Criar Casa                *
                *  3- Criar Dispositivo         *
                *  4- Adicionar Divisao         *
                *  5- Adicionar Disp. a Casa    *
                *  6- Ligar Dispositivo         *
                *  7- Desligar Dispositivo      *
                *  8- Avançar no Tempo          *
                *  9- Abrir Menu Estatistica    *
                * 10- Guardar                   *
                * 11- Fechar                    *
                *********************************
                """);
    }

    public static void estatistica () {
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

    public static void devMenu () {
        System.out.print("""
                *******SmartDevice********
                *  1- SmartBulb          *
                *  2- SmartSpeaker       *
                *  3- SmartCamera        *
                **************************
                """);
    }
}
