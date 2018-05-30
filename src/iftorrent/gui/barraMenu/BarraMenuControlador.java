package iftorrent.gui.barraMenu;

import com.turn.ttorrent.common.Torrent;
import iftorrent.JanelaPrincipal;
import iftorrent.arquivos.Logger;
import static iftorrent.gui.barraMenu.BarraMenuConstantes.*;
import iftorrent.gui.ferramentas.Auxiliar;
import iftorrent.gui.ferramentas.Comunicador;
import iftorrent.gui.tour.apresentacoes.ApresentarSoftware;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 *
 * A classe <b>BarraMenuControlador</b> serve para controlar a interface da
 * Barra Menu.
 * <p>
 * Esta classe implementa <b>Initializable</b>.
 *
 * @author Eduardo Toffolo
 * @author Otávio Farinon
 * 
 */
public class BarraMenuControlador implements Initializable {

    /**
     * 
     *
     * Layout da Barra Menu.
     *
     * 
     */
    @FXML
    private HBox painel;

    /**
     * 
     *
     * Barra Menu.
     *
     * 
     */
    @FXML
    private MenuBar menu;

    /**
     * 
     *
     * Primeiro menu da barra.
     *
     * 
     */
    @FXML
    private Menu arquivo;

    /**
     * 
     *
     * Itens do menu arquivo.
     *
     * 
     */
    @FXML
    private MenuItem gerar_novo_arquivo_torrent, abrir_arquivo_torrent, item_arquivo3,
            verificar_arquivo_torrent;

    /**
     * 
     *
     * Segundo menu da barra.
     *
     * 
     */
    @FXML
    private Menu visualizar;

    /**
     * 
     *
     * Itens do menu visualizar.
     *
     * 
     */
    @FXML
    private MenuItem item_visualizar1, item_visualizar2, alterar_modo_de_exibicao;

    /**
     * 
     *
     * Quarto Menu da barra.
     *
     * 
     */
    @FXML
    private Menu configuracoes;

    /**
     * 
     *
     * Itens do menu configuracoes.
     *
     * 
     */
    @FXML
    private MenuItem item_configuracoes1, configurar_teclas_de_atalho,
            configuracoes_gerais, item_configuracoes4, item_configuracoes5;

    /**
     * 
     *
     * Quinto Menu da barra.
     *
     * 
     */
    @FXML
    private Menu ajuda;

    /**
     * 
     *
     * Itens do menu ajuda.
     *
     * 
     */
    @FXML
    private MenuItem fazer_um_tour, ir_para_pagina_de_ajuda, relatar_erro_sugestao, sobre_a_equipe;

    /**
     * 
     *
     * Sexto Menu da barra (link para site).
     *
     * 
     */
    @FXML
    private Menu site;

    /**
     * 
     *
     * Método para inicializar os elementos da interface da Barra Menu.
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
        Comunicador.setBarra_menu(this);
        Label menu_label = new Label();

        menu_label.setOnMouseClicked((Event event) -> {
            Comunicador.getMain().getHostServices().showDocument(URL_SITE);
        });
        this.site.setGraphic(menu_label);
        this.arquivo.setText(MENU1_TEXTO1);
        this.gerar_novo_arquivo_torrent.setText(MENU1_ITEM1_TEXTO1);
        this.gerar_novo_arquivo_torrent.setAccelerator(new KeyCodeCombination(KeyCode.F1));
        this.abrir_arquivo_torrent.setText(MENU1_ITEM2_TEXTO1);
        this.abrir_arquivo_torrent.setAccelerator(new KeyCodeCombination(KeyCode.F2));
        this.item_arquivo3.setText(MENU1_ITEM3_TEXTO1);
        this.item_arquivo3.setAccelerator(new KeyCodeCombination(KeyCode.F3));
        this.item_arquivo3.setDisable(true);
        this.verificar_arquivo_torrent.setText(MENU1_ITEM4_TEXTO1);
        this.verificar_arquivo_torrent.setAccelerator(new KeyCodeCombination(KeyCode.F4));
        this.visualizar.setText(MENU2_TEXTO1);
        this.item_visualizar1.setText(MENU2_ITEM1_TEXTO1);
        this.item_visualizar1.setAccelerator(new KeyCodeCombination(KeyCode.F5));
        this.item_visualizar1.setDisable(true);
        this.item_visualizar2.setText(MENU2_ITEM2_TEXTO1);
        this.item_visualizar2.setAccelerator(new KeyCodeCombination(KeyCode.F6));
        this.item_visualizar2.setDisable(true);
        this.alterar_modo_de_exibicao.setText(MENU2_ITEM3_TEXTO1);
        this.alterar_modo_de_exibicao.setAccelerator(new KeyCodeCombination(KeyCode.F7));
        this.configuracoes.setText(MENU3_TEXTO1);
        this.item_configuracoes1.setText(MENU3_ITEM1_TEXTO1);
        this.item_configuracoes1.setAccelerator(new KeyCodeCombination(KeyCode.F8));
        this.item_configuracoes1.setDisable(true);
        this.configurar_teclas_de_atalho.setText(MENU3_ITEM2_TEXTO1);
        this.configurar_teclas_de_atalho.setAccelerator(new KeyCodeCombination(KeyCode.F9));
        this.configuracoes_gerais.setText(MENU3_ITEM3_TEXTO1);
        this.configuracoes_gerais.setAccelerator(new KeyCodeCombination(KeyCode.F10));
        this.item_configuracoes4.setText(MENU3_ITEM4_TEXTO1);
        this.item_configuracoes4.setAccelerator(new KeyCodeCombination(KeyCode.F11));
        this.item_configuracoes4.setDisable(true);
        this.item_configuracoes5.setText(MENU3_ITEM5_TEXTO1);
        this.item_configuracoes5.setAccelerator(new KeyCodeCombination(KeyCode.F12));
        this.item_configuracoes5.setDisable(true);
        this.ajuda.setText(MENU4_TEXTO1);
        this.fazer_um_tour.setText(MENU4_ITEM1_TEXTO1);
        this.fazer_um_tour.setAccelerator(new KeyCodeCombination(KeyCode.F1, KeyCodeCombination.CONTROL_DOWN));
        this.ir_para_pagina_de_ajuda.setText(MENU4_ITEM2_TEXTO1);
        this.ir_para_pagina_de_ajuda.setAccelerator(new KeyCodeCombination(KeyCode.F2, KeyCodeCombination.CONTROL_DOWN));
        this.relatar_erro_sugestao.setText(MENU4_ITEM3_TEXTO1);
        this.relatar_erro_sugestao.setAccelerator(new KeyCodeCombination(KeyCode.F3, KeyCodeCombination.CONTROL_DOWN));
        this.sobre_a_equipe.setText(MENU4_ITEM4_TEXTO1);
        this.sobre_a_equipe.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCodeCombination.CONTROL_DOWN));
        menu_label.setText(MENU5_TEXTO1);
        HBox.setHgrow(menu, Priority.ALWAYS);
        this.painel.getStylesheets().setAll(URL_CSS1);
    }

    /**
     * 
     *
     * Método para criar a interface simples.
     *
     * @throws IOException Se o arquivo FXML não puder ser carregado.<br>
     *
     * 
     */
    @FXML
    public void abrir_janela_simples() throws IOException {       
        Parent componentes = FXMLLoader.load(getClass().getResource(URL_FXML_JANELA_SIMPLES));
        Scene cena = new Scene(componentes);
        Stage janela = new Stage();
        double largura_janela = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double altura_janela = Toolkit.getDefaultToolkit().getScreenSize().getHeight();        
        JanelaPrincipal.stage_principal.hide();
        janela.setAlwaysOnTop(true);
        janela.setScene(cena);
        //janela.setWidth(MenuPrincipalControlador.itens.size()*150);
        janela.setHeight(100);
        janela.setResizable(false);
        janela.setX(largura_janela - janela.getWidth() - 4);
        janela.setY(altura_janela - janela.getHeight() - 8);        
        janela.getIcons().add(new Image(new File(URL_ICONE1).toURI().toURL().toExternalForm()));                                
        janela.show();        
        janela.setOnCloseRequest((WindowEvent event) -> {
            JanelaPrincipal.stage_principal.show();
        });      
    }

    /**
     * 
     *
     * Método para criar um arquivo torrent a partir de arquivos do computador.
     *
     * 
     */
    @FXML
    public void criar_novo_torrent() {
        Comunicador.getBarra_de_ferramentas().acao_gerar_torrent();
    }

    /**
     * 
     *
     * Método para abrir FileChooser que irá abrir um arquivo torrent.
     *
     * 
     */
    @FXML
    public void abrir_arquivo_torrent() {
        Comunicador.getBarra_de_ferramentas().acao_adicionar_arquivo();
    }

    /**
     * 
     *
     * Método para abrir um magnet link.
     *
     * 
     */
    @FXML
    public void abrir_magnet_link() {
        Comunicador.getBarra_de_ferramentas().acao_link_magnetico();
    }

    /**
     * 
     *
     * Método para criar a janela que mostra informações sobre a equipe.
     *
     * @throws IOException Se o arquivo FXML não puder ser carregado.<br>
     *
     * 
     */
    @FXML
    public void abrir_janela_sobre() throws IOException {
        Parent componentes = FXMLLoader.load(
                getClass().getResource(URL_FXML_SOBRE));
        Scene cena = new Scene(componentes);
        Stage janela = new Stage();

        janela.setTitle(JANELA_SOBRE_TITULO1);
        janela.setHeight(JANELA_SOBRE_TAMANHO1.Y);
        janela.setWidth(JANELA_SOBRE_TAMANHO1.X);
        janela.setScene(cena);
        janela.getIcons().add(new Image(new File(URL_ICONE1).toURI().toURL().toExternalForm()));
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setResizable(false);
        janela.show();
    }

    /**
     * 
     *
     * Método para criar a janela para configuração das teclas de atalho do
     * cliente torrent.
     *
     * @throws IOException Se o arquivo FXML não puder ser carregado.<br>
     *
     * 
     */
    @FXML
    public void abrir_janela_atalhos() throws IOException {
        Parent componentes = FXMLLoader.load(
                getClass().getResource(URL_FXML_TECLAS_DE_ATALHO));
        Scene cena = new Scene(componentes);
        Stage janela = new Stage();

        janela.setTitle(JANELA_TECLAS_DE_ATALHO_TITULO1);
        janela.setHeight(JANELA_TECLAS_DE_ATALHO_TAMANHO1.Y);
        janela.setWidth(JANELA_TECLAS_DE_ATALHO_TAMANHO1.X);
        janela.setScene(cena);
        janela.getIcons().add(new Image(new File(URL_ICONE1).toURI().toURL().toExternalForm()));
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setResizable(false);
        janela.show();
    }

    /**
     * 
     *
     * Método para criar abrir uma nova aba no navegador padrão do usuário com o
     * site para ajuda.
     *
     * 
     */
    /**
     * 
     *
     * Método para criar a janela para configuração das teclas de atalho do
     * cliente torrent.
     *
     * @throws IOException Se o arquivo FXML não puder ser carregado.<br>
     *
     * 
     */
    @FXML
    public void abrir_janela_configuracoes() throws IOException {
        Parent componentes = FXMLLoader.load(
                getClass().getResource(URL_FXML_JANELA_CONFIGURACOES));
        Scene cena = new Scene(componentes);
        Stage janela = new Stage();

        janela.setTitle(JANELA_CONFIGURACOES_TITULO);
        janela.setScene(cena);
        janela.getIcons().add(new Image(new File(URL_ICONE1).toURI().toURL().toExternalForm()));
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setResizable(false);
        janela.show();
    }

    /**
     * 
     *
     * Método para criar abrir uma nova aba no navegador padrão do usuário com o
     * site para ajuda.
     *
     * 
     */
    @FXML
    public void ir_para_site_de_ajuda() {
        Comunicador.getMain().getHostServices().showDocument(URL_WIKI);
    }

    /**
     * 
     *
     * Método para fazer um tour pelo programa.
     *
     * 
     */
    @FXML
    public void fazer_tour() {
        ApresentarSoftware.comecar();
    }

    /**
     * 
     *
     * Método para criar a janela para o usuário sugerir uma melhoria ou relatar
     * um erro.
     *
     * @throws IOException Se o arquivo FXML não puder ser carregado.<br>
     *
     * 
     */
    @FXML
    public void abrir_janela_sugestao() throws IOException {
        Parent componentes = FXMLLoader.load(
                getClass().getResource(URL_FXML_JANELA_SUGESTAO));
        Scene cena = new Scene(componentes);
        Stage janela = new Stage();

        janela.setTitle(JANELA_SUGESTAO_TITULO);
        janela.setHeight(JANELA_SUGESTAO_TAMANHO.Y);
        janela.setWidth(JANELA_SUGESTAO_TAMANHO.X);
        janela.setScene(cena);
        janela.getIcons().add(new Image(new File(URL_ICONE1).toURI().toURL().toExternalForm()));
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setResizable(false);
        janela.show();
    }

    @FXML
    private void verificar_arquivo() {
        FileChooser escolherArquivo = new FileChooser();
        File arquivoSelecionado = escolherArquivo.showOpenDialog(JanelaPrincipal.stage_principal);

        if (arquivoSelecionado != null) {
            try {
                Torrent.load(arquivoSelecionado);
                Auxiliar.criar_dialogo(Alert.AlertType.CONFIRMATION, "Informação", "Verificação de arquivo", "Verificação bem sucedida");
            } catch (Exception e) {
                Logger.escrever_erro(e);
                Auxiliar.criar_dialogo(Alert.AlertType.ERROR, "Erro", "Verificação de arquivo", "Verificação mal sucedida");
            }
        }
    }
    
    public HBox getPainel(){
        return painel;
    }

}
