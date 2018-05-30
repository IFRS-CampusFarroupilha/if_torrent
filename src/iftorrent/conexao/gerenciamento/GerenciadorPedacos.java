package iftorrent.conexao.gerenciamento;

import iftorrent.criptografia.IntegridadeSHA1;
import iftorrent.arquivos.GerenciadorDisco;
import java.util.List;

/**Classe de gerenciamento dos pedaços de um arquivo torrent
 *
 * @author Garren Souza
 */
public class GerenciadorPedacos {

    private IntegridadeSHA1 hasher;
    private GerenciadorDisco gerenciador_disco;
    private FilaRequisicoes fila_requisicoes;
    List<byte[]> hashs;
    
}
