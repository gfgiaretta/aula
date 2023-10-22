package com.bcopstein;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.BeforeAll;
import java.util.Arrays;
import static org.mockito.Mockito.*;

public class Ex2Test {
    private ServicoEstatistica se = null;
    private static IEventoRepository rep;
    private static ICalculoEstatistica calc;

    @BeforeAll
    public static void inicializa(){
        rep = mock(IEventoRepository.class);
        when(rep.todos()).thenReturn(Arrays.asList(
            new Evento(10,"POA Day RUN", 10, 3, 2021, 5000, 0, 43, 0),
            new Evento(12,"POA Night RUN", 15, 5, 2021, 5000, 0, 42,0),
            new Evento(11,"NIKE RUN", 17, 6, 2021, 21000, 1, 22,0),
            new Evento(14,"SUMMER RUN", 22, 8, 2021, 5000, 0, 41, 0),      
            new Evento(16,"SPRING RUN", 22, 8, 2021, 5000, 0, 41, 30),      
            new Evento(18,"WINTER RUN", 2, 8, 2021, 5000, 0, 42, 30)));
            
        calc = mock(ICalculoEstatistica.class);
        when(calc.calculaEstatisticas(21000)).thenReturn(new EstatisticasDTO(4920, 4920, 0));
        when(calc.calculaEstatisticas(5000)).thenReturn(new EstatisticasDTO(2520, 2520, 42.42640687119285));
    }

    @ParameterizedTest
    @CsvSource({
        "21000, 4920",
        "5000, 2520"
    })
    public void testCalculaEstatisticasMedia(int dist, int med) {
        se = new ServicoEstatistica(rep, calc);
        EstatisticasDTO edto = se.calculaEstatisticas(dist);
        Assertions.assertEquals(edto.getMedia(), med);
    }

    @ParameterizedTest
    @CsvSource({
        "21000, 4920",
        "5000, 2520"
    })
    public void testCalculaEstatisticasMediana(int dist, int med) {
        se = new ServicoEstatistica(rep, calc);
        EstatisticasDTO edto = se.calculaEstatisticas(dist);
        Assertions.assertEquals(edto.getMediana(), med);
    }

    @ParameterizedTest
    @CsvSource({
        "21000, 0",
        "5000, 42.42640687119285"
    })
    public void testCalculaEstatisticasDesvioPadrao(int dist, double dp) {
        se = new ServicoEstatistica(rep, calc);
        EstatisticasDTO edto = se.calculaEstatisticas(dist);
        Assertions.assertEquals(edto.getDesvioPadrao(), dp);
    }

    @Test
    public void TestCalculaAumentoPerformanceProva1() {
        se = new ServicoEstatistica(rep, calc);
        PerformanceDTO pdto = se.calculaAumentoPerformance(5000,2021);
        Assertions.assertEquals(pdto.getProva1(), "POA Day RUN");
    }

    @Test
    public void TestCalculaAumentoPerformanceProva2() {
        se = new ServicoEstatistica(rep, calc);
        PerformanceDTO pdto = se.calculaAumentoPerformance(5000,2021);
        Assertions.assertEquals(pdto.getProva2(), "POA Night RUN");
    }

    @Test
    public void TestCalculaAumentoPerformanceReducao() {
        se = new ServicoEstatistica(rep, calc);
        PerformanceDTO pdto = se.calculaAumentoPerformance(5000,2021);
        Assertions.assertEquals(pdto.getReducao(), 60);
    }

    @Test
    public void TestCalculaAumentoPerformanceException() {
        se = new ServicoEstatistica(rep, calc);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            se.calculaAumentoPerformance(21000,2021);
        });
    }
}