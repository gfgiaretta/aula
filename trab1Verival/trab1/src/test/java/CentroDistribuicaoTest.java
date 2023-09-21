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
        c = new CentroDistribuicao(500, 2500, 10000); 
    } 

    @ParameterizedTest
    @CsvSource({
        "100, TIPOPOSTO.NORMAL, 400, 2400, 9900",
        "100, TIPOPOSTO.ESTRATEGICO, 400, 2400, 9900",
    })
    void situacaoNormal (int qtdade, TIPOPOSTO tipoPosto, int aditivo, int alcool, int gasolina) {
        int[] vet = c.encomendaCombustivel(qtdade, tipoPosto);
        int resultadoEsperado[] = {aditivo, gasolina, alcool};
        Assertions.assertEquals(resultadoEsperado, vet);
    }
}
