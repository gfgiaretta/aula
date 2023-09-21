import com.bcopstein.Funcionario;

import org.junit.jupiter.api.Assertions; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class FuncionarioTest {
    private Funcionario f = null;

    @BeforeEach 
    void setUp() { 
        f = new Funcionario(1); 
    } 

    @ParameterizedTest
    @CsvSource({
        "1, 0",
        "2500, 0",
        "2501, 0.12",
        "5001, 300.12",
        "5000, 300",
        "10000, 900",
    })
    void boundaryIRPF (double x, double eRes) {
        f.setSalarioBruto(x);
        Assertions.assertEquals(eRes, f.getIRPF());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 0.045",
        "2500, 112.5",
        "2501, 112.545",
        "5001, 225",
        "5000, 225",
        "10000, 225",
    })
    void boundaryINSS (double x, double eRes) {
        f.setSalarioBruto(x);
        Assertions.assertEquals(eRes, f.getINSS());
    }

    @ParameterizedTest
    @CsvSource({
        "1, 0.955",
        "2500, 2387.5",
        "2501, 2388.335",
        "5001, 4475.88",
        "5000, 4475",
        "10000, 8875",
    })
    void boundarySalarioLiquido (double x, double eRes) {
        f.setSalarioBruto(x);
        Assertions.assertEquals(eRes, f.getSalarioLiquido());
    }

    @Test
    public void testeException () {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            f.setSalarioBruto(0);
        });
    }
}