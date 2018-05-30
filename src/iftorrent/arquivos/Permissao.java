package iftorrent.arquivos;

import iftorrent.excecoes.EnumNaoEncontradaExcecao;

/**
 * 
 * 
 * A enum <b>Permissao</b> armazena um conjunto de enums para representar
 * permissões de arquivos.
 *  
 * <p><table summary="" style="width:70%" border="1" align=center>
 *      <tr>
 *          <th>
 *              Enum
 *          </th>
 *          <th>
 *              Sigla
 *          </th>
 *          <th>
 *              Número
 *          </th>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Permissao#___}
 *          </td>
 *          <td align="center">
 *              "---"
 *          </td>
 *          <td align="center">
 *              0
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Permissao#__X}
 *          </td>
 *          <td align="center">
 *              "--x"
 *          </td>
 *          <td align="center">
 *              1
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Permissao#_W_}
 *          </td>
 *          <td align="center">
 *              "-w-"
 *          </td>
 *          <td align="center">
 *              2
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Permissao#_WX}
 *          </td>
 *          <td align="center">
 *              "-wx"
 *          </td>
 *          <td align="center">
 *              3
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Permissao#R__}
 *          </td>
 *          <td align="center">
 *              "r--"
 *          </td>
 *          <td align="center">
 *              4
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Permissao#R_X}
 *          </td>
 *          <td align="center">
 *              "r-x"
 *          </td>
 *          <td align="center">
 *              5
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Permissao#RW_}
 *          </td>
 *          <td align="center">
 *              "rw-"
 *          </td>
 *          <td align="center">
 *              6
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Permissao#RWX}
 *          </td>
 *          <td align="center">
 *              "rwx"
 *          </td>
 *          <td align="center">
 *              7
 *          </td>
 *      </tr>
 *  </table><p>
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public enum Permissao {
    
    /**
     * Sem permissões.
     *  
     * <p><table summary="" style="width:40%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Número
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "---"
     *          </td>
     *          <td align="center">
     *              0
     *          </td>
     *      </tr>
     *  </table><p>
     */
    ___(0, "---"),
    
    /**
     * Permissão para executar.
     *  
     * <p><table summary="" style="width:40%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Número
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "--x"
     *          </td>
     *          <td align="center">
     *              1
     *          </td>
     *      </tr>
     *  </table><p>
     */
    __X(1, "--x"),
    
    /**
     * Permissão para ler.
     *  
     * <p><table summary="" style="width:40%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Número
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "-w-"
     *          </td>
     *          <td align="center">
     *              2
     *          </td>
     *      </tr>
     *  </table><p>
     */
    _W_(2, "-w-"),
    
    /**
     * Permissão para ler e executar.
     *  
     * <p><table summary="" style="width:40%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Número
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "-wx"
     *          </td>
     *          <td align="center">
     *              3
     *          </td>
     *      </tr>
     *  </table><p>
     */
    _WX(3, "-wx"),
    
    /**
     * Permissão para escrever.
     *  
     * <p><table summary="" style="width:40%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Número
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "r--"
     *          </td>
     *          <td align="center">
     *              4
     *          </td>
     *      </tr>
     *  </table><p>
     */
    R__(4, "r--"),
    
    /**
     * Permissão para escrever e executar.
     *  
     * <p><table summary="" style="width:40%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Número
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "r-x"
     *          </td>
     *          <td align="center">
     *              5
     *          </td>
     *      </tr>
     *  </table><p>
     */
    R_X(5, "r-x"),
    
    /**
     * Permissão para escrever e ler.
     * 
     * <p><table summary="" style="width:40%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Número
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "rw-"
     *          </td>
     *          <td align="center">
     *              6
     *          </td>
     *      </tr>
     *  </table><p>
     */
    RW_(6, "rw-"),
    
    /**
     * Permissão para ler, escrever e executar.
     *  
     * <p><table summary="" style="width:40%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Número
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "rwx"
     *          </td>
     *          <td align="center">
     *              7
     *          </td>
     *      </tr>
     *  </table><p>
     */
    RWX(7, "rwx");
    
    /** 
     * Inteiro usado para representar numericamente as permissões. 
     */
    
    private final int numero;
    
    /**
     * 
     * 
     * Objeto do tipo <b>String</b> usado para representar alfabeticamente as 
     * permissões através de três caracteres.
     * 
     * 
     */
    private final String sigla;

    /**
     * 
     * 
     * Construtor de cada enum.
     * 
     * @param numero Um inteiro que será atribuído à constante <i>numero</i> da enum.<br>
     * @param sigla Um objeto do tipo <b>String</b> que será atribuído à constante 
     * <i>sigla</i> da enum.<br>
     * 
     * 
     */
    private Permissao(int numero, String sigla) {
        this.numero = numero;
        this.sigla = sigla;
    }

    /**
     * 
     * 
     * Getter da constante inteira <i>numero</i>.
     * 
     * @return O inteiro <i>numero</i> da enum.<br>
     * 
     * 
     */
    public int numero() {
        return numero;
    }

    /**
     * 
     * 
     * Getter da constante do tipo <b>String</b> <i>sigla</i>.
     * 
     * @return O objeto do tipo <b>String</b> <i>sigla</i> da enum.<br>
     * 
     * 
     */
    public String sigla() {
        return sigla;
    }

    /**
     * 
     * 
     * Método para verificar a permissão de leitura.
     * 
     * @return O boolean que representa a permissão para leitura (true e false
     * representando permitido e não permitido, respectivamente).<br>
     * 
     * 
     */
    public boolean pode_ler() {
        return this.numero > 3;
    }

    /**
     * 
     * 
     * Método para verificar a permissão de escrita.
     * 
     * @return O boolean que representa a permissão para escrita (true e false
     * representando permitido e não permitido, respectivamente).<br>
     * 
     * 
     */
    public boolean pode_escrever() {
        return this.numero % 4 > 1;
    }

    /**
     * 
     * 
     * Método para verificar a permissão de execução.
     * 
     * @return O boolean que representa a permissão para execução (true e false
     * representando permitido e não permitido, respectivamente).<br>
     * 
     * 
     */
    public boolean pode_executar() {
        return this.numero % 2 == 1;
    }

    /**
     * 
     * 
     * Método para encontrar uma enum <b>Permissão</b> a partir da sua constante 
     * inteira <i>numero</i>.
     * 
     * @param numero Um inteiro utilizado para a busca da enum.<br>
     * 
     * @return A enum <b>Permissao</b> encontrada.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * 
     * 
     */
    public static Permissao procurar_permissao(int numero) 
            throws EnumNaoEncontradaExcecao {
        for (Permissao p : Permissao.values()) {
            if (p.numero == numero) {
                return p;
            }
        }
        throw new EnumNaoEncontradaExcecao();
    }

    /**
     * 
     * 
     * Método para encontrar uma enum <b>Permissão</b> a partir da sua constante
     * do tipo String <i>sigla</i>.
     * 
     * @param sigla Um objeto do tipo <b>String</b> utilizado para a busca da enum.<br>
     * 
     * @return A enum <b>Permissao</b> encontrada ou null caso a não enum não exista.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * 
     * 
     */
    public static Permissao procurar_permissao(String sigla) throws 
            EnumNaoEncontradaExcecao {
        for (Permissao p : Permissao.values()) {
            if (p.sigla.equalsIgnoreCase(sigla)) {
                return p;
            }
        }
        throw new EnumNaoEncontradaExcecao();
    }
}
