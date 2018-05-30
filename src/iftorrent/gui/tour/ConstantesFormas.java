package iftorrent.gui.tour;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class ConstantesFormas {
    public static final String CIRCULO = "Circle";
    public static final String RETANGULO = "RECTANGLE";
    
    public static Forma botaoPadrao(String texto){
        Forma botao = new Forma(RETANGULO, 12,Color.LIGHTGREY, Color.BLACK, 4, texto, Font.font("Arial", 18));
        botao.setCoresOnHover(Color.WHITE, Color.BLACK);
        botao.setCoresOnClique(Color.SLATEGREY, Color.WHITE);
        botao.setOnClique(() -> {});
        botao.setOnHover(() -> {});
        
        return botao;
    }
    
    public static Forma botaoEntendido(String texto, double font_size){
        Forma botao = new Forma(CIRCULO, 10,Color.GHOSTWHITE, Color.BLACK, 2, texto, Font.font("Arial", 13));
        botao.setCoresOnHover(Color.WHITE, Color.BLACK);
        botao.setCoresOnClique(Color.BLACK, Color.WHITE);
        botao.setOnClique(() -> {});
        botao.setOnHover(() -> {});
        botao.realocar_conteudo(-botao.getLargura()/2 + 5, botao.texto.altura_texto_total/3);
        
        return botao;
    }
}
