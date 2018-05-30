package iftorrent;

import iftorrent.gui.ferramentas.Auxiliar;
import iftorrent.gui.ferramentas.TuplaXY;
import javafx.scene.image.Image;

/**
 *
 * @author Eduardo Toffolo
 */
public class Imagens {

    public static final Image ICONE = Auxiliar.criar_imagem_tratando_erro(
            "imagens/icone.png", new TuplaXY(256, 256)).getImage();

}
