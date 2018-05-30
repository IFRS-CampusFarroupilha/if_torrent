package iftorrent.gui.janelaConfiguracoes;

import iftorrent.ConstantesGerais;
import static iftorrent.ConstantesGerais.*;
import iftorrent.arquivos.Logger;
import iftorrent.gui.ferramentas.Comunicador;
import iftorrent.gui.ferramentas.ItemMenu;
import static iftorrent.gui.janelaConfiguracoes.ConstantesJanelaConfiguracoes.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author 05200195
 */
public class JanelaConfiguracoesControlador implements Initializable {

    @FXML
    MenuButton diretorios;
    @FXML
    MenuButton privacidade;
    @FXML
    Button salvar, usar_recomendadas;
    @FXML
    CheckBox segundo_plano;
    @FXML
    TextField taxa_download, taxa_upload;

    public final ArrayList<ItemMenu> itens_diretorios = new ArrayList<>();
    public final ArrayList<ItemMenu> itens_privacidade = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Comunicador.setJanela_configuracoes(this);
        CustomMenuItem adicionar_diretorio = new CustomMenuItem(new Label("Modificar diretorio"));

        diretorios.getItems().add(adicionar_diretorio);
        itens_privacidade.add(criar_novo_item_simples("IP"));
        itens_privacidade.add(criar_novo_item_simples("Nome"));
        itens_privacidade.add(criar_novo_item_simples("Usuário"));
        ItemMenu.setar_largura_padrao(TAMANHO_PADRAO);
        adicionar_diretorio.setHideOnClick(false);
        adicionar_diretorio.setOnAction(evento -> {
            DirectoryChooser selecionar_diretorio = new DirectoryChooser();
            File diretorio_selecionado = selecionar_diretorio.showDialog(diretorios.getScene().getWindow());

            if (diretorio_selecionado != null) {
                if (!itens_diretorios.isEmpty()) {
                    itens_diretorios.remove(0);
                    diretorios.getItems().remove(0);
                }
                itens_diretorios.add(criar_novo_item(diretorio_selecionado.getAbsolutePath()));
                ConstantesGerais.PASTA_DOWNLOADS = diretorio_selecionado.getAbsolutePath();
            }
        });

        try {
            inicializar();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.escrever_erro(ex);
        }
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
        ItemMenu manipulador = contruir_item(texto);
        CustomMenuItem item = new CustomMenuItem(manipulador.contruir_item(), false);

        diretorios.getItems().add(0, item);
        ((Button) manipulador.pegar_direita()).setOnAction(evento -> {
            diretorios.getItems().remove(item);
            itens_diretorios.remove(manipulador);
            ConstantesGerais.PASTA_DOWNLOADS = texto;
            itens_diretorios.add(criar_novo_item(texto));
        });
        return manipulador;
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
        final Button botao_sair = new Button("", new Label("X"));

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
        item.setar_direita(botao_sair);
        return item;
    }

    public ItemMenu contruir_item_simples(String texto) {
        ItemMenu item = new ItemMenu();
        CheckBox caixa = new CheckBox();

        item.setar_esquerda(new Label(texto));
        item.setar_direita(caixa);
        return item;
    }

    public ItemMenu criar_novo_item_simples(String texto) {
        ItemMenu manipulador = this.contruir_item_simples(texto);
        CustomMenuItem item = new CustomMenuItem(manipulador.contruir_item(), false);

        privacidade.getItems().add(item);
        return manipulador;
    }

    @FXML
    public void salvar() throws IOException {
        Double download = 0.0,
                upload = 0.0;

        if (!taxa_download.getText().isEmpty()) {
            download = Double.parseDouble(taxa_download.getText());
        }
        if (!taxa_upload.getText().isEmpty()) {
            upload = Double.parseDouble(taxa_upload.getText());
        }
        ArrayList<Boolean> privacidades_segundoplano = new ArrayList();

        for (int i = 0; i < 3; i++) {
            if (((CheckBox) itens_privacidade.get(i).pegar_direita()).isSelected()) {
                privacidades_segundoplano.add(true);
            } else {
                privacidades_segundoplano.add(false);
            }
        }
        if (segundo_plano.isSelected()) {
            privacidades_segundoplano.add(true);
        } else {
            privacidades_segundoplano.add(false);
        }
        Configuracao config = new Configuracao(download, upload,
                privacidades_segundoplano.get(3),
                privacidades_segundoplano.get(0),
                privacidades_segundoplano.get(1),
                privacidades_segundoplano.get(2));

        deletar_arquivo(NOME_ARQUIVO_CONFIGURACOES + EXTENSAO_ARQUIVOS);
        escrever_arquivo_configuracoes(NOME_ARQUIVO_CONFIGURACOES
                + EXTENSAO_ARQUIVOS, config);
        deletar_arquivo(NOME_ARQUIVO_DIRETORIOS + EXTENSAO_ARQUIVOS);
        escrever_arquivo_diretorio(NOME_ARQUIVO_DIRETORIOS + EXTENSAO_ARQUIVOS);
        ((Stage) salvar.getScene().getWindow()).close();
    }

    private void escrever_arquivo_configuracoes(String diretorio, Configuracao config) throws IOException {
        File arquivo_novo = new File(diretorio);

        try (ObjectOutputStream ois = new ObjectOutputStream(
                new FileOutputStream(arquivo_novo));) {
            ois.writeObject(config);
        }
    }

    public void escrever_arquivo_diretorio(String caminho) throws IOException {
        File arquivo_diretorios = new File(caminho);

        try (ObjectOutputStream ois = new ObjectOutputStream(
                new FileOutputStream(arquivo_diretorios));) {
            Diretorio d;

            if (!itens_diretorios.isEmpty()) {
                for (ItemMenu i : itens_diretorios) {
                    d = new Diretorio(((Label) i.pegar_centro()).getText());
                    ois.writeObject(d);
                }
            }
        }
    }

    public boolean deletar_arquivo(String caminho) {
        File arquivo = new File(caminho);

        return arquivo.delete();
    }

    public void inicializar() throws IOException, ClassNotFoundException {
        inicializar_restante();
        inicializar_diretorios();
    }

    public void inicializar_restante() {
        File restante = new File(NOME_ARQUIVO_CONFIGURACOES + EXTENSAO_ARQUIVOS);

        if (!restante.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(restante));) {
            Configuracao config = (Configuracao) ois.readObject();

            setarConfiguracoes(config);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.escrever_erro(ex);
        }
    }

    public void inicializar_diretorios() {
        File diretorios_salvos = new File(NOME_ARQUIVO_DIRETORIOS + EXTENSAO_ARQUIVOS);

        if (!diretorios_salvos.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(diretorios_salvos));) {
            Diretorio d;

            do {
                d = (Diretorio) ois.readObject();
                if (d != null) {
                    itens_diretorios.add(criar_novo_item(d.getDiretorio()));
                }
            } while (d != null);
        } catch (IOException | ClassNotFoundException ex) {
            if (!(ex instanceof EOFException)) {
                Logger.escrever_erro(ex);
            }
        }
    }

    private void setarConfiguracoes(Configuracao config) {
        segundo_plano.setSelected(config.isSegundo_plano());
        taxa_download.setText(String.valueOf(config.getDownload()));
        taxa_upload.setText(String.valueOf(config.getUpload()));
        ((CheckBox) itens_privacidade.get(0).pegar_direita()).setSelected(config.isIp());
        ((CheckBox) itens_privacidade.get(1).pegar_direita()).setSelected(config.isNome());
        ((CheckBox) itens_privacidade.get(2).pegar_direita()).setSelected(config.isUsuario());
    }

    public void checar_diretorio() {
        boolean existe = false;

        for (ItemMenu item : itens_diretorios) {
            if (((Label) item.pegar_centro()).getText().equals(ConstantesGerais.PASTA_DOWNLOADS)) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            itens_diretorios.add(criar_novo_item(ConstantesGerais.PASTA_DOWNLOADS));
        }
    }
}
