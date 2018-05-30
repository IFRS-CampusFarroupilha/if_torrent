package iftorrent.gui.barraLateral;

import static iftorrent.gui.barraLateral.BarraLateralConstantes.*;
import iftorrent.gui.ferramentas.Comunicador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Controlador para a barra lateral da GUI.
 * 
 * @author Leonardo Bortolini
 */
public class BarraLateralControlador implements Initializable {
    /**
     * Árvore principal da barra lateral.
     */
    @FXML
    private TreeView<String> arvore;
    
    /**
     * Container no qual a árvore está situada.
     */
    @FXML
    private VBox painel;
    
    public VBox getPainel() {
        return this.painel;
    }
    
    public void mouse_clicado(MouseEvent evento){
        TreeItem<String> item = arvore.getSelectionModel().getSelectedItem();
        if(item.equals(arvore.getTreeItem(0))) Comunicador.getMenu_principal().selecionar_torrents();
        if(item.equals(arvore.getTreeItem(1))) Comunicador.getMenu_principal().selecionar_baixando();
        if(item.equals(arvore.getTreeItem(2))) Comunicador.getMenu_principal().selecionar_baixados();
        if(item.equals(arvore.getTreeItem(3))) Comunicador.getMenu_principal().selecionar_pausados();
        if(item.equals(arvore.getTreeItem(4))) Comunicador.getMenu_principal().selecionar_parados();
        if(item.equals(arvore.getTreeItem(5))) Comunicador.getMenu_principal().selecionar_historico_de_download();
        if(item.equals(arvore.getTreeItem(6))) Comunicador.getMenu_principal().selecionar_enviando();
    }
    
    /**
     * O método incitialize é usando para inicialiar a barra lateral da GUI,
     * ele adiciona todos os itens que serão disponibilizados como menu de 
     * seleção para o usuario.
     * 
     * @param url O diretório do objeto root ou null caso o mesmo não seja 
     * encontrado.
     * @param rb O recurso para localizar o objeto root ou null caso o mesmo não
     * seja encontrado.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Comunicador.setBarra_lateral(this);
        this.painel.setMinWidth(TAMANHO_MINIMO_VBOX);
        this.painel.setMaxWidth(TAMANHO_MINIMO_VBOX);
        painel.getStylesheets().add(URL_CSS1);
        arvore.setShowRoot(false);
        TreeItem<String> root = new TreeItem<>();
        TreeItem item1 = new TreeItem(TREEITEM_TEXTO1);
        TreeItem item2 = new TreeItem(TREEITEM_TEXTO2);
        TreeItem item3 = new TreeItem(TREEITEM_TEXTO3);
       // TreeItem item4 = new TreeItem(TREEITEM_TEXTO4);
       // TreeItem item5 = new TreeItem(TREEITEM_TEXTO5);
       // TreeItem item6 = new TreeItem(TREEITEM_TEXTO6);
        TreeItem item7 = new TreeItem(TREEITEM_TEXTO7);
        
        root.getChildren().addAll(item1, item2, item3, item7);
        PseudoClass subElementPseudoClass = PseudoClass.getPseudoClass("sub-tree-item");
        arvore.setCellFactory(tv -> {
            TreeCell<String> celula = new TreeCell<String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisclosureNode(null);
                    if (empty) {
                        setText("");
                        setGraphic(null);                  
                    } else {
                        setText(item);
                    }
                }
            };
            celula.treeItemProperty().addListener((obs, oldTreeItem, newTreeItem) -> {
                celula.pseudoClassStateChanged(subElementPseudoClass,
                        newTreeItem != null && newTreeItem.getParent() != celula.getTreeView().getRoot());
            });
            return celula;
        });
        VBox.setVgrow(arvore, Priority.ALWAYS);
        arvore.setRoot(root);
    }
}
