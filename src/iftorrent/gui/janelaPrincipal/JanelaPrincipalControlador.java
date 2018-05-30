package iftorrent.gui.janelaPrincipal;

import iftorrent.arquivos.torrent.ArquivoTorrentPropriedades;
import iftorrent.gui.barraDeFerramentas.BarraDeFerramentasControlador;
import iftorrent.gui.ferramentas.Comunicador;
import static iftorrent.gui.janelaPrincipal.ConstantesJanelaPrincipal.*;
import iftorrent.gui.menuPrincipal.MenuPrincipalControlador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * 
 *
 * Controlador da JanelaPrincipal.
 *
 * @author Eduardo Toffolo
 * @author Leonardo Bortolini
 *
 * 
 */
public class JanelaPrincipalControlador implements Initializable {

    @FXML
    public TableView<ArquivoTorrentPropriedades> tabela;
    
    /**
     * 
     *
     * Parte central da GUI (tabela que agrupa os torrents).
     *
     * 
     */
    @FXML
    private HBox menu_principal;

    /**
     * 
     *
     * VerticalBox que agrupa todos os outros componentes da GUI (agrupa os
     * FXMLs com a tag Include).
     *
     * 
     */
    @FXML
    private VBox painel1;

    /**
     * 
     *
     * HorizontalBox onde está incluido a parte abaixo da barra do menu.
     *
     * 
     */
    @FXML
    private HBox painel2;

    /**
     * 
     *
     * Está VerticalBox está inclusa no painel2 (em sua segunda partição) e
     * inclui a BarraDeFerramentas.
     *
     * @see BarraDeFerramentasControlador
     *
     * 
     */
    @FXML
    public VBox painel3;

    /**
     * 
     *
     * Essa VerticalBox está inclusa no painel3 e inclui o MenuPrincipal e a
     * AbasPrioridades.
     *
     * @see MenuPrincipalControlador
     *
     * 
     */
    @FXML
    private VBox painel4;
    
    /**
     * 
     *
     * O método initialize da JanelaPrincipal é usado para escolher a prioridade
     * de expansão dos componentes, ou seja, para quando a janela se expandir
     * seus componentes a acompanharem.
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
        Comunicador.setJanela_principal(this);
        VBox.setVgrow(this.painel2, Priority.ALWAYS);
        VBox.setVgrow(this.painel4, Priority.ALWAYS);
        VBox.setVgrow(this.menu_principal, Priority.ALWAYS);
        HBox.setHgrow(this.painel3, Priority.ALWAYS);
        this.painel1.setPrefSize(JANELA_TAMANHO1.X, JANELA_TAMANHO1.Y);
    }
    
    public VBox getPainel(){
        return painel1;
    }
}