package jogodavida;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.math.Vector2;

/**
 * Jogo da vida (John Conway's Game of Life)
 * 
 * https://playgameoflife.com/
 * https://pt.wikipedia.org/wiki/Jogo_da_vida
 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 * 
 * O jogo da vida se passa em um arranjo bidimensional infinito de células que
 * podem estar em um de dois estados, vivo ou morto. Cada célula interage com
 * suas oito vizinhas, as células adjacentes horizontal, vertical e
 * diagonalmente. O jogo evolui em unidades de tempo discretas chamadas de
 * gerações. A cada nova geração, o estado do jogo é atualizado pela
 * aplicação das seguintes regras:
 *     
 *   1) Toda célula morta com exatamente três vizinhos vivos torna-se viva (nascimento);
 *   2) Toda célula viva com menos de dois vizinhos vivos morre por isolamento;
 *   3) Toda célula viva com mais de três vizinhos vivos morre por superpopulação;
 *   4) Toda célula viva com dois ou três vizinhos vivos permanece viva.[3]
 * 
 * As regras são aplicadas simultaneamente em todas as células para chegar ao
 * estado da próxima geração.
 * 
 * Fonte: Wikipedia (https://pt.wikipedia.org/wiki/Jogo_da_vida)
 * 
 * @author Prof. Dr. David Buzatto
 */
public class Main extends EngineFrame {
    
    private static final int TAMANHO_TELA = 600;
    private static final int DIMENSAO_GRADE = 50;
    private static final int LARGURA_CELULA = TAMANHO_TELA / DIMENSAO_GRADE;
    
    private boolean[][] grade;
    
    private static double tempoAtualizacao;
    private double contadorTempo;
    private boolean executando;
    private boolean mostrarAjuda;
    
    public Main() {
        super ( TAMANHO_TELA, TAMANHO_TELA, "Jogo da Vida", 60, true );
    }
    
    @Override
    public void create() {
        
        setDefaultFontSize( 20 );
        
        grade = new boolean[DIMENSAO_GRADE][DIMENSAO_GRADE];
        tempoAtualizacao = 0.2;
        mostrarAjuda = true;
        
        prepararCenarioInicial();
        
    }
    
    @Override
    public void update( double delta ) {
        
        if ( executando ) {
            contadorTempo += delta;
            if ( contadorTempo >= tempoAtualizacao ) {
                contadorTempo = 0;
                avancarParaNovaGeracao();
            }
        }
        
        if ( isKeyPressed( KEY_ENTER ) ) {
            executando = !executando;
        }
        
        if ( isKeyPressed( KEY_UP ) ) {
            tempoAtualizacao += 0.05;
            if ( tempoAtualizacao >= 2.0 ) {
                tempoAtualizacao = 2.0;
            }
        }
        
        if ( isKeyPressed( KEY_DOWN ) ) {
            tempoAtualizacao -= 0.05;
            if ( tempoAtualizacao <= 0.05 ) {
                tempoAtualizacao = 0.05;
            }
        }
        
        if ( isKeyPressed( KEY_R ) ) {
            prepararCenarioInicial();
        }
        
        if ( isKeyPressed( KEY_F1 ) ) {
            mostrarAjuda = !mostrarAjuda;
        }
        
        if ( isMouseButtonPressed( MOUSE_BUTTON_LEFT ) ) {
            Vector2 mousePos = getMousePositionPoint();
            int i = (int) mousePos.y / LARGURA_CELULA;
            int j = (int) mousePos.x / LARGURA_CELULA;
            grade[i][j] = !grade[i][j];
        }
        
    }
    
    @Override
    public void draw() {
        
        clearBackground( BLACK );

        for ( int i = 0; i < DIMENSAO_GRADE; i++ ) {
            for ( int j = 0; j < DIMENSAO_GRADE; j++ ) {
                if ( grade[i][j] ) {
                    fillRectangle( j * LARGURA_CELULA, i * LARGURA_CELULA, LARGURA_CELULA, LARGURA_CELULA, WHITE );
                }
            }
        }
        
        for ( int i = 0; i < DIMENSAO_GRADE; i++ ) {
            drawLine( i * LARGURA_CELULA, 0, i * LARGURA_CELULA, TAMANHO_TELA, DARKGRAY );
        }
        
        for ( int i = 0; i < DIMENSAO_GRADE; i++ ) {
            drawLine( 0, i * LARGURA_CELULA, TAMANHO_TELA, i * LARGURA_CELULA, DARKGRAY );
        }
        
        if ( mostrarAjuda ) {
            drawText( "    <ENTER>: pausar/retomar", 20, 20, WHITE );
            drawText( "<UP>/<DOWN>: acelerar/desacelerar", 20, 40, WHITE );
            drawText( "        <R>: resetar e pausar", 20, 60, WHITE );
            drawText( "       <F1>: esconder/mostrar essa ajuda", 20, 80, WHITE );
            drawText( String.format( "Nova geração em %.2fs.", tempoAtualizacao ), 20, getScreenHeight() - 40, WHITE );
        }
    
    }
    
    private void prepararCenarioInicial() {
        
        executando = false;
        
        for ( int i = 0; i < DIMENSAO_GRADE; i++ ) {
            for ( int j = 0; j < DIMENSAO_GRADE; j++ ) {
                grade[i][j] = false;
            }
        }
        
        int iIni = DIMENSAO_GRADE / 2 + 2;
        int jIni = DIMENSAO_GRADE / 2;
        
        // glider
        grade[iIni][jIni] = true;
        grade[iIni+1][jIni+1] = true;
        grade[iIni+2][jIni-1] = true;
        grade[iIni+2][jIni] = true;
        grade[iIni+2][jIni+1] = true;
        
    }
    
    private void avancarParaNovaGeracao() {
        
        // sua implementação aqui!
        
    }
    
    public static void main( String[] args ) {
        new Main();
    }
    
}
