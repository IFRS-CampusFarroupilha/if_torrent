package iftorrent.gui.ferramentas;

import iftorrent.arquivos.Logger;
import iftorrent.excecoes.ImagemNaoCarregadaExcecao;
import iftorrent.gui.alertas.Alertas;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 * 
 *
 * A classe <b>ManipuladorImagem</b> serve para editar uma imagem.
 *
 * @author Eduardo Toffolo
 * @author Guilherme Giordani
 *
 * 
 */
public class ManipuladorImagem {

    /**
     * 
     *
     * Método para redimensionar a imagem para o tamanho desejado. É
     * recomendado, para não perder resolução, redimensionar a imagem para, no
     * máximo, metade do seu tamanho, repetindo o processo até atingir o tamanho
     * ideal.
     *
     * @param imagem Um objeto do tipo <b>BufferedImage</b> que representa a
     * imagem a ser redimensionada. <br>
     * @param largura_final Um inteiro que represnta a largura final. <br>
     * @param altura_final Um inteiro que representa a altura final. <br>
     *
     * @return Um objeto do tipo <b>BufferedImage</b> que representa a imagem
     * redimensionada.<br>
     *
     * 
     */
    public static BufferedImage redimensionar_imagem(BufferedImage imagem,
            int largura_final, int altura_final) {
        BufferedImage imagem_final = new BufferedImage(largura_final,
                altura_final, BufferedImage.TYPE_INT_ARGB);
        Graphics2D grafico_da_imagem = imagem_final.createGraphics();

        grafico_da_imagem.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        grafico_da_imagem.drawImage(imagem, 0, 0, largura_final, altura_final,
                null);
        grafico_da_imagem.dispose();
        return imagem_final;
    }

    /**
     * 
     *
     * Método para converter um objeto do tipo <b>BufferedImage</b> para um
     * objeto do tipo <b>WritableImage</b>.
     *
     * @param imagem Um objeto do tipo <b>BufferedImage</b> que representa a
     * imagem original.<br>
     *
     * @return Um objeto do tipo <b>WritableImage</b> que representa a imagem
     * convertida.<br>
     *
     * 
     */
    public static WritableImage converter_para_WritableImage(
            BufferedImage imagem) {
        WritableImage imagem_editavel = new WritableImage(
                imagem.getWidth(), imagem.getHeight());
        PixelWriter editor_de_pixel = imagem_editavel.getPixelWriter();

        for (int x = 0; x < imagem.getWidth(); x++) {
            for (int y = 0; y < imagem.getHeight(); y++) {
                editor_de_pixel.setArgb(x, y, imagem.getRGB(x, y));
            }
        }
        return imagem_editavel;
    }

    /**
     * 
     *
     * Método para redimensionar uma imagem mantendo uma boa resolução.
     *
     * @param imagem Um objeto do tipo <b>BufferedImage</b> que representa a
     * imagem a ser redimensionada.<br>
     * @param tamanho Um objeto do tipo <b>TuplaXY</b> que representa as novas
     * dimensões da imagem.<br>
     * @param taxa_de_reducao Um inteiro que represesnta em quanto os lados da
     * imagem será reduzido.<br>
     *
     * @return Um objeto do tipo <b>BufferedImage</b> que representa a imagem
     * redimensionada.<br>
     *
     * 
     *
     * @see #redimensionar_imagem(java.awt.image.BufferedImage, int, int)
     */
    public static BufferedImage redimencionar_imagem(BufferedImage imagem,
            TuplaXY tamanho, int taxa_de_reducao) {
        while (imagem.getHeight() / taxa_de_reducao > tamanho.X
                && imagem.getWidth() / taxa_de_reducao > tamanho.Y) {
            imagem = redimensionar_imagem(imagem,
                    imagem.getHeight() / taxa_de_reducao,
                    imagem.getWidth() / taxa_de_reducao);
        }
        return redimensionar_imagem(imagem, (int) tamanho.Y, (int) tamanho.X);
    }

    /**
     * 
     *
     * Método que recebe um objeto do tipo <b>BufferedImage</b> que representa a
     * imagem a ser convertida para um objeto do tipo <b>ImageView</b>.
     *
     * @param imagem Um objeto do tipo <b>BufferedImage</b> que representa a
     * imagem a ser convertida.<br>
     *
     * @return Um objeto do tipo <b>ImageView</b> que representa o objeto
     * convertido.<br>
     *
     * 
     */
    public static ImageView converter_para_ImageView(BufferedImage imagem) {
        WritableImage imagem_editavel = null;

        if (imagem != null) {
            imagem_editavel = converter_para_WritableImage(imagem);
        }
        return new ImageView(imagem_editavel);
    }

    /**
     * 
     *
     * Método que cria um objeto do tipo <b>ImageView</b> que representa uma
     * imagem através da url informada e a redimensiona para o tamanho
     * informado.
     *
     * @param tamanho Um objeto do tipo <b>TuplaXY</b> que representa as
     * dimensões da imagem. <br>
     * @param url Um objeto do tipo <b>String</b> que representa o diretório da
     * imagem. <br>
     *
     * @return Um objeto do tipo <b>ImageView</b> que representa a imagem
     * redimensionada. <br>
     *
     * @throws ImagemNaoCarregadaExcecao Se não houver sucesso no carregamento
     * da imagem.<br>
     *
     * 
     */
    public static ImageView criar_imagem(String url, TuplaXY tamanho)
            throws ImagemNaoCarregadaExcecao {
        BufferedImage imagem = null;

        try {
            imagem = ImageIO.read(new File(url));
        } catch (IOException ex) {
            throw new ImagemNaoCarregadaExcecao(ex);
        }
        imagem = redimencionar_imagem(imagem, tamanho, 2);
        if (imagem == null) {
            throw new ImagemNaoCarregadaExcecao();
        }
        return converter_para_ImageView(imagem);
    }

    /**
     * 
     *
     * Método que recebe que cria uma imagem com o tamanho especificado.
     *
     * @param tamanho Um objeto do tipo <b>TuplaXY</b> que representa as
     * dimensões da imagem. <br>
     * @param url Um objeto do tipo <b>String</b> que representa o diretório da
     * imagem. <br>
     *
     * @return Um objeto do tipo <b>ImageView</b> que representa a imagem
     * redimensionada. <br>
     *
     * 
     */
    public static ImageView criar_imagem_tratando_erro(String url,
            TuplaXY tamanho) {
        ImageView imagem = null;

        try {
            imagem = ManipuladorImagem.criar_imagem(url, tamanho);
        } catch (ImagemNaoCarregadaExcecao ex) {
            Alertas.pegar_alerta_imagem(url.split("/")[url.split("/").length - 1]).showAndWait();
            Logger.escrever_erro(ex);
        }
        return imagem;
    }

}
