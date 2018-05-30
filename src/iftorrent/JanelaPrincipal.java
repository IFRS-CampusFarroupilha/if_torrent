package iftorrent;

import com.sun.javafx.application.LauncherImpl;
import com.turn.ttorrent.client.Client;
import static iftorrent.ConstantesGerais.*;
import iftorrent.arquivos.Logger;
import iftorrent.arquivos.torrent.Torrent;
import static iftorrent.arquivos.torrent.Torrent.cria_arquivo_propriedade_torrents;
import iftorrent.conexao.Rastreador;
import iftorrent.excecoes.ESExcecao;
import static iftorrent.gui.ferramentas.Auxiliar.criar_dialogo;
import static iftorrent.gui.ferramentas.Auxiliar.criar_thread;
import iftorrent.gui.ferramentas.Comunicador;
import static iftorrent.gui.janelaConfiguracoes.ConstantesJanelaConfiguracoes.NOME_ARQUIVO_DIRETORIOS;
import iftorrent.gui.janelaConfiguracoes.Diretorio;
import iftorrent.gui.janelaLogin.JanelaLoginControlador;
import static iftorrent.gui.janelaPrincipal.ConstantesJanelaPrincipal.*;
import java.io.EOFException;
import java.io.File;
import static java.io.File.separator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import the8472.mldht.Launcher;

/**
 * Janela Teste
 *
 * @author Eduardo Toffolo
 */
public class JanelaPrincipal extends Application {

    public static String usuario;
    public static Stage stage_principal;
    public static ArrayList<Client> clientes = new ArrayList();
    public static List<String> linhas = new ArrayList();
    public static Rastreador rastreador;
    public static Launcher lancador_dht;
    public Thread thread_lancador_dht;
    public static Pane pane_principal;

    @Override
    public void init() throws Exception {
        super.init();
        try {
            try {
                Logger.abrir_log();
            } catch (ESExcecao ex) {
            }
            File pasta_torrents = new File(PASTA_TORRENTS);
            JanelaPrincipal.rastreador = Rastreador.novo_rastreador(MEU_ENDERECO_IP_LOCAL, PORTA_DEDICADA_RASTREADOR, PASTA_TORRENTS);
            if (!pasta_torrents.exists()) {
                pasta_torrents.mkdir();
            }
            File diretorios_salvos = new File(NOME_ARQUIVO_DIRETORIOS + EXTENSAO_ARQUIVOS);

            if (!diretorios_salvos.exists()) {
                diretorios_salvos.createNewFile();
                File arquivo_diretorios = new File(NOME_ARQUIVO_DIRETORIOS + EXTENSAO_ARQUIVOS);

                try (ObjectOutputStream out = new ObjectOutputStream(
                        new FileOutputStream(arquivo_diretorios));) {
                    Diretorio d2 = new Diretorio(DIRETORIO_ATUAL);

                    out.writeObject(d2);
                }
            } else {
                try (ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(diretorios_salvos));) {
                    Diretorio d;

                    do {
                        d = (Diretorio) ois.readObject();
                        if (d != null) {
                            PASTA_DOWNLOADS = d.getDiretorio();
                        }
                    } while (d != null);
                } catch (IOException | ClassNotFoundException ex) {
                    if (!(ex instanceof EOFException)) {
                        Logger.escrever_erro(ex);
                    }
                }
            }
            File arquivo = new File(DIRETORIO_ATUAL + NOME_ARQUIVO + EXTENSAO_ARQUIVOS);

            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            linhas = Files.readAllLines(arquivo.toPath(), StandardCharsets.UTF_8);
            if (linhas.isEmpty()) {
                LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(100));
            }
            for (int i = 0; i < linhas.size(); i++) {
                double progresso = (100 * i) / linhas.size();

                LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progresso));
                Torrent.abre_torrent(new File(PASTA_TORRENTS + separator
                        + linhas.get(i)
                        + ".torrent"));
            }
            this.thread_lancador_dht = new Thread(() -> {
                try {
                    this.lancador_dht.main(null);
                } catch (Exception ex) {
                    iftorrent.arquivos.Logger.escrever_erro(ex);
                }
            });
            this.thread_lancador_dht.start();
        } catch (IOException ex) {
            Logger.escrever_erro(ex);
        }
    }

    @Override
    public void stop() {
        Comunicador.getAbas_propriedades().matar_thread_selecao_itens();
        Comunicador.getAbas_propriedades().matar_thread_grafico();
        Comunicador.getAbas_propriedades().matar_thread_mapa_pedacos();
        Comunicador.getAbas_propriedades().matar_thread_info();
        Comunicador.getMenu_principal().selecionar_torrents();
        apaga_arquivo_antigo_torrents();
        for (int i = 0; i < clientes.size(); i++) {
            Client cliente = clientes.get(i);

            System.out.println(cliente);
            Torrent.salva_nome_torrent_em_arquivo(cliente);
            criar_thread(() -> {
                cliente.stop();
            });
        }
        if (!Logger.fechar_log()) {
            criar_dialogo(Alert.AlertType.ERROR,
                    "",
                    "Erro",
                    "Erro ao fechar o arquivo de log.");
        }
        if (stage_principal != null) {
            stage_principal.close();
        }
        System.exit(CODIGO_PROGRAMA_FECHADO_SUCESSO);
    }

    public void apaga_arquivo_antigo_torrents() {
        File arquivo_velho = new File(DIRETORIO_ATUAL + NOME_ARQUIVO + EXTENSAO_ARQUIVOS);

        arquivo_velho.delete();
        try {
            arquivo_velho.createNewFile();
        } catch (IOException ex) {
            Logger.escrever_erro(ex);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Stage janela_login = JanelaLoginControlador.criarJanela();

            //janela_login.showAndWait();
        } catch (IOException e) {
            Logger.escrever_erro(e);
        }
        if (Comunicador.getJanelaLogin().entrou || true) {
            stage_principal = stage;
            Comunicador.setMain(this);
            Parent root = FXMLLoader.load(getClass().getResource("/iftorrent/gui/janelaPrincipal/JanelaPrincipalFXML.fxml"));
            pane_principal = new Pane(root);
            stage_principal.widthProperty().addListener((observable, oldValue, newValue) -> {
                Comunicador.getJanela_principal().getPainel().setPrefWidth(newValue.doubleValue()-16);
            });
            stage_principal.heightProperty().addListener((observable, oldValue, newValue) -> {
                Comunicador.getJanela_principal().getPainel().setPrefHeight(newValue.doubleValue()-38);
            });
            
            Scene scene = new Scene(pane_principal);

            stage.setTitle(JANELA_TITULO1);
            stage.getIcons().add(new Image(new File(URL_ICONE1).toURI().toURL().toExternalForm()));
            stage.setScene(scene);
            Comunicador.getAbas_propriedades().iniciar_thread_selecao_itens();
            Comunicador.getAbas_propriedades().iniciar_thread_grafico();
            Comunicador.getAbas_propriedades().iniciar_thread_mapa_pedacos();
            Comunicador.getAbas_propriedades().iniciar_thread_info();
            stage.show();
        }
        obter_torrents_existentes();
    }

    /**
     * Percorre ArrayList estático clientes da classe JanelaPrincipal e cria
     * objetos ArquivoPropriedadesTorrents
     */
    private void obter_torrents_existentes() throws IOException, NoSuchAlgorithmException {
        for (int i = 0; i < clientes.size(); i++) {
            cria_arquivo_propriedade_torrents(clientes.get(i));
        }
        JanelaPrincipal.rastreador.iniciar();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LauncherImpl.launchApplication(JanelaPrincipal.class, PreCarregador.class, args);
    }
}
