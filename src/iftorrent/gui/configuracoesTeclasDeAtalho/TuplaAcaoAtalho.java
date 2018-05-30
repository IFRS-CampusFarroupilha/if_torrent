package iftorrent.gui.configuracoesTeclasDeAtalho;

import iftorrent.gui.ferramentas.Comunicador;
import static iftorrent.gui.configuracoesTeclasDeAtalho.ConfiguracoesTeclasDeAtalhoConstantes.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * 
 * 
 * A classe <b>TuplaAcaoAtalho</b> é utilizada para inserir a descrição de uma 
 * ação e o campo para inserção do atalho que executará a mesma em uma linha da 
 * tabela.
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public class TuplaAcaoAtalho {

    /**
     * 
     * 
     * Um objeto do tipo <b>String</b> que representa a descrição da ação que 
     * será executada pelo atalho.
     * 
     * 
     */
    private String acao;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>Atalho</b> que representa o atalho. Por padrão, o 
     * atalho é inicializado como <i>UNDEFINED</i>.
     * 
     * 
     */
    private Atalho atalho = new Atalho(KeyCode.UNDEFINED);
    
    /**
     * 
     * 
     * Um objeto do tipo <b>Button</b> que representa o campo onde será 
     * inserido o atalho.
     * 
     * 
     */
    private final Button entrada_de_texto = new Button();

    /**
     * 
     * 
     * Construtor que recebe a descrição da ação a ser executada pelo atalho e 
     * o controlador da janela de configuração de teclas de atalho para 
     * estabelecer comunicação com o mesmo.
     * 
     * @param acao Um objeto do tipo <b>String</b> que representa a descrição 
     * da ação que será executada pelo atalho.<br>
     * 
     * 
     */
    public TuplaAcaoAtalho(String acao) {
        this.acao = acao;
        this.carregar();
    }
    
    /**
     * 
     * 
     * Construtor que recebe a descrição da ação a ser executada pelo atalho e 
     * o controlador da janela de configuração de teclas de atalho para 
     * estabelecer comunicação com o mesmo.
     * 
     * @param acao Um objeto do tipo <b>String</b> que representa a descrição 
     * da ação que será executada pelo atalho.<br>
     * 
     * 
     */
    public TuplaAcaoAtalho(String acao, Atalho padrao) {
        this.acao = acao;
        this.atalho = padrao;
        this.entrada_de_texto.setText(padrao.toString());
        this.carregar();
    }
    
    

    /**
     * 
     * 
     * Método para definir os eventos dos componentes da janela de configuração 
     * de teclas de atalho.
     * 
     * 
     */
    public final void carregar() {
        this.entrada_de_texto.setOnKeyPressed((KeyEvent evento) -> {
            if (evento.getCode().equals(KeyCode.ENTER)) {
                Comunicador.getConfiguracoes_teclas_de_atalho().delegar_foco(this);
            } else {
                atalho = new Atalho(evento);
                entrada_de_texto.setText(atalho.toString());
            }
        });
        this.entrada_de_texto.setOnMouseClicked((MouseEvent evento) -> {
            Comunicador.getConfiguracoes_teclas_de_atalho().adicionar_foco(this);
        });
        this.entrada_de_texto.setPrefSize(ENTRADA_DE_TEXTO_TAMANHO1.X, 
                ENTRADA_DE_TEXTO_TAMANHO1.Y);
        this.entrada_de_texto.setText(this.atalho.toString());
    }

    /**
     * 
     * 
     * Construtor vazio.
     * 
     * 
     */
    public TuplaAcaoAtalho() {
        this.carregar();
    }

    /**
     * 
     * 
     * Getter da ação.
     * 
     * @return Um objeto do tipo <b>String</b> que representa a descrição da 
     * ação que será executada pelo atalho.<br>
     * 
     * 
     */
    public String getAcao() {
        return acao;
    }

    /**
     * 
     * 
     * Setter da ação.
     * 
     * @param acao Um objeto do tipo <b>String</b> que representa a descrição da 
     * ação que será executada pelo atalho.<br>
     * 
     * 
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    /**
     * 
     * 
     * Getter da atalho.
     * 
     * @return Um objeto do tipo <b>Atalho</b> que representa o atalho.<br>
     * 
     * 
     */
    public Atalho getAtalho() {
        return this.atalho;
    }

    /**
     * 
     * 
     * Setter da atalho.
     * 
     * @param atalho Um objeto do tipo <b>Atalho</b> que representa o atalho.<br>
     * 
     * 
     */
    public void setAtalho(Atalho atalho) {
        this.atalho = atalho;
    }

    /**
     * 
     * 
     * Getter do campo de entrada de texto.
     * 
     * @return Um objeto do tipo <b>Node</b> que representa o campo de entrada 
     * de texto.<br>
     * 
     * 
     */
    public Node getEntrada_de_texto() {
        return entrada_de_texto;
    }

}
