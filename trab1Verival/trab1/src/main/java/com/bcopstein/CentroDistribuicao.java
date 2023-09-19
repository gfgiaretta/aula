package com.bcopstein;

public class CentroDistribuicao { 
    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA } 
    public enum TIPOPOSTO { COMUM, ESTRATEGICO } 
    
    public static final int MAX_ADITIVO = 500; 
    public static final int MAX_ALCOOL = 2500; 
    public static final int MAX_GASOLINA = 10000;

    private int aditivo;
    private int gasolina;
    private int alcool;

    private SITUACAO situacao;
    
    public CentroDistribuicao (int tAditivo, int tGasolina, int tAlcool) {
        if (tAditivo < 0 || tGasolina < 0 || tAlcool < 0 || tAditivo > MAX_ADITIVO || tGasolina > MAX_GASOLINA || tAlcool > MAX_ALCOOL)
        {
            throw new IllegalArgumentException("parâmetro inválido.");
        }
        aditivo = tAditivo;
        gasolina = tGasolina;
        alcool = tAlcool;
        defineSituacao();
    } 
    
    public void defineSituacao() {
        if (aditivo < MAX_ADITIVO/4 || gasolina < MAX_GASOLINA/4 || alcool < MAX_ALCOOL/4) {
            situacao = SITUACAO.EMERGENCIA;
        }
        else if (aditivo < MAX_ADITIVO/2 || gasolina < MAX_GASOLINA/2 || alcool < MAX_ALCOOL/2) {
            situacao = SITUACAO.SOBRAVISO;
        }
        else {
            situacao = SITUACAO.NORMAL;
        }
    } 
    
    public SITUACAO getSituacao() {
        return situacao;
    } 
    
    public int gettGasolina() {
        return gasolina;
    } 
    
    public int gettAditivo() {
        return aditivo;
    } 
    
    public int gettAlcool() {
        return alcool;
    } 
    
    public int recebeAditivo(int qtdade) {
        if (qtdade <= 0 || qtdade > MAX_ADITIVO)
        {
            return -1;
        }

        if (qtdade + aditivo > MAX_ADITIVO)
        {
            qtdade = MAX_ADITIVO - aditivo;
            aditivo = MAX_ADITIVO;
            defineSituacao();
            return qtdade;
        }

        aditivo =+ qtdade;
        defineSituacao();
        return qtdade;
    } 
    
    public int recebeGasolina(int qtdade) {
        if (qtdade <= 0 || qtdade > MAX_GASOLINA)
        {
            return -1;
        }

        if (qtdade + gasolina > MAX_GASOLINA)
        {
            qtdade = MAX_GASOLINA - gasolina;
            gasolina = MAX_GASOLINA;
            defineSituacao();
            return qtdade;
        }

        gasolina =+ qtdade;
        defineSituacao();
        return qtdade;
    } 
    
    public int recebeAlcool(int qtdade) {
        if (qtdade <= 0 || qtdade > MAX_ALCOOL)
        {
            return -1;
        }

        if (qtdade + alcool > MAX_ALCOOL)
        {
            qtdade = MAX_ALCOOL - alcool;
            alcool = MAX_ALCOOL;
            defineSituacao();
            return qtdade;
        }

        alcool =+ qtdade;
        defineSituacao();
        return qtdade;
    } 
    
    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        if (aditivo < qtdade*0.05/2)

        if (situacao == SITUACAO.NORMAL)
        {
            aditivo -= qtdade*0.05;
            gasolina -= qtdade*0.7;
            alcool -= qtdade*0.25;
        }
        else {
            if (tipoPosto == TIPOPOSTO.COMUM)
            {
                if (situacao == SITUACAO.NORMAL)
                {
                    aditivo -= qtdade*0.05;
                    gasolina -= qtdade*0.7;
                    alcool -= qtdade*0.25;
                }
                else if (situacao == SITUACAO.SOBRAVISO)
                {
                    aditivo -= qtdade*0.05/2;
                    gasolina -= qtdade*0.7/2;
                    alcool -= qtdade*0.25/2;
                }
            }
            else
            {
                if (situacao == SITUACAO.SOBRAVISO)
                {
                    aditivo -= qtdade*0.05;
                    gasolina -= qtdade*0.7;
                    alcool -= qtdade*0.25;
                }
                else
                {
                    aditivo -= qtdade*0.05/2;
                    gasolina -= qtdade*0.7/2;
                    alcool -= qtdade*0.25/2;
                }
            }
        }
        defineSituacao();
    } 
} 
