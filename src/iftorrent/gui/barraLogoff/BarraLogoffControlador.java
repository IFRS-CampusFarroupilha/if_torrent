package iftorrent.gui.barraLogoff;

import iftorrent.gui.ferramentas.Comunicador;
import static iftorrent.gui.barraLogoff.BarraLogoffConstantes.*;
import static iftorrent.gui.ferramentas.ManipuladorImagem.criar_imagem_tratando_erro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Polygon;

/**
 * 
 *
 * A classe <b>BarraLogoffControlador</b> serve para controlar a interface da
 * Barra de Logoff.
 * <p>
 * Esta classe implementa <b>Initializable</b>.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class BarraLogoffControlador implements Initializable {

    /**
     * 
     *
     * Leiaute da Barra de Loggof.
     *
     * 
     */
    @FXML
    private HBox menu;

    /**
     * 
     *
     * Botão para expandir a barra e exibir os botões.
     *
     * 
     */
    @FXML
    private Button botao_expandir;

    /**
     * 
     *
     * Painel em que os botões estão inseridos.
     *
     * 
     */
    private final HBox painel = new HBox();

    /**
     * 
     *
     * Botão para encerrar a aplicação.
     *
     * 
     */
    private final Button encerrar = new Button();

    /**
     * 
     *
     * Botão para fechar a interface gráfica, mas continuar a execução em
     * segundo plano.
     *
     * 
     */
    private final Button fechar = new Button();

    /**
     * 
     *
     * Botão para bloquear a interface.
     *
     * 
     */
    private final Button bloquear = new Button();

    /**
     * 
     *
     * Método para inicializar os elementos da interface da Barra de Logoff.
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
        Comunicador.setBarra_logoff(this);
        Polygon seta = new Polygon(
                54.0, 52.0,
                54.0, 58.0,
                50.0, 55.0
        );

        this.menu.getStylesheets().add(URL_CSS1);
        this.menu.setAlignment(Pos.CENTER_RIGHT);
        this.botao_expandir.setGraphic(seta);
        this.botao_expandir.setMinWidth(BOTAO_LARGURA2);
        this.botao_expandir.setPrefWidth(BOTAO_LARGURA1);
        this.botao_expandir.setMaxHeight(BARRA_LOGOFF_ALTURA1);
        this.encerrar.setGraphic(criar_imagem_tratando_erro(URL_ENCERRAR, IMAGEM_TAMANHO1));
        this.fechar.setGraphic(criar_imagem_tratando_erro(URL_FECHAR, IMAGEM_TAMANHO1));
        this.bloquear.setGraphic(criar_imagem_tratando_erro(URL_BLOQUEAR, IMAGEM_TAMANHO1));
        this.painel.getChildren().addAll(
                this.encerrar,
                this.fechar,
                this.bloquear
        );
        this.painel.setId("painel");
        this.painel.setMaxHeight(BARRA_LOGOFF_ALTURA1);
        this.painel.setAlignment(Pos.CENTER);
    }

    /**
     * 
     *
     * Método para mostrar (caso esteja oculto) ou ocultar (caso esteja exibido)
     * o conteúdo da barra de logoff. 
     *
     * 
     */
    @FXML
    public void mostrar_ou_ocultar() {
        if (!menu.getChildren().contains(painel)) {
            menu.getChildren().add(painel);
        } else {
            menu.getChildren().remove(painel);
        }
    }

}
