package com.bcopstein;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.BeforeAll;
import java.util.Arrays;
import static org.mockito.Mockito.*;

public class Ex4Test {
    private EstatisticaDesconsidera ed = null;
    private ServicoEstatistica se = null;
    private static IEventoRepository rep;

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
    }

    @ParameterizedTest
    @CsvSource({
        "21000, 4920",
        "5000, 2520"
    })
    public void testIntegracaoServicoEstatisticaEstatisticaDesconsideraMedia(int dist, int med) {
        ed = new EstatisticaDesconsidera(rep);
        se = new ServicoEstatistica(rep, ed);
        EstatisticasDTO edto = se.calculaEstatisticas(dist);
        Assertions.assertEquals(edto.getMedia(), med);
    }

    @ParameterizedTest
    @CsvSource({
        "21000, 4920",
        "5000, 2520"
    })
    public void testIntegracaoServicoEstatisticaEstatisticaDesconsideraMediana(int dist, int med) {
        ed = new EstatisticaDesconsidera(rep);
        se = new ServicoEstatistica(rep, ed);
        EstatisticasDTO edto = se.calculaEstatisticas(dist);
        Assertions.assertEquals(edto.getMediana(), med);
    }

    @ParameterizedTest
    @CsvSource({
        "21000, 0",
        "5000, 42.42640687119285"
    })
    public void testIntegracaoServicoEstatisticaEstatisticaDesconsideraDesvioPadrao(int dist, double dp) {
        ed = new EstatisticaDesconsidera(rep);
        se = new ServicoEstatistica(rep, ed);
        EstatisticasDTO edto = se.calculaEstatisticas(dist);
        Assertions.assertEquals(edto.getDesvioPadrao(), dp);
    }
}
