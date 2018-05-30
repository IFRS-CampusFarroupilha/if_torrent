package iftorrent.gui.janelaSugestao;

import iftorrent.gui.ferramentas.TuplaXY;
import javafx.geometry.Insets;

/**
 * 
 *
 * A classe <b>JanelaSugestãoConstantes</b> é utilizada para agrupar todas as
 * constantes referente aos dados para envio de relatórios via e-mail.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class ConstantesJanelaSugestao {

    public static final TuplaXY JANELA_SUGESTAO_TAMANHO1
            = new TuplaXY(510, 320);
    public static final String ENDERECO_EMAIL1
            = "iftorrent@farroupilha.ifrs.edu.br";
    public static final String SENHA_EMAIL1
            = "iftorrent123*";
    public static final String NOME_ENDERECO_EMAIL1
            = "IFTorrent: Compartilhando arquivos e ideias";
    public static final String ASSUNTO_EMAIL1
            = "Relatório de Erro";
    public static final String NOME_HOST_EMAIL1
            = "smtp.gmail.com";
    public static final int PORTA_HOST_EMAIL1
            = 465;
    public static final boolean CRIPTOGRAFIA_SSL_EMAIL1
            = true;
    public static final String EXTENSAO_PARA_ANEXO1
            = ".*";
    public static final String NOME_DA_EXTENSAO_PARA_ANEXO1
            = "Todos arquivos";
    public static String ALERTA_ENVIAR_EMAIL_ERRO_TITULO1
            = "Relatório";
    public static String ALERTA_ENVIAR_EMAIL_ERRO_CABECALHO1
            = "O relatório não foi enviado";
    public static String ALERTA_ENVIAR_EMAIL_ERRO_CONTEUDO1
            = "Ocorreu um erro no envio do relatório!"
            + "\nPor favor, tente novamente.";
    public static String ALERTA_ENVIAR_EMAIL_SUCESSO_TITULO1
            = "Relatório";
    public static String ALERTA_ENVIAR_EMAIL_SUCESSO_CABECALHO1
            = "Relatório enviado";
    public static String ALERTA_ENVIAR_EMAIL_SUCESSO_CONTEUDO1
            = "O relatório foi enviado com sucesso!";
    public static String NOME_ANEXO_LOGGER
            = "Logger.txt";
    public static final String DESCRICAO_ANEXO_LOGGER
            = "Registro de atividades do programa";
    public static final String DIRETORIO_ANEXO_LOGGER
            = "log.txt";
    public static final String CARACTER_SEPARADOR_EMAILS
            = "-";
    public static final Insets JANELA_SUGESTAO_BORDAS
            = new Insets(10, 20, 10, 20);
    public static final double ESPACAMENTO_ENTRE_COMPONENTES
            = 20;
    public static final double ESPACAMENTO_ENTRE_BOTOES
            = 10;
    public static final double TAMANHO_FONTE_TEXTO_GERAL
            = 13;
    public static final double TAMANHO_FONTE_TEXTO_TITULO
            = 20;
    public static final String JANELA_SUGESTAO_TITULO
            = "Insira uma descrição para o erro ou para a "
            + "\n           sugestão no campo abaixo:        ";
    public static final String BOTAO_SUBMETER_TEXTO
            = "Submeter";
    public static final String BOTAO_CANCELAR_TEXTO
            = "Cancelar";
    public static final String BOTAO_ANEXAR_ARQUIVOS_TEXTO
            = "Anexar arquivo(s)";
    public static final String LABEL_ADICIONAR_DESTINATARIO_TEXTO
            = "Insira o(s) e-mail(s) para resposta "
            + "\n           (separados por \"" + CARACTER_SEPARADOR_EMAILS + "\"):";
    public static final double ALTURA_MAXIMA_LABEL_DESTINATARIOS
            = 50;
    public static final double ALTURA_MAXIMA_TITULO
            = 70;
    public static final double ALTURA_MAXIMA_LISTVIEW_ANEXOS
            = 100;
}
