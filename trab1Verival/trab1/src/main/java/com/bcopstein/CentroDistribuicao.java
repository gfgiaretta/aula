package com.bcopstein;
public class CentroDistribuicao {
    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA }
    public enum TIPOPOSTO { COMUM, ESTRATEGICO }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    private int tAditivo;
    private int tGasolina;
    private int tAlcool;
    private SITUACAO situacao;

    public CentroDistribuicao (int tAditivo, int tGasolina, int tAlcool) {
        if(tAditivo < 0 || tGasolina < 0 || tAlcool < 0 || tAditivo > MAX_ADITIVO || tGasolina > MAX_GASOLINA || tAlcool > MAX_ALCOOL){
            throw new IllegalArgumentException("Quantidade inválida.");
        }

        recebeAditivo(tAditivo);
        recebeGasolina(tGasolina);
        recebeAlcool(tAlcool);

        defineSituacao();
    }

    public void defineSituacao(){
        if((this.tAditivo < (MAX_ADITIVO * .25))
                || (this.tGasolina < (MAX_GASOLINA * .25))
                || (this.tAlcool < (MAX_ALCOOL * .25))){
            this.situacao = SITUACAO.EMERGENCIA;
        } else
        if((this.tAditivo < (MAX_ADITIVO * .5))
                || (this.tGasolina < (MAX_GASOLINA * .5))
                || (this.tAlcool < (MAX_ALCOOL * .5))){
            this.situacao = SITUACAO.SOBRAVISO;
        } else {
            this.situacao = SITUACAO.NORMAL;
        }
    }

    public SITUACAO getSituacao(){
        return this.situacao;
    }

    public int gettGasolina(){
        return this.tGasolina;
    }

    public int gettAditivo(){
        return this.tAditivo;
    }
    public int gettAlcool(){
        return this.tAlcool;
    }

    public int recebeAditivo(int qtdade) {
        if(qtdade < 0){
            return -1;
        }

        int possivelArmazenar = MAX_ADITIVO - this.tAditivo;
        int qtdArmazenada = qtdade > possivelArmazenar ? possivelArmazenar : qtdade;

        this.tAditivo += qtdArmazenada;

        defineSituacao();

        return qtdArmazenada;
    }

    public int recebeGasolina(int qtdade) {
        if(qtdade < 0){
            return -1;
        }

        int possivelArmazenar = MAX_GASOLINA - this.tGasolina;
        int qtdArmazenada = qtdade > possivelArmazenar ? possivelArmazenar : qtdade;

        this.tGasolina += qtdArmazenada;

        defineSituacao();

        return qtdArmazenada;
    }

    public int recebeAlcool(int qtdade) {
        if(qtdade < 0){
            return -1;
        }

        int possivelArmazenar = MAX_ALCOOL - this.tAlcool;
        int qtdArmazenada = qtdade > possivelArmazenar ? possivelArmazenar : qtdade;

        this.tAlcool += qtdArmazenada;

        defineSituacao();

        return qtdArmazenada;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        int[] result = new int[3];

        if(qtdade < 0){
            result[0] =-7;
            return result;
        } else if(this.situacao == SITUACAO.EMERGENCIA && tipoPosto == TIPOPOSTO.COMUM){
            result[0] = -14;
            return result;
        }

        double multSituacao = this.situacao == SITUACAO.NORMAL ? 1 : this.situacao == SITUACAO.SOBRAVISO ? .5 : .25;
        double multPosto = this.situacao == SITUACAO.NORMAL ? 1 : tipoPosto == TIPOPOSTO.ESTRATEGICO ? 2 : 1;

        int consumoAditivo  = (int) (qtdade * .05 * multPosto * multSituacao);
        int consumoAlcool   = (int) (qtdade * .25 * multPosto * multSituacao);
        int consumoGasolina = (int) (qtdade * .70 * multPosto * multSituacao);

        if((consumoAditivo > gettAditivo())
                || (consumoAlcool   > gettAlcool())
                || (consumoGasolina > gettGasolina())){

            result[0] = -21;

        } else {

            this.tAditivo -= consumoAditivo;
            this.tAlcool -= consumoAlcool;
            this.tGasolina -= consumoGasolina;

            result[0] = this.tAditivo;
            result[1] = this.tGasolina;
            result[2] = this.tAlcool;

            defineSituacao();
        }
        return result;
    }
}