package iftorrent.gui.ferramentas;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * 
 *
 * A classe <b>CampoDeTextoOuvinte</b> serve para controlar o evento de mudança 
 * de foco de um campo de texto.
 * <p>
 * Esta classe implementa <b>ChangeListener{@code <Boolean>}</b>.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class CampoDeTextoOuvinte implements ChangeListener<Boolean> {

    /**
     * 
     * 
     * Um objeto do tipo <b>TextField</b> que representa o campo de texto que 
     * desencadeará o evento.
     * 
     * 
     */
    private TextField campo_de_texto;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>String</b> que representa a cor do texto do campo 
     * de texto quando o mesmo estiver sem foco. Por padrão, é inicializado com 
     * a cor preta.
     * 
     * 
     */
    private String cor_quando_focado = "BLACK";
    
    /**
     * 
     * 
     * Um objeto do tipo <b>String</b> que representa a cor do texto do campo 
     * de texto quando o mesmo estiver com foco. Por padrão, é inicializado com 
     * a cor preta.
     * 
     * 
     */
    private String cor_quando_desfocado = "BLACK";
    
    /**
     * 
     * 
     * Um objeto do tipo <b>String</b> que representa o conteúdo do campo 
     * de texto quando o mesmo estiver sem foco. Por padrão, é inicializado com 
     * uma literal vazia.
     * 
     * 
     */
    private String conteudo_quando_focado = "";
    
    /**
     * 
     * 
     * Um objeto do tipo <b>String</b> que representa o conteúdo do campo 
     * de texto quando o mesmo estiver com foco. Por padrão, é inicializado com 
     * uma literal vazia.
     * 
     * 
     */
    private String conteudo_quando_desfocado = "";

    /**
     * 
     * 
     * Construtor vazio.
     * 
     * 
     */
    public CampoDeTextoOuvinte() {
    }
    
    /**
     * 
     * 
     * Construtor que inicializa todos os atributos e deixa o campo de texto no 
     * estado de desfocado.
     * 
     * @param campo_de_texto Um objeto do tipo <b>TextField</b> que representa 
     * o campo de texto que desencadeará o evento.<br>
     * @param cor_quando_focado Um objeto do tipo <b>String</b> que representa a cor 
     * do texto do campo de texto quando o mesmo estiver sem foco. Por padrão, 
     * é inicializado com a cor preta.<br>
     * @param cor_quando_desfocado Um objeto do tipo <b>String</b> que representa a cor 
     * do texto do campo de texto quando o mesmo estiver com foco. Por padrão, 
     * é inicializado com a cor preta.<br>
     * @param conteudo_quando_focado Um objeto do tipo <b>String</b> que representa 
     * o conteúdo do campo de texto quando o mesmo estiver sem foco. Por padrão,
     * é inicializado com uma literal vazia.<br>
     * @param conteudo_quando_desfocado Um objeto do tipo <b>String</b> que representa 
     * o conteúdo do campo de texto quando o mesmo estiver com foco. Por padrão,
     * é inicializado com uma literal vazia.<br>
     * 
     * 
     */
    public CampoDeTextoOuvinte(TextField campo_de_texto, String cor_quando_focado, 
            String cor_quando_desfocado, String conteudo_quando_focado, 
            String conteudo_quando_desfocado) {
        this.campo_de_texto = campo_de_texto;
        this.cor_quando_focado = cor_quando_focado;
        this.cor_quando_desfocado = cor_quando_desfocado;
        this.conteudo_quando_focado = conteudo_quando_focado;
        this.conteudo_quando_desfocado = conteudo_quando_desfocado;
        this.invocar_tratamento_de_evento(false);
    }

    /**
     * 
     *
     * Método para mudança de foco de um campo de texto.
     *
     * @param valor_observavel <br>
     * @param valor_antigo Um booleano que representa o estado anterior em
     * relação ao foco do campo de texto.<br>
     * @param valor_novo Um booleano que representa o estado atual em relação ao
     * foco do campo de texto.<br>
     *
     * 
     */
    @Override
    public void changed(ObservableValue valor_observavel, Boolean valor_antigo,
            Boolean valor_novo) {
        if (this.campo_de_texto.getText().equals(conteudo_quando_focado)
                || this.campo_de_texto.getText().equals(conteudo_quando_desfocado)) {
            if (valor_novo) {
                this.formatar_campo_de_pesquisa(conteudo_quando_focado, cor_quando_focado);
            } else {
                this.formatar_campo_de_pesquisa(conteudo_quando_desfocado, cor_quando_desfocado);
            }
        }
    }

    /**
     * 
     *
     * Método para alterar o conteúdo e a cor da fonte de um campo de texto.
     *
     * @param conteudo Um objeto do tipo <b>String</b> que representa o conteúdo
     * a ser inserido do campo de texto.<br>
     * @param cor_do_texto Um objeto do tipo <b>String</b> que representa a cor
     * da fonte do conteúdo do campo de texto.<br>
     *
     * 
     */
    public void formatar_campo_de_pesquisa(String conteudo, String cor_do_texto) {
        this.campo_de_texto.setText(conteudo);
        this.campo_de_texto.setStyle("-fx-text-fill: " + cor_do_texto);
    }

    /**
     * 
     * 
     * Getter do campo de texto.
     * 
     * @return Um objeto do tipo <b>TextField</b> que representa o campo de 
     * texto que desencadeará o evento.<br>
     * 
     * 
     */
    public TextField getCampo_de_texto() {
        return campo_de_texto;
    }

    /**
     * 
     * 
     * Setter do campo de texto.
     * 
     * @param campo_de_texto Um objeto do tipo <b>TextField</b> que representa 
     * o campo de texto que desencadeará o evento.<br>
     * 
     * 
     */
    public void setCampo_de_texto(TextField campo_de_texto) {
        this.campo_de_texto = campo_de_texto;
    }

    /**
     * 
     * 
     * Getter da cor de texto padrão quando o campo de texto não estiver 
     * selecionado.
     * 
     * @return Um objeto do tipo <b>String</b> que representa a cor do texto 
     * do campo de texto quando o mesmo estiver sem foco. Por padrão, é 
     * inicializado com a cor preta.<br>
     * 
     * 
     */
    public String getCor_quando_focado() {
        return cor_quando_focado;
    }

    /**
     * 
     * 
     * Setter da cor de texto padrão quando o campo de texto não estiver 
     * selecionado.
     * 
     * @param cor_quando_focado Um objeto do tipo <b>String</b> que representa a cor 
     * do texto do campo de texto quando o mesmo estiver sem foco.<br>
     * 
     * 
     */
    public void setCor_quando_focado(String cor_quando_focado) {
        this.cor_quando_focado = cor_quando_focado;
    }

    /**
     * 
     * 
     * Getter da cor de texto padrão quando o campo de texto estiver 
     * selecionado.
     * 
     * @return Um objeto do tipo <b>String</b> que representa a cor do texto do 
     * campo de texto quando o mesmo estiver com foco. Por padrão, é 
     * inicializado com a cor preta.<br>
     * 
     * 
     */
    public String getCor_quando_desfocado() {
        return cor_quando_desfocado;
    }

    /**
     * 
     * 
     * Setter da cor de texto padrão quando o campo de texto estiver 
     * selecionado.
     * 
     * @param cor_quando_desfocado Um objeto do tipo <b>String</b> que representa a cor 
     * do texto do campo de texto quando o mesmo estiver com foco.<br>
     * 
     * 
     */
    public void setCor_quando_desfocado(String cor_quando_desfocado) {
        this.cor_quando_desfocado = cor_quando_desfocado;
    }

    /**
     * 
     * 
     * Getter do conteúdo padrão quando o campo de texto estiver selecionado.
     * 
     * @return Um objeto do tipo <b>String</b> que representa o conteúdo do 
     * campo de texto quando o mesmo estiver sem foco. Por padrão, é 
     * inicializado com uma literal vazia.<br>
     * 
     * 
     */
    public String getConteudo_quando_focado() {
        return conteudo_quando_focado;
    }

    /**
     * 
     * 
     * Setter do conteúdo padrão quando o campo de texto estiver selecionado.
     * 
     * @param conteudo_quando_focado Um objeto do tipo <b>String</b> que representa o 
     * conteúdo do campo de texto quando o mesmo estiver sem foco.<br>
     * 
     * 
     */
    public void setConteudo_quando_focado(String conteudo_quando_focado) {
        this.conteudo_quando_focado = conteudo_quando_focado;
    }

    /**
     * 
     * 
     * Getter do conteúdo padrão quando o campo de texto não estiver 
     * selecionado.
     * 
     * @return Um objeto do tipo <b>String</b> que representa o conteúdo do 
     * campo de texto quando o mesmo estiver com foco. Por padrão, é 
     * inicializado com uma literal vazia.<br>
     * 
     * 
     */
    public String getConteudo_quando_desfocado() {
        return conteudo_quando_desfocado;
    }

    /**
     * 
     * 
     * Setter do conteúdo padrão quando o campo de texto não estiver 
     * selecionado.
     * 
     * @param conteudo_quando_desfocado Um objeto do tipo <b>String</b> que representa 
     * o conteúdo do campo de texto quando o mesmo estiver com foco.<br>
     * 
     * 
     */
    public void setConteudo_quando_desfocado(String conteudo_quando_desfocado) {
        this.conteudo_quando_desfocado = conteudo_quando_desfocado;
    }
    
    /**
     * 
     * 
     * Método para invocar o tratamento do evento de troca foco do campo de 
     * texto.
     * 
     * @param esta_focado Um booleano que representa o estado a ser atribuído 
     * ao campo de texto (focado ou não).<br>
     * 
     * 
     */
    public void invocar_tratamento_de_evento(boolean esta_focado){
        this.changed(null, null, esta_focado);
    }

}
