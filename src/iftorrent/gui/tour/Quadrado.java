package iftorrent.gui.tour;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;
import javafx.util.Duration;


public class Quadrado extends Pane{
    public Shape forma;
    
    /**
     * Cria um quadrado de fundo transparente para circular um elemento.
     * 
     * @param largura Largura do quadrado.
     * @param altura Altura do quadrado.
     * @param tera_animacao_entrada True caso o quadrado será animado ao aparecer.
     * @param grossura_borda Grossura da borda do quadrado.
     * @param cor_borda Cor da borda do quadrado.
     */
    public Quadrado(double largura, double altura, boolean tera_animacao_entrada, double grossura_borda,
            Paint cor_borda) {
        Rectangle retangulo = new Rectangle(largura, altura);
        retangulo.setFill(Color.TRANSPARENT);
        retangulo.setStroke(cor_borda);
        retangulo.setStrokeLineJoin(StrokeLineJoin.ROUND);
        retangulo.setStrokeWidth(grossura_borda);
        
        if(tera_animacao_entrada){
            retangulo.setScaleX(0);
            retangulo.setScaleY(0);
        }
        forma = retangulo;
        
        getChildren().addAll(forma);
    }
    
    /**
     * Faz com que, caso o quadrado tenha animação, o mesmo aparecerá com uma animação de tamanho
     * (Escala).
     * 
     * @param largura Largura que o quadrado terá no ápice da animação (irá retornar
     * ao tamanho original após o ápice, sempre).
     * @param altura Altura que o quadrado terá no ápice da animação (irá retornar
     * ao tamanho original após o ápice, sempre).
     * @param duracao Duração da animação em segundos.
     */
    public void aparecer(double largura, double altura, double duracao){
        ScaleTransition animacao_tamanho = new ScaleTransition(Duration.seconds(duracao), forma);
        
        animacao_tamanho.setToX(largura);
        animacao_tamanho.setToY(altura);
        animacao_tamanho.setOnFinished((ActionEvent e) -> {
            ScaleTransition animacao_tamanho1 = new ScaleTransition(Duration.seconds(0.2), forma);
            
            animacao_tamanho1.setToX(1);
            animacao_tamanho1.setToY(1);
            animacao_tamanho1.play();
        });
        animacao_tamanho.play();
    }
    
    public double getAltura(){
        return forma.getBoundsInLocal().getHeight();
    }
    
    public double getLargura(){
        return forma.getBoundsInLocal().getWidth();
    }
}
