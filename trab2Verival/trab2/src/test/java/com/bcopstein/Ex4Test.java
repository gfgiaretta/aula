package com.bcopstein;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

    @Test
    public void testIntegracaoServicoEstatisticaEstatisticaDesconsideraMedia() {
        ed = new EstatisticaDesconsidera(rep);
        se = new ServicoEstatistica(rep, ed);
        EstatisticasDTO edto = se.calculaEstatisticas(21000);
        Assertions.assertEquals(edto.getMedia(), 4920);
    }

    @Test
    public void testIntegracaoServicoEstatisticaEstatisticaDesconsideraMediana() {
        ed = new EstatisticaDesconsidera(rep);
        se = new ServicoEstatistica(rep, ed);
        EstatisticasDTO edto = se.calculaEstatisticas(21000);
        Assertions.assertEquals(edto.getMediana(), 4920);
    }

    @Test
    public void testIntegracaoServicoEstatisticaEstatisticaDesconsideraDesvioPadrao() {
        ed = new EstatisticaDesconsidera(rep);
        se = new ServicoEstatistica(rep, ed);
        EstatisticasDTO edto = se.calculaEstatisticas(21000);
        Assertions.assertEquals(edto.getDesvioPadrao(), 0);
    }
}
