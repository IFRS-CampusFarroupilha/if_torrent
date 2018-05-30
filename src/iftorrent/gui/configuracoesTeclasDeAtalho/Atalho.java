package iftorrent.gui.configuracoesTeclasDeAtalho;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination.Modifier;
import javafx.scene.input.KeyEvent;

/**
 * 
 * 
 * A classe <b>Atalho</b> serve para representar uma combinação entre uma tecla
 * e os modificadores (<i>Alt, Shift e Ctrl</i>).
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public class Atalho {
    
    /**
     * 
     * 
     * Um booleano que indica se o modificador <i>Shift</i> está ativado.
     * 
     * 
     */
    private boolean shift_ativado = false;
    
    /**
     * 
     * 
     * Um booleano que indica se o modificador <i>Ctrl</i> está ativado.
     * 
     * 
     */
    private boolean ctrl_ativado = false;
    
    /**
     * 
     * 
     * Um booleano que indica se o modificador <i>Alt</i> está ativado.
     * 
     * 
     */
    private boolean alt_ativado = false;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>KeyCode</b> que representa a tecla.
     * 
     * 
     */
    private KeyCode tecla;

    /**
     * 
     * 
     * Construtor que inicializa a tecla (sem modificadores).
     * 
     * @param tecla Um objeto do tipo <b>KeyCode</b> que representa a tecla.<br>
     * 
     * 
     */
    public Atalho(KeyCode tecla) {
        this.tecla = tecla;
    }
    
    /**
     * 
     * 
     * Construtor que inicializa a tecla.
     * 
     * @param evento Um objeto do tipo <b>KeyEvent</b> que representa a tecla 
     * e os modificadores.<br>
     * 
     * 
     */
    public Atalho(KeyEvent evento) {
        this.tecla = evento.getCode();
        this.alt_ativado = evento.isAltDown();
        this.ctrl_ativado = evento.isControlDown();
        this.shift_ativado = evento.isShiftDown();
    }

    /**
     * 
     * 
     * Construtor que inicializa a tecla.
     * 
     * @param tecla Um objeto do tipo <b>KeyCode</b> que representa a tecla.<br>
     * @param shift Um booleano que indica se o modificador <i>Shift</i> está 
     * ativado.<br>
     * @param ctrl Um booleano que indica se o modificador <i>Ctrl</i> está 
     * ativado.<br>
     * @param alt Um booleano que indica se o modificador <i>Alt</i> está 
     * ativado.<br>
     * 
     * 
     */
    public Atalho(KeyCode tecla, boolean shift, boolean ctrl, boolean alt) {
        this.tecla = tecla;
        this.alt_ativado = alt;
        this.ctrl_ativado = ctrl;
        this.shift_ativado = shift;
    }

    /**
     * 
     * 
     * Método que informa se o modificador <i>Shift</i> está ativado.
     * 
     * @return Um booleano que indica se o modificador <i>Shift</i> está ativado.<br>
     * 
     * 
     */
    public boolean isShift_ativado() {
        return shift_ativado;
    }

    /**
     * 
     * 
     * Método que informa se o modificador <i>Shift</i> está ativado.
     * 
     * @param shift_ativado Um booleano que indica se o modificador <i>Shift</i>
     * está ativado.<br>
     * 
     * 
     */
    public void setShift_ativado(boolean shift_ativado) {
        this.shift_ativado = shift_ativado;
    }

    /**
     * 
     * 
     * Método que informa se o modificador <i>Ctrl</i> está ativado.
     * 
     * @return Um booleano que indica se o modificador <i>Ctrl</i> está ativado.<br>
     * 
     * 
     */
    public boolean isCtrl_ativado() {
        return ctrl_ativado;
    }

    /**
     * 
     * 
     * Método que informa se o modificador <i>Ctrl</i> está ativado.
     * 
     * @param ctrl_ativado Um booleano que indica se o modificador <i>Ctrl</i>
     * está ativado.<br>
     * 
     * 
     */
    public void setCtrl_ativado(boolean ctrl_ativado) {
        this.ctrl_ativado = ctrl_ativado;
    }

    /**
     * 
     * 
     * Método que informa se o modificador <i>Alt</i> está ativado.
     * 
     * @return Um booleano que indica se o modificador <i>Alt</i> está ativado.<br>
     * 
     * 
     */
    public boolean isAlt_ativado() {
        return alt_ativado;
    }

    /**
     * 
     * 
     * Método que informa se o modificador <i>Alt</i> está ativado.
     * 
     * @param alt_ativado Um booleano que indica se o modificador <i>Alt</i>
     * está ativado.<br>
     * 
     * 
     */
    public void setAlt_ativado(boolean alt_ativado) {
        this.alt_ativado = alt_ativado;
    }

    /**
     * 
     * 
     * Getter da tecla.
     * 
     * @return Um objeto do tipo <b>KeyCode</b> que representa a tecla.<br>
     * 
     * 
     */
    public KeyCode getTecla() {
        return tecla;
    }

    /**
     * 
     * 
     * Setter da tecla.
     * 
     * @param tecla Um objeto do tipo <b>KeyCode</b> que representa a tecla.<br>
     * 
     * 
     */
    public void setTecla(KeyCode tecla) {
        this.tecla = tecla;
    }
    
    /**
     * 
     * 
     * Método que converte o objeto para um objeto do tipo <b>String</b>.
     * 
     * @return Um objeto do tipo <b>String</b> que representa a tecla e os 
     * modificadores ativados.<br>
     * 
     * 
     */
    @Override
    public String toString() {
        String atalho = "";
        
        if(this.ctrl_ativado){
            atalho = this.adicionar(atalho, KeyCode.CONTROL.getName());
        }
        if(this.alt_ativado){
            atalho = this.adicionar(atalho, KeyCode.ALT.getName());
        }
        if(this.shift_ativado){
            atalho = this.adicionar(atalho, KeyCode.SHIFT.getName());
        }
        if(!this.tecla.equals(KeyCode.ALT) &&
           !this.tecla.equals(KeyCode.SHIFT) &&
           !this.tecla.equals(KeyCode.CONTROL) &&
           !this.tecla.equals(KeyCode.ALT_GRAPH)){
            atalho = this.adicionar(atalho, this.tecla.getName());
        }
        return atalho;
    }
    
    /**
     * 
     * 
     * Método para concatenar objetos do tipo <b>String</b> adicionando um 
     * símbolo entre eles.
     * 
     * @param frase Um objeto do tipo <b>String</b> no qual será concatenado 
     * outro conteúdo.<br>
     * @param parte Um objeto do tipo <b>String</b> que representa o trecho a 
     * ser adicionado.<br>
     * 
     * @return Um objeto do tipo <b>String</b> que representa a frase final.<br>
     * 
     * 
     */
    private String adicionar(String frase, String parte){
        return frase.equals("") ? parte : frase + " + " + parte;
    }
    
    public KeyCodeCombination getKeyCodeCombination(){
        ArrayList<Modifier> modificadores = new ArrayList<>();
        
        if(this.alt_ativado){
            modificadores.add(KeyCodeCombination.ALT_DOWN);
        }
        if (this.ctrl_ativado){
            modificadores.add(KeyCodeCombination.CONTROL_DOWN);
        }
        if(this.shift_ativado){
            modificadores.add(KeyCodeCombination.SHIFT_DOWN);
        }
        return new KeyCodeCombination(this.getTecla(), (Modifier[]) modificadores.toArray());
    }
    
}
