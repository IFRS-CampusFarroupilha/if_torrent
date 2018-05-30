package iftorrent.conexao;

/**
 * Enum que contém os tipos de finalização de um socket, referentes à quantidade
 * de dados (ByteBuffer's contidos na lista de envio) que será enviada após a
 * requisição de encerramento de um socket.
 *
 * @author Garren Souza
 */
public enum TiposFinalizacao {
    DIE(1),
    ONE_BREATHE(2),
    GO_TO_BED(3),
    FIRST_FINISH_THAT(4);

    int tipo;

    /**
     * Método que retorna o valor da respectiva constantes, sendo que <b>DIE</b>
     * representa a finalização imediata, <b>ONE_BREATHE</b> permite o envio de
     * apenas 1/3 da lista de dados, <b>GO_TO_BED</b> permite o envio de 2/3 da
     * lista e <b>FIRST_FINISH_THAT</b> permite o envio de tudo o que houver na
     * lista até o momento.
     *
     *
     * @return
     */
    public int obter_tipo() {
        return tipo;
    }

    TiposFinalizacao(int tipo) {
        this.tipo = tipo;
    }
}
