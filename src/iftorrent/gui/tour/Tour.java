package iftorrent.gui.tour;

import iftorrent.gui.ferramentas.TuplaXY;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Classe de testes do Tour
 */
//NÃO È PRA VALER, NÃO PRECISA FAZER REVISÃO DESSA CLASSE AQUI!!!!//
public class Tour extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Mouse mause = new Mouse("Imagens/mouse.png", new TuplaXY(22,10));
            
        Quadrado teste = new Quadrado(100, 150, true, 6, Color.RED);
        teste.setTranslateX(100);
        teste.setTranslateY(100);
        
        BalaoDialogo teste2 = new BalaoDialogo("OESTE", true, "Como você pode ver,\n pois não é cego.\nAINDA BEM",
                null, null);
        teste2.setTranslateX(300);
        teste2.setTranslateY(300);
        Pane root = new Pane(teste, teste2, mause);
        teste.aparecer(1.3, 1.3, 0.5);
        teste2.aparecer_triangulo(1.3, 1.3, 0.5);
        teste2.aparecer_balao(1.3, 1.3, 0.5);
        teste2.animacao_escrita(0.1);
        mause.mover_mouse(1, new TuplaXY(150,70), new TuplaXY(0, 0));
        
        Scene cena = new Scene(root, 800, 600);
        stage.setTitle("teste");
        stage.setScene(cena);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
