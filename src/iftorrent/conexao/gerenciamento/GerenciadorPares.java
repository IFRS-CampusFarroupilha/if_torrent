package iftorrent.conexao.gerenciamento;

import iftorrent.arquivos.torrent.ArquivoTorrentPropriedades;
import iftorrent.conexao.MonitorTCP;
import java.util.ArrayList;

/**
 * Classe de controle das operações realizadas sobre os pares
 *
 * @author Garren Souza
 */
public class GerenciadorPares {

    private MonitorTCP monitor;
    private int limite_pares;
    public ArrayList<Par> pares;
    private Limitador limitador;
    private GerenciadorPedacos gerenciador_pedacos;
    private ArquivoTorrentPropriedades propriedades;
    /*
    TODO
    Leitura do arquivo torrent
    Obtenção de pares
    Consulta disponibilidade de pedacos e constrói fila de requisicoes
    Distribui requisicoes entre os pares
    Comunicacao com Gerenciador Pedacos
        Checa finalizacao do download
    Checagem de Tracker
    Atualização de estatísticas
    Busca chunks disponiveis
    verifica seeds
    verificar reqisições e processo de upload
     */
}
