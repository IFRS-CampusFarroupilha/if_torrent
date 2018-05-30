package iftorrent.gui.tour;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Seta extends Pane{
    Shape seta;
    Point2D ponto_inicial;
    Point2D ponto_final;
    double comprimento;
    Paint cor;

    public Seta(Point2D ponto_inicial, Point2D ponto_final, Paint cor) {
        this.ponto_inicial = ponto_inicial;
        this.ponto_final = ponto_final;
        this.cor = cor;
        
        comprimento = Math.hypot(ponto_final.getX() - ponto_inicial.getX(),
            ponto_final.getY() - ponto_inicial.getY());
        
        double tamanho = comprimento/2;
        
        Polygon seta = new Polygon();
                seta.getPoints().addAll(new Double[]{
                            0.0, tamanho,
                            -tamanho, -tamanho,
                            -tamanho/8, -tamanho/5*3,
                            -tamanho/8, -tamanho*2,
                            tamanho/8, -tamanho*2,
                            tamanho/8, -tamanho/5*3,
                            tamanho, -tamanho});

        double angulo = Math.atan2(ponto_final.getY() - ponto_inicial.getY(),
            ponto_final.getX() - ponto_inicial.getX()) * 180 / 3.14;

        seta.setRotate((angulo - 90));
        seta.setFill(cor);
        this.seta = seta;
        
        getChildren().addAll(this.seta);
    }
    
    public Seta(Point2D ponto_inicial, Point2D ponto_final, double altura_apontador, double largura_apontador, Paint cor) {
        this.ponto_inicial = ponto_inicial;
        this.ponto_final = ponto_final;
        this.cor = cor;
        
        comprimento = Math.hypot(ponto_final.getX() - ponto_inicial.getX(),
            ponto_final.getY() - ponto_inicial.getY());
        
        double tamanho_seta = comprimento/2;
        
        Polygon seta = new Polygon();
                seta.getPoints().addAll(new Double[]{
                            0.0, altura_apontador/2,
                            -largura_apontador/2, -altura_apontador/2,
                            -largura_apontador/8, -altura_apontador/2/5*3,
                            -largura_apontador/8, -tamanho_seta*2,
                            largura_apontador/8, -tamanho_seta*2,
                            largura_apontador/8, -altura_apontador/2/5*3,
                            largura_apontador/2, -altura_apontador/2});

        double angulo = Math.atan2(ponto_final.getY() - ponto_inicial.getY(),
            ponto_final.getX() - ponto_inicial.getX()) * 180 / 3.14;

        seta.setRotate((angulo - 90));
        seta.setFill(cor);
        this.seta = seta;
        
        getChildren().addAll(this.seta);
    }
    
    public void alterar_tamanho(double porcentagem_X, double porcentagem_Y){
        seta.setScaleX(porcentagem_X/100);
        seta.setScaleY(porcentagem_Y/100);
    }
    
}
