package iftorrent.texto;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Point2D;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Texto extends Pane {
    private String texto;
    public Text text = new Text();
    public Font fonte;
    public double largura_texto_total;
    public double altura_texto_total;

    public Texto(String texto, Font fonte) {
        text = new Text(texto);
        if (fonte != null) {
            text.setFont(fonte);
        }
        text.setFill(Color.BLACK);
        this.texto = texto;
        largura_texto_total = getLarguraTexto();
        altura_texto_total = getAlturaTexto();
        
        getChildren().addAll(text);
    }

    public Texto(String texto) {
        this.texto = texto;
        text.setText(texto);
        text.setFill(Color.BLACK);
        largura_texto_total = getLarguraTexto();
        altura_texto_total = getAlturaTexto();
        
        getChildren().addAll(text);
    }

    /**
     *
     * @return Retorna a largura do espaço que o texto ocupa.
     */
    public double getLarguraTexto() {
        return text.getLayoutBounds().getWidth();
    }

    /**
     *
     * @return Retorna a altura do espaço que o texto ocupa.
     */
    public double getAlturaTexto() {
        return text.getLayoutBounds().getHeight();
    }

    /**
     *
     * @return O texto formatado.
     */
    public Text getText() {
        return text;
    }

    /**
     *
     * @return O texto em forma de String.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Altera o texto formatado atual, para um outro.
     *
     * @param text Novo texto.
     */
    public void setText(Text text) {
        this.text = text;
        this.texto = text.getText();
    }

    /**
     * Altera o texto mantendo a formatação.
     *
     * @param texto Novo texto.
     */
    public void setText(String texto) {
        this.text.setText(texto);
        this.texto = texto;
    }

    /**
     * Altera a cor do texto.
     * @param cor Nova cor.
     */
    public void mudar_cor(Paint cor) {
        text.setFill(cor);
    }

    /**
     * Muda o efeito do texto.
     * @param efeito Novo efeito.
     */
    public void mudar_efeito(Effect efeito) {
        text.setEffect(efeito);
    }

    /**
     * Faz uma animação de escrita no texto.
     * 
     * @param duracao Duração da animação em segundos.
     * @param ponto_relativo Ponto ao qual o centro do texto em X sepre ficará ligado.
     */
    public void aparecer_texto(double duracao, Point2D ponto_relativo) {
        Animation animacao = new Transition() {
            {
                setCycleDuration(Duration.seconds(duracao));
            }
            @Override
            protected void interpolate(double frac) {
                int tamanho_texto = texto.length();
                int n = Math.round(tamanho_texto * (float) frac);
                
                text.setText(texto.substring(0, n));
                getChildren().get(0).setTranslateX(ponto_relativo.getX() - text.getLayoutBounds().getWidth()/2);
                getChildren().get(0).setTranslateY(ponto_relativo.getY());
            }
        };
        animacao.play();
    }
    
    /**
     * Faz uma animação de escrita no texto.
     * @param duracao_por_caractere Duração da animação de cada caractere.
     */
    public void aparecer_texto_fixo(double duracao_por_caractere) {
        Animation animacao = new Transition() {
            {
                setCycleDuration(Duration.seconds(duracao_por_caractere*texto.length()));
            }

            @Override
            protected void interpolate(double frac) {
                int tamanho_texto = texto.length();
                int n = Math.round(tamanho_texto * (float) frac);
                
                text.setText(texto.substring(0, n));
            }
        };
        animacao.play();
    }
    
    /**
     * @deprecated 
     * REVER, NÃO PRECISA FAZER REVISAO
     * @param espacoX 
     */
    public void reorganiza_texto_conforme_espaco(double espacoX){
        espacoX *= 0.78; //???????? FUNCIONA//
        int tamanhoDeUmDigito = (int) (largura_texto_total / texto.length());
        int digitos_por_linha = (int) (espacoX/tamanhoDeUmDigito);
        String novo_texto = "";
        
        for (int i = 0; i < texto.length(); i++) {
            novo_texto += texto.charAt(i);
            if((i+1) % digitos_por_linha == 0 && i > 0){
                novo_texto += "\n";
            }
        }
        texto = novo_texto;
    }
}
