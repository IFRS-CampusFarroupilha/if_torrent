package iftorrent.gui.sobre;

import iftorrent.gui.ferramentas.Comunicador;
import static iftorrent.gui.sobre.ConstantesSobre.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Controlador da janela sobre a equipe.
 *
 * @author Gabriel Müller
 */
public class SobreControlador implements Initializable {
    
    /**
     * 
     *
     * Um objeto do tipo <b>AnchorPane</b> que representa a janela onde o
     * painel é exibido.
     *
     * 
     */
    @FXML
    private AnchorPane janela;

    /**
     * 
     *
     * Um objeto do tipo <b>Label</b> que representa o campo de texto que
     * apresenta as informações sobre a equipe.
     *
     * 
     */
    @FXML
    private Label texto;

    /**
     * Método para inicializar os elementos da janela sobre a equipe.
     *
     * @param url O diretório do objeto root ou null caso o mesmo não seja
     * encontrado.
     * @param rb O recurso para localizar o objeto root ou null caso o mesmo não
     * seja encontrado.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        texto.setFont(Font.font(FONTE_NOME, FontWeight.NORMAL, FONTE_TAMANHO));
        Comunicador.setSobre(this);
        this.janela.getStylesheets().add(URL_CSS1);
        this.texto.setText(TEXTO);
    }
}
