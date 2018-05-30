package iftorrent.gui.tour;

import static iftorrent.gui.ferramentas.ManipuladorImagem.criar_imagem_tratando_erro;
import iftorrent.gui.ferramentas.TuplaXY;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * CLASSE EM CONSTRUÇÃO
 */
public class Mouse extends Pane{

    public ImageView img_mouse;

    public Mouse(String imagem, TuplaXY tamanho) {
        this.img_mouse = criar_imagem_tratando_erro(imagem, tamanho);
        
        getChildren().addAll(this.img_mouse);
    }

    public void mover_mouse(double duracao_seg, TuplaXY... pontos) {
        Path caminho = new Path();
        
        for (TuplaXY ponto : pontos) {
            caminho.getElements().add(new MoveTo(ponto.X, ponto.Y));
        }
        PathTransition pathTransition = new PathTransition();

        pathTransition.setDuration(Duration.seconds(duracao_seg));
        pathTransition.setPath(caminho);
        pathTransition.setNode(img_mouse);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();
    }
}
