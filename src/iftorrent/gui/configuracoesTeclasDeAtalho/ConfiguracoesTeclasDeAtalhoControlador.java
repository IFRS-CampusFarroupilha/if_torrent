package iftorrent.gui.configuracoesTeclasDeAtalho;

import iftorrent.gui.barraMenu.BarraMenuConstantes;
import iftorrent.gui.ferramentas.Comunicador;
import static iftorrent.gui.configuracoesTeclasDeAtalho.ConfiguracoesTeclasDeAtalhoConstantes.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * 
 * A classe <b>ConfiguracoesTeclasDeAtalhoControlador</b> serve para controlar 
 * a interface da janela de configuração de teclas de atalho.
 * <p>
 * Esta classe implementa <b>Initializable</b>.
 *
 * @author Eduardo Toffolo
 * @author Otávio Farinon
 * 
 * 
 */
public class ConfiguracoesTeclasDeAtalhoControlador implements Initializable {

    /**
     * 
     * 
     * Um objeto do tipo <b>TableView</b> que representa a tabela.
     * 
     * 
     */
    @FXML
    private TableView<TuplaAcaoAtalho> tabela;

    /**
     * 
     * 
     * Um objeto do tipo <b>TableColumn</b> que representa as colunas da tabela.
     * 
     * 
     */
    @FXML
    private TableColumn opcoes, atalhos;

    /**
     * 
     * 
     * Um objeto do tipo <b>VBox</b> que representa o painel em que a tabela 
     * é inserida.
     * 
     * 
     */
    @FXML
    private VBox painel;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>HBox</b> que representa a barra inferior onde se 
     * encontram os botões "Salvar" e "Cancelar".
     * 
     * 
     */
    @FXML
    private HBox barra_inferior;
    
    /**
     * 
     * 
     * Objetos do tipo <b>Button</b> que representam os botões para salvar e 
     * cancelar as edições das teclas de atalho.
     * 
     * 
     */
    @FXML
    private Button salvar, cancelar;
    
    /**
     * 
     * 
     * Método para inicializar os elementos da interface da janela de 
     * configuração de teclas de atalho.
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
        Comunicador.setConfiguracoes_teclas_de_atalho(this);
        this.opcoes.setCellValueFactory(
                new PropertyValueFactory<>("acao"));
        this.atalhos.setCellValueFactory(
                new PropertyValueFactory<>("entrada_de_texto"));
        this.opcoes.setMinWidth(COLUNA1_LARGURA1);
        this.atalhos.setMinWidth(COLUNA2_LARGURA1);
        this.opcoes.setResizable(false);
        this.atalhos.setResizable(false);
        VBox.setVgrow(this.tabela, Priority.ALWAYS);
        this.opcoes.setText(COLUNA1_TITULO1);
        this.atalhos.setText(COLUNA2_TITULO1);
        this.barra_inferior.setMinHeight(BARRA_INFERIOR_ALTURA1);
        this.barra_inferior.setSpacing(BARRA_INFERIOR_ESPACAMENTO);
        this.salvar.setMinSize(BOTAO_TAMANHO1.X, BOTAO_TAMANHO1.Y);
        this.cancelar.setMinSize(BOTAO_TAMANHO1.X, BOTAO_TAMANHO1.Y);
        this.salvar.setPrefSize(BOTAO_TAMANHO2.X, BOTAO_TAMANHO2.Y);
        this.cancelar.setPrefSize(BOTAO_TAMANHO2.X, BOTAO_TAMANHO2.Y);
        this.salvar.setText(BOTAO1_TEXTO1);
        this.cancelar.setText(BOTAO2_TEXTO1);
        this.tabela.setMinWidth(COLUNA1_LARGURA1 + COLUNA2_LARGURA1 + 15);
        this.painel.getStylesheets().add(URL_CSS1);
        salvar.setVisible(false);
        
        adicionar_linha(BarraMenuConstantes.MENU1_ITEM1_TEXTO1, new Atalho(KeyCode.F1));
        adicionar_linha(BarraMenuConstantes.MENU1_ITEM2_TEXTO1, new Atalho(KeyCode.F2));
        adicionar_linha(BarraMenuConstantes.MENU1_ITEM3_TEXTO1, new Atalho(KeyCode.F3));
        adicionar_linha(BarraMenuConstantes.MENU1_ITEM4_TEXTO1, new Atalho(KeyCode.F4));
        adicionar_linha(BarraMenuConstantes.MENU2_ITEM1_TEXTO1, new Atalho(KeyCode.F5));
        adicionar_linha(BarraMenuConstantes.MENU2_ITEM2_TEXTO1, new Atalho(KeyCode.F6));
        adicionar_linha(BarraMenuConstantes.MENU2_ITEM3_TEXTO1, new Atalho(KeyCode.F7));
        adicionar_linha(BarraMenuConstantes.MENU3_ITEM1_TEXTO1, new Atalho(KeyCode.F8));
        adicionar_linha(BarraMenuConstantes.MENU3_ITEM2_TEXTO1, new Atalho(KeyCode.F9));
        adicionar_linha(BarraMenuConstantes.MENU3_ITEM3_TEXTO1, new Atalho(KeyCode.F10));
        adicionar_linha(BarraMenuConstantes.MENU3_ITEM4_TEXTO1, new Atalho(KeyCode.F11));
        adicionar_linha(BarraMenuConstantes.MENU3_ITEM5_TEXTO1, new Atalho(KeyCode.F12));
        adicionar_linha(BarraMenuConstantes.MENU4_ITEM1_TEXTO1, new Atalho(KeyCode.F1, false, true, false));
        adicionar_linha(BarraMenuConstantes.MENU4_ITEM2_TEXTO1, new Atalho(KeyCode.F2, false, true, false));
        adicionar_linha(BarraMenuConstantes.MENU4_ITEM3_TEXTO1, new Atalho(KeyCode.F3, false, true, false));
        adicionar_linha(BarraMenuConstantes.MENU4_ITEM4_TEXTO1, new Atalho(KeyCode.F4, false, true, false));
    
    }

    /**
     * 
     * 
     * Método para passar o foco para a próxima linha da tabela em relação a 
     * linha já selecionada.
     * 
     * @param ultimo_selecionado Um objeto do tipo <b>TuplaAcaoAtalho</b> que 
     * representa uma linha da tabela.<br>
     * 
     * 
     */
    public void delegar_foco(TuplaAcaoAtalho ultimo_selecionado) {
        int index = this.tabela.getItems().indexOf(ultimo_selecionado) + 1;

        if (index < this.tabela.getItems().size()) {
            this.tabela.getSelectionModel().select(ultimo_selecionado);
            this.tabela.getSelectionModel().selectNext();
            this.tabela.getItems().get(index).getEntrada_de_texto().requestFocus();
        }
    }

    /**
     * 
     * 
     * Método para colocar foco em determinada linha da tabela.
     * 
     * @param caixa_selecionada Um objeto do tipo <b>TuplaAcaoAtalho</b> que 
     * representa uma linha da tabela.<br>
     * 
     * 
     */
    public void adicionar_foco(TuplaAcaoAtalho caixa_selecionada) {
        this.tabela.getSelectionModel().select(caixa_selecionada);
    }
    
    /**
     * 
     * 
     * Método para pegar todas as linhas da tabela.
     * 
     * @return Um objeto do tipo <b>ObservableList</b> que representa todas as 
     * linhas da tabela.<br>
     * 
     * 
     */
    public ObservableList<TuplaAcaoAtalho> getTuplas_acao_atalho(){
        return this.tabela.getItems();
    }
    
    /**
     * 
     * 
     * Método para adicionar uma linha na tabela.
     * 
     * @param acao Um objeto do tipo <b>String</b> que representa a descrição 
     * da ação que o atalho a ser inserido pelo usuário executará.<br>
     * 
     * 
     */
    public void adicionar_linha(String acao){
        this.tabela.getItems().add(new TuplaAcaoAtalho(acao));
    }
    
    /**
     * 
     * 
     * Método para adicionar uma linha na tabela.
     * 
     * @param acao Um objeto do tipo <b>String</b> que representa a descrição 
     * da ação que o atalho a ser inserido pelo usuário executará.<br>
     * 
     * 
     */
    public void adicionar_linha(String acao, Atalho padrao){
        this.tabela.getItems().add(new TuplaAcaoAtalho(acao, padrao));
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        ((Stage) cancelar.getScene().getWindow()).close();
    }
    

}
