package iftorrent.gui.janelaSimples;

import iftorrent.JanelaPrincipal;
import iftorrent.arquivos.torrent.ArquivoTorrentPropriedades;
import iftorrent.gui.menuPrincipal.MenuPrincipalControlador;
import java.awt.MouseInfo;
import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author 05200195
 */
public class InterfaceSimplesControlador implements Initializable {

    @FXML
    ListView lista;

    @FXML
    public ListView getLista() {
        return lista;
    }
    
    @FXML
    Button btn;

    @FXML
    HBox hbox, caixaTexto;
    ObservableList arquivos;

    Tooltip tooltip = new Tooltip();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //HBox.setHgrow(lista, Priority.NEVER);
        arquivos = FXCollections.observableArrayList();

        arquivos.setAll(MenuPrincipalControlador.itens);
        lista.setItems(arquivos);
        //lista.setPrefHeight(10);
        lista.setMinWidth(arquivos.size() * 185);
        lista.setMaxWidth(arquivos.size() * 185);
        btn.setMinHeight(75);
        if (arquivos.size() == 0){
            btn.setMinWidth(75);
            btn.setText("Lista vazia - clique para voltar");
        }
    
        lista.setCellFactory((list) -> new ColorRectCell());
    }

    static class ColorRectCell extends ListCell<ArquivoTorrentPropriedades> {

        @Override
        public void updateItem(ArquivoTorrentPropriedades item, boolean empty) {
            super.updateItem(item, empty);
            StringBuffer outputBuffer;
            int tamanho;
            String espacos;

            if (item != null) {
                if (item.getNome().length() < 30) {
                    tamanho = 30 - item.getNome().length();
                    outputBuffer = new StringBuffer(tamanho);
                    espacos = concatenarString(tamanho, outputBuffer);
                    setText(item.getNome() + espacos);
                } else {
                    setText(item.getNome().substring(0, 30));
                }
                //setMaxWidth(100);
            }
            if (empty || item == null) {
                setMaxWidth(0);
            }
        }
    }

    public static String concatenarString(int tamanho, StringBuffer outputBuffer) {

        for (int i = 0; i < tamanho; i++) {
            outputBuffer.append(" ");
        }

        return outputBuffer.toString();
    }

    @FXML
    public void handleMousePressed(MouseEvent event) {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();

        String texto = (((ArquivoTorrentPropriedades) lista.getSelectionModel().getSelectedItem()).getNome() + ":"
                + "\n Estado:" + ((ArquivoTorrentPropriedades) lista.getSelectionModel().getSelectedItem()).getEstado()
                + "\n Downloaded: " + String.valueOf(((ArquivoTorrentPropriedades) lista.getSelectionModel().getSelectedItem()).getDownloaded())
                + "\n Falta:" + String.valueOf(((ArquivoTorrentPropriedades) lista.getSelectionModel().getSelectedItem()).getFalta())
                + "\n Progresso:" + String.format("%.2f", ((ArquivoTorrentPropriedades) lista.getSelectionModel().getSelectedItem()).getProgresso() * 100) + "%");
        tooltip.setX(mouseLocation.getX());
        tooltip.setY(mouseLocation.getY());
        tooltip.setText(texto);
        tooltip.show(getLista().getScene().getWindow());
    }

    public void handleMouseReleased(MouseEvent event) {        
        tooltip.hide();
    }
    
    public void handleVoltarClickedEvent(ActionEvent event){
        JanelaPrincipal.stage_principal.show();
        ((Stage) (btn.getScene().getWindow())).close();     
    }
}
