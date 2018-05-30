package iftorrent.arquivos;

import iftorrent.excecoes.ESExcecao;

/**
 * 
 * 
 * A interface <b>ManipuladorInterface</b> serve para a modelagem das classes 
 * filhas de <b>Manipulador</b>.
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public interface ManipuladorInterface {
    
    /**
     * 
     * 
     * Cria um objeto idêntico ao objeto que invocou o método.
     * 
     * @return Um objeto do tipo <b>Manipulador</b> que possui mesmo estado do 
     * objeto invocador.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     */
    public Manipulador copia() throws ESExcecao;
    
    /**
     * 
     * 
     * Abre os leitores e/ou escritores de arquivos do objeto. Não é 
     * compatível com o recurso <i>try-with-resources</i>.
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     */
    public void abrir() throws ESExcecao;
    
    /**
     * 
     * 
     * Fecha os leitores e/ou escritores de arquivos do objeto. Não será 
     * possível a leitura ou escrita do arquivo após a invocação. Não é 
     * compatível com o recurso <i>try-with-resources</i>. Após o uso desta 
     * classe, favor invocar este método para finalizar adequadamente as Streams.
     * 
     * @throws ESExcecao Se algum erro occorer.<br>
     * 
     * 
     */
    public void fechar() throws ESExcecao;
}
