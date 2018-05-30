package iftorrent.gui.tour;

import static iftorrent.gui.tour.ConstantesFormas.*;
import iftorrent.texto.Texto;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class Forma extends Pane {

    Shape forma;
    Texto texto;
    Pane container;

    Paint cor_fundo;
    Paint cor_borda;
    Paint cor_fundo_hover;
    Paint cor_borda_hover;
    Paint cor_fundo_clique;
    Paint cor_borda_clique;

    Runnable acao_clique;
    Runnable acao_hover;

    public Forma(Shape forma, Paint cor_borda, double grossura_borda, String texto, Font fonte) {
        forma.setStroke(cor_borda);
        forma.setStrokeWidth(grossura_borda);
        this.cor_borda = cor_borda;
        this.cor_fundo = forma.getFill();
        this.forma = forma;
        this.texto = new Texto(texto, fonte);
        container = new Pane(this.texto);
        alinhar_conteudo_centro();
        mover_conteudo(0, container.getBoundsInLocal().getHeight() / 1.5);
        getChildren().addAll(this.forma, container);
    }

    public Forma(String tipo, double espacamento, Paint cor_fundo, Paint cor_borda, double grossura_borda, String texto, Font fonte) {
        Shape forma = null;

        this.texto = new Texto(texto, fonte);
        container = new Pane(this.texto);
        this.cor_borda = cor_borda;
        this.cor_fundo = cor_fundo;

        switch (tipo) {
            case CIRCULO:
                double raio = 0;
                if (this.texto.altura_texto_total > this.texto.largura_texto_total) {
                    raio = this.texto.altura_texto_total / 2 + espacamento / 2;
                } else {
                    raio = this.texto.largura_texto_total / 2 + espacamento / 2;
                }
                forma = new Circle(raio, cor_fundo);
                break;
            case RETANGULO:
                forma = new Rectangle(this.texto.largura_texto_total + espacamento * 2,
                        this.texto.altura_texto_total + espacamento * 3, cor_fundo);
                break;
            default:
                return;
        }
        forma.setStroke(cor_borda);
        forma.setStrokeWidth(grossura_borda);
        this.forma = forma;
        alinhar_conteudo_centro();
        mover_conteudo(0, container.getBoundsInLocal().getHeight() / 1.5);
        getChildren().addAll(this.forma, container);
    }

    public void setOnClique(Runnable acao) {
        acao_clique = acao;
        this.setOnMousePressed((event) -> {
            if(cor_borda_clique != null){
                forma.setStroke(cor_borda_clique);
            }
            if(cor_fundo_clique != null){
                forma.setFill(cor_fundo_clique);
            }
        });
        this.setOnMouseReleased((event) -> {
            acao_clique.run();
            forma.setFill(cor_fundo);
            forma.setStroke(cor_borda);
        });
    }

    public void setOnHover(Runnable acao) {
        acao_hover = acao;
        this.setOnMouseEntered((event) -> {
            forma.setFill(cor_fundo_hover);
            forma.setStroke(cor_borda_hover);
            acao_hover.run();
        });

        this.setOnMouseExited((event) -> {
            forma.setFill(cor_fundo);
            forma.setStroke(cor_borda);
        });
    }

    public void setCoresOnClique(Paint cor_fundo, Paint cor_borda) {
        this.cor_fundo_clique = cor_fundo;
        this.cor_borda_clique = cor_borda;
    }
    
    public void setCoresOnHover(Paint cor_fundo, Paint cor_borda) {
        this.cor_fundo_hover = cor_fundo;
        this.cor_borda_hover = cor_borda;
    }

    /**
     * Método para tentar alinhar o conteúdo da forma ao centro da mesma (pode
     * não funcionar dependendo do elemento.
     */
    private void alinhar_conteudo_centro() {
        realocar_conteudo((forma.getBoundsInLocal().getWidth() - container.getBoundsInLocal().getWidth()) / 2,
                (forma.getBoundsInLocal().getHeight() - container.getBoundsInLocal().getHeight()) / 2);
    }

    /**
     * Move os conteúdos da forma de acordo com a posição atual deles.
     *
     * @param X Distância em pixels para mover no eixo X.
     * @param Y Distância em pixels para mover no eixo Y.
     */
    public void mover_conteudo(double X, double Y) {
        container.setTranslateX(container.getTranslateX() + X);
        container.setTranslateY(container.getTranslateY() + Y);
    }

    /**
     * Move os conteúdos da forma de acordo com a posição inicial dos mesmos
     * (0,0).
     *
     * @param X Distância em pixels para mover no eixo X.
     * @param Y Distância em pixels para mover no eixo Y.
     */
    public void realocar_conteudo(double X, double Y) {
        container.setTranslateX(0);
        container.setTranslateY(0);
        container.setTranslateX(X);
        container.setTranslateY(Y);
    }

    public double getLargura() {
        return forma.getBoundsInLocal().getWidth();
    }

    public double getAltura() {
        return forma.getBoundsInLocal().getHeight();
    }
}
