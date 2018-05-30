package iftorrent.gui.ferramentas;

import java.io.Serializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 *
 * A classe <b>ItemMenu</b> serve para estruturar o conteúdo de um item de um 
 * menu.
 * <p>
 *
 * @author Eduardo Toffolo
 *
 *
 */
public final class ItemMenu implements Serializable{

    /**
     *
     * 
     * Um objeto do tipo <b>BorderPane</b> que organiza e une as demais partes 
     * do item.
     * 
     *
     */
    private final BorderPane conteudo = new BorderPane();
    
    /**
     *
     * 
     * Um objeto do tipo <b>HBox</b> que representa o conteúdo no centro do 
     * item.
     * 
     *
     */
    private final HBox centro = new HBox();
    
    /**
     *
     * 
     * Um objeto do tipo <b>HBox</b> que representa o conteúdo à esquerda do 
     * item.
     * 
     *
     */
    private final HBox esquerda = new HBox();
    
    /**
     *
     * 
     * Um objeto do tipo <b>HBox</b> que representa o conteúdo à direita do 
     * item.
     * 
     *
     */
    private final HBox direita = new HBox();
    
    /**
     * 
     * Um ponto flutuante com dupla precisão que representa a largura padrão de
     * um item. Por padrão, o valor inicializado é <b>150.0</b>.
     * 
     */
    private static double largura_padrao = 150.0;

    /**
     * 
     * Construtor vazio.
     * 
     */
    public ItemMenu() {
        inicializar();
    }

    /**
     * 
     * Método para inicializar os componentes do item.
     * 
     */
    private void inicializar() {
        this.conteudo.setPrefWidth(largura_padrao);
        this.conteudo.setLeft(this.esquerda);
        this.conteudo.setCenter(this.centro);
        this.conteudo.setRight(this.direita);
        this.direita.setAlignment(Pos.CENTER_RIGHT);
        this.esquerda.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(this.esquerda, Priority.NEVER);
        HBox.setHgrow(this.centro  , Priority.ALWAYS);
        HBox.setHgrow(this.direita , Priority.NEVER);
    }
    
    /**
     * 
     * Construtor que recebe o conteúdo do centro do item.
     * 
     * @param centro Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * do centro do item.<br>
     * 
     */
    public ItemMenu(Node centro) {
        inicializar();
        this.setar_centro(centro);
    }
    
    /**
     * 
     * Construtor que recebe o conteúdo do centro e da esquerda do item.
     * 
     * @param esquerda Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * da esquerda do item.<br>
     * @param centro Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * do centro do item.<br>
     * 
     */
    public ItemMenu(Node esquerda, Node centro) {
        inicializar();
        this.setar_esquerda(esquerda);
        this.setar_centro(centro);
    }
    
    /**
     * 
     * Construtor que recebe o conteúdo do centro, da esquerda e da direita 
     * do item.
     * 
     * @param esquerda Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * da esquerda do item.<br>
     * @param centro Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * do centro do item.<br>
     * @param direita Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * da direita do item.<br>
     * 
     */
    public ItemMenu(Node esquerda, Node centro, Node direita) {
        inicializar();
        this.setar_esquerda(esquerda);
        this.setar_centro(centro);
        this.setar_direita(direita);
    }

    /**
     * 
     * Setter do conteúdo da esquerda do item.
     * 
     * @param nodo Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * da esquerda do item.<br>
     * 
     */
    public void setar_esquerda(Node nodo) {
        this.esquerda.getChildren().setAll(nodo);
    }

    /**
     * 
     * Setter do conteúdo da direita do item.
     * 
     * @param nodo Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * da direita do item.<br>
     * 
     */
    public void setar_direita(Node nodo) {
        this.direita.getChildren().setAll(nodo);
    }

    /**
     * 
     * Setter do conteúdo do centro do item.
     * 
     * @param nodo Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * do centro do item.<br>
     * 
     */
    public void setar_centro(Node nodo) {
        this.setar_centro(nodo, Pos.CENTER_LEFT);
    }

    /**
     * 
     * Setter do conteúdo do centro do item.
     * 
     * @param nodo Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * do centro do item.<br>
     * @param alinhamento Uma enum do tipo <b>Pos</b> que representa o 
     * posicionamento do conteúdo do centro.<br>
     * 
     */
    public void setar_centro(Node nodo, Pos alinhamento) {
        this.centro.getChildren().setAll(nodo);
        this.centro.setAlignment(alinhamento);
    }
    
    /**
     * 
     * Setter do alinhamento do conteúdo do centro do item.
     * 
     * @param alinhamento Uma enum do tipo <b>Pos</b> que representa o 
     * posicionamento do conteúdo do centro.<br>
     * 
     */
    public void setar_alinhamento_centro(Pos alinhamento){
        this.centro.setAlignment(alinhamento);
    }
    
    /**
     * 
     * Getter do conteúdo da direita do item.
     * 
     * @return Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * da direita do item.<br>
     * 
     */
    public Node pegar_direita(){
        return this.direita.getChildren().get(0);
    }
    
    /**
     * 
     * Getter do conteúdo da esquerda do item.
     * 
     * @return Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * da esquerda do item.<br>
     * 
     */
    public Node pegar_esquerda(){
        return this.esquerda.getChildren().get(0);
    }
    
    /**
     * 
     * Getter do conteúdo do centro do item.
     * 
     * @return Um objeto do tipo <b>Node</b> que representa o conteúdo 
     * do centro do item.<br>
     * 
     */
    public Node pegar_centro(){
        return this.centro.getChildren().get(0);
    }
    
    /**
     * 
     * Método para obter o conteúdo do item.
     * 
     * @return Um objeto do tipo <b>Node</b> que representa o conteúdo do 
     * item.<br>
     * 
     */
    public Node contruir_item(){
        return this.conteudo;
    }
    
    /**
     * 
     * Getter da largura padrão dos itens.
     * 
     * @return Um ponto flutuante com dupla precisão que representa a largura 
     * padrão dos itens.<br>
     * 
     */
    public static double pegar_largura_padrao(){
        return largura_padrao;
    }
    
    /**
     * 
     * Setter da largura padrão dos itens.
     * 
     * @param largura Um ponto flutuante com dupla precisão que representa a 
     * largura padrão dos itens.<br>
     * 
     */
    public static void setar_largura_padrao(double largura){
        ItemMenu.largura_padrao = largura;
    }
    
    /**
     * 
     * Método para redimensionar a largura de um item.
     * 
     * @param largura Um ponto flutuante com dupla precisão que representa a 
     * nova largura do item.<br>
     * 
     */
    public void redimensionar_item(double largura){
        this.conteudo.setPrefWidth(largura);
    }

}
