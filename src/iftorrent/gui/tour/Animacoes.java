package iftorrent.gui.tour;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animacoes {
    /**
     * Anima o tamanho de um objeto (a escala).
     * 
     * @param nodo O objeto.
     * @param largura Largura que o objeto terá no ápice da animação (irá retornar
     * ao tamanho original após o ápice, sempre).
     * @param altura Altura que o objeto terá no ápice da animação (irá retornar
     * ao tamanho original após o ápice, sempre).
     * @param duracao Duração da animação em segundos.
     */
    public static void animacao_escala(Node nodo, double largura, double altura, double duracao) {
        ScaleTransition animacao_tamanho = new ScaleTransition(Duration.seconds(duracao), nodo);
        ScaleTransition animacao_tamanho2 = new ScaleTransition(Duration.seconds(0.2), nodo);
        
        animacao_tamanho.setToX(largura);
        animacao_tamanho.setToY(altura);
        animacao_tamanho.setOnFinished((ActionEvent e) -> {
            animacao_tamanho2.setToX(1);
            animacao_tamanho2.setToY(1);
            animacao_tamanho2.play();
        });
        animacao_tamanho.play();
    }
}
