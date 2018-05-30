package iftorrent.arquivos;

import iftorrent.excecoes.EnumNaoEncontradaExcecao;

/**
 * 
 * 
 * A enum <b>Modo</b> armazena um conjunto de enums para representar modos de 
 * abertura de arquivos.
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
 *              Código
 *          </th>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Modo#LEITURA}
 *          </td>
 *          <td align="center">
 *              "r"
 *          </td>
 *          <td align="center">
 *              1
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Modo#LEITURA_ESCRITA}
 *          </td>
 *          <td align="center">
 *              "r+"
 *          </td>
 *          <td align="center">
 *              2
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Modo#REESCRITA}
 *          </td>
 *          <td align="center">
 *              "w"
 *          </td>
 *          <td align="center">
 *              3
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Modo#REESCRITA_LEITURA}
 *          </td>
 *          <td align="center">
 *              "w+"
 *          </td>
 *          <td align="center">
 *              4
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Modo#ANEXACAO}
 *          </td>
 *          <td align="center">
 *              "a"
 *          </td>
 *          <td align="center">
 *              5
 *          </td>
 *      </tr>
 *      <tr>
 *          <td align="center">
 *              {@link Modo#ANEXACAO_LEITURA}
 *          </td>
 *          <td align="center">
 *              "a+"
 *          </td>
 *          <td align="center">
 *              6
 *          </td>
 *      </tr>
 *  </table><p>
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public enum Modo {
    /**
     * 
     * 
     * Modo de somente leitura com o cursor no ínicio do arquivo. Permite o
     * posicionamento do cursor para leitura.
     * 
     * <p><table summary="" style="width:50%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Código
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "r"
     *          </td>
     *          <td align="center">
     *              1
     *          </td>
     *      </tr>
     * </table><p>
     * 
     * 
     */
    LEITURA           (1, "r"),
    
    /**
     * 
     * 
     * Modo de leitura e escrita com o cursor no ínicio do arquivo. Permite o
     * posicionamento do cursor para leitura e escrita.
     * 
     * <p><table summary="" style="width:50%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Código
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "r+"
     *          </td>
     *          <td align="center">
     *              2
     *          </td>
     *      </tr>
     * </table><p>
     * 
     * 
     */
    LEITURA_ESCRITA   (2, "r+"),
    
    /**
     * 
     * 
     * Modo de somente escrita com o cursor no ínicio do arquivo. Permite o
     * posicionamento do cursor para escrita. Caso o arquivo exista, o mesmo
     * será sobrescrito. Caso contrário, será criado um de mesmo nome.
     * 
     * <p><table summary="" style="width:50%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Código
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "w"
     *          </td>
     *          <td align="center">
     *              3
     *          </td>
     *      </tr>
     * </table><p>
     * 
     * 
     */
    REESCRITA         (3, "w"),
    
    /**
     * 
     * 
     * Modo de leitura e escrita com o cursor no ínicio do arquivo. Permite o
     * posicionamento do cursor para leitura e escrita. Caso o arquivo exista, o
     * mesmo será sobrescrito. Caso contrário, será criado um de mesmo nome.
     * 
     * <p><table summary="" style="width:50%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Código
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "w+"
     *          </td>
     *          <td align="center">
     *              4
     *          </td>
     *      </tr>
     * </table><p>
     * 
     * 
     */
    REESCRITA_LEITURA (4, "w+"),
    
    /**
     * 
     * 
     * Modo de somente anexação com o cursor no final do arquivo. Não permite o
     * posicionamento do cursor. Todo e qualquer texto será adicionado ao final
     * do arquivo.
     * 
     * <p><table summary="" style="width:50%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Código
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "a"
     *          </td>
     *          <td align="center">
     *              5
     *          </td>
     *      </tr>
     * </table><p>
     * 
     * 
     */
    ANEXACAO          (5, "a"),
    
    /**
     * 
     * 
     * Modo de leitura e anexação com o cursor no final do arquivo. Permite o
     * posicionamento do cursor para leitura. Todo e qualquer texto será
     * adicionado ao final do arquivo.
     * 
     * <p><table summary="" style="width:50%" border="1" align=center>
     *      <tr>
     *          <th>
     *              Sigla
     *          </th>
     *          <th>
     *              Código
     *          </th>
     *      </tr>
     *      <tr>
     *          <td align="center">
     *              "a+"
     *          </td>
     *          <td align="center">
     *              6
     *          </td>
     *      </tr>
     * </table><p>
     * 
     * 
     */
    ANEXACAO_LEITURA  (6, "a+");

    /** 
     * Usada para representar numéricamente as permissões.
     */
    private final int codigo;
    
    /**
     * 
     * 
     * Objeto do tipo <b>String</b> usada para representar alfabeticamente as 
     * permissões através de dois caracteres (o segundo é complementar, portanto 
     * opcional).
     * 
     * 
     */
    private final String sigla;

    /**
     * 
     * 
     * Construtor de cada enum.
     * 
     * @param codigo Um inteiro que será atribuído à constante <i>codigo</i> 
     * da enum.<br>
     * @param sigla Um objeto do tipo <b>String</b> que será atribuído à constante 
     * <i>sigla</i> da enum.<br>
     * 
     * 
     */
    Modo(int codigo, String sigla) {
        this.codigo = codigo;
        this.sigla = sigla;
    }

    /**
     * 
     * 
     * Getter da constante inteira <i>codigo</i>.
     * 
     * @return O inteiro <i>codigo</i> da enum.<br>
     * 
     * 
     */
    public int codigo() {
        return this.codigo;
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
        return this.sigla;
    }

    /**
     * 
     * 
     * Método para encontrar uma enum <b>Modo</b> a partir da sua constante 
     * inteira <i>codigo</i>.
     * 
     * @param codigo Um inteiro utilizado para a busca da enum.<br>
     * 
     * @return A enum <b>Modo</b> encontrada.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * 
     * 
     */
    public static Modo procurar_modo(int codigo) throws EnumNaoEncontradaExcecao {
        for (Modo m : Modo.values()) {
            if (m.codigo == codigo) {
                return m;
            }
        }
        throw new EnumNaoEncontradaExcecao();
    }

    /**
     * 
     * 
     * Método para encontrar uma enum <b>Modo</b> a partir da sua constante 
     * do tipo <b>String</b> <i>sigla</i>.
     * 
     * @param sigla Um objeto do tipo <b>String</b> utilizado para a busca da enum.<br>
     * 
     * @return A enum <b>Modo</b> encontrada.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * 
     * 
     */
    public static Modo procurar_modo(String sigla) throws EnumNaoEncontradaExcecao {
        for (Modo modo : Modo.values()) {
            if (modo.sigla.equalsIgnoreCase(sigla)) {
                return modo;
            }
        }
        throw new EnumNaoEncontradaExcecao();
    }

}