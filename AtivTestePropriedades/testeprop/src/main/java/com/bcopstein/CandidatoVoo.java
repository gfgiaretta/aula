package com.bcopstein;

public class CandidatoVoo {

    public static boolean autoriza(float peso, float altura, float tempoEscadas) {
        if (peso <= 0 || altura <= 0 || tempoEscadas <= 0)
        {
            throw new IllegalArgumentException("Valor inválido.");
        }
        else if (peso <= 50 || peso >= 101 || altura <= 1.52 || altura >= 1.93 || tempoEscadas >= 80)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}