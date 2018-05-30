package iftorrent.conexao.gerenciamento;


import iftorrent.conexao.SocketTCP;
import iftorrent.conexao.par.EstatisticasPar;
import iftorrent.conexao.par.Requisicao;
import java.util.List;

/**
 * Classe que representa um par [em fase de planejamento]
 *
 * @author Garren Souza
 */
public class Par {

    private SocketTCP socket;
    private long limite_atual;
    private byte[] identificador;
    private EstatisticasPar estatisticas;
    private boolean[] tem_pedacos;
    private int pedacos;
    private boolean semeando;
    private int tamanho_fila_requisicoes;
    private List<Requisicao> requisicoes;

    /**
     * Método de construção da classe Par
     *
     * @param socket Objeto de tipo SocketChannel
     * @param gerenciador Lista de pares que pertence à uma classe do tipo
     * GerenciadorPares
     */
    public Par(SocketTCP socket, GerenciadorPares gerenciador) {
        this.socket = socket;
        gerenciador.pares.add(this);
    }

    /**
     * Adiciona uma requisição à fila de requisições do Par no caso de o pedaco
     * correspondente ao <b>numero_pedaco</b> já tiver sido baixado pelo cliente
     * remoto ao qual este Par se encontra conectado.
     *
     * @param numero_pedaco - int Número que representa o pedaco
     * @param tamanho_pedaco - tamanho do pedaco (consta no mapa de informações
     * contido pela classe GerenciadorPares)
     * @return boolean - verdadeiro se e apenas se a requisicao for adicionada
     * ao Par
     */
    public boolean adiciona_requisicao(int numero_pedaco, int tamanho_pedaco) {
        if (tem_pedaco(numero_pedaco)) {
            if (this.requisicoes.size() < this.tamanho_fila_requisicoes) {
                this.requisicoes.add(this.cria_requisicao(numero_pedaco, tamanho_pedaco));
                return true;
            }
        }
        return false;
    }

    /**
     * Método que confere à existência de um pedaco neste Par através de análise
     * do BitField
     *
     * @param numero_pedaco - Número do pedaco que se deseja checar existencia
     * no Par
     * @return boolean - Retorna verdadeiro se e apenas se o pedaco existir na
     * lista de pedacos do Par em questão
     */
    public boolean tem_pedaco(int numero_pedaco) {
        return (tem_pedacos[(this.pedacos - 1) - numero_pedaco]);
    }

    /**
     * Método que instância um objeto de tipo Requisição construindo o mesmo com
     * os dados recebidos através dos parâmetros <b>numero_pedaco</b> e
     * <b>tamanho_pedaco</b>
     *
     * @param numero_pedaco - numero do pedaco (numero correspondente no
     * bitfield)
     * @param tamanho_pedaco - tamanho do pedaco (consta no mapa de informações
     * contido pela classe GerenciadorPares)
     * @return Requisicao - Objeto do tipo Requisicao
     */
    public Requisicao cria_requisicao(int numero_pedaco, int tamanho_pedaco) {
        int compensacao = (numero_pedaco - 1) * tamanho_pedaco;
        return new Requisicao(numero_pedaco, compensacao, tamanho_pedaco);
    }

    /**
     * Método que certifica igualdade entre pares através de comparação do
     * identificador(PeerID)
     *
     * @param objeto Objeto sobre o qual se deseja realizar o teste de igualdade
     * @return boolean - verdadeiro no caso de tratar-se do mesmo objeto
     * (valores e tipo)
     */
    public boolean equals(Object objeto) {
        if (objeto.getClass() != this.getClass()) {
            return false;
        }
        Par par = (Par) objeto;

        for (int i = 0; i < par.identificador.length; i++) {
            if (this.identificador[i] != par.identificador[i]) {
                return false;
            }
        }
        return true;
    }

    /*
    Implementar limitador dinamico da fila de requisicoes, será utilizado por exemplo, 
    no caso de haver uma boa taxa de download, o que faria com que este par pudesse 
    ter uma fila de requisições um pouco maior, compensando deficiencias em pares mais 
    lentos, que teriam as suas filas diminuídas, aumentando o throughtput de rede, sem
    ter que necessáriamente encontrar outros pares, o que pesaria em processamento e uso 
    de memória
     */

    public void envia_requisicao() {

    }
}
