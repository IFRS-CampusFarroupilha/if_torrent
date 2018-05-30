package iftorrent.gui.tour.apresentacoes;

import iftorrent.gui.ferramentas.Comunicador;
import iftorrent.gui.tour.Animacoes;
import iftorrent.gui.tour.BalaoDialogo;
import iftorrent.gui.tour.ConstantesBalaoDialogo;
import static iftorrent.gui.tour.ConstantesFormas.botaoEntendido;
import iftorrent.gui.tour.Forma;
import iftorrent.gui.tour.Quadrado;
import static iftorrent.gui.tour.apresentacoes.ApresentarSoftware.*;
import javafx.geometry.Bounds;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AbasPropriedades {
    private static Bounds propriedades_barra;
    private static TabPane barra;
    private static Quadrado mostra;
    private static BalaoDialogo dialogo;
    private static Forma btn_entendido;
        
    private static String fala_aba = "Esta é a aba de propriedades, nela você pode ver as propriedades \n"
            + "de um determinado arquivo torrent, como por exemplo: \n\n"
            + "Tamanho do arquivo; \n"
            + "Velocidade de download; \n"
            + "Número de pedaços do arquivo; \n"
            + "Entre outros... \n\n\n";
    
    private static String fala_mapa_pedacos = "Esta aba (Mapa de Pedaços) informa o status de \n"
            +"cada pedaço do arquivo que está sendo baixado. \n\n\n\n";
    
    private static String fala_informacoes = "Esta aba (Informações) informa as propriedades \n"
            +"do arquivo que está sendo baixado,\n como por exemplo: \n\n"
            + "Nome; \n"
            + "Tamanho(MB); \n"
            + "Número de pedaços; \n"
            + "Progresso \n"
            + "etc... \n\n\n";
    
    private static String fala_velocidade = "Esta aba (Velocidade) mostra a velocidade \n"
            + "de download e upload de um determinado arquivo. \n\n\n\n";
    
    public static void apresentar() {
        barra = Comunicador.getAbas_propriedades().getPainel();
        propriedades_barra = barra.localToScene(Comunicador.getBarra_lateral().getPainel().getLayoutBounds());
        
        introduzir_barra();
    }
    
    private static void introduzir_barra(){
        btn_entendido = botaoEntendido("Entendi", 10);
        mostra = new Quadrado(barra.getWidth(), barra.getHeight(), true, 6, Color.RED);
        dialogo = new BalaoDialogo(ConstantesBalaoDialogo.SUL, true, fala_aba, null, null);
        
        mudar_posicao(mostra, propriedades_barra.getMinX()-3, propriedades_barra.getMinY()-3);
        mudar_posicao(dialogo, mostra.getTranslateX()+30, mostra.getTranslateY()-dialogo.getAltura()-10);
        mudar_posicao(btn_entendido, dialogo.getTranslateX()+dialogo.getLargura()/2 -3.5, dialogo.getTranslateY()+dialogo.getAltura()-btn_entendido.getAltura()/2);
        
        ((Rectangle) mostra.forma).widthProperty().bind(barra.widthProperty());
        ((Rectangle) mostra.forma).heightProperty().bind(barra.heightProperty());

        mostra.translateYProperty().bind(barra.layoutYProperty().add(60));
        dialogo.translateYProperty().bind(mostra.translateYProperty().add(-dialogo.getAltura()-5));
        btn_entendido.translateYProperty().bind(dialogo.translateYProperty().add(dialogo.getAltura()-btn_entendido.getAltura()/2 -10));
        
        btn_entendido.setOnClique(() -> {
            limpar_tour(false);
            introduzir_mapa_pedacos();
        });
        
        adicionar_elementos(mostra, dialogo, btn_entendido);
        adicionar_elementos(btn_pular_tour);//sempre por cima
        
        mostra.aparecer(1.1, 1.15, 0.3);
        dialogo.aparecer_balao_inteiro(1.1, 1.1, 0.4);
        Animacoes.animacao_escala(btn_entendido, 1, 1, 0.4);
    }
    
    private static void introduzir_mapa_pedacos(){
        btn_entendido = botaoEntendido("Entendi", 10);
        dialogo = new BalaoDialogo(ConstantesBalaoDialogo.SUL, true, fala_mapa_pedacos, null, null);
        
        mudar_posicao(dialogo, propriedades_barra.getMinX()-80, propriedades_barra.getMinY()-dialogo.getAltura()-3);
        mudar_posicao(btn_entendido, dialogo.getTranslateX()+dialogo.getLargura()/2 -3.5, dialogo.getTranslateY()+dialogo.getAltura()-btn_entendido.getAltura()/2);
        
        dialogo.translateYProperty().bind(barra.layoutYProperty().add(60).add(-dialogo.getAltura()));
        btn_entendido.translateYProperty().bind(dialogo.translateYProperty().add(dialogo.getAltura()-btn_entendido.getAltura()/2 -10));
        
        btn_entendido.setOnClique(() -> {
            limpar_tour(false);
            introduzir_informacoes();
        });
        
        barra.getSelectionModel().select(0);
        
        adicionar_elementos(dialogo, btn_entendido);
        adicionar_elementos(btn_pular_tour);//sempre por cima
        
        dialogo.aparecer_balao_inteiro(1.1, 1.1, 0.4);
        Animacoes.animacao_escala(btn_entendido, 1, 1, 0.4);
    }
         
    private static void introduzir_informacoes(){
        btn_entendido = botaoEntendido("Entendi", 10);
        dialogo = new BalaoDialogo(ConstantesBalaoDialogo.SUL, true, fala_informacoes, null, null);
        
        mudar_posicao(dialogo, propriedades_barra.getMinX()+20, propriedades_barra.getMinY()-dialogo.getAltura()-3);
        mudar_posicao(btn_entendido, dialogo.getTranslateX()+dialogo.getLargura()/2 -3.5, dialogo.getTranslateY()+dialogo.getAltura()-btn_entendido.getAltura()/2);
        
        dialogo.translateYProperty().bind(barra.layoutYProperty().add(60).add(-dialogo.getAltura()));
        btn_entendido.translateYProperty().bind(dialogo.translateYProperty().add(dialogo.getAltura()-btn_entendido.getAltura()/2 -10));
        
        btn_entendido.setOnClique(() -> {
            limpar_tour(false);
            introduzir_velocidade();
        });
        
        barra.getSelectionModel().select(1);
        
        adicionar_elementos(dialogo, btn_entendido);
        adicionar_elementos(btn_pular_tour);//sempre por cima
        
        dialogo.aparecer_balao_inteiro(1.1, 1.1, 0.4);
        Animacoes.animacao_escala(btn_entendido, 1, 1, 0.4);
    }
    
    private static void introduzir_pares(){
        barra.getSelectionModel().select(2);
    }
            
    private static void introduzir_velocidade(){
        btn_entendido = botaoEntendido("Entendi", 10);
        dialogo = new BalaoDialogo(ConstantesBalaoDialogo.SUL, true, fala_velocidade, null, null);
        
        mudar_posicao(dialogo, propriedades_barra.getMinX()+125, propriedades_barra.getMinY()-dialogo.getAltura()-3);
        mudar_posicao(btn_entendido, dialogo.getTranslateX()+dialogo.getLargura()/2 -3.5, dialogo.getTranslateY()+dialogo.getAltura()-btn_entendido.getAltura()/2);
        
        dialogo.translateYProperty().bind(barra.layoutYProperty().add(60).add(-dialogo.getAltura()));
        btn_entendido.translateYProperty().bind(dialogo.translateYProperty().add(dialogo.getAltura()-btn_entendido.getAltura()/2 -10));
        
        btn_entendido.setOnClique(() -> {
            barra.getSelectionModel().select(0);
            limpar_tour(false);
            AjudaESite.apresentar();
        });
        
        barra.getSelectionModel().select(3);
        
        adicionar_elementos(dialogo, btn_entendido);
        adicionar_elementos(btn_pular_tour);//sempre por cima
        
        dialogo.aparecer_balao_inteiro(1.1, 1.1, 0.4);
        Animacoes.animacao_escala(btn_entendido, 1, 1, 0.4);
    }
}
