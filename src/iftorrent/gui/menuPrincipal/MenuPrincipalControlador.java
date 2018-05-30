package iftorrent.gui.menuPrincipal;

import com.turn.ttorrent.client.Client;
import static iftorrent.ConstantesGerais.EXTENSAO_TORRENTS;
import static iftorrent.ConstantesGerais.PASTA_TORRENTS;
import iftorrent.JanelaPrincipal;
import static iftorrent.arquivos.ConstantesLogger.EXECUCAO;
import iftorrent.arquivos.Logger;
import iftorrent.arquivos.torrent.ArquivoTorrentPropriedades;
import static iftorrent.arquivos.torrent.ArquivoTorrentPropriedades.*;
import iftorrent.arquivos.torrent.Torrent;
import iftorrent.gui.ferramentas.Comunicador;
import static iftorrent.gui.menuPrincipal.ConstantesMenuPrincipal.*;
import java.io.File;
import static java.io.File.separator;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Controlador da parte principal do programa (central).
 *
 * @author Leonardo Bortolini
 * @author Rafael Coelho
 */
public class MenuPrincipalControlador implements Initializable {

    public static ArrayList<ArquivoTorrentPropriedades> itens = new ArrayList();
    /**
     * 
     *
     * Tabela que contém arquivos sendo baixados.
     *
     * 
     */
    @FXML
    public TableView<ArquivoTorrentPropriedades> tabela;
    @FXML
    public TableColumn<ArquivoTorrentPropriedades, String> nome;
    @FXML
    public TableColumn<ArquivoTorrentPropriedades, Long> tamanho;
    @FXML
    public TableColumn<ArquivoTorrentPropriedades, Long> falta;
    @FXML
    public TableColumn<ArquivoTorrentPropriedades, Long> downloaded;
    @FXML
    public TableColumn<ArquivoTorrentPropriedades, Long> uploaded;
    @FXML
    public TableColumn<ArquivoTorrentPropriedades, String> estado;
    @FXML
    public TableColumn<ArquivoTorrentPropriedades, Double> progresso;

    /**
     * Método initialize utilizado para inicializar o componente MenuPrincipal.
     *
     * @param url O diretório do objeto root ou null caso o mesmo não seja
     * encontrado.
     * @param rb O recurso para localizar o objeto root ou null caso o mesmo não
     * seja encontrado.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Comunicador.setMenu_principal(this);
        definir_tamanhos();
        HBox.setHgrow(tabela, Priority.ALWAYS);
        nome.setCellValueFactory(new PropertyValueFactory("nome"));
        tamanho.setCellValueFactory(new PropertyValueFactory("tamanho"));
        falta.setCellValueFactory(new PropertyValueFactory("falta"));
        downloaded.setCellValueFactory(new PropertyValueFactory("downloaded"));
        uploaded.setCellValueFactory(new PropertyValueFactory("uploaded"));
        estado.setCellValueFactory(new PropertyValueFactory("estado"));
        progresso.setCellValueFactory(new PropertyValueFactory("progresso"));
        progresso.setCellFactory(ProgressBarTableCell.<ArquivoTorrentPropriedades>forTableColumn());
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabela.setRowFactory(tv -> new TableRow<ArquivoTorrentPropriedades>() {
            private final Tooltip tooltip = new Tooltip();

            @Override
            public void updateItem(ArquivoTorrentPropriedades arq, boolean empty) {
                super.updateItem(arq, empty);
                if (arq == null) {
                    setTooltip(null);
                } else {
                    if (arq.getEstado().equals(ArquivoTorrentPropriedades.obtem_nome_estado(Client.ClientState.VALIDATING))) {
                        tooltip.setText(arq.getEstado());
                    } else {
                        tooltip.setText(String.format("%.2f%%", arq.getProgresso() * 100));
                    }
                    setTooltip(tooltip);
                }
            }
        });
    }

    /**
     * 
     *
     * Método responsável por selecionar todos os torrents presentes no
     * diretório de torrents e exibí-los.
     *
     * 
     */
    public void selecionar_torrents() {
        if (itens.isEmpty()) {
            return;
        }
        tabela.getItems().clear();
        itens.forEach(tabela.getItems()::add);
    }

    /**
     * 
     *
     * Método responsável por selecionar todos os torrents presentes no
     * diretório de torrents, que possuam estado igual a "baixando", e
     * exibí-los.
     *
     * 
     */
    public void selecionar_baixando() {
        if (itens.isEmpty()) {
            return;
        }
        tabela.getItems().clear();
        itens.forEach((item) -> {
            if (item.getEstado().equals(BAIXANDO)) {
                tabela.getItems().add(item);
            }
        });
    }

    /**
     * 
     *
     * Método responsável por selecionar todos os torrents presentes no
     * diretório de torrents, que possuam estado igual a "baixado", e exibí-los.
     *
     * 
     */
    public void selecionar_baixados() {
        if (itens.isEmpty()) {
            return;
        }
        tabela.getItems().clear();
        itens.forEach((item) -> {
            if (item.getEstado().equals(BAIXADO)) {
                tabela.getItems().add(item);
            }
        });
    }

    /**
     * 
     *
     * Método responsável por selecionar todos os torrents presentes no
     * diretório de torrents, que possuam estado igual a "pausado", e exibí-los.
     *
     * 
     */
    public void selecionar_pausados() {
        if (itens.isEmpty()) {
            return;
        }
        tabela.getItems().clear();
        itens.forEach((item) -> {
            if (item.getEstado().equals(PAUSADO)) {
                tabela.getItems().add(item);
            }
        });
    }

    /**
     * 
     *
     * Método responsável por selecionar todos os torrents presentes no
     * diretório de torrents, que possuam estado igual a "parado", e exibí-los.
     *
     * 
     */
    public void selecionar_parados() {
        if (itens.isEmpty()) {
            return;
        }
        tabela.getItems().clear();
        itens.forEach((item) -> {
            if (item.getEstado().equals(PARADO)) {
                tabela.getItems().add(item);
            }
        });
    }

    /**
     * 
     *
     *
     *
     * 
     */
    public void selecionar_historico_de_download() {
        //if(tabela.getItems().isEmpty()) return;
        // for (int i = 0; i < tabela.getItems().size(); i++) tabela.getItems().remove(i);
        // VER ESSE
    }

    /**
     * 
     *
     * Método responsável por selecionar todos os torrents presentes no
     * diretório de torrents, que possuam estado igual a "enviando", e
     * exibí-los.
     *
     * 
     */
    public void selecionar_enviando() {
        if (itens.isEmpty()) {
            return;
        }
        tabela.getItems().clear();
        itens.forEach((item) -> {
            if (item.getEstado().equals(ENVIANDO)) {
                tabela.getItems().add(item);
            }
        });
    }

    private void definir_tamanhos() {
        nome.setPrefWidth(COLUNA1_NOME_PREFWIDTH);
        tamanho.setMaxWidth(COLUNA2_TAMANHO_MAXWIDTH);
        progresso.setPrefWidth(COLUNA7_PROGRESSO_PREFWIDTH);
        progresso.setMaxWidth(COLUNA7_PROGRESSO_MAXWIDTH);
    }

    public boolean pausar_torrent() {
        return pausar_torrent(tabela.getSelectionModel().getSelectedItem());
    }

    public boolean pausar_torrent(ArquivoTorrentPropriedades arquivo) {
        if (arquivo == null) {
            return false;
        }
        if (arquivo.getEstado().equals(PAUSADO)) {
            return true;
        }
        if (arquivo.getEstado().equals(PARADO)) {
            return false;
        }
        for (Client cliente : JanelaPrincipal.clientes) {
            if (cliente.getTorrent().getName().equals(arquivo.getNome())) {
                cliente.stop();
                arquivo.setEstado(PAUSADO);
                return true;
            }
        }
        return false;
    }

    public boolean reiniciar_torrent() {
        ArquivoTorrentPropriedades arquivo = tabela.getSelectionModel().getSelectedItem();

        if (arquivo == null) {
            return false;
        }
        if (arquivo.getEstado().equals(PARADO)) {
            return false;
        }
        Torrent.abre_torrent(new File(PASTA_TORRENTS + separator
                + arquivo.getNome()
                + EXTENSAO_TORRENTS));
        int index = tabela.getItems().indexOf(arquivo);

        for (ArquivoTorrentPropriedades arq : tabela.getItems()) {
            if (arq.getNome().equals(arquivo.getNome())) {
                tabela.getItems().remove(index);
                tabela.getItems().remove(arq);
                tabela.getItems().add(index, arq);
                tabela.getSelectionModel().select(arq);
                return true;
            }
        }
        return false;
    }

    /**
     * Método que trata o evento de parar torrent.
     *
     * @return Valor lógico indicando se foi possível a remoção.
     */
    public boolean parar_torrent() {
        return parar_torrent(tabela.getSelectionModel().getSelectedItem());
    }

    /**
     * Para o torrent recebido como parâmetro e o mesmo não pode ser continuado,
     * mas permanece na tabela/
     *
     * @param arquivo ArquivoTorrentPropriedades
     * @return Valor lógico indicando se foi possível parar o torrent.
     */
    public boolean parar_torrent(ArquivoTorrentPropriedades arquivo) {
        if (arquivo == null) {
            return false;
        }
        if (arquivo.getEstado().equals(PARADO)) {
            return true;
        }
        for (Client cliente : JanelaPrincipal.clientes) {
            if (cliente.getTorrent().getName().equals(arquivo.getNome())) {
                cliente.stop();
                arquivo.setEstado(PARADO);
                atualiza_torrent(arquivo);
                tabela.refresh();
                return true;
            }
        }
        return false;
    }

    /**
     * Remove o arquivo torrent da tabela e para o cliente.
     *
     * @return Valor lógico indicando se foi possível remover o arquivo torrent.
     */
    public boolean remover_torrents_selecionados() {
        ObservableList<ArquivoTorrentPropriedades> arquivos = 
                tabela.getSelectionModel().getSelectedItems();
        boolean sem_erros = true;

        for(ArquivoTorrentPropriedades arquivo : arquivos){
            if (!remover_torrent(arquivo)){
                sem_erros = false;
            }
        }
        return sem_erros;
    }
    
    private boolean remover_torrent(ArquivoTorrentPropriedades arquivo){
        if (arquivo == null) {
            return false;
        }
        if (tabela.getItems().stream().anyMatch(a -> (a.getNumero() == arquivo.getNumero()))) {
            tabela.getItems().remove(arquivo);
            MenuPrincipalControlador.itens.remove(arquivo);
            for (Iterator<Client> iterator = JanelaPrincipal.clientes.iterator(); iterator.hasNext();) {
                Client cliente = iterator.next();

                if (cliente.getTorrent().getName().equals(arquivo.getNome())) {
                    iterator.remove();
                    cliente.stop();
                    JanelaPrincipal.clientes.remove(cliente);
                }
            }
            Comunicador.getAbas_propriedades().remover_arquivo_grafico(arquivo);
            return true;
        }
        return false;
    }

    /**
     * Adiciona o ArquivoTorrentPropriedades na tabela.
     *
     * @param arquivo ArquivoTorrentPropriedades
     */
    public void adiciona_torrent_na_tabela(ArquivoTorrentPropriedades arquivo) {
        if (arquivo != null) {
            if (!(tabela.getItems().stream().anyMatch(a -> (a.getNumero() == arquivo.getNumero()))
                    && MenuPrincipalControlador.itens.contains(arquivo))) {
                tabela.getItems().add(arquivo);
                Comunicador.getAbas_propriedades().adicionar_grafico_arquivo(arquivo);
                MenuPrincipalControlador.itens.add(arquivo);
            } else {
                Logger.escrever(EXECUCAO, AVISO_TORRENT_NAO_ADICIONADO);
            }
        }
    }

    public void atualiza_torrent(ArquivoTorrentPropriedades arquivo) {
        for (int i = 0; i < tabela.getItems().size(); i++) {
            if (tabela.getItems().get(i).getNumero() == arquivo.getNumero()) {
                tabela.getItems().get(i).atualizar(arquivo);
                break;
            }
        }
        tabela.refresh();
    }

    public String pegar_estado_torrent(Client cliente) {
        for (int i = 0; i < tabela.getItems().size(); i++) {
            if (tabela.getItems().get(i).getNumero() == cliente.getTorrent().getHexInfoHash().hashCode()) {
                if (tabela.getItems().get(i).getEstado().equals(PAUSADO)
                        || tabela.getItems().get(i).getEstado().equals(PARADO)) {
                    return tabela.getItems().get(i).getEstado();
                }
            }
        }
        return "Erro";
    }

    public void subir_item() {
        ArquivoTorrentPropriedades arquivo_para_mudar = tabela.getSelectionModel().getSelectedItem();
        int indice_antigo = tabela.getSelectionModel().getSelectedIndex();

        if (indice_antigo == 0) {
            return;
        }
        ArquivoTorrentPropriedades arquivo_para_substituir = tabela.getItems().get(indice_antigo - 1);
        ArquivoTorrentPropriedades auxiliar = arquivo_para_substituir;

        tabela.getItems().set(indice_antigo - 1, arquivo_para_mudar);
        tabela.getItems().set(indice_antigo, auxiliar);
        tabela.refresh();
    }

    public void descer_item() {
        ArquivoTorrentPropriedades arquivo_para_mudar = tabela.getSelectionModel().getSelectedItem();
        int indice_antigo = tabela.getSelectionModel().getSelectedIndex();

        if (indice_antigo == (tabela.getItems().size() - 1)) {
            return;
        }
        ArquivoTorrentPropriedades arquivo_para_substituir = tabela.getItems().get(indice_antigo + 1);
        ArquivoTorrentPropriedades auxiliar = arquivo_para_substituir;

        tabela.getItems().set(indice_antigo + 1, arquivo_para_mudar);
        tabela.getItems().set(indice_antigo, auxiliar);
        tabela.refresh();
    }

    public boolean isSelecionado(ArquivoTorrentPropriedades arquivo) {
        if (tabela.getSelectionModel().getSelectedItem() == null) {
            return false;
        }
        return tabela.getSelectionModel().getSelectedItem().getNumero() == arquivo.getNumero();
    }

}
