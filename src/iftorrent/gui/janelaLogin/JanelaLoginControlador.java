package iftorrent.gui.janelaLogin;

import static iftorrent.Imagens.ICONE;
import iftorrent.JanelaPrincipal;
import iftorrent.gui.ferramentas.Auxiliar;
import iftorrent.gui.ferramentas.Comunicador;
import iftorrent.gui.ferramentas.TuplaXY;
import static iftorrent.gui.janelaLogin.ConstantesJanelaLogin.IMAGEM_JANELA_LOGIN;
import static iftorrent.gui.janelaLogin.ConstantesJanelaLogin.TITULO_JANELA_LOGIN;
import static iftorrent.gui.janelaLogin.ConstantesJanelaLogin.URL_FXML_JANELA_LOGIN;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Otávio Farinon
 * @author Eduardo Toffolo
 * @author Rafael Coelho
 */
public class JanelaLoginControlador implements Initializable {

    @FXML
    private ImageView icone;

    @FXML
    private TextField usuario, senha;

    public boolean entrou = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Comunicador.setJanelaLogin(this);
        Image imagem = Auxiliar.criar_imagem_tratando_erro(IMAGEM_JANELA_LOGIN, 
                new TuplaXY(200, 200)).getImage();
        
        if(imagem != null) icone.setImage(imagem);
    }

    @FXML
    private void tecla_pressionada(KeyEvent evento) {
        if (evento.getCode() == KeyCode.ENTER) {
            fazer_login();
        }
    }

    @FXML
    private void entrar(ActionEvent event) {
        fazer_login();
    }

    private void fazer_login() {
        //try {
            usuario.setText("anacosta");
            senha.setText("ana123*");
            JanelaPrincipal.usuario = usuario.getText();
           
            //DEVE SER REMOVIDO E O CÓDIGO ABAIXO DESCOMENTADO NA REDE LOCAL
            //ATUALMENTE, A CONEXÃO COM O BD NÃO FUNCIONA NA REDE FORA DO IFRS.
            usuario_fez_login();
            
            /*String nome;
            String password;

            nome = usuario.getText();
            password = senha.getText();
            int codigo_verificacao = LDAP.verifica_senha(nome, password);
            DaoUsuario dao = new DaoUsuario();

            if (codigo_verificacao == ConstantesConexao.OK_CONEXAO_LDAP
                    || (codigo_verificacao == ConstantesConexao.ERRO_CONEXAO_LDAP
                    && dao.usuario_existe_bd(nome, password))) {
                usuario_fez_login();
            } else {
                if (!(dao.usuario_existe_bd(nome, password))) {
                    dao.adicionar(new Usuario(nome, Hash.hash(password, Algoritmos.SHA_3_256, Charsets.CHARSET_UTF_8)));
                }
                if (dao.verifica_senha(nome, password)) {
                    usuario_fez_login();
                } else {
                    Logger.escrever(ConstantesLogger.INICIALIZACAO, "Falha na autenticação: usuário/senha inválidos.");
                    Auxiliar.criar_dialogo(Alert.AlertType.ERROR, "Erro", "", "Falha na autenticação: usuário/senha inválidos.");
                }
            }
        } catch (Exception exception) {
            Logger.escrever_erro(exception);
            Auxiliar.criar_dialogo(Alert.AlertType.ERROR, "Erro", "", "Falha na autenticação no LDAP");
        }
*/
    }

    private void usuario_fez_login() {
        entrou = true;
        ((Stage) usuario.getParent().getScene().getWindow()).close();
    }

    @FXML
    private void sair(ActionEvent event) {
        ((Stage) usuario.getParent().getScene().getWindow()).close();
    }

    public static Stage criarJanela() throws IOException {
        Parent componentes = FXMLLoader.load(
                JanelaLoginControlador.class.getResource(URL_FXML_JANELA_LOGIN));
        Scene cena = new Scene(componentes);
        Stage janela = new Stage();

        janela.setTitle(TITULO_JANELA_LOGIN);
        janela.setScene(cena);
        if (ICONE != null) {
            janela.getIcons().add(ICONE);
        }
        janela.setResizable(false);
        return janela;
    }
}
