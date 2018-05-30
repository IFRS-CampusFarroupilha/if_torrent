package iftorrent.gui.tour.apresentacoes;

import static iftorrent.gui.tour.ConstantesFormas.*;
import iftorrent.gui.tour.Forma;
import static iftorrent.gui.tour.apresentacoes.ApresentarSoftware.*;
import iftorrent.texto.Texto;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class BoasVindas {
        private static Rectangle fundo_gradiente;
        private static Texto titulo;
        private static Texto texto;
        private static Forma btn_continuar;
        private static Forma btn_pular;
    
    public static void dar_boasVindas(){
        Stop[] gradientes = new Stop[] { new Stop(0, Color.rgb(0, 0, 0, 0)), new Stop(0.75, Color.gray(0.95)),new Stop(1, Color.gray(1))};
        LinearGradient cor_gradiente = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, gradientes);
        fundo_gradiente = new Rectangle(largura_janela+4, altura_janela+4, cor_gradiente);
        titulo = new Texto("Bem Vindo!", Font.font("Arial", 34));
        texto = new Texto("Que tal fazer um passeio pelo IFTorrent e \n"
                + "saber mais sobre suas funcionalidades?", Font.font("Arial", 20));
        btn_continuar = botaoPadrao("Continuar");
        btn_pular = botaoPadrao("Pular Tour");
        
        seta_posicao_inicial();
        gruda_posicoes();
                
        btn_continuar.setOnClique(() -> {
            limpar_tour(false);
            BarraLateral.apresentar();
        });
        btn_pular.setOnClique(() -> {
            limpar_tour(true);
        });
        
        titulo.aparecer_texto_fixo(0.01);
        texto.aparecer_texto_fixo(0.02);
        
        adicionar_elementos(fundo_gradiente, titulo, texto, btn_continuar, btn_pular);
    }
    
    
    private static void seta_posicao_inicial(){
        mudar_posicao(fundo_gradiente, 0, 0);
        
        mudar_posicao(texto, fundo_gradiente.getX() + fundo_gradiente.getWidth() - texto.largura_texto_total - 50,
                fundo_gradiente.getY() + fundo_gradiente.getHeight()/4*2);
        
        mudar_posicao(titulo, texto.getTranslateX() + (texto.largura_texto_total - titulo.largura_texto_total)/2, 
                texto.getTranslateY()- texto.altura_texto_total - titulo.altura_texto_total);
        
        mudar_posicao(btn_continuar, texto.getTranslateX() + texto.largura_texto_total/2 - btn_continuar.getLargura() - 30, 
                texto.getTranslateY() + btn_continuar.getAltura() + 30);
        
        mudar_posicao(btn_pular, texto.getTranslateX() + texto.largura_texto_total/2 + 30, btn_continuar.getTranslateY());
    }
    
    
    private static void gruda_posicoes(){
        fundo_gradiente.widthProperty().bind(palco.widthProperty());
        fundo_gradiente.heightProperty().bind(palco.heightProperty());
        texto.translateXProperty().bind(palco.widthProperty().add(-texto.largura_texto_total -50));
        texto.translateYProperty().bind(palco.heightProperty().divide(4).multiply(2));
        titulo.translateXProperty().bind(texto.translateXProperty().add((texto.largura_texto_total - titulo.largura_texto_total)/2));
        titulo.translateYProperty().bind(texto.translateYProperty().add(-texto.altura_texto_total - titulo.altura_texto_total));
        btn_continuar.translateXProperty().bind(texto.translateXProperty().add(texto.largura_texto_total/2 - btn_continuar.getLargura() - 30));
        btn_continuar.translateYProperty().bind(texto.translateYProperty().add(btn_continuar.getAltura() + 30));
        btn_pular.translateXProperty().bind(texto.translateXProperty().add(texto.largura_texto_total/2 + 30));
        btn_pular.translateYProperty().bind(btn_continuar.translateYProperty());
    }
}