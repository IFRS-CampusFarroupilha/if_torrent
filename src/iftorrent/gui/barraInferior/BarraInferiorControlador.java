package iftorrent.gui.barraInferior;

import iftorrent.gui.ferramentas.Comunicador;
import static iftorrent.gui.barraInferior.BarraInferiorConstantes.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * 
 *
 * A classe <b>BarraInferiorControlador</b> serve para controlar a interface
 * da barra inferior da janela.
 * <p>
 * Esta classe implementa <b>Initializable</b>.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class BarraInferiorControlador implements Initializable {
    
    /**
     * 
     * 
     * Um objeto do tipo <b>BorderPane</b> que representa a barra inferior.
     * 
     * 
     */
    @FXML
    private BorderPane painel;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>Label</b>.
     * 
     * 
     */
    @FXML
    private Label barra_inferior;
    
    /**
     * 
     * 
     * Método para inicializar os elementos da interface da barra inferior da 
     * janela.
     *
     * @param url O diretório do objeto root ou null caso o mesmo não seja
     * encontrado.<br>
     * @param rb O recurso para localizar o objeto root ou null caso o mesmo não
     * seja encontrado.<br>
     * 
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Comunicador.setBarra_inferior(this);
        this.barra_inferior.setMinHeight(LABEL_ALTURA1);
        this.barra_inferior.setText(" " + LABEL_TEXTO1);
        this.painel.getStylesheets().add(URL_CSS1);
    }
}
