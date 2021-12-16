import com.example.demo.greed.ElJueguito;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreedTests {

    ElJueguito elJueguito = new ElJueguito();

    @Test
    void tiradaConUnoVale100(){
        //given
        int[] tiradas = {1};
        //when
        int score = elJueguito.score(tiradas);
        //then
        assertEquals(100, score);
    }

    @Test
    void tiradaConDosUnosVale200(){
        //given
        int[] tiradas = {1,1};
        //when
        int score = elJueguito.score(tiradas);
        //then
        assertEquals(200, score);
    }

    @Test
    void tiradaConTresUnosVale1000(){
        //given
        int[] tiradas = {1,1,1};
        //when
        int score = elJueguito.score(tiradas);
        //then
        assertEquals(1000, score);
    }
    @Test
    void tiradaConCuatroUnosVale2000(){
        //given
        int[] tiradas = {1,1,1,1};
        //when
        int score = elJueguito.score(tiradas);
        //then
        assertEquals(2000, score);
    }
    @Test
    void tiradaConCincoUnosVale4000(){
        //given
        int[] tiradas = {1,1,1,1,1};
        //when
        int score = elJueguito.score(tiradas);
        //then
        assertEquals(4000, score);
    }
    @Test
    void tiradaConSeisUnosVale8000(){
        //given
        int[] tiradas = {1,1,1,1,1,1};
        //when
        int score = elJueguito.score(tiradas);
        //then
        assertEquals(8000, score);
    }
    @Test
    void tiradaConTresParejasVale800(){
        //given
        int[] tiradas = {2,2,3,3,4,4};
        //when
        int score = elJueguito.score(tiradas);
        //then
        assertEquals(800, score);
    }

    @Test
    void tiradaConTresParejasYParejaDeUnosVale1000(){
        //given
        int[] tiradas = {1,1,3,3,4,4};
        //when
        int score = elJueguito.score(tiradas);
        //then
        assertEquals(1000, score);
    }

    @Test
    void tiradaConTresParejasYParejaDeCincosVale900(){
        //given
        int[] tiradas = {5,5,3,3,4,4};
        //when
        int score = elJueguito.score(tiradas);
        //then
        assertEquals(900, score);
    }

    @Test
    void tiradaConTresParejasYParejaDeCincosYUnosVale1100(){
        //given
        int[] tiradas = {5,5,1,1,4,4};
        //when
        int score = elJueguito.score(tiradas);
        //then
        assertEquals(1100, score);
    }

    @Test
    void tiradaConTripleOfrecePuntuacionCorrecta(){
        List<Integer> valoresEstandar = List.of(2,3,4,5,6);
        int[] tiradas = new int[6];
        for (Integer valorEstandar : valoresEstandar){
            tiradas[0] = tiradas[1] = tiradas[2] = valorEstandar;
            int score = elJueguito.score(tiradas);
            assertEquals(valorEstandar * 100, score);
        }
    }

    @Test
    void tiradaConCuadrupleOfrecePuntuacionCorrecta(){
        List<Integer> valoresEstandar = List.of(2,3,4,5,6);
        int[] tiradas = new int[6];
        for (Integer valorEstandar : valoresEstandar){
            tiradas[0] = tiradas[1] = tiradas[2] = tiradas[3] = valorEstandar;
            int score = elJueguito.score(tiradas);
            assertEquals(valorEstandar * 100 * 2, score);
        }
    }

    @Test
    void tiradaConQuintupleOfrecePuntuacionCorrecta(){
        List<Integer> valoresEstandar = List.of(2,3,4,5,6);
        int[] tiradas = new int[6];
        for (Integer valorEstandar : valoresEstandar){
            tiradas[0] = tiradas[1] = tiradas[2] = tiradas[3] = tiradas[4] = valorEstandar;
            int score = elJueguito.score(tiradas);
            assertEquals(valorEstandar * 100 * 4, score);
        }
    }

    @Test
    void tiradaConSextupleOfrecePuntuacionCorrecta(){
        List<Integer> valoresEstandar = List.of(2,3,4,5,6);
        int[] tiradas = new int[6];
        for (Integer valorEstandar : valoresEstandar){
            Arrays.fill(tiradas, valorEstandar);
            int score = elJueguito.score(tiradas);
            assertEquals(valorEstandar * 100 * 8, score);
        }
    }
}
