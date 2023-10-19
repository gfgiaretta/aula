package com.bcopstein;

import java.util.List;

public interface IEventoRepository {
    List<Evento> todos();
    boolean cadastra(Evento evento);
}
