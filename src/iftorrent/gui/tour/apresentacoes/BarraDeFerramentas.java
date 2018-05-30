package iftorrent.gui.tour.apresentacoes;

import static iftorrent.gui.barraDeFerramentas.BarraDeFerramentasConstantes.BOTAO_TAMANHO1;
import static iftorrent.gui.barraDeFerramentas.BarraDeFerramentasConstantes.BOTAO_TAMANHO2;
import static iftorrent.gui.tour.apresentacoes.ApresentarSoftware.*;
import iftorrent.gui.ferramentas.Comunicador;
import iftorrent.gui.tour.Animacoes;
import iftorrent.gui.tour.BalaoDialogo;
import static iftorrent.gui.tour.ConstantesBalaoDialogo.*;
import static iftorrent.gui.tour.ConstantesFormas.*;
import iftorrent.gui.tour.Forma;
import iftorrent.gui.tour.Quadrado;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class BarraDeFerramentas {
    private static Button botoes[];
    private static BorderPane barra_pesquisa;
    private static Bounds propriedades_barra;
    private static Quadrado mostra;
    private static Forma btn_entendi;
    private static BalaoDialogo apresenta_botoes;
    private static BalaoDialogo apresenta_botao[];
    private static BalaoDialogo apresenta_pesquisa;

    private static String fala_botoes = "Essa é a barra de ferramentas. Nela contém botões para trabalhar \n"
            + "com arquivos torrent.\n";

    private static String fala_botao[] = {
        "Esse é o botão REINICIAR, ele serve para que quando algum download \n"
        + "estiver pausado ou parado você possa retomá-lo. \n",
        "Esse é o botão PAUSAR, ele serve para pausar um download em andamento. \n",
        "Esse é o botão PARAR, ele serve para parar um download em andamento. \n",
        "Esse é o botão SUBIR ARQUIVO, ele serve para mover o torrent para cima na tabela. \n",
        "Esse é o botão DESCER ARQUIVO, ele serve para mover o torrent para baixo na tabela. \n",
        "Esse é o botão REMOVER ARQUIVO, ele serve para remover um torrent da tabela, \n"
        + "parando qualquer ação que estava sendo feita. \n",
        "Esse é o botão ADICIONAR ARQUIVO, ele serve para adicionar um arquivo torrent para ser baixado. \n",
        "Esse é o botão MAGNETIC LINK, ele serve para baixar um arquivo torrent por meio de um link. \n",
        "Esse é o botão GERAR TORRENT, ele serve para gerar um torrent de algum arquivo. \n"
    };
    
    private static String fala_pesquisa = "Essa é a barra de pesquisa. Nela você pode pesquisar por um arquivo \n torrent pelo seu nome.\n";

    private static int numero_botao;

    public static void apresentar() {
        propriedades_barra = Comunicador.getBarra_de_ferramentas().getPainel().localToScene(
                Comunicador.getBarra_de_ferramentas().getPainel().getBoundsInLocal());
        
        botoes = Comunicador.getBarra_de_ferramentas().getBotoes();
        
        introduzir_barra();
    }

    private static void introduzir_barra() {
        mostra = new Quadrado(botoes.length*BOTAO_TAMANHO1.X + 7/2 * botoes.length-1, propriedades_barra.getHeight(), true, 3, Color.RED);
        btn_entendi = botaoEntendido("Entendi", 10);
        apresenta_botoes = new BalaoDialogo(NORTE, true, fala_botoes, null, null);

        btn_entendi.setOnClique(() -> {
            remover_elementos(apresenta_botoes, btn_entendi);
            introduzir_botoes();
        });
        
        seta_posicao_inicial_barra();

        adicionar_elementos(mostra, apresenta_botoes, btn_entendi);
        adicionar_elementos(btn_pular_tour);//sempre por cima
        
        mostra.aparecer(1.2, 1.4, 0.3);
        apresenta_botoes.aparecer_balao_inteiro(1.4, 1.4, 0.3);
        Animacoes.animacao_escala(btn_entendi, 1, 1, 0.3);
    }

    private static void introduzir_botoes() {
        numero_botao = 0;
        apresenta_botao = new BalaoDialogo[fala_botao.length];

        for (int i = 0; i < apresenta_botao.length; i++) {
            apresenta_botao[i] = new BalaoDialogo(NORTE, true, fala_botao[i], Color.rgb(255, 179, 135), Color.CRIMSON);
            mudar_posicao(apresenta_botao[i], mostra.getTranslateX() + mostra.getLargura()/botoes.length*i - apresenta_botao[i].getLargura()/2 + BOTAO_TAMANHO1.X/1.5, 
                    propriedades_barra.getMaxY()+10);
        }
        
        mudar_posicao(btn_entendi, apresenta_botao[numero_botao].getTranslateX() + apresenta_botao[numero_botao].getLargura()/2-5,
                apresenta_botao[numero_botao].getTranslateY()+ apresenta_botao[numero_botao].getAltura()+10);
        
        btn_entendi.setOnClique(() -> {
            numero_botao++;
            if (numero_botao >= apresenta_botao.length) {
                limpar_tour(false);
                introduzir_pesquisa();
            } else {
                remover_elementos(apresenta_botao[numero_botao-1], btn_entendi);
                mudar_posicao(btn_entendi, apresenta_botao[numero_botao].getTranslateX()+ apresenta_botao[numero_botao].getLargura()/2-5,
                        apresenta_botao[numero_botao].getTranslateY()+ apresenta_botao[numero_botao].getAltura()+10);

                adicionar_elementos(apresenta_botao[numero_botao], btn_entendi);
                apresenta_botao[numero_botao].aparecer_balao_inteiro(1.1, 1.1, 0.2);
                Animacoes.animacao_escala(btn_entendi, 1, 1, 0.2);
            }
        });
        
        adicionar_elementos(apresenta_botao[numero_botao], btn_entendi);
        apresenta_botao[numero_botao].aparecer_balao_inteiro(1.1, 1.1, 0.2);
        Animacoes.animacao_escala(btn_entendi, 1, 1, 0.2);
    }
    
    private static void introduzir_pesquisa(){
        barra_pesquisa = Comunicador.getBarra_de_ferramentas().getPesquisa();
        mostra = new Quadrado(BOTAO_TAMANHO2.X + /*TAMANHODABARRA ~*/ 293, propriedades_barra.getHeight(), true, 3, Color.RED);
        btn_entendi = botaoEntendido("Entendi", 10);
        apresenta_pesquisa = new BalaoDialogo(NORTE, true, fala_pesquisa, Color.rgb(255, 179, 135), Color.CRIMSON);
        
        seta_posicao_inicial_pesquisa();
        gruda_posicoes_pesquisa();
        
        btn_entendi.setOnClique(() -> {
            limpar_tour(false);
            AbasPropriedades.apresentar();
        });
        
        adicionar_elementos(mostra, apresenta_pesquisa, btn_entendi);
        adicionar_elementos(btn_pular_tour);//sempre por cima
        
        mostra.aparecer(1.2, 1.4, 0.4);
        apresenta_pesquisa.aparecer_balao_inteiro(1.1, 1.1, 0.2);
        Animacoes.animacao_escala(btn_entendi, 1, 1, 0.2);
    }
    
    private static void seta_posicao_inicial_barra(){
        mudar_posicao(mostra, propriedades_barra.getMinX() - 1.5, propriedades_barra.getMinY());
        mudar_posicao(apresenta_botoes, mostra.getTranslateX()+ mostra.getWidth() / 2, mostra.getTranslateY()+ mostra.getHeight() + 40);
        mudar_posicao(btn_entendi, apresenta_botoes.getTranslateX()+ apresenta_botoes.getLargura() / 2, apresenta_botoes.getTranslateY()+ apresenta_botoes.getAltura());
    }
    
     private static void seta_posicao_inicial_pesquisa(){
         mudar_posicao(mostra, barra_pesquisa.getLayoutX() + 180,
                propriedades_barra.getMinY());
        //barra_pesquisa.getBoundsInParent().getMinX());
        mudar_posicao(apresenta_pesquisa, mostra.getTranslateX() + apresenta_pesquisa.getLargura()/2,
                propriedades_barra.getMaxY()+10);
        mudar_posicao(btn_entendi, apresenta_pesquisa.getTranslateX()+apresenta_pesquisa.getLargura()/2, 
                apresenta_pesquisa.getTranslateY()+apresenta_pesquisa.getAltura());
     }
     
     private static void gruda_posicoes_pesquisa(){
        mostra.translateXProperty().bind(barra_pesquisa.layoutXProperty().add(180));
        apresenta_pesquisa.translateXProperty().bind(mostra.translateXProperty().add(-apresenta_pesquisa.getLargura()/2 + BOTAO_TAMANHO2.X*2));
        btn_entendi.translateXProperty().bind(apresenta_pesquisa.translateXProperty().add(apresenta_pesquisa.getLargura()/2));
        
    }
}
