import com.example.demo.gameoflife.otrotipo.GameOfLife;
import com.example.demo.gameoflife.Tablero;
import org.junit.jupiter.api.Test;

public class GameOfLifeTests {

    @Test
    void bigMatrix(){
        int tamX = 1000;
        int tamY = 1000;
        String matrix = getBigMatrixAsString(tamX, tamY);
        long start = System.currentTimeMillis();
        Tablero tablero = new Tablero(tamY,tamX, matrix);
        for (int i=0;i<1000;i++){
            tablero.siguienteGeneracion();
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
    }

    @Test
    void bigMatrix2(){
        int tamX = 1000;
        int tamY = 1000;
        long start = System.currentTimeMillis();
        GameOfLife gameOfLife = new GameOfLife(tamX, tamY);
        gameOfLife.setLivingCell(0,0);
        gameOfLife.setLivingCell(1,0);
        gameOfLife.setLivingCell(2,0);
        for (int i=0;i<1000;i++){
            gameOfLife.computeNextGeneration();
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
    }

    @Test
    void smolMatrix(){
        int tamX = 5;
        int tamY = 5;
        String matrix = getSmolMatrixAsString();
        Tablero tablero = new Tablero(tamY,tamX, matrix);
        for (int i=0;i<1000;i++){
            System.out.println("Generación "+ i);
            System.out.println(tablero);
            tablero.siguienteGeneracion();
        }
        System.out.println("Última generación");
        System.out.println(tablero);
    }
    @Test
    void smolMatrix2(){
        int tamX = 5;
        int tamY = 5;
        GameOfLife gameOfLife = new GameOfLife(tamX, tamY);
        gameOfLife.setLivingCell(0,2);
        gameOfLife.setLivingCell(1,2);
        gameOfLife.setLivingCell(2,2);
        gameOfLife.setLivingCell(3,2);
        gameOfLife.setLivingCell(4,2);
        for (int i=0;i<1000;i++){
            System.out.println("Generación "+ i);
            System.out.println(gameOfLife);
            gameOfLife.computeNextGeneration();
        }
        System.out.println("Última generación");
        System.out.println(gameOfLife);
    }
    String getSmolMatrixAsString(){
        StringBuilder sb = new StringBuilder();
        sb.append("..*..");
        sb.append("..*..");
        sb.append("..*..");
        sb.append("..*..");
        sb.append("..*..");
        return sb.toString();
    }
    String getBigMatrixAsString(int tamX, int tamY){
        StringBuilder sb = new StringBuilder(tamX * tamY);
        for (int i=0;i<tamY;i++){
            sb.append(".".repeat(tamX));
        }
        return sb.toString();
    }
}
