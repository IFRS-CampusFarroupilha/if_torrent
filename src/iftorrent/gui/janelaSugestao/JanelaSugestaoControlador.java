package iftorrent.gui.janelaSugestao;

import iftorrent.ConstantesGerais;
import iftorrent.JanelaPrincipal;
import iftorrent.arquivos.Logger;
import static iftorrent.gui.ferramentas.Auxiliar.*;
import static iftorrent.gui.janelaSugestao.ConstantesJanelaSugestao.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author Guilherme Giordani
 * @author Eduardo Toffolo
 */
public class JanelaSugestaoControlador implements Initializable {

    @FXML
    private VBox painel;
    @FXML
    private HBox painel_botoes, painel_arquivos, painel_email;
    @FXML
    private Label titulo, inserir_email;
    @FXML
    private TextArea texto;
    @FXML
    private Button cancelar, enviar, anexar_arquivos;
    @FXML
    private ListView arquivos;
    @FXML
    private TextField campo_email;

    private List<File> arquivos_anexados = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        painel.setPrefWidth(JANELA_SUGESTAO_TAMANHO1.X);
        painel_botoes.setAlignment(Pos.CENTER_RIGHT);
        painel_botoes.setSpacing(ESPACAMENTO_ENTRE_BOTOES);
        painel_arquivos.setAlignment(Pos.CENTER_LEFT);
        painel.setSpacing(ESPACAMENTO_ENTRE_COMPONENTES);
        painel.setAlignment(Pos.CENTER);
        painel.setPadding(JANELA_SUGESTAO_BORDAS);
        titulo.setText(JANELA_SUGESTAO_TITULO);
        titulo.setFont(new Font(TAMANHO_FONTE_TEXTO_TITULO));
        titulo.setPrefHeight(ALTURA_MAXIMA_TITULO);
        titulo.setAlignment(Pos.CENTER);
        enviar.setText(BOTAO_SUBMETER_TEXTO);
        anexar_arquivos.setText(BOTAO_ANEXAR_ARQUIVOS_TEXTO);
        cancelar.setText(BOTAO_CANCELAR_TEXTO);
        inserir_email.setText(LABEL_ADICIONAR_DESTINATARIO_TEXTO);
        inserir_email.setPrefHeight(ALTURA_MAXIMA_LABEL_DESTINATARIOS);
        enviar.setFont(new Font(TAMANHO_FONTE_TEXTO_GERAL));
        cancelar.setFont(new Font(TAMANHO_FONTE_TEXTO_GERAL));
        anexar_arquivos.setFont(new Font(TAMANHO_FONTE_TEXTO_GERAL));
        arquivos.setMaxHeight(ALTURA_MAXIMA_LISTVIEW_ANEXOS);
        painel_email.setSpacing(ESPACAMENTO_ENTRE_BOTOES);
        painel_email.setAlignment(Pos.CENTER);
        VBox.setVgrow(texto, Priority.ALWAYS);
        HBox.setHgrow(campo_email, Priority.ALWAYS);
    }

    /**
     * Método para caso o botão enviar for clicado, é mandado um e-mail para o
     * grupo do IFTorrent.
     *
     * @param event
     */
    @FXML
    private void enviar(ActionEvent event) {
        criar_thread(() -> {
            MultiPartEmail email = new MultiPartEmail();

            try {
                email.setHostName(NOME_HOST_EMAIL1);
                email.addTo(ENDERECO_EMAIL1);
                email.setFrom(ENDERECO_EMAIL1, NOME_ENDERECO_EMAIL1);
                email.setSubject(ASSUNTO_EMAIL1 + ": " + JanelaPrincipal.usuario);
                email.setMsg(texto.getText());
                email.setAuthentication(ENDERECO_EMAIL1, SENHA_EMAIL1);
                email.setSmtpPort(PORTA_HOST_EMAIL1);
                email.setSSLOnConnect(CRIPTOGRAFIA_SSL_EMAIL1);
                email.attach(criar_anexo(NOME_ANEXO_LOGGER,
                        DESCRICAO_ANEXO_LOGGER, DIRETORIO_ANEXO_LOGGER));
                if (!campo_email.getText().isEmpty()) {
                    for (String destinatario : campo_email.getText().split(
                            CARACTER_SEPARADOR_EMAILS)) {
                        email.addTo(destinatario);
                    }
                }
                for (File arquivo : arquivos_anexados) {
                    email.attach(arquivo);
                }
                email.send();
                cancelar(null);
                criar_dialogo(Alert.AlertType.INFORMATION,
                        ALERTA_ENVIAR_EMAIL_SUCESSO_TITULO1,
                        ALERTA_ENVIAR_EMAIL_SUCESSO_CABECALHO1,
                        ALERTA_ENVIAR_EMAIL_SUCESSO_CONTEUDO1);
            } catch (EmailException ex) {
                Logger.escrever_erro(ex);
                criar_dialogo(Alert.AlertType.ERROR,
                        ALERTA_ENVIAR_EMAIL_ERRO_TITULO1,
                        ALERTA_ENVIAR_EMAIL_ERRO_CABECALHO1,
                        ALERTA_ENVIAR_EMAIL_ERRO_CONTEUDO1);
            }
        }).start();
    }

    private EmailAttachment criar_anexo(String nome, String descricao,
            String diretorio) {
        EmailAttachment anexo = new EmailAttachment();

        anexo.setPath(diretorio);
        anexo.setDisposition(EmailAttachment.ATTACHMENT);
        anexo.setDescription(descricao);
        anexo.setName(nome);
        return anexo;
    }

    /**
     * Método para caso o botão cancelar for clicado, a janela de sujestão será
     * ocultada.
     *
     * @param event
     */
    @FXML
    private void cancelar(ActionEvent event) {
        ((Stage) cancelar.getScene().getWindow()).close();
    }

    @FXML
    private void selecionar_arquivos() {
        arquivos_anexados = abre_seletor_arquivos(new Stage(),
                NOME_DA_EXTENSAO_PARA_ANEXO1, EXTENSAO_PARA_ANEXO1,
                new File(ConstantesGerais.DIRETORIO_PADRAO));
        if (arquivos_anexados == null) arquivos_anexados = new ArrayList();
        arquivos.setItems(FXCollections.observableList(arquivos_anexados));
    }

}
