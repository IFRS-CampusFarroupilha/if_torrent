package iftorrent;

import static iftorrent.ConstantesGerais.DIRETORIO_ATUAL;
import static iftorrent.ConstantesGerais.EXTENSAO_ARQUIVOS;
import static iftorrent.gui.janelaPrincipal.ConstantesJanelaPrincipal.NOME_ARQUIVO;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author coelho
 */
public class PreCarregador extends Preloader {

    private Label progress;
    private ProgressBar bar;
    private Stage stage;
    private Scene scene;

    @Override
    public void init() throws Exception {
        super.init();
        Platform.runLater(() -> {
            createPreloaderScene();
        });
    }

    private void createPreloaderScene() {
        bar = new ProgressBar();
        progress = new Label("Inicializando torrents: 0%");
        VBox vbox = new VBox(bar, progress);
        BorderPane p = new BorderPane();

        vbox.setAlignment(Pos.CENTER);
        p.setCenter(vbox);
        scene = new Scene(p, 300, 150);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.setScene(scene);
        File arquivo = new File(DIRETORIO_ATUAL + NOME_ARQUIVO + EXTENSAO_ARQUIVOS);
        
        if(!arquivo.exists()){
            arquivo.createNewFile();
        }
        List<String> linhas = Files.readAllLines(arquivo.toPath(), StandardCharsets.UTF_8);

        if (!linhas.isEmpty()) {
            this.stage.show();
        } else {
            this.stage.hide();
        }
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if (info instanceof ProgressNotification) {
            progress.setText("Inicializando torrents: " + ((ProgressNotification) info).getProgress() + "%");
            bar.setProgress(((ProgressNotification) info).getProgress() / 100);
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();

        switch (type) {
            case BEFORE_LOAD:
            case BEFORE_INIT:
                break;
            case BEFORE_START:
                stage.hide();
                break;
        }
    }
}
