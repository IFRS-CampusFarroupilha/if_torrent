package iftorrent.gui.tour.apresentacoes;

import iftorrent.JanelaPrincipal;
import iftorrent.gui.tour.ConstantesFormas;
import iftorrent.gui.tour.Forma;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ApresentarSoftware{
    public static Window janela = JanelaPrincipal.stage_principal.getScene().getWindow();
    public static Stage palco = JanelaPrincipal.stage_principal;
    public static Pane panela = JanelaPrincipal.pane_principal;
    public static double largura_janela = janela.getWidth();
    public static double altura_janela = janela.getHeight();
    public static Pane cena = new Pane();
    public static Forma btn_pular_tour;
    
    public static void comecar(){
        btn_pular_tour = ConstantesFormas.botaoPadrao("Pular Tour");
        mudar_posicao(btn_pular_tour, largura_janela - btn_pular_tour.getLargura() - 20,
                altura_janela - btn_pular_tour.getAltura() - 40);
        btn_pular_tour.translateXProperty().bind(palco.widthProperty().add(-btn_pular_tour.getLargura() - 20));
        btn_pular_tour.translateYProperty().bind(palco.heightProperty().add(-btn_pular_tour.getAltura() - 40));
        btn_pular_tour.setOnClique(() -> {
            limpar_tour(true);
        });
        
        panela.getChildren().add(cena);
        
        BoasVindas.dar_boasVindas();
    }
    
    public static void adicionar_elementos(Node... adicionar){
        cena.getChildren().addAll(adicionar);
    }
    
    public static void remover_elementos(Node... remover){
        cena.getChildren().removeAll(remover);
    }
    
    public static void limpar_tour(boolean definitivo){
        cena.getChildren().clear();
        if(definitivo){
            panela.getChildren().remove(cena);
        }
    }
    
    public static void mudar_posicao(Node objeto, double X, double Y){
        if(X != Double.NaN){
            objeto.setTranslateX(X);
        }
        if(Y != Double.NaN){
            objeto.setTranslateY(Y);
        }
    }
}
