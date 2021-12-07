import com.example.demo.gameoflife.Tablero;
import org.junit.jupiter.api.Test;

public class GameOfLifeTests {

    @Test
    void creacionDeTablero(){
        int tamX = 10;
        int tamY = 10;
        StringBuilder sb = new StringBuilder(tamY * tamX);
        sb.append("......**.*");
        sb.append("......**.*");
        sb.append("......**.*");
        sb.append("......**.*");
        sb.append("......**.*");
        sb.append("......**.*");
        sb.append("......**.*");
        sb.append("......**.*");
        sb.append("......**.*");
        sb.append("......**.*");
        Tablero tablero = new Tablero(tamY,tamX, sb.toString());
        for (int i=0;i<10;i++){
            System.out.println("Generación "+ i);
            System.out.println(tablero);
            tablero.siguienteGeneracion();
        }
    }
}
