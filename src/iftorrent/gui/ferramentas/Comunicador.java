package iftorrent.gui.ferramentas;

import iftorrent.JanelaPrincipal;
import iftorrent.gui.abasPropriedades.AbasPropriedadesControlador;
import iftorrent.gui.barraDeFerramentas.BarraDeFerramentasControlador;
import iftorrent.gui.barraInferior.BarraInferiorControlador;
import iftorrent.gui.barraLateral.BarraLateralControlador;
import iftorrent.gui.barraLogoff.BarraLogoffControlador;
import iftorrent.gui.barraMenu.BarraMenuControlador;
import iftorrent.gui.configuracoesTeclasDeAtalho.ConfiguracoesTeclasDeAtalhoControlador;
import iftorrent.gui.janelaConfiguracoes.JanelaConfiguracoesControlador;
import iftorrent.gui.janelaLogin.JanelaLoginControlador;
import iftorrent.gui.janelaPrincipal.JanelaPrincipalControlador;
import iftorrent.gui.janelaSimples.InterfaceSimplesControlador;
import iftorrent.gui.menuPrincipal.MenuPrincipalControlador;
import iftorrent.gui.sobre.SobreControlador;

/**
 * 
 * 
 * A classe <b>Comunicador</b> serve para estabelecer uma comunicação entre os 
 * componentes da interface gráfica.
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public class Comunicador {
    
    /**
     * 
     * 
     * Controlador das abas de propriedades.
     * 
     * 
     */
    private static AbasPropriedadesControlador abas_propriedades;
    
    /**
     * 
     * 
     * Controlador da barra de ferramentas.
     * 
     * 
     */
    private static BarraDeFerramentasControlador barra_de_ferramentas;
    
    /**
     * 
     * 
     * Controlador da barra inferior.
     * 
     * 
     */    
    private static BarraInferiorControlador barra_inferior;
    
    /**
     * 
     * 
     * Controlador da barra de ferramentas.
     * 
     * 
     */
    private static JanelaLoginControlador janelaLogin;

    /**
     * 
     * 
     * Controlador da barra lateral esquerda.
     * 
     * 
     */
    private static BarraLateralControlador barra_lateral;
    
    /**
     * 
     * 
     * Controlador da barra de logoff.
     * 
     * 
     */
    private static BarraLogoffControlador barra_logoff;
    
    /**
     * 
     * 
     * Controlador da barra menu.
     * 
     * 
     */
    private static BarraMenuControlador barra_menu;
    
    /**
     * 
     * 
     * Controlador da janela sobre a equipe.
     * 
     * 
     */
    private static SobreControlador sobre;
    
    /**
     * 
     * 
     * Controlador da interface simples.
     * 
     * 
     */
    private static InterfaceSimplesControlador interface_simples;
    
    /**
     * 
     * 
     * Controlador da janela de configurações de teclas de atalho.
     * 
     * 
     */
    private static ConfiguracoesTeclasDeAtalhoControlador configuracoes_teclas_de_atalho;
    
    /**
     * 
     * 
     * Controlador da janela principal em geral.
     * 
     * 
     */
    private static JanelaPrincipalControlador janela_principal;
    
    /**
     * 
     * 
     * Controlador do menu principal.
     * 
     * 
     */
    private static MenuPrincipalControlador menu_principal;
    
    /**
     * 
     * 
     * Main que inicia a janela principal.
     * 
     * 
     */
    private static JanelaPrincipal main;
    
    /**
     * 
     * 
     * Controlador da janela de configurações
     * 
     * 
     */
    private static JanelaConfiguracoesControlador janela_configuracoes;

    /**
     * 
     * 
     * Getter do controlador das abas de propriedades.
     * 
     * @return Um objeto do tipo <b>AbasPropriedadesControlador</b> que 
     * representa o controlador das abas de propriedades.<br>
     * 
     * 
     */
    public static AbasPropriedadesControlador getAbas_propriedades() {
        return abas_propriedades;
    }
    
    /**
     * 
     * 
     * Setter do controlador das abas de propriedades.
     * 
     * @param abas_propriedades Um objeto do tipo 
     * <b>AbasPropriedadesControlador</b> que representa o controlador das 
     * abas de propriedades.<br>
     * 
     * 
     */
    public static void setAbas_propriedades(AbasPropriedadesControlador abas_propriedades) {
        Comunicador.abas_propriedades = abas_propriedades;
    }
    
    /**
     * 
     * 
     * Getter do controlador da janela de configurações.
     * 
     * @return Um objeto do tipo <b>JanelaConfiguracoesControlador</b> que 
     * representa o controlador da janela de configuracoes.<br>
     * 
     * 
     */
    public static JanelaConfiguracoesControlador getJanela_configuracoes(){
        return janela_configuracoes;
    }
    
    /**
     * 
     * 
     * Setter do controlador da janela de configurações.
     * 
     * @param controlador Um objeto do tipo 
     * <b>JanelaConfiguracoesControlador</b> que representa o controlador da
     * janela de configurações.<br>
     * 
     * 
     */
    public static void setJanela_configuracoes(JanelaConfiguracoesControlador controlador) {
        Comunicador.janela_configuracoes = controlador;
    }
    
    /**
     * 
     * 
     * Getter do controlador da janela sobre a equipe.
     * 
     * @return Um objeto do tipo <b>SobreControlador</b> que 
     * representa o controlador da janela sobre a equipe.<br>
     * 
     * 
     */
    public static SobreControlador getSobre() {
        return sobre;
    }

    /**
     * 
     * 
     * Setter do controlador da janela sobre a equipe.
     * 
     * @param sobre Um objeto do tipo 
     * <b>SobreControlador</b> que representa o controlador da 
     * janela sobre a equipe.<br>
     * 
     * 
     */
    public static void setSobre(SobreControlador sobre) {
        Comunicador.sobre = sobre;
    }
    
    /**
     * 
     * 
     * Getter do controlador da barra de ferramentas.
     * 
     * @return Um objeto do tipo <b>BarraDeFerramentasControlador</b> que 
     * representa o controlador da barra de ferramentas.<br>
     * 
     * 
     */
    /**
     * 
     * 
     * Getter do controlador da interface simples.
     * 
     * @return Um objeto do tipo <b>InterfaceSimplesControlador</b> que 
     * representa o controlador da interface simples.<br>
     * 
     * 
     */
    public static InterfaceSimplesControlador getInterface_Simples() {
        return interface_simples;
    }

    /**
     * 
     * 
     * Setter do controlador da interface simples.
     * 
     * @param interface_simples Um objeto do tipo 
     * <b>InterfaceSimplesControlador</b> que representa o controlador da 
     * interface simples.<br>
     * 
     * 
     */
    public static void setInterface_simples(InterfaceSimplesControlador interface_simples) {
        Comunicador.interface_simples = interface_simples;
    }
    
    /**
     * 
     * 
     * Getter do controlador da barra de ferramentas.
     * 
     * @return Um objeto do tipo <b>BarraDeFerramentasControlador</b> que 
     * representa o controlador da barra de ferramentas.<br>
     * 
     * 
     */
    public static BarraDeFerramentasControlador getBarra_de_ferramentas() {
        return barra_de_ferramentas;
    }

    /**
     * 
     * 
     * Setter do controlador da barra de ferramentas.
     * 
     * @param barra_de_ferramentas Um objeto do tipo 
     * <b>BarraDeFerramentasControlador</b> que representa o controlador da 
     * barra de ferramentas.<br>
     * 
     * 
     */
    public static void setBarra_de_ferramentas(BarraDeFerramentasControlador barra_de_ferramentas) {
        Comunicador.barra_de_ferramentas = barra_de_ferramentas;
    }

    /**
     * 
     * 
     * Getter do controlador da barra inferior.
     * 
     * @return Um objeto do tipo <b>BarraInferiorControlador</b> que 
     * representa o controlador da barra inferior.<br>
     * 
     * 
     */
    public static BarraInferiorControlador getBarra_inferior() {
        return barra_inferior;
    }

    /**
     * 
     * 
     * Setter do controlador da barra inferior.
     * 
     * @param barra_inferior Um objeto do tipo <b>BarraInferiorControlador</b> que 
     * representa o controlador da barra inferior.<br>
     * 
     * 
     */
    public static void setBarra_inferior(BarraInferiorControlador barra_inferior) {
        Comunicador.barra_inferior = barra_inferior;
    }

    /**
     * 
     * 
     * Getter do controlador da barra lateral esquerda.
     * 
     * @return Um objeto do tipo <b>BarraLateralControlador</b> que 
     * representa o controlador da barra lateral esquerda.<br>
     * 
     * 
     */
    public static BarraLateralControlador getBarra_lateral() {
        return barra_lateral;
    }

    /**
     * 
     * 
     * Setter do controlador da barra lateral esquerda.
     * 
     * @param barra_lateral Um objeto do tipo <b>BarraLateralControlador</b> que 
     * representa o controlador da barra lateral esquerda.<br>
     * 
     * 
     */
    public static void setBarra_lateral(BarraLateralControlador barra_lateral) {
        Comunicador.barra_lateral = barra_lateral;
    }

    /**
     * 
     * 
     * Getter do controlador da barra de logoff.
     * 
     * @return Um objeto do tipo <b>BarraLogoffControlador</b> que 
     * representa o controlador das aba barra de logoff.<br>
     * 
     * 
     */
    public static BarraLogoffControlador getBarra_logoff() {
        return barra_logoff;
    }

    /**
     * 
     * 
     * Setter do controlador da barra de logoff.
     * 
     * @param barra_logoff Um objeto do tipo <b>BarraLogoffControlador</b> que 
     * representa o controlador das aba barra de logoff.<br>
     * 
     * 
     */
    public static void setBarra_logoff(BarraLogoffControlador barra_logoff) {
        Comunicador.barra_logoff = barra_logoff;
    }

    /**
     * 
     * 
     * Getter do controlador da barra menu.
     * 
     * @return Um objeto do tipo <b>BarraMenuControlador</b> que 
     * representa o controlador da barra menu.<br>
     * 
     * 
     */
    public static BarraMenuControlador getBarra_menu() {
        return barra_menu;
    }

    /**
     * 
     * 
     * Setter do controlador da barra menu.
     * 
     * @param barra_menu Um objeto do tipo <b>BarraMenuControlador</b> que 
     * representa o controlador da barra menu.<br>
     * 
     * 
     */
    public static void setBarra_menu(BarraMenuControlador barra_menu) {
        Comunicador.barra_menu = barra_menu;
    }

    /**
     * 
     * 
     * Getter do controlador da janela de configuração de teclas de atalho.
     * 
     * @return Um objeto do tipo <b>ConfiguracoesTeclasDeAtalhoControlador</b> 
     * que representa o controlador da janela de configuração de teclas de 
     * atalho.<br>
     * 
     * 
     */
    public static ConfiguracoesTeclasDeAtalhoControlador getConfiguracoes_teclas_de_atalho() {
        return configuracoes_teclas_de_atalho;
    }

    /**
     * 
     * 
     * Setter do controlador da janela de configuração de teclas de atalho.
     * 
     * @param configuracoes_teclas_de_atalho Um objeto do tipo 
     * <b>ConfiguracoesTeclasDeAtalhoControlador</b> que representa o 
     * controlador da janela de configuração de teclas de atalho.<br>
     * 
     * 
     */
    public static void setConfiguracoes_teclas_de_atalho(ConfiguracoesTeclasDeAtalhoControlador configuracoes_teclas_de_atalho) {
        Comunicador.configuracoes_teclas_de_atalho = configuracoes_teclas_de_atalho;
    }

    /**
     * 
     * 
     * Getter do controlador da janela principal.
     * 
     * @return Um objeto do tipo <b>JanelaPrincipalControlador</b> que 
     * representa o controlador da janela principal.<br>
     * 
     * 
     */
    public static JanelaPrincipalControlador getJanela_principal() {
        return janela_principal;
    }

    /**
     * 
     * 
     * Setter do controlador da janela principal.
     * 
     * @param janela_principal Um objeto do tipo <b>JanelaPrincipalControlador</b> que 
     * representa o controlador da janela principal.<br>
     * 
     * 
     */
    public static void setJanela_principal(JanelaPrincipalControlador janela_principal) {
        Comunicador.janela_principal = janela_principal;
    }

    /**
     * 
     * 
     * Getter do controlador do menu principal.
     * 
     * @return Um objeto do tipo <b>MenuPrincipalControlador</b> que 
     * representa o controlador do menu principal.<br>
     * 
     * 
     */
    public static MenuPrincipalControlador getMenu_principal() {
        return menu_principal;
    }

    /**
     * 
     * 
     * Setter do controlador do menu principal.
     * 
     * @param menu_principal Um objeto do tipo <b>MenuPrincipalControlador</b> 
     * que representa o controlador do menu principal.<br>
     * 
     * 
     */
    public static void setMenu_principal(MenuPrincipalControlador menu_principal) {
        Comunicador.menu_principal = menu_principal;
    }

    /**
     * 
     * 
     * Getter da main.
     * 
     * @return Um objeto do tipo <b>JanelaPrincipal</b> que 
     * representa a main.<br>
     * 
     * 
     */    
    
    public static JanelaPrincipal getMain() {
        return main;
    }

    //TODO Javadocs
    public static JanelaLoginControlador getJanelaLogin() {
        return janelaLogin;
    }

    public static void setJanelaLogin(JanelaLoginControlador janelaLogin) {
        Comunicador.janelaLogin = janelaLogin;
    }
    
    
    /**
     * 
     * 
     * Setter da main.
     * 
     * @param main Um objeto do tipo <b>JanelaPrincipal</b> que 
     * representa a main.<br>
     * 
     * 
     */
    public static void setMain(JanelaPrincipal main) {
        Comunicador.main = main;
    }
    
}
