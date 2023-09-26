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
        "500, 10000, 2500, 8000, 100, 4400, 500",
        "300, 10000, 2500, 6000, 0, 5800, 1000",
        "500, 6000, 2500, 8571, 71, 0, 357",
        "500, 10000, 2000, 8000, 100, 4400, 0",
        "300, 10000, 2500, 6020, -21, 0, 0",
        "500, 6000, 2500, 8573, -21, 0, 0",
        "500, 10000, 2500, 10005, -21, 0, 0"
    })
    void testEncomendaPostoComumSituacaoNormal (int sAditivo, int sGasolina, int sAlcool, int qtdade, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(sAditivo, sGasolina, sAlcool);
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.COMUM);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }


    @ParameterizedTest
    @CsvSource({
        "500, 10000, 2500, 8000, 100, 4400, 500",
        "300, 10000, 2500, 6000, 0, 5800, 1000",
        "500, 6000, 2500, 8571, 71, 0, 357",
        "500, 10000, 2000, 8000, 100, 4400, 0",
        "300, 10000, 2500, 6020, -21, 0, 0",
        "500, 6000, 2500, 8573, -21, 0, 0",
        "500, 10000, 2500, 10005, -21, 0, 0"
    })
    void testEncomendaPostoEstrategicoSituacaoNormal (int sAditivo, int sGasolina, int sAlcool, int qtdade, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(sAditivo, sGasolina, sAlcool);
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.ESTRATEGICO);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }

    @ParameterizedTest
    @CsvSource({
        "200, 3500, 1200, 4000, 100, 2100, 700",
        "200, 10000, 2500, 8000, 0, 7200, 1500",
        "500, 3500, 2500, 10000, 250, 0, 1250",
        "500, 10000, 1200, 9600, 260, 6640, 0",
        "200, 10000, 2500, 8040, -21, 0, 0",
        "500, 3500, 2500, 10004, -21, 0, 0",
        "500, 10000, 1200, 9608, -21, 0, 0"
    })
    void testEncomendaPostoComumSituacaoSobraviso (int sAditivo, int sGasolina, int sAlcool, int qtdade, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(sAditivo, sGasolina, sAlcool);
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.COMUM);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }

    @ParameterizedTest
    @CsvSource({
        "200, 3500, 1200, 2000, 100, 2100, 700",
        "200, 10000, 2500, 4000, 0, 7200, 1500",
        "500, 3500, 2500, 5000, 250, 0, 1250",
        "500, 10000, 1200, 4800, 260, 6640, 0",
        "200, 10000, 2500, 4020, -21, 0, 0",
        "500, 3500, 2500, 5002, -21, 0, 0",
        "500, 10000, 1200, 4804, -21, 0, 0"
    })
    void testEncomendaPostoEstrategicoSituacaoSobraviso (int sAditivo, int sGasolina, int sAlcool, int qtdade, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(sAditivo, sGasolina, sAlcool);
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.ESTRATEGICO);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }

    @ParameterizedTest
    @CsvSource({
        "100, 2100, 600, 2000, 50, 1400, 350",
        "100, 10000, 2500, 4000, 0, 8600, 2000",
        "500, 2100, 2500, 6000, 350, 0, 1750",
        "500, 10000, 600, 4800, 380, 8320, 0",
        "100, 10000, 2500, 4040, -21, 0, 0",
        "500, 2100, 2500, 6003, -21, 0, 0",
        "500, 10000, 600, 4808, -21, 0, 0"
    })
    void testEncomendaPostoEstrategicoSituacaoEmergencia (int sAditivo, int sGasolina, int sAlcool, int qtdade, int aditivo, int gasolina, int alcool) {
        c = new CentroDistribuicao(sAditivo, sGasolina, sAlcool);
        int[] vet = c.encomendaCombustivel(qtdade, TIPOPOSTO.ESTRATEGICO);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertArrayEquals(resultadoEsperado, vet);
    }

    @Test 
    public void testEncomendaPostoComumSituacaoEmergencia() { 
        c = new CentroDistribuicao(100, 100, 100);
        int[] vet = c.encomendaCombustivel(100, TIPOPOSTO.COMUM);
        int resultadoEsperado[] = {-14, 0, 0};
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