import com.bcopstein.CentroDistribuicao;
import com.bcopstein.CentroDistribuicao.TIPOPOSTO;

import org.junit.jupiter.api.Assertions; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CentroDistribuicaoTest {
    private CentroDistribuicao c = null;

    @BeforeEach 
    void setUp() { 
        c = new CentroDistribuicao(500, 10000, 2500); 
    } 

    @ParameterizedTest
    @CsvSource({
        "10000, TIPOPOSTO.NORMAL, 0, 3000, 0",
        "10000, TIPOPOSTO.ESTRATEGICO, 0, 3000, 0",
        "10020, TIPOPOSTO.NORMAL, -21, 0, 0",
        "10020, TIPOPOSTO.ESTRATEGICO, -21, 0, 0"
    })
    void testSituacaoNormal (int qtdade, TIPOPOSTO tipoPosto, int aditivo, int gasolina, int alcool) {
        int[] vet = c.encomendaCombustivel(qtdade, tipoPosto);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertEquals(resultadoEsperado, vet);
    }

    @ParameterizedTest
    @CsvSource({
        "5000, TIPOPOSTO.NORMAL, 125, 1750, 625",
        "5000, TIPOPOSTO.ESTRATEGICO, 250, 3500, 1250",
        "11432, TIPOPOSTO.NORMAL, -21, 0, 0",
        "5716, TIPOPOSTO.ESTRATEGICO, -21, 0, 0"
    })
    void testSituacaoSobraviso (int qtdade, TIPOPOSTO tipoPosto, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(500, 4000, 2500);
        int[] vet = c.encomendaCombustivel(qtdade, tipoPosto);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertEquals(resultadoEsperado, vet);
    }

    @ParameterizedTest
    @CsvSource({
        "20, TIPOPOSTO.NORMAL, -14, 0, 0",
        "3000, TIPOPOSTO.ESTRATEGICO, 425, 2950, 225",
        "11432, TIPOPOSTO.NORMAL, -14, 0, 0",
        "4808, TIPOPOSTO.ESTRATEGICO, -21, 0, 0"
    })
    void testSituacaoEmergencia (int qtdade, TIPOPOSTO tipoPosto, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(500, 4000, 600);
        int[] vet = c.encomendaCombustivel(qtdade, tipoPosto);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertEquals(resultadoEsperado, vet);
    }
}
