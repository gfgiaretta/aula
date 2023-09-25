import com.bcopstein.CentroDistribuicao;
import com.bcopstein.CentroDistribuicao.SITUACAO;
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
    void testEncomendaSituacaoNormal (int qtdade, TIPOPOSTO tipoPosto, int aditivo, int gasolina, int alcool) {
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
    void testEncomendaSituacaoSobraviso (int qtdade, TIPOPOSTO tipoPosto, int aditivo, int gasolina, int alcool) {
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
    void testEncomendaSituacaoEmergencia (int qtdade, TIPOPOSTO tipoPosto, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(500, 4000, 600);
        int[] vet = c.encomendaCombustivel(qtdade, tipoPosto);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertEquals(resultadoEsperado, vet);
    }

    @Test 
    public void testEncomendaInvalida() { 
        int[] vet = c.encomendaCombustivel(-1, TIPOPOSTO.COMUM);
        int resultadoEsperado[] = {-7, 0, 0};
        Assertions.assertEquals(resultadoEsperado, vet); 
    } 

    @ParameterizedTest
    @CsvSource({
        "-1, 0, 0",
        "0, -1, 0",
        "0, 0, -1",
        "501, 0, 0",
        "0, 10001, 0",
        "0, 0, 2501"
    })
    void testeBuilderException (int aditivo, int gasolina, int alcool) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            c = new CentroDistribuicao(aditivo, gasolina, alcool);
        });
    }

    @ParameterizedTest
    @CsvSource({
        "1, SITUACAO.NORMAL",
        "6000, SITUACAO.SOBRAVISO",
        "9000, SITUACAO.EMERGENCIA,"
    })
    void testDefineSituacao (int qtdade, SITUACAO situacao) {
        c.encomendaCombustivel(qtdade, TIPOPOSTO.COMUM);
        c.defineSituacao();
        Assertions.assertEquals(c.getSituacao(), situacao);
    }

    @Test 
    public void testGettAditivo() { 
        Assertions.assertEquals(c.gettAditivo(), 500);
    }

    @Test 
    public void testGettGasolina() { 
        Assertions.assertEquals(c.gettGasolina(), 10000);
    }

    @Test 
    public void testGettAlcool() { 
        Assertions.assertEquals(c.gettAlcool(), 2500);
    }

    @ParameterizedTest
    @CsvSource({
        "200, 200",
        "300, 250",
        "-1, -1"
    })
    void testRecebeAditivo (int qtdade, int resultadoEsperado) {
        c = new CentroDistribuicao(250, 5000, 1250);
        int temp = c.recebeAditivo(qtdade);
        Assertions.assertEquals(temp, resultadoEsperado);
    }

    @ParameterizedTest
    @CsvSource({
        "4000, 4000",
        "6000, 5000",
        "-1, -1"
    })
    void testRecebeGasolina (int qtdade, int resultadoEsperado) {
        c = new CentroDistribuicao(250, 5000, 1250);
        int temp = c.recebeGasolina(qtdade);
        Assertions.assertEquals(temp, resultadoEsperado);
    }

    @ParameterizedTest
    @CsvSource({
        "1200, 1200",
        "1500, 1250",
        "-1, -1"
    })
    void testRecebeAlcool (int qtdade, int resultadoEsperado) {
        c = new CentroDistribuicao(250, 5000, 1250);
        int temp = c.recebeAlcool(qtdade);
        Assertions.assertEquals(temp, resultadoEsperado);
    }
}
