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
        "10000, 0, 3000, 0",
        "10020, -21, 0, 0"
    })
    void testEncomendaPostoComumSituacaoNormal (int qtdade, int aditivo, int gasolina, int alcool) {
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.COMUM);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }

    @ParameterizedTest
    @CsvSource({
        "10000, 0, 3000, 0",
        "10020, -21, 0, 0"
    })
    void testEncomendaPostoEstrategicoSituacaoNormal (int qtdade, int aditivo, int gasolina, int alcool) {
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.ESTRATEGICO);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }

    @ParameterizedTest
    @CsvSource({
        "5000, 375, 2250, 1875",
        "11432, -21, 0, 0"
    })
    void testEncomendaPostoComumSituacaoSobraviso (int qtdade, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(500, 4000, 2500);
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.COMUM);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }

    @ParameterizedTest
    @CsvSource({
        "5000, 250, 500, 1250",
        "5716, -21, 0, 0"
    })
    void testEncomendaPostoEstrategicoSituacaoSobraviso (int qtdade, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(500, 4000, 2500);
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.ESTRATEGICO);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }

    @ParameterizedTest
    @CsvSource({
        "20, -14, 0, 0",
        "11432, -14, 0, 0"
    })
    void testEncomendaPostoComumSituacaoEmergencia (int qtdade, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(500, 4000, 600);
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.COMUM);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }

    @ParameterizedTest
    @CsvSource({
        "3000, 425, 2950, 225",
        "4808, -21, 0, 0"
    })
    void testEncomendaPostoEstrategcoSituacaoEmergencia (int qtdade, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(500, 4000, 600);
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.ESTRATEGICO);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }

    @Test 
    public void testEncomendaInvalida() { 
        int[] vet = c.encomendaCombustivel(-1, TIPOPOSTO.COMUM);
        int resultadoEsperado[] = {-7, 0, 0};
        Assertions.assertArrayEquals(resultadoEsperado, vet); 
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

    @Test
    public void testDefineSituacaoNormal () {
        c.encomendaCombustivel(1, TIPOPOSTO.COMUM);
        c.defineSituacao();
        Assertions.assertEquals(c.getSituacao(), SITUACAO.NORMAL);
    }

    @Test
    public void testDefineSituacaoSobraviso () {
        c.encomendaCombustivel(6000, TIPOPOSTO.COMUM);
        c.defineSituacao();
        Assertions.assertEquals(c.getSituacao(), SITUACAO.SOBRAVISO);
    }

    @Test
    public void testDefineSituacaoEmergencia () {
        c.encomendaCombustivel(9000, TIPOPOSTO.COMUM);
        c.defineSituacao();
        Assertions.assertEquals(c.getSituacao(), SITUACAO.EMERGENCIA);
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