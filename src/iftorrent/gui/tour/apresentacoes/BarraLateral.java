package iftorrent.gui.tour.apresentacoes;

import iftorrent.gui.ferramentas.Comunicador;
import iftorrent.gui.tour.Animacoes;
import iftorrent.gui.tour.BalaoDialogo;
import iftorrent.gui.tour.ConstantesBalaoDialogo;
import static iftorrent.gui.tour.ConstantesFormas.*;
import iftorrent.gui.tour.Forma;
import iftorrent.gui.tour.Quadrado;
import iftorrent.gui.tour.Seta;
import static iftorrent.gui.tour.apresentacoes.ApresentarSoftware.*;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class BarraLateral {
        private static Bounds propriedades_barra;
        private static Quadrado mostra;
        private static BalaoDialogo dialogo;
        private static Forma btn_entendido;
        private static Seta seta;
        private static BalaoDialogo balao;
    
    private static String fala_apresentacao = 
            "Aqui ao lado, temos um menu onde é possível navegar atrávez de seus arquivos torrents  \n"
            + "que estarão organizados por seus estados atuais, ou seja,\n"
            + "você poderá filtrar seus arquivos de forma fácil e rápida.\n"
            + "Podendo escolher entre: \n\n\n"
            + "Todos os arquivos.\n\n"
            + "Arquivos que ainda estão sendo baixados.\n\n"
            + "Arquivos que já foram completamente baixados.\n\n"
            + "E por fim, os arquivos que você está enviando.\n";
    
    private static String fala_uso_barra = 
            "Ao escolher um dos itens, a tabela ao lado será alterada,  \n"
            + "mostrando apenas os arquivos do item (Estado) selecionado.  \n";
    
    public static void apresentar(){
        propriedades_barra = Comunicador.getBarra_lateral().getPainel().localToScene(
                Comunicador.getBarra_lateral().getPainel().getBoundsInLocal());
        introduzir_barra();
    }
    
    private static void introduzir_barra(){
        btn_entendido = botaoEntendido("Entendi", 10);
        mostra = new Quadrado(propriedades_barra.getWidth(), propriedades_barra.getHeight()/2, true, 6, Color.RED);
        dialogo = new BalaoDialogo(ConstantesBalaoDialogo.OESTE, true, fala_apresentacao, null, null);
        
        seta_posicao_inicial_introducao();
        
        btn_entendido.setOnClique(() -> {
            remover_elementos(dialogo, btn_entendido, btn_pular_tour);
            uso_da_barra();
        });
        
        adicionar_elementos(mostra, dialogo, btn_entendido);
        adicionar_elementos(btn_pular_tour);//sempre por cima
        
        mostra.aparecer(1.1, 1.1, 0.3);
        dialogo.aparecer_balao_inteiro(1.1, 1.1, 0.4);
        Animacoes.animacao_escala(btn_entendido, 1, 1, 0.3);
        
    }
    
    private static void uso_da_barra(){
        balao = new BalaoDialogo(ConstantesBalaoDialogo.NENHUM, true, fala_uso_barra, null, null);
        seta = new Seta(Point2D.ZERO, new Point2D(50, -50), 20, 20, Color.RED);
                
        btn_entendido.setOnClique(() -> {
            limpar_tour(false);
            BarraDeFerramentas.apresentar();
        });
        
        /*
        
        BINDAR A SETA PRA SEMPRE APONTAR PRO PAINEL DAS TABELAS DOS TORRENTS
        
        */
        seta_posicao_inicial_uso();
        
        adicionar_elementos(balao, seta, btn_entendido);
        adicionar_elementos(btn_pular_tour);//sempre por cima
        
        balao.aparecer_balao_inteiro(1.1, 1.1, 0.25);
        Animacoes.animacao_escala(btn_entendido, 1, 1, 0.3);
    }
    
    private static void seta_posicao_inicial_introducao(){
        mudar_posicao(mostra, propriedades_barra.getMinX(), propriedades_barra.getMinY());
        mudar_posicao(dialogo, mostra.getTranslateX() + mostra.getLargura() + 10, 
                mostra.getTranslateY() + mostra.getAltura()/2 - dialogo.getAltura()/2);
        mudar_posicao(btn_entendido, dialogo.getTranslateX()+ dialogo.getLargura() - btn_entendido.getLargura() - 30, 
                dialogo.getTranslateY()+ dialogo.getAltura() - btn_entendido.getAltura() - 30);
    }
    
    private static void seta_posicao_inicial_uso(){
        mudar_posicao(balao, mostra.getTranslateX() + mostra.getLargura()/2, mostra.getTranslateY()+ mostra.getHeight() - 10);
        mudar_posicao(seta, balao.getTranslateX() + balao.getLargura() - 15, balao.getTranslateY()- 30);
        mudar_posicao(btn_entendido, balao.getTranslateX() + balao.getLargura()/2,
                balao.getTranslateY() + balao.getAltura()/6*5 + btn_entendido.getAltura()/2);
    }
}
