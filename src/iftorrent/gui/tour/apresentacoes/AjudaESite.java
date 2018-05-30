package iftorrent.gui.tour.apresentacoes;

import iftorrent.gui.ferramentas.Comunicador;
import static iftorrent.gui.tour.ConstantesFormas.botaoPadrao;
import iftorrent.gui.tour.Forma;
import iftorrent.gui.tour.Quadrado;
import static iftorrent.gui.tour.apresentacoes.ApresentarSoftware.adicionar_elementos;
import static iftorrent.gui.tour.apresentacoes.ApresentarSoftware.altura_janela;
import static iftorrent.gui.tour.apresentacoes.ApresentarSoftware.largura_janela;
import static iftorrent.gui.tour.apresentacoes.ApresentarSoftware.limpar_tour;
import static iftorrent.gui.tour.apresentacoes.ApresentarSoftware.mudar_posicao;
import static iftorrent.gui.tour.apresentacoes.ApresentarSoftware.palco;
import iftorrent.texto.Texto;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class AjudaESite {
        private static HBox painel;
        private static Rectangle fundo_gradiente;
        private static Texto titulo;
        private static Texto texto;
        private static Quadrado mostra;
        private static Forma btn_end;
        
        public static void apresentar(){
            painel = Comunicador.getBarra_menu().getPainel();
            
            finalizar_tour();
        }
        
        private static void finalizar_tour(){
        Stop[] gradientes = new Stop[] { new Stop(0, Color.rgb(0, 0, 0, 0)), new Stop(0.60, Color.gray(0.95)),new Stop(1, Color.gray(1))};
        LinearGradient cor_gradiente = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, gradientes);
        mostra = new Quadrado(45, 25, true, 3, Color.RED);
        fundo_gradiente = new Rectangle(largura_janela+4, altura_janela+4, cor_gradiente);
        titulo = new Texto("E aqui acaba o nosso tour!", Font.font("Arial", 34));
        texto = new Texto("Se precisar de ajuda, clique na aba Ajuda ali em cima. \n"
                + "Ali você poderá relatar um erro ou sujestão, ou encontrar a nossa página \n"
                + "de ajuda.", Font.font("Arial", 16));
        btn_end = botaoPadrao("Finalizar");
                
        btn_end.setOnClique(() -> {
            limpar_tour(true);
        });
        
        setar_posicao_inicial();
        gruda_posicoes();
        
        titulo.aparecer_texto_fixo(0.01);
        texto.aparecer_texto_fixo(0.02);
        mostra.aparecer(1.2, 1.2, 0.2);
        
        adicionar_elementos(fundo_gradiente, mostra, titulo, texto, btn_end);
    }
        
    private static void setar_posicao_inicial(){
        mudar_posicao(fundo_gradiente, 0, 0);
        
        mudar_posicao(titulo, fundo_gradiente.getX() + fundo_gradiente.getWidth()/2 - titulo.getLarguraTexto()/2, 
                fundo_gradiente.getY() + fundo_gradiente.getHeight()/5*2);
        
        mudar_posicao(texto, fundo_gradiente.getX() + fundo_gradiente.getWidth()/2 - texto.getLarguraTexto()/2,
                titulo.getTranslateY() + texto.getAlturaTexto());
        
        mudar_posicao(btn_end, fundo_gradiente.getX() + fundo_gradiente.getWidth()/2 - btn_end.getLargura()/2, 
                texto.getTranslateY() + btn_end.getAltura() + 30);
        
        mudar_posicao(mostra, painel.getLayoutX()+230, painel.getLayoutY());
    }
    
    private static void gruda_posicoes(){
        fundo_gradiente.widthProperty().bind(palco.widthProperty());
        fundo_gradiente.heightProperty().bind(palco.heightProperty());
        titulo.translateXProperty().bind(palco.widthProperty().divide(2).add(-titulo.getLarguraTexto()/2));
        titulo.translateYProperty().bind(palco.heightProperty().divide(5).multiply(2).add(-titulo.getAlturaTexto()/2));
        texto.translateXProperty().bind(palco.widthProperty().divide(2).add(-texto.getLarguraTexto()/2));
        texto.translateYProperty().bind(titulo.translateYProperty().add(texto.getAlturaTexto()+5));
        btn_end.translateXProperty().bind(palco.widthProperty().divide(2).add(-btn_end.getLargura()/2));
        btn_end.translateYProperty().bind(texto.translateYProperty().add(btn_end.getAltura() + 30));
    }
        
        
}
