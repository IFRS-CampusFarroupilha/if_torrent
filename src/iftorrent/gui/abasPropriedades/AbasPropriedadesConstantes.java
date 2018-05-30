package iftorrent.gui.abasPropriedades;

import static iftorrent.ConstantesGerais.UNIDADE_DE_INFORMACAO_PADRAO1;
import static iftorrent.ConstantesGerais.UNIDADE_DE_TEMPO_PADRAO1;
import static iftorrent.ConstantesGerais.UNIDADE_DE_VELOCIDADE_PADRAO1;
import iftorrent.gui.ferramentas.TuplaXY;

/**
 * 
 *
 * A classe <b>AbasPropriedadesConstantes</b> é utilizada para agrupar todas as
 * constantes referente ao arquivo AbasPropriedadesControlador.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class AbasPropriedadesConstantes {

    public static final TuplaXY PAINEL_TAMANHO1 = new TuplaXY(800, 300);
    public static final String TAB_MAPA_PEDACOS1 = "Mapa de Pedaços";
    public static final String TAB_INFORMACOES_NOME1 = "Informações";
    public static final String TAB_RASTREADORES_NOME1 = "Pares";
    public static final String TAB_VELOCIDADE_NOME1 = "Velocidade";
    public static final String LEGENDA_DOWNLOAD1 = "Download";
    public static final String LEGENDA_UPLOAD1 = "Upload";
    public static final String EIXO_X1_TEXTO1 = "Tempo"
            + " (" + UNIDADE_DE_TEMPO_PADRAO1 + ")";
    public static final String EIXO_Y1_TEXTO1 = "Velocidade"
            + " (" + UNIDADE_DE_VELOCIDADE_PADRAO1 + ")";
    public static final String URL_CSS1
            = "/iftorrent/gui/abasPropriedades/AbasPropriedadesCSS.css";
    public static final int LIMITE_DE_PONTOS_GRAFICO1 = 10;
    public static final int TAMANHO_LADO_QUADRADO = 25;
    public static final int TAMANHO_LADO_QUADRADO_LEGENDA = 20;
    public static final double VELOCIDADE_MAXIMA_INICIAL = 5;
    public static final String COR_PEDACOS_DISPONIVEIS1 = "blue";
    public static final String COR_PEDACOS_REQUISITADOS1 = "red";
    public static final String COR_PEDACOS_BAIXADOS1 = "green";
    public static final String COR_PEDACOS_NAO_DISPONIVEIS1 = "transparent";
    public static final int CODIGO_PEDACOS_BAIXADOS1 = 1;
    public static final int CODIGO_PEDACOS_DISPONIVEIS1 = 2;
    public static final int CODIGO_PEDACOS_REQUISITADOS1 = 3;
    public static final int CODIGO_PEDACOS_NAO_DISPONIVEIS = 4;
    public static final String INFO_ESTADO = "Estado:";
    public static final String INFO_NOME = "Nome arquivo:";
    public static final String INFO_NUMERO_PARES = "Nº pares:";
    public static final String INFO_NUMERO_PARES_CONECTADOS = "Nº pares conectados:";
    public static final String INFO_SEMEADOR = "Pode semear:";
    public static final String INFO_N_PECAS_BAIXADAS = "Peças baixadas:";
    public static final String INFO_N_PECAS_DISPOIVEIS = "Peças disponíveis:";
    public static final String INFO_N_PECAS_NAO_DISPONIVEIS = "Peças indisponíveis:";
    public static final String INFO_N_PECAS_TOTAIS = "Peças totais:";
    public static final String INFO_N_PECAS_REQUISITADAS = "Peças requisitadas:";
    public static final String INFO_PROGRESSO = "Progresso:";
    public static final String INFO_QTD_BAIXADA = "Baixado ("
            + UNIDADE_DE_INFORMACAO_PADRAO1 + "):";
    public static final String INFO_QTD_BAIXADA_EXECUCAO = "Baixado em execucao ("
            + UNIDADE_DE_INFORMACAO_PADRAO1 + "):";
    public static final String INFO_QTD_TOTAL = "Total ("
            + UNIDADE_DE_INFORMACAO_PADRAO1 + "):";
    public static final String INFO_QTD_ENVIADA = "Enviado ("
            + UNIDADE_DE_INFORMACAO_PADRAO1 + "):";
    public static final String INFO_QTD_FALTA = "Falta ("
            + UNIDADE_DE_INFORMACAO_PADRAO1 + "):";
    public static final String INFO_NOME_SUBARQUIVOS = "Nomes dos subarquivos: ";
    public static final String INFO_NUMERO = "Número torrent:";
    public static final String INFO_MAX_VELOCIDADE_DOWNLOAD = "Veloc. máx. de download:";
    public static final String INFO_MAX_VELOCIDADE_UPLOAD = "Veloc. máx. de upload:";
    public static final int ESPACAMENTO_COLUNAS_INFO = 10;
    public static final int ESPACAMENTO_LINHAS_INFO = 10;
    public static final int ESPACAMENTO_META_DADOS_E_DADOS_INFO = 5;
    public static final int DELAY_THREAD_SELECAO_DE_ITENS = 1000; //em milisegundos
    public static final int DELAY_THREAD_ATUALIZAR_MAPA = 10000; //em milisegundos
    public static final int DELAY_THREAD_ATUALIZAR_INFO = 10000; //em milisegundos
    public static final int DELAY_THREAD_ATUALIZAR_GRAFICO = 1000; //em milisegundos
    public static final int INTERVALO_TEMPO_GRAFICO = DELAY_THREAD_ATUALIZAR_GRAFICO / 1000; //em segundos
    public static final String TEXTO_COLUNA_NUMERO = "Número";
    public static final String TEXTO_COLUNA_IP = "IP";
    public static final String TEXTO_COLUNA_CONECTADO = "Estado";
    public static final String TEXTO_COLUNA_CHOKED = "Pode enviar";
    public static final String TEXTO_COLUNA_CHOKING = "Pode baixar";
    public static final String TEXTO_COLUNA_PEDACO = "Pedaço";
    public static final String TEXTO_COLUNA_PORTA = "Porta";
    public static final double TAMANHO_COLUNA_NUMERO = 100;
    public static final double TAMANHO_COLUNA_IP = 110;
    public static final double TAMANHO_COLUNA_CONECTADO = 110;
    public static final double TAMANHO_COLUNA_BLOQUEADO = 100;
    public static final double TAMANHO_COLUNA_NOS_BLOQUEANDO = 100;
    
}
