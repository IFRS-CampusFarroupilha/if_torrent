/*
 * Métodos auxiliares de interface gráfica.
 */
package iftorrent.gui.ferramentas;

import static iftorrent.Imagens.ICONE;
import iftorrent.arquivos.ConstantesLogger;
import iftorrent.arquivos.Logger;
import iftorrent.excecoes.ImagemNaoCarregadaExcecao;
import iftorrent.gui.alertas.Alertas;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author coelho
 */
public class Auxiliar {
    
    @FunctionalInterface
    public interface Acao {

        public void acionar();

    }

    public static List<File> abre_seletor_arquivos(
            Window janela,
            String nome_extensao,
            String formato_extensao,
            File pasta_inicial) {
        FileChooser file_chooser = new FileChooser();

        file_chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(nome_extensao, formato_extensao));
        file_chooser.setInitialDirectory(pasta_inicial);
        return file_chooser.showOpenMultipleDialog(janela);
    }

    /**
     * Enum com os tipos de sistemas operacionais.
     */
    public enum OSType {
        Windows, MacOS, Linux, Outro
    };

    /**
     * Retorna o tipo de sistema operacional da máquina.
     *
     * @return OSType
     */
    private static OSType obtem_sistema_operacional() {
        OSType detectedOS = OSType.Outro;
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);

        if ((OS.contains("mac")) || (OS.contains("darwin"))) {
            detectedOS = OSType.MacOS;
        } else if (OS.contains("win")) {
            detectedOS = OSType.Windows;
        } else if (OS.contains("nux") || OS.contains("unix")) {
            detectedOS = OSType.Linux;
        }
        return detectedOS;
    }

    public static Service criar_thread(Acao acao) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        final CountDownLatch latch = new CountDownLatch(1);

                        Platform.runLater(() -> {
                            acao.acionar();
                            latch.countDown();
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        return service;
    }
    
    public static ImageView criar_imagem_tratando_erro(String url,
            TuplaXY tamanho) {
        ImageView imagem = null;

        try {
            imagem = ManipuladorImagem.criar_imagem(url, tamanho);
        } catch (ImagemNaoCarregadaExcecao ex) {
            Logger.escrever_erro(ex);
        }
        if (imagem == null) {
            Alertas.pegar_alerta_imagem(url.split("/")[url.split("/").length - 1]).showAndWait();
            Logger.escrever(ConstantesLogger.ERRO, "Ícone não encontrado.");
            imagem = new ImageView();
        }
        return imagem;
    }
    
    public static Alert criar_dialogo(
            Alert.AlertType tipo, 
            String titulo,
            String cabecalho, 
            String conteudo) {
        Alert alerta = new Alert(tipo);
        
        if (ICONE == null) {
            ((Stage) alerta.getDialogPane().getScene().getWindow()).getIcons().add(ICONE);
        }
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        alerta.initModality(Modality.APPLICATION_MODAL);
        alerta.showAndWait();
        return alerta;
    }
}
