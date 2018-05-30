package iftorrent.gui.barraDeFerramentas;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import iftorrent.ConstantesGerais;
import iftorrent.JanelaPrincipal;
import static iftorrent.arquivos.ConstantesLogger.ERRO;
import static iftorrent.arquivos.ConstantesLogger.EXECUCAO;
import iftorrent.arquivos.Logger;
import iftorrent.arquivos.torrent.Torrent;
import iftorrent.daos.ArquivoUsuario.ArquivoUsuario;
import iftorrent.daos.ArquivoUsuario.DaoArquivoUsuario;
import iftorrent.daos.SSH;
import iftorrent.daos.daoArquivo.ArquivoTorrent;
import iftorrent.daos.daoArquivo.DaoArquivo;
import iftorrent.excecoes.BancoDeDadosExcecao;
import static iftorrent.gui.barraDeFerramentas.BarraDeFerramentasConstantes.*;
import iftorrent.gui.ferramentas.Auxiliar;
import iftorrent.gui.ferramentas.CampoDeTextoOuvinte;
import iftorrent.gui.ferramentas.Comunicador;
import iftorrent.gui.ferramentas.ItemMenu;
import static iftorrent.gui.ferramentas.ManipuladorImagem.criar_imagem_tratando_erro;
import static iftorrent.gui.menuPrincipal.ConstantesMenuPrincipal.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * 
 *
 * A classe <b>BarraDeFerramentasControlador</b> serve para controlar a
 * interface da Barra de Ferramentas.
 * <p>
 * Esta classe implementa <b>Initializable</b>.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class BarraDeFerramentasControlador implements Initializable {

    /**
     * 
     *
     * Leiaute da Barra de Ferramentas.
     *
     * 
     */
    @FXML
    private BorderPane painel;

    /**
     * 
     *
     * Campo de texto para pesquisas.
     *
     * 
     */
    @FXML
    private TextField campo_de_texto;

    /**
     * 
     *
     * Botão para iniciar/continuar o download/upload do arquivo.
     *
     * 
     */
    @FXML
    private Button botao_iniciar;

    /**
     * 
     *
     * Botão para pausar o download/upload do arquivo.
     *
     * <div>
     */
    @FXML
    private Button botao_pausar;

    /**
     * 
     *
     * Botão para parar o download/upload do arquivo.
     *
     * 
     */
    @FXML
    private Button botao_parar;

    /**
     * 
     *
     * Botão para subir o arquivo na fila de downloads.
     *
     * 
     */
    @FXML
    private Button botao_subir_fila;

    /**
     * 
     *
     * Botão para descer o arquivo na fila de downloads
     *
     * 
     */
    @FXML
    private Button botao_descer_fila;

    /**
     * 
     *
     * Botão para remover um arquivo.
     *
     * 
     */
    @FXML
    private Button botao_remover;

    /**
     * 
     *
     * Botão para adicionar um arquivo.
     *
     * 
     */
    @FXML
    private Button botao_adicionar_arquivo;

    /**
     * 
     *
     * Botão para adicionar um arquivo através de um link magnético.
     *
     * 
     */
    @FXML
    private Button botao_link_magnetico;

    /**
     * 
     *
     * Botão para gerar um arquivo torrent para outro arquivo.
     *
     * 
     */
    @FXML
    private Button botao_gerar_torrent;

    /**
     * 
     *
     * Visualizador de imagem.
     *
     * 
     */
    @FXML
    private MenuButton icone_barra_de_pesquisa;
    
    /**
     * 
     *
     * Barra de pesquisa com o icone e o textfield;
     *
     * 
     */
    @FXML
    private BorderPane painel_pesquisa_logoff;

    /**
     * 
     *
     * Conjunto de todos manipuladores de itens de menu.
     *
     * 
     */
    private final ArrayList<ItemMenu> itens = new ArrayList<>();

    /**
     * 
     *
     * Método do evento de ação.
     *
     * 
     */
    @FXML
    private void acao_iniciar() {
        if (!Comunicador.getMenu_principal().reiniciar_torrent()) {
            Logger.escrever(EXECUCAO, AVISO_TORRENT_NAO_REINICIADO);
            Auxiliar.criar_dialogo(Alert.AlertType.ERROR,
                    "Erro",
                    "Arquivo não encontrado",
                    "Não foi possível reiniciar o arquivo torrent.");
        }
    }

    /**
     * 
     *
     * Método do evento de ação.
     *
     * 
     */
    @FXML
    public void acao_pausar() {
        if (!Comunicador.getMenu_principal().pausar_torrent()) {
            Logger.escrever(EXECUCAO, AVISO_TORRENT_NAO_PAUSADO);
            Auxiliar.criar_dialogo(Alert.AlertType.ERROR,
                    "Erro",
                    "Arquivo não encontrado ou não ativo",
                    "Não foi possível pausar o arquivo torrent.");
        }
    }

    /**
     * 
     *
     * Método do evento de ação.
     *
     * 
     */
    @FXML
    public void acao_parar() {
        if (!Comunicador.getMenu_principal().parar_torrent()) {
            Logger.escrever(EXECUCAO, AVISO_TORRENT_NAO_PARADO);
            Auxiliar.criar_dialogo(Alert.AlertType.ERROR,
                    "Erro",
                    "Arquivo não encontrado ou não ativo",
                    "Não foi possível parar o arquivo torrent.");
        }
    }

    /**
     * 
     *
     * Método do evento de ação.
     *
     * 
     */
    @FXML
    public void acao_subir_fila() {
        Comunicador.getMenu_principal().subir_item();
    }

    /**
     * 
     *
     * Método do evento de ação.
     *
     * 
     */
    @FXML
    public void acao_descer_fila() {
        Comunicador.getMenu_principal().descer_item();
    }

    /**
     * 
     *
     * Método para apagar um arquivo torrent da tabela e fechar o cliente.
     *
     * 
     */
    @FXML
    public void acao_remover() {
        if (!Comunicador.getMenu_principal().remover_torrents_selecionados()) {
            Logger.escrever(EXECUCAO, AVISO_TORRENT_NAO_REMOVIDO);
            Auxiliar.criar_dialogo(Alert.AlertType.ERROR,
                    "Erro",
                    "Arquivo não encontrado",
                    "Não foi possível remover o arquivo torrent.");
        }
    }

    /**
     * 
     *
     * Método para abrir FileChooser que irá abrir um arquivo torrent.
     *
     * 
     */
    @FXML
    public void acao_adicionar_arquivo() {
        Stage janela = new Stage();
        File pasta = new File(ConstantesGerais.PASTA_DOWNLOADS);
        if (!pasta.exists()) {
            Auxiliar.criar_dialogo(Alert.AlertType.ERROR,
                    "Erro",
                    "Pasta de download nao encontrada",
                    "Selecione uma válida na janela de configurações gerais");
            return;
        }
        List<File> arquivos_selecionado = Auxiliar.abre_seletor_arquivos((Window) janela,
                "Arquivos Torrent",
                ".torrent",
                new File(ConstantesGerais.PASTA_DOWNLOADS));

        if (arquivos_selecionado == null) {
            return;
        }
        for(File arquivo_selecionado : arquivos_selecionado) {
            if (!Torrent.abre_torrent(arquivo_selecionado)) {
                Logger.escrever(ERRO, "Erro ao adicionar o arquivo torrent");
                Auxiliar.criar_dialogo(Alert.AlertType.ERROR,
                        "Erro",
                        "Arquivo não encontrado",
                        "Não foi possível adicionar o arquivo torrent.");
            }
        }
    }

    /**
     * 
     *
     * Método para abrir um magnet link.
     *
     * 
     */
    @FXML
    public void acao_link_magnetico() {
        /*    TextInputDialog entrada_texto = new TextInputDialog("Informe o magnet link");
        Optional<String> texto = entrada_texto.showAndWait();

        if (texto.isPresent()) {
            //String magnet_uri = texto.get();
            String magnet_uri = "magnet:?xt=urn:btih:6d59397900aa052a9b64a9dc0904205d3cd787e3&dn=LibreOffice_5.4.3_MacOS_x86-64.dmg";

            try {
                Torrent.abre_magnet_link(magnet_uri);
            } catch (UnknownHostException ex) {
                Logger.escrever_erro(ex);
                Alert aviso = new Alert(Alert.AlertType.ERROR);

                aviso.setHeaderText("Arquivo não encontrado");
                aviso.setContentText("Não foi possível adicionar o arquivo torrent.");
                aviso.showAndWait();
            }
        }
         */
    }

    /**
     * 
     *
     * Método para criar um arquivo torrent a partir de arquivos do computador.
     *
     * 
     */
    @FXML
    public void acao_gerar_torrent() {
        List<File> arquivos = Auxiliar.abre_seletor_arquivos(JanelaPrincipal.stage_principal,
                "Todos Arquivos",
                "*.*",
                new File(ConstantesGerais.DIRETORIO_ATUAL));

        if (arquivos == null) {
            return;
        }
        TextInputDialog caixa_entrada_texto = new TextInputDialog();
        List lista_trackers = new ArrayList();
        //posteriormente pode ser transferido à um arquivo 
        lista_trackers.add("udp://tracker.torrent.eu.org:451");
        lista_trackers.add("udp://z.crazyhd.com:2710/announce");
        lista_trackers.add("udp://tracker.acg.gg:2710/announce");
        lista_trackers.add("udp://tracker.coppersurfer.tk:6969");
        lista_trackers.add("udp://104.238.198.186:8000/announce");
        lista_trackers.add("udp://tc.animereactor.ru:8082/announce");
        lista_trackers.add("udp://tc.animereactor.ru:8082/announce");
        try {
            lista_trackers.add("udp://" + InetAddress.getLocalHost() + ":50230/announce");
        } catch (UnknownHostException ex) {
            Logger.escrever_erro(ex);
        }
        caixa_entrada_texto.setContentText("Qual o nome do novo arquivo torrent?");
        caixa_entrada_texto.showAndWait().ifPresent(valor -> {
            try {
                if (valor.isEmpty()) {
                    valor = "noNamed";
                }
                if (!valor.contains(".torrent")) {
                    valor += ".torrent";
                }
                File arquivo = new File(valor);

                Torrent.cria_torrent(
                        arquivo,
                        arquivos,
                        (ArrayList) lista_trackers
                );
                try {
                    SSH.envia_arquivo_servidor(valor);
                } catch (JSchException | SftpException ex) {
                    Logger.escrever_erro(ex);
                    Auxiliar.criar_dialogo(Alert.AlertType.ERROR,
                            "Erro",
                            "Conexão SSH",
                            "Não foi possível cadastrar o arquivo torrent no site");
                }
                //cadastra arquivo torrent no BD
                DaoArquivo dao = new DaoArquivo();

                dao.adicionar(new ArquivoTorrent(arquivo.getName(), arquivo.getTotalSpace(), ""));
                //cadastra ligação entre arquivo e usuario no BD
                DaoArquivoUsuario dao2 = new DaoArquivoUsuario();
                
                dao2.adicionar(new ArquivoUsuario(
                        dao.obtem_id(arquivo.getName()), 
                        JanelaPrincipal.usuario)
                );
                Auxiliar.criar_dialogo(Alert.AlertType.INFORMATION,
                        "Informação",
                        "",
                        "Arquivo torrent criado com sucesso!");
            } catch (IOException ex) {
                Logger.escrever_erro(ex);
                Auxiliar.criar_dialogo(Alert.AlertType.INFORMATION,
                        "Erro",
                        "Arquivo",
                        "Não foi possível salvar o Arquivo torrent.");
            } catch (BancoDeDadosExcecao ex) {
                Logger.escrever_erro(ex);
                Auxiliar.criar_dialogo(Alert.AlertType.INFORMATION,
                        "Erro",
                        "Banco de Dados",
                        "Não foi possível cadastrar o Arquivo torrent no site.");
            }
        });
    }

    /**
     * 
     *
     * Método do evento de ação.
     *
     * 
     */
    @FXML
    public void acao_pesquisar() {
        System.out.println("teste10");
    }

    /**
     * 
     *
     * Método para inicializar os elementos da interface da Barra de
     * Ferramentas.
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
        Comunicador.setBarra_de_ferramentas(this);
        this.botao_iniciar.setTooltip(new Tooltip("Iniciar"));
        this.botao_iniciar.setGraphic(
                criar_imagem_tratando_erro(URL_INICIAR, IMAGEM_TAMANHO1));
        this.botao_pausar.setTooltip(new Tooltip("Pausar"));
        this.botao_pausar.setGraphic(
                criar_imagem_tratando_erro(URL_PAUSAR, IMAGEM_TAMANHO1));
        this.botao_parar.setTooltip(new Tooltip("Parar"));
        this.botao_parar.setGraphic(
                criar_imagem_tratando_erro(URL_PARAR, IMAGEM_TAMANHO1));
        this.botao_subir_fila.setTooltip(new Tooltip("Subir fila"));
        this.botao_subir_fila.setGraphic(
                criar_imagem_tratando_erro(URL_SUBIR_FILA, IMAGEM_TAMANHO1));
        this.botao_descer_fila.setTooltip(new Tooltip("Descer fila"));
        this.botao_descer_fila.setGraphic(
                criar_imagem_tratando_erro(URL_DESCER_FILA, IMAGEM_TAMANHO1));
        this.botao_remover.setTooltip(new Tooltip("Remover"));
        this.botao_remover.setGraphic(
                criar_imagem_tratando_erro(URL_REMOVER, IMAGEM_TAMANHO1));
        this.botao_adicionar_arquivo.setTooltip(new Tooltip("Adicionar arquivo"));
        this.botao_adicionar_arquivo.setGraphic(
                criar_imagem_tratando_erro(URL_ADICIONAR_ARQUIVO, IMAGEM_TAMANHO1));
        this.botao_link_magnetico.setTooltip(new Tooltip("Link magnético"));
        this.botao_link_magnetico.setGraphic(
                criar_imagem_tratando_erro(URL_LINK_MAGNETICO, IMAGEM_TAMANHO1));
        this.botao_gerar_torrent.setTooltip(new Tooltip("Gerar torrent"));
        this.botao_gerar_torrent.setGraphic(
                criar_imagem_tratando_erro(URL_GERAR_TORRENT, IMAGEM_TAMANHO1));
        this.icone_barra_de_pesquisa.setGraphic(
                criar_imagem_tratando_erro(URL_PESQUISAR, IMAGEM_TAMANHO2));
        CustomMenuItem adicionador_de_menus = new CustomMenuItem();
        TextField campo_de_texto_menu = new TextField();

        adicionador_de_menus.setContent(campo_de_texto_menu);
        adicionador_de_menus.setHideOnClick(false);
        adicionador_de_menus.setOnAction(evento -> {
            campo_de_texto_menu.requestFocus();
        });
        campo_de_texto_menu.setOnAction(evento -> {
            itens.add(criar_novo_item(campo_de_texto_menu.getText()));
            campo_de_texto_menu.clear();
        });
        campo_de_texto_menu.focusedProperty().addListener(
                new CampoDeTextoOuvinte(campo_de_texto_menu, TEXTO_COR1,
                        TEXTO_COR2, CAMPO_DE_TEXTO_CONTEUDO1,
                        CAMPO_DE_TEXTO_CONTEUDO3));
        this.icone_barra_de_pesquisa.getItems().add(adicionador_de_menus);
        this.campo_de_texto.focusedProperty().addListener(
                new CampoDeTextoOuvinte(this.campo_de_texto, TEXTO_COR1,
                        TEXTO_COR2, CAMPO_DE_TEXTO_CONTEUDO1,
                        CAMPO_DE_TEXTO_CONTEUDO2));
        this.formatar(this.botao_iniciar);
        this.formatar(this.botao_pausar);
        this.formatar(this.botao_parar);
        this.formatar(this.botao_subir_fila);
        this.formatar(this.botao_descer_fila);
        this.formatar(this.botao_remover);
        this.formatar(this.botao_adicionar_arquivo);
        this.formatar(this.botao_link_magnetico);
        this.formatar(this.botao_gerar_torrent);
        this.formatar(this.campo_de_texto);
        this.painel.getStylesheets().add(URL_CSS1);

        botao_pausar.setDisable(true); //NAO FUNCIONA PRA ESCREVER EM ARQUIVO, FUTURAMENTE ARRUMAR (OU TENTAR PELO MENOS)
        botao_parar.setDisable(true);
        botao_iniciar.setDisable(true);
    }

    /**
     * 
     *
     * Formata as dimensões de um <b>Button</b> com valores padrão.
     *
     * @param painel Um objeto do tipo <b>Button</b> que representa o objeto
     * para formatação.<br>
     *
     * 
     */
    private void formatar(Button botao) {
        botao.setPrefSize(BOTAO_TAMANHO1.X, BOTAO_TAMANHO1.Y);
    }

    /**
     * 
     *
     * Formata as dimensões de um <b>TextField</b> com valores padrão.
     *
     * @param painel Um objeto do tipo <b>TextField</b> que representa o objeto
     * para formatação.<br>
     *
     * 
     */
    private void formatar(TextField campo_de_texto) {
        campo_de_texto.setPrefSize(CAMPO_DE_TEXTO_TAMANHO1.X, CAMPO_DE_TEXTO_TAMANHO1.Y);
    }

    /**
     * 
     *
     * Método para criar um item e seu manipulador com uma formatação padrão.
     *
     * @param texto Um objeto do tipo <b>String</b> que representa o conteúdo do
     * item.<br>
     *
     * @return Um objeto do tipo <b>ItemMenu</b> que representa o manipulador do
     * item.<br>
     *
     * 
     */
    public ItemMenu contruir_item(String texto) {
        ItemMenu item = new ItemMenu();
        CheckBox caixa = new CheckBox();
        final Button botao_sair = new Button("", new Label("X"));

        caixa.setOnKeyPressed(evento -> {
            if (evento.getCode().equals(KeyCode.ENTER)) {
                caixa.fire();
            }
        });
        botao_sair.setOnKeyPressed(evento -> {
            if (evento.getCode().equals(KeyCode.ENTER)) {
                botao_sair.fire();
            }
        });
        botao_sair.setOnMouseEntered(evento -> {
            botao_sair.setStyle("-fx-background-color: " + BOTAO_COR1 + ";");
        });
        botao_sair.setOnMouseExited(evento -> {
            botao_sair.setStyle("-fx-background-color: " + BOTAO_COR2 + ";");
        });
        botao_sair.setMinSize(BOTAO_TAMANHO2.X, BOTAO_TAMANHO2.Y);
        botao_sair.setPrefSize(BOTAO_TAMANHO2.X, BOTAO_TAMANHO2.Y);
        botao_sair.setMaxSize(BOTAO_TAMANHO2.X, BOTAO_TAMANHO2.Y);
        botao_sair.setStyle("-fx-background-color: " + BOTAO_COR2 + ";");
        item.setar_centro(new Label(texto));
        item.setar_esquerda(caixa);
        item.setar_direita(botao_sair);
        return item;
    }

    /**
     * 
     *
     * Método para criar um novo manipulador de item.
     *
     * @param texto Um objeto do tipo <b>String</b> que representa o conteúdo do
     * item. <br>
     *
     * @return Um objeto do tipo <b>ItemMenu</b> que representa o manipulador do
     * item.<br>
     *
     * 
     */
    public ItemMenu criar_novo_item(String texto) {
        ItemMenu manipulador = this.contruir_item(texto);
        CustomMenuItem item = new CustomMenuItem(manipulador.contruir_item(), false);

        this.icone_barra_de_pesquisa.getItems().add(
                this.icone_barra_de_pesquisa.getItems().size() - 1, item);
        ((Button) manipulador.pegar_direita()).setOnAction(evento -> {
            this.icone_barra_de_pesquisa.getItems().remove(item);
            itens.remove(manipulador);
        });
        return manipulador;
    }

    /**
     * 
     *
     * Método para pegar a tag que o item representa.
     *
     * @param manipulador Um objeto do tipo <b>ItemMenu</b> que representa o
     * manipulador do item.<br>
     *
     * @return Um objeto do tipo <b>String</b> que representa a tag que o item
     * representa.
     *
     * 
     */
    public String conteudo_do_item(ItemMenu manipulador) {
        return ((Label) manipulador.pegar_centro()).getText();
    }

    /**
     * 
     *
     * Método para determir se um item do menu foi selecionado.
     *
     * @param manipulador Um objeto do tipo <b>ItemMenu</b> que representa o
     * manipulador do item.<br>
     *
     * @return Um booleano que representa se a caixa de seeção está ou não
     * selecionada.<br>
     *
     * 
     */
    public boolean esta_selecionado(ItemMenu manipulador) {
        return ((CheckBox) manipulador.pegar_esquerda()).isSelected();
    }

    /**
     * 
     *
     * Método para pegar todas as tags selecionadas para a pesquisa.
     *
     * @return Um objeto do tipo <b>ArrayList{@code <String>}</b> que representa
     * a lista das tags selecionadas para a pesquisa.<br>
     *
     * 
     */
    public ArrayList<String> pegar_tags() {
        ArrayList<String> tags = new ArrayList<>();

        itens.stream().filter((m) -> (this.esta_selecionado(m))).forEachOrdered((m) -> {
            tags.add(this.conteudo_do_item(m));
        });
        return tags;
    }

    public BorderPane getPainel() {
        return painel;
    }
    
    public Button[] getBotoes(){
        return new Button[]{botao_iniciar, botao_pausar, botao_parar, botao_subir_fila,
            botao_descer_fila, botao_remover, botao_adicionar_arquivo, botao_link_magnetico, botao_gerar_torrent};
    }
    
    public BorderPane getPesquisa(){
        return painel_pesquisa_logoff;
    }
}
