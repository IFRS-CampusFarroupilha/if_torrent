package iftorrent.gui.ferramentas;

/**
 * 
 * 
 * A classe <b>TuplaXY</b> é utilizada para representar uma tupla de dois
 * elementos inteiros.
 *
 * @author Eduardo Toffolo
 *
 *
 */
public class TuplaXY {

    /**
     * 
     * 
     * Um ponto flutuante que representa um valor referente ao eixo das
     * abscissas (eixo x ou eixo horizontal) em um plano cartesiano.<br>
     * 
     *
     */
    public double X;

    /**
     * 
     * 
     * Um ponto flutuante que representa um valor referente ao eixo das
     * coordenadas (eixo y ou eixo vertital) em um plano cartesiano.<br>
     * 
     *
     */
    public double Y;

    /**
     * 
     * 
     * Construtor da classe que recebe os valores da tupla.
     *
     * @param x Um ponto flutuante que representa o valor de X da classe.<br>
     * @param y Um ponto flutuante que representa o valor de Y da classe.<br>
     * 
     *
     */
    public TuplaXY(double x, double y) {
        this.X = x;
        this.Y = y;
    }

}
