package iftorrent.gui.abasPropriedades;

import com.turn.ttorrent.client.peer.SharingPeer;
import iftorrent.arquivos.torrent.ArquivoTorrentPropriedades;
import static iftorrent.gui.abasPropriedades.AbasPropriedadesConstantes.*;
import iftorrent.gui.ferramentas.Comunicador;
import iftorrent.gui.ferramentas.Par;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * 
 *
 * A classe <b>AbasPropriedadesControlador</b> serve para controlar a interface
 * das abas com as propriedades de um arquivo torrent.
 * <p>
 * Esta classe implementa <b>Initializable</b>.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class AbasPropriedadesControlador implements Initializable {

    /**
     * 
     *
     * Leiaute de abas para as propriedades.
     *
     * 
     */
    @FXML
    private TabPane painel_abas;

    @FXML
    private HBox legenda_mapa_pedacos;

    @FXML
    private Tab informacoes, pares, mapa_pedacos, velocidade;

    @FXML
    private HBox painel_info;

    @FXML
    private HBox painel_grafico;

    @FXML
    private TableView<ParPropriedades> tabela_pares;

    @FXML
    private TableColumn coluna_numero, coluna_ip, coluna_porta, coluna_conectado,
            coluna_nos_bloqueando, coluna_bloqueado, coluna_pedaco;

    @FXML
    private FlowPane painel_mapa_pedacos;

    @FXML
    private ScrollPane painel_com_rolagem;

    public class DadosGrafico {

        public ArquivoTorrentPropriedades arquivo;
        public LineChart grafico;
        public int tempo_em_execucao = 0;
        public double velocidade_maxima_atingida = 5;
        public long ultima_quantidade_baixada = 0;
        public long ultima_quantidade_enviada = 0;
        public final XYChart.Series<String, Double> velocidade_upload
                = new XYChart.Series<>();
        public final XYChart.Series<String, Double> velocidade_download
                = new XYChart.Series<>();
        public final CategoryAxis axis_x = new CategoryAxis();
        public final NumberAxis axis_y = new NumberAxis();
        public final Timeline timeline = new Timeline();

        public DadosGrafico() {
            grafico = new LineChart(axis_x, axis_y);
            grafico.getData().addAll(velocidade_download, velocidade_upload);
            grafico.setAnimated(false);
            grafico.setLegendSide(Side.LEFT);
            grafico.setCreateSymbols(false);
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(DELAY_THREAD_ATUALIZAR_GRAFICO), evento -> {
                        if (arquivo.getEstado().equals(ArquivoTorrentPropriedades.BAIXANDO)
                                || arquivo.getEstado().equals(ArquivoTorrentPropriedades.ENVIANDO)) {
                            atualiza_grafico(this);
                        }
                    }));
            timeline.setCycleCount(Animation.INDEFINITE);
        }

        public void adicionar_velocidade_de_download(int tempo, double velocidade) {
            this.adicionar_velocidade(new XYChart.Data<>(Integer.toString(tempo), velocidade),
                    this.velocidade_download);
        }

        public void adicionar_velocidade_de_upload(int tempo, double velocidade) {
            this.adicionar_velocidade(new XYChart.Data<>(Integer.toString(tempo), velocidade),
                    this.velocidade_upload);
        }

        private void adicionar_velocidade(XYChart.Data<String, Double> ponto,
                XYChart.Series<String, Double> linha) {
            if (ponto.getYValue() > this.velocidade_maxima_atingida) {
                this.velocidade_maxima_atingida = Math.round(ponto.getYValue().floatValue() / 10) * 10 + 10;
                this.axis_y.setUpperBound(this.velocidade_maxima_atingida);
                this.axis_y.setTickUnit(this.velocidade_maxima_atingida / 4);
            }
            linha.getData().add(ponto);
            if (LIMITE_DE_PONTOS_GRAFICO1 > 0
                    && linha.getData().size() > LIMITE_DE_PONTOS_GRAFICO1) {
                linha.getData().remove(0);
            }
        }
    }

    private final ArrayList<DadosGrafico> dados_arquivos = new ArrayList<>();

    private Timeline timeline_mapa;

    private Timeline timeline_info;

    private Timeline selecao_de_itens;

    private ArquivoTorrentPropriedades ultimo_arquivo;

    private final GridPane info_coluna1 = new GridPane();

    private final GridPane info_coluna2 = new GridPane();

    private final GridPane info_coluna3 = new GridPane();

    private final GridPane info_coluna4 = new GridPane();

    private final Hyperlink nomes_subarquivos = new Hyperlink("+ arquivos");

    private int num_pecas_baixadas = 0,
            num_pecas_disponiveis = 0,
            num_pecas_requisitadas = 0;

    /**
     * 
     *
     * Método para inicializar os elementos da interface das abas de
     * propriedades dos arquivos.
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
        Comunicador.setAbas_propriedades(this);
        this.painel_abas.getStylesheets().add(URL_CSS1);
        this.painel_abas.setMinHeight(200);
        this.painel_abas.setPrefSize(PAINEL_TAMANHO1.X, PAINEL_TAMANHO1.Y);
        this.velocidade.setText(TAB_VELOCIDADE_NOME1);
        this.pares.setText(TAB_RASTREADORES_NOME1);
        this.informacoes.setText(TAB_INFORMACOES_NOME1);
        this.mapa_pedacos.setText(TAB_MAPA_PEDACOS1);
        this.painel_mapa_pedacos.setHgap(2);
        this.painel_mapa_pedacos.setVgap(2);
        this.painel_mapa_pedacos.setPadding(new Insets(10, 10, 10, 10));
        this.painel_com_rolagem.setFitToWidth(true);
        VBox.setVgrow(this.painel_com_rolagem, Priority.ALWAYS);
        this.legenda_mapa_pedacos.setPrefHeight(30);
        this.legenda_mapa_pedacos.setAlignment(Pos.CENTER_LEFT);
        this.legenda_mapa_pedacos.setPadding(new Insets(5, 5, 5, 5));
        this.legenda_mapa_pedacos.setSpacing(10);
        this.legenda_mapa_pedacos.getChildren().add(new Label("  Não disponíveis:"));
        this.legenda_mapa_pedacos.getChildren().add(criar_quadrado("",
                CODIGO_PEDACOS_NAO_DISPONIVEIS, TAMANHO_LADO_QUADRADO_LEGENDA));
        this.legenda_mapa_pedacos.getChildren().add(new Label("  Disponíveis:"));
        this.legenda_mapa_pedacos.getChildren().add(criar_quadrado("",
                CODIGO_PEDACOS_DISPONIVEIS1, TAMANHO_LADO_QUADRADO_LEGENDA));
        this.legenda_mapa_pedacos.getChildren().add(new Label("  Requisitados:"));
        this.legenda_mapa_pedacos.getChildren().add(criar_quadrado("",
                CODIGO_PEDACOS_REQUISITADOS1, TAMANHO_LADO_QUADRADO_LEGENDA));
        this.legenda_mapa_pedacos.getChildren().add(new Label("  Baixados:"));
        this.legenda_mapa_pedacos.getChildren().add(criar_quadrado("",
                CODIGO_PEDACOS_BAIXADOS1, TAMANHO_LADO_QUADRADO_LEGENDA));
        this.painel_info.setAlignment(Pos.CENTER);
        this.info_coluna1.setAlignment(Pos.CENTER);
        this.info_coluna1.setVgap(ESPACAMENTO_LINHAS_INFO);
        this.info_coluna1.setHgap(ESPACAMENTO_META_DADOS_E_DADOS_INFO);
        this.info_coluna2.setAlignment(Pos.CENTER);
        this.info_coluna2.setVgap(ESPACAMENTO_LINHAS_INFO);
        this.info_coluna2.setHgap(ESPACAMENTO_META_DADOS_E_DADOS_INFO);
        this.info_coluna3.setAlignment(Pos.CENTER);
        this.info_coluna3.setVgap(ESPACAMENTO_LINHAS_INFO);
        this.info_coluna3.setHgap(ESPACAMENTO_META_DADOS_E_DADOS_INFO);
        this.info_coluna4.setAlignment(Pos.CENTER);
        this.info_coluna4.setVgap(ESPACAMENTO_LINHAS_INFO);
        this.info_coluna4.setHgap(ESPACAMENTO_META_DADOS_E_DADOS_INFO);
        this.painel_info.getChildren().addAll(
                info_coluna1,
                info_coluna2,
                info_coluna3,
                info_coluna4
        );
        HBox.setHgrow(info_coluna1, Priority.ALWAYS);
        HBox.setHgrow(info_coluna2, Priority.ALWAYS);
        HBox.setHgrow(info_coluna3, Priority.ALWAYS);
        HBox.setHgrow(info_coluna4, Priority.ALWAYS);
        this.painel_info.setSpacing(ESPACAMENTO_COLUNAS_INFO);
        inserir_meta_dados_info();
        this.coluna_bloqueado.setCellValueFactory(new PropertyValueFactory("pode_enviar"));
        this.coluna_conectado.setCellValueFactory(new PropertyValueFactory("estado"));
        this.coluna_ip.setCellValueFactory(new PropertyValueFactory("ip"));
        this.coluna_nos_bloqueando.setCellValueFactory(new PropertyValueFactory("pode_baixar"));
        this.coluna_numero.setCellValueFactory(new PropertyValueFactory("numero"));
        this.coluna_pedaco.setCellValueFactory(new PropertyValueFactory("pedaco"));
        this.coluna_porta.setCellValueFactory(new PropertyValueFactory("porta"));
        this.coluna_bloqueado.setText(TEXTO_COLUNA_CHOKING);
        this.coluna_conectado.setText(TEXTO_COLUNA_CONECTADO);
        this.coluna_ip.setText(TEXTO_COLUNA_IP);
        this.coluna_nos_bloqueando.setText(TEXTO_COLUNA_CHOKED);
        this.coluna_numero.setText(TEXTO_COLUNA_NUMERO);
        this.coluna_pedaco.setText(TEXTO_COLUNA_PEDACO);
        this.coluna_porta.setText(TEXTO_COLUNA_PORTA);
        this.coluna_numero.setPrefWidth(TAMANHO_COLUNA_NUMERO);
        this.coluna_ip.setPrefWidth(TAMANHO_COLUNA_IP);
        this.coluna_conectado.setPrefWidth(TAMANHO_COLUNA_CONECTADO);
        this.coluna_bloqueado.setPrefWidth(TAMANHO_COLUNA_BLOQUEADO);
        this.coluna_nos_bloqueando.setPrefWidth(TAMANHO_COLUNA_NOS_BLOQUEANDO);
    }

    private void limpar_pares() {
        this.tabela_pares.getItems().clear();
    }

    private void adicionar_pares(ArquivoTorrentPropriedades arquivo) {
        limpar_pares();
        atualizar_pares(arquivo);
    }

    private void atualizar_par(ParPropriedades par) {
        for (ParPropriedades p : this.tabela_pares.getItems()) {
            if (p.getNumero() == par.getNumero()) {
                p.atualizar(par);
                return;
            }
        }
        this.tabela_pares.getItems().add(par);
    }

    public void atualizar_pares(ArquivoTorrentPropriedades arquivo) {
        Iterator<SharingPeer> iterador = arquivo.getPares().iterator();

        while (iterador.hasNext()) {
            this.atualizar_par(new ParPropriedades(iterador.next()));
        }
        this.tabela_pares.refresh();
    }

    private void inserir_meta_dados_info() {
        this.info_coluna1.add(new Label(INFO_NOME), 0, 0);
        this.info_coluna1.add(new Label(INFO_NOME_SUBARQUIVOS), 0, 1);
        this.info_coluna1.add(new Label(INFO_NUMERO), 0, 2);
        this.info_coluna1.add(new Label(INFO_ESTADO), 0, 3);
        this.info_coluna1.add(new Label(INFO_PROGRESSO), 0, 4);

        this.info_coluna2.add(new Label(INFO_SEMEADOR), 0, 0);
        this.info_coluna2.add(new Label(INFO_NUMERO_PARES), 0, 1);
        this.info_coluna2.add(new Label(INFO_NUMERO_PARES_CONECTADOS), 0, 2);
        this.info_coluna2.add(new Label(INFO_MAX_VELOCIDADE_DOWNLOAD), 0, 3);
        this.info_coluna2.add(new Label(INFO_MAX_VELOCIDADE_UPLOAD), 0, 4);

        this.info_coluna3.add(new Label(INFO_QTD_TOTAL), 0, 0);
        this.info_coluna3.add(new Label(INFO_QTD_BAIXADA), 0, 1);
        this.info_coluna3.add(new Label(INFO_QTD_BAIXADA_EXECUCAO), 0, 2);
        this.info_coluna3.add(new Label(INFO_QTD_FALTA), 0, 3);
        this.info_coluna3.add(new Label(INFO_QTD_ENVIADA), 0, 4);

        this.info_coluna4.add(new Label(INFO_N_PECAS_TOTAIS), 0, 0);
        this.info_coluna4.add(new Label(INFO_N_PECAS_DISPOIVEIS), 0, 1);
        this.info_coluna4.add(new Label(INFO_N_PECAS_BAIXADAS), 0, 2);
        this.info_coluna4.add(new Label(INFO_N_PECAS_REQUISITADAS), 0, 3);
        this.info_coluna4.add(new Label(INFO_N_PECAS_NAO_DISPONIVEIS), 0, 4);
    }

    public void inserir_informacoes_sobre_torrent(ArquivoTorrentPropriedades arquivo) {
        limpar_informacoes();
        Hyperlink nome_arquivo = new Hyperlink(arquivo.getNome());
        String nomes_subarquivos_torrent = "";

        for (String nome : arquivo.getNomes_subarquivos()) {
            nomes_subarquivos_torrent += nome + "\n";
        }
        this.nomes_subarquivos.setTooltip(new Tooltip(nomes_subarquivos_torrent));
        nome_arquivo.setMaxWidth(80);
        nome_arquivo.setTooltip(new Tooltip(arquivo.getNome()));
        this.info_coluna1.add(nome_arquivo, 1, 0);
        this.info_coluna1.add(nomes_subarquivos, 1, 1);
        this.info_coluna1.add(new Label(arquivo.getNumero() + ""), 1, 2);
        this.info_coluna1.add(new Label(arquivo.getEstado()), 1, 3);
        this.info_coluna1.add(new Label(String.format("%.2f", arquivo.getProgresso() * 100) + "%"), 1, 4);

        //olha que delícia de teste ternário
        this.info_coluna2.add(new Label(arquivo.getSemeador() ? "Sim" : "Não"), 1, 0);
        this.info_coluna2.add(new Label(arquivo.getNumero_de_pares() + ""), 1, 1);
        this.info_coluna2.add(new Label(arquivo.getNumero_de_pares_conectados() + ""), 1, 2);
        this.info_coluna2.add(new Label(arquivo.getTaxa_max_baixar() + ""), 1, 3);
        this.info_coluna2.add(new Label(arquivo.getTaxa_max_enviar() + ""), 1, 4);

        this.info_coluna3.add(new Label(arquivo.getTamanho() + ""), 1, 0);
        this.info_coluna3.add(new Label(arquivo.getDownloaded() + ""), 1, 1);
        this.info_coluna3.add(new Label(arquivo.getBaixado_durante_execucao() + ""), 1, 2);
        this.info_coluna3.add(new Label(arquivo.getFalta() + ""), 1, 3);
        this.info_coluna3.add(new Label(arquivo.getUploaded() + ""), 1, 4);

        this.info_coluna4.add(new Label(arquivo.getNumero_pedacos() + ""), 1, 0);
        this.info_coluna4.add(new Label(num_pecas_disponiveis + ""), 1, 1);
        this.info_coluna4.add(new Label(num_pecas_baixadas + ""), 1, 2);
        this.info_coluna4.add(new Label(num_pecas_requisitadas + ""), 1, 3);
        this.info_coluna4.add(new Label((arquivo.getNumero_pedacos()
                - num_pecas_disponiveis) + ""), 1, 4);
    }

    public void setMapa_de_pedacos(List<Par<Integer, Integer>> pedacos) {
        painel_mapa_pedacos.getChildren().clear();
        pedacos.stream().map((pedaco) -> {
            return criar_quadrado(pedaco.getKey().toString(), pedaco.getValue(),
                    TAMANHO_LADO_QUADRADO);
        }).forEachOrdered((quadrado) -> {
            painel_mapa_pedacos.getChildren().add(quadrado);
        });
    }

    private HBox criar_quadrado(String conteudo, int cor, int lado) {
        HBox quadrado = new HBox();

        quadrado.setAlignment(Pos.CENTER);
        quadrado.setPrefHeight(lado);
        quadrado.setPrefWidth(lado);
        quadrado.setMaxHeight(lado);
        quadrado.setMaxWidth(lado);
        quadrado.getChildren().add(new Label(conteudo));
        quadrado.setStyle(
                "-fx-border-color: black;"
                + "-fx-border-width: 1;"
                + "-fx-background-color: " + getCor(cor) + ";");
        return quadrado;
    }

    private String getCor(int codigo) {
        switch (codigo) {
            case CODIGO_PEDACOS_BAIXADOS1:
                return COR_PEDACOS_BAIXADOS1;
            case CODIGO_PEDACOS_DISPONIVEIS1:
                return COR_PEDACOS_DISPONIVEIS1;
            case CODIGO_PEDACOS_REQUISITADOS1:
                return COR_PEDACOS_REQUISITADOS1;
            default:
                return COR_PEDACOS_NAO_DISPONIVEIS1;
        }
    }

    public void atulizar_aba_mapa_de_pedacos(ArquivoTorrentPropriedades arquivo) {
        ArrayList<Par<Integer, Integer>> pedacos
                = new ArrayList<>(arquivo.getNumero_pedacos());

        num_pecas_baixadas = 0;
        num_pecas_disponiveis = 0;
        num_pecas_requisitadas = 0;
        for (int i = 0; i < arquivo.getNumero_pedacos(); i++) {
            pedacos.add(i, new Par<>(i, CODIGO_PEDACOS_NAO_DISPONIVEIS));
        }
        arquivo.getPedacos_disponiveis().stream().forEach(bit -> {
            pedacos.get(bit).setValue(CODIGO_PEDACOS_DISPONIVEIS1);
            num_pecas_disponiveis++;
        });
        arquivo.getPedacos_baixados().stream().forEach(bit -> {
            pedacos.get(bit).setValue(CODIGO_PEDACOS_BAIXADOS1);
            num_pecas_baixadas++;
        });
        arquivo.getPedacos_requisitados().stream().forEach(bit -> {
            pedacos.get(bit).setValue(CODIGO_PEDACOS_REQUISITADOS1);
            num_pecas_requisitadas++;
        });
        this.setMapa_de_pedacos(pedacos);
    }

    public void iniciar_thread_selecao_itens() {
        matar_thread_selecao_itens();
        selecao_de_itens = new Timeline(
                new KeyFrame(Duration.millis(DELAY_THREAD_SELECAO_DE_ITENS), evento -> {
                    ArquivoTorrentPropriedades arquivo
                            = Comunicador.getMenu_principal().tabela
                                    .getSelectionModel().getSelectedItem();

                    if (arquivo == null) {
                        limpar_grafico();
                        limpar_mapa_pedacos();
                        limpar_informacoes();
                        limpar_pares();
                    } else if (ultimo_arquivo == null
                            || ultimo_arquivo.getNumero() != arquivo.getNumero()) {
                        trocar_grafico(arquivo);
                        atulizar_aba_mapa_de_pedacos(arquivo);
                        inserir_informacoes_sobre_torrent(arquivo);
                        adicionar_pares(arquivo);
                    }
                    ultimo_arquivo = arquivo;
                }));
        selecao_de_itens.setCycleCount(Animation.INDEFINITE);
        selecao_de_itens.play();
    }

    private void limpar_mapa_pedacos() {
        painel_mapa_pedacos.getChildren().clear();
    }

    private void limpar_informacoes() {
        this.info_coluna1.getChildren().clear();
        this.info_coluna2.getChildren().clear();
        this.info_coluna3.getChildren().clear();
        this.info_coluna4.getChildren().clear();
        inserir_meta_dados_info();
    }

    public void matar_thread_selecao_itens() {
        if (selecao_de_itens != null) {
            selecao_de_itens.stop();
        }
    }

    private void limpar_grafico() {
        painel_grafico.getChildren().clear();
    }

    private void trocar_grafico(ArquivoTorrentPropriedades arquivo) {
        DadosGrafico dados = getDados_do_grafico_do_arquivo(arquivo);

        if (dados == null) {
            dados = arquivos_para_dados_grafico(arquivo);
            dados_arquivos.add(dados);
        }
        painel_grafico.getChildren().clear();
        painel_grafico.getChildren().add(dados.grafico);
        HBox.setHgrow(dados.grafico, Priority.ALWAYS);
    }

    public void iniciar_thread_grafico() {
        matar_thread_grafico();
        dados_arquivos.forEach((dados) -> {
            dados.timeline.play();
        });
    }

    private void atualiza_grafico(DadosGrafico dados) {
        if (dados.tempo_em_execucao > 0) {
            dados.adicionar_velocidade_de_download(
                    dados.tempo_em_execucao, (dados.arquivo.getDownloaded()
                    - dados.ultima_quantidade_baixada) * 8
                    / INTERVALO_TEMPO_GRAFICO);
            dados.adicionar_velocidade_de_upload(
                    dados.tempo_em_execucao, (dados.arquivo.getUploaded()
                    - dados.ultima_quantidade_enviada) * 8
                    / INTERVALO_TEMPO_GRAFICO);
        }
        dados.ultima_quantidade_baixada = dados.arquivo.getDownloaded();
        dados.ultima_quantidade_enviada = dados.arquivo.getUploaded();
        dados.tempo_em_execucao++;
    }

    public void matar_thread_grafico() {
        dados_arquivos.forEach((dados) -> {
            dados.timeline.stop();
        });
    }

    public void iniciar_thread_mapa_pedacos() {
        matar_thread_mapa_pedacos();
        timeline_mapa = new Timeline(
                new KeyFrame(Duration.millis(DELAY_THREAD_ATUALIZAR_MAPA), evento -> {
                    ArquivoTorrentPropriedades arquivo
                            = Comunicador.getMenu_principal().tabela
                                    .getSelectionModel().getSelectedItem();

                    if (arquivo != null) {
                        Comunicador.getAbas_propriedades()
                                .atulizar_aba_mapa_de_pedacos(arquivo);
                        if(arquivo.getEstado().equals(ArquivoTorrentPropriedades.BAIXADO)){
                            matar_thread_mapa_pedacos();
                        }
                    }
                }));
        timeline_mapa.setCycleCount(Animation.INDEFINITE);
        timeline_mapa.play();
    }

    public void matar_thread_mapa_pedacos() {
        if (timeline_mapa != null) {
            timeline_mapa.stop();
        }
    }

    public void iniciar_thread_info() {
        matar_thread_info();
        timeline_info = new Timeline(
                new KeyFrame(Duration.millis(DELAY_THREAD_ATUALIZAR_INFO), evento -> {
                    ArquivoTorrentPropriedades arquivo
                            = Comunicador.getMenu_principal().tabela
                                    .getSelectionModel().getSelectedItem();

                    if (arquivo != null) {
                        inserir_informacoes_sobre_torrent(arquivo);
                    }
                }));
        timeline_info.setCycleCount(Animation.INDEFINITE);
        timeline_info.play();
    }

    public void matar_thread_info() {
        if (timeline_info != null) {
            timeline_info.stop();
        }
    }

    private DadosGrafico getDados_do_grafico_do_arquivo(
            ArquivoTorrentPropriedades arquivo) {
        for (DadosGrafico dg : dados_arquivos) {
            if (dg.arquivo.getNumero() == arquivo.getNumero()) {
                return dg;
            }
        }
        return null;
    }

    private DadosGrafico arquivos_para_dados_grafico(
            ArquivoTorrentPropriedades arquivo) {
        DadosGrafico dg = new DadosGrafico();

        dg.arquivo = arquivo;
        dg.velocidade_download.setName(LEGENDA_DOWNLOAD1);
        dg.velocidade_upload.setName(LEGENDA_UPLOAD1);
        dg.axis_y.setLowerBound(0);
        dg.axis_y.setUpperBound(VELOCIDADE_MAXIMA_INICIAL);
        dg.axis_y.setAutoRanging(false);
        dg.axis_x.setLabel(EIXO_X1_TEXTO1);
        dg.axis_y.setLabel(EIXO_Y1_TEXTO1);
        return dg;
    }

    private boolean dados_grafico_contem(ArquivoTorrentPropriedades arquivo) {
        return dados_arquivos.stream().anyMatch((dados)
                -> (dados.arquivo.getNumero() == arquivo.getNumero()));
    }

    public void adicionar_grafico_arquivo(ArquivoTorrentPropriedades arquivo) {
        if (!dados_grafico_contem(arquivo)) {
            DadosGrafico dados = arquivos_para_dados_grafico(arquivo);

            dados_arquivos.add(dados);
            dados.timeline.play();
        }
    }

    public void remover_arquivo_grafico(ArquivoTorrentPropriedades arquivo) {
        DadosGrafico dados = getDados_do_grafico_do_arquivo(arquivo);

        if (dados != null) {
            dados.timeline.stop();
            dados_arquivos.remove(dados);
        }
    }
    
    public TabPane getPainel(){
        return painel_abas;
    }

}
