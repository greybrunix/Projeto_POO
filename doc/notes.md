# Projeto POO

## Classes

### SmartDevice

#### Geral-SD

Ligar e Desligar -> todos os devices terão uma variavel de tipo Mode - uma enum com ON e OFF, este ligar e desligar será acedido por um método não dissimilar, mas separado em dois metodos, um para ligar e outro para desligar, este, quando ligado só pode ser desligado no dia seguinte.

#### Especificações-SD

##### SMARTBULB

1. Tonalidade -> terá uma variavel de tipo Tone - uma enum de Neutral, Warm e Cold.
2. Dimensao -> em centimetros
3. Consumo diário -> formula em base de um valor fixo + um fator do tipo de luz

##### SMARTSPEAKER

1. Volume -> Um inteiro entre 0 e 20 (20 será uma constante para determinar o máximo volume)
2. Rádio -> Uma string
3. Marca -> Também uma String
4. Consumo diário -> Marca + fator do volume

##### SMARTCAMERA

1. Resolução -> poderá ser um par
2. Tamanho do ficheiro -> provavelmente em MB
3. consumo diário -> tamanho * resolution

### Casas

#### Geral-C

Casa <- Divisão de Salas <- SmartDevices + Nome do Dono e NIF

#### Especificações-C

1. ligar e desligar todos os dispositivos **numa dada divisão**
2. ligar e desligar um **dispositivo específico**

### Comercializadores de Energia

#### Geral-CE

1. Cada fornecedor tem uma propria definição de preço a que vende energia.
2. **Não pode existir casas sem fornecedor de energia associado em algum momento**

#### Especificações-CE

1. No arranque do sistema já se sabe o valor base do custo diário do kwh de energia
2. No arranque do sistema já se sabe o fator multiplicativo dos impostos.

##### EXEMPLOS

PrecoDiaPorDispositivo = (ValorBase\*ConsumoDispositivo\*(1+Imposto))\*0.9
PrecoDiaPorDispositivo = numeroDispositivos >10?(ValorBase\*ConsumoDispositivo\*(1+Imposto)) \*0.9:(ValorBase\*ConsumoDispositivo\*(1+Imposto))*0.75

## Simulador
