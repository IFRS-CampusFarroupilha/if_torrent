package iftorrent.gui.alertas;

import static iftorrent.Imagens.ICONE;
import static iftorrent.gui.alertas.ConstantesAlertas.*;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * A classe <b>Alertas</b> é utilizada para agrupar todas as janelas de alertas
 * padrão que serão utilizadas.
 *
 * @author Eduardo Toffolo
 */
public class Alertas {

    /**
     * 
     *
     * Método que retorna um alerta de erro ao carregar imagens.
     *
     * @param conteudo Um objeto do tipo <b>String</b> que representa o conteúdo
     * do alerta.<br>
     *
     * @return Um objeto do tipo <b>Alert</b> que representa a janela de
     * alerta.<br>
     *
     * 
     */
    public static Alert pegar_alerta_imagem(String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle(IMAGEM_ERRO_TITULO1);
        alerta.setHeaderText(IMAGEM_ERRO_CABECALHO1);
        alerta.setContentText(IMAGEM_ERRO_CONTEUDO1 + conteudo);
        Stage janela = (Stage) alerta.getDialogPane().getScene().getWindow();

        if (ICONE != null) {
            janela.getIcons().add(ICONE);
        }
        alerta.initModality(Modality.APPLICATION_MODAL);
        return alerta;
    }

}
