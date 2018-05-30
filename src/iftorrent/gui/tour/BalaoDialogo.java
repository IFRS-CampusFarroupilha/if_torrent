package iftorrent.gui.tour;

import static iftorrent.gui.tour.ConstantesBalaoDialogo.*;
import iftorrent.texto.Texto;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;

public class BalaoDialogo extends Pane {

    public Shape balao;
    public Shape triangulo;
    public Texto texto;
    public String conteudo;

    /**
     * Construtor para criar um balão de diálogo, que será um retângulo, com um
     * texto dentro, podendo ser animado ou não.
     *
     * @param largura Largura do balão (retângulo).
     * @param altura Altura do balão (retângulo).
     * @param direcao_cardinal Direção para qual o rabinho do balão ficará
     * apontado.
     * @param tera_animacao_entrada True caso o balão terá animação de entrada.
     * @param fala O diálogo que aparecerá.
     * @param cor_fundo A cor do fundo do balão.
     * @param cor_borda A cor da borda do balão.
     */
    public BalaoDialogo(double largura, double altura, String direcao_cardinal,
            boolean tera_animacao_entrada, String fala, Paint cor_fundo, Paint cor_borda) {

        Rectangle retangulo = new Rectangle(largura, altura);
        if (cor_fundo == null) {
            retangulo.setFill(Color.ALICEBLUE);
        } else {
            retangulo.setFill(cor_fundo);
        }
        if (cor_borda == null) {
            retangulo.setStroke(Color.CADETBLUE);
        } else {
            retangulo.setStroke(cor_borda);
        }
        retangulo.setStrokeLineJoin(StrokeLineJoin.ROUND);
        retangulo.setStrokeWidth(8);
        balao = retangulo;
        triangulo = interpreta_direcao(direcao_cardinal);

        texto = new Texto(fala);
        texto.setTranslateX(balao.getStrokeWidth());
        texto.setTranslateY(balao.getStrokeWidth() * 2);//???????? *2 ??????? só aceita que funfa//
        conteudo = fala;

        if (tera_animacao_entrada) {
            balao.setScaleX(0);
            balao.setScaleY(0);
            triangulo.setScaleX(0);
            triangulo.setScaleY(0);
        }

        getChildren().addAll(balao, triangulo, texto);
    }

    /**
     * Construtor para criar um balão de diálogo, que será um retângulo, com um
     * texto dentro, podendo ser animado ou não. Por não receber parâmetros de
     * tamanho, a largura e a altura do balão de diálogo, dependerá apenas do
     * tamanho do texto (diálogo) que contém.
     *
     * @param direcao_cardinal Direção para qual o rabinho do balão ficará
     * apontado.
     * @param tera_animacao_entrada True caso o balão terá animação de entrada.
     * @param fala O diálogo que aparecerá.
     * @param cor_fundo A cor do fundo do balão.
     * @param cor_borda A cor da borda do balão.
     */
    public BalaoDialogo(String direcao_cardinal, boolean tera_animacao_entrada, String fala,
            Paint cor_fundo, Paint cor_borda) {
        conteudo = fala;
        texto = new Texto(fala);
        texto.setTranslateX(8);
        texto.setTranslateY(8 * 2 +5);//???????? *2 ??????? só aceita que funfa//

        Rectangle retangulo = new Rectangle(texto.getLarguraTexto() + 8 + 2, texto.getAlturaTexto() + 8 + 2);
        if (cor_fundo == null) {
            retangulo.setFill(Color.ALICEBLUE);
        } else {
            retangulo.setFill(cor_fundo);
        }
        if (cor_borda == null) {
            retangulo.setStroke(Color.CADETBLUE);
        } else {
            retangulo.setStroke(cor_borda);
        }
        retangulo.setStrokeLineJoin(StrokeLineJoin.ROUND);
        retangulo.setStrokeWidth(8);
        balao = retangulo;
        triangulo = interpreta_direcao(direcao_cardinal);

        if (tera_animacao_entrada) {
            balao.setScaleX(0);
            balao.setScaleY(0);
            if (triangulo != null) {
                triangulo.setScaleX(0);
                triangulo.setScaleY(0);
            }
        }
        if(triangulo != null){
            getChildren().addAll(balao, triangulo, texto);
        }else{
            getChildren().addAll(balao, texto);
        }
    }

    /**
     * MÉTODO EM CONSTRUÇÃO, NÃO PRECISA REVISAR
     *
     * @param largura
     * @param altura
     * @param inicio
     * @param tera_animacao
     * @param texto
     */
    public BalaoDialogo(double largura, double altura, Point2D inicio,
            boolean tera_animacao, String texto) {

        Rectangle retangulo = new Rectangle(largura, altura);
        retangulo.setFill(Color.ALICEBLUE);
        retangulo.setStroke(Color.CADETBLUE);
        retangulo.setStrokeLineJoin(StrokeLineJoin.ROUND);
        retangulo.setStrokeWidth(8);
        balao = retangulo;

//        Shape triangulo = interpreta_direcao(direcao_cardinal);
//        triangulo.setTranslateX(200);
//        triangulo.setTranslateY(200);
//        
//        getChildren().addAll(balao, triangulo);
    }

    /**
     * Método para direcionar o rabinho do balão do diálogo.
     *
     * @param direcao String que diz para qual direção o rabinho deve estar
     * apontado.
     * @return
     */
    private Shape interpreta_direcao(String direcao) {
        Double pontos[] = null;
        Polygon poligono = new Polygon();
        double X = 0;
        double Y = 0;

        switch (direcao.toUpperCase()) {
            case NORTE:
                pontos = new Double[]{
                    0.0, 0.0,
                    10.0, 10.0,
                    -10.0, 10.0};
                X = ((Rectangle) balao).getWidth() / 2;
                Y = -10;
                break;
            case SUL:
                pontos = new Double[]{
                    -10.0, 0.0,
                    10.0, 0.0,
                    0.0, 10.0};
                X = ((Rectangle) balao).getWidth() / 2;
                Y = ((Rectangle) balao).getHeight();
                break;
            case LESTE:
                pontos = new Double[]{
                    0.0, 0.0,
                    10.0, 10.0,
                    0.0, 20.0};
                X = ((Rectangle) balao).getWidth();
                Y = ((Rectangle) balao).getHeight() / 2 - 10;
                break;
            case OESTE:
                pontos = new Double[]{
                    0.0, 0.0,
                    -10.0, 10.0,
                    0.0, 20.0};
                X = 0;
                Y = ((Rectangle) balao).getHeight() / 2 - 10;
                break;
            default:
                return null;
        }
        poligono.getPoints().addAll(pontos);
        poligono.setFill(balao.getStroke());
        poligono.setStroke(balao.getStroke());
        poligono.setStrokeLineJoin(balao.getStrokeLineJoin());
        poligono.setStrokeWidth(balao.getStrokeWidth());
        poligono.setTranslateX(X);
        poligono.setTranslateY(Y);

        return poligono;
    }

    /**
     * Faz com que, caso o balão tenha animação, apareça com uma animação de
     * tamanho (Escala).
     *
     * @param largura Largura que o balão terá no ápice da animação (irá
     * retornar ao tamanho original após o ápice, sempre).
     * @param altura Altura que o balão terá no ápice da animação (irá retornar
     * ao tamanho original após o ápice, sempre).
     * @param duracao Duração da animação em segundos.
     */
    public void aparecer_balao(double largura, double altura, double duracao) {
        Animacoes.animacao_escala(balao, largura, altura, duracao);
    }

    /**
     * Faz com que, caso o balão tenha animação, seu rabinho apareça com uma
     * animação de tamanho (Escala).
     *
     * @param largura Largura que o rabinho terá no ápice da animação (irá
     * retornar ao tamanho original após o ápice, sempre).
     * @param altura Altura que o rabinho terá no ápice da animação (irá
     * retornar ao tamanho original após o ápice, sempre).
     * @param duracao Duração da animação em segundos.
     */
    public void aparecer_triangulo(double largura, double altura, double duracao) {
        if (triangulo != null) {
            Animacoes.animacao_escala(triangulo, largura, altura, duracao);
        }
    }

    /**
     * Faz com que, caso o texto tenha animação, apareça com uma animação de
     * tamanho (Escala).
     *
     * @param largura Largura que o texto terá no ápice da animação (irá
     * retornar ao tamanho original após o ápice, sempre).
     * @param altura Altura que o texto terá no ápice da animação (irá retornar
     * ao tamanho original após o ápice, sempre).
     * @param duracao Duração da animação em segundos.
     */
    public void aparecer_texto(double largura, double altura, double duracao) {
        Animacoes.animacao_escala(texto, largura, altura, duracao);
    }

    /**
     * Faz com que o texto do balão (caso tenham animação) apareça com uma
     * animação de digitação.
     *
     * @param duracao_por_caractere Duração da animação em segundos.
     */
    public void animacao_escrita(double duracao_por_caractere) {
        texto.aparecer_texto_fixo(duracao_por_caractere);
    }

    public void aparecer_balao_inteiro(double largura, double altura, double duracao) {
        aparecer_balao(largura, altura, duracao);
        aparecer_triangulo(largura, altura, duracao);
        aparecer_texto(largura, altura, duracao);
    }
    
    public double getAltura(){
        return balao.getBoundsInLocal().getHeight();
    }
    
    public double getLargura(){
        return balao.getBoundsInLocal().getWidth();
    }

}
