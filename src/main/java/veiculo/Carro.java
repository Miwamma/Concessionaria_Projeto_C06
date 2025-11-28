package veiculo;

import interfacee.Acoes;

public class Carro extends Veiculo implements Acoes {

    // Construtor
    public Carro(int id, String marca, String modelo, int ano, int potencia, int velocidadeMaxima, String codigoComunicacao) {
        super(id, marca, modelo, ano, potencia, velocidadeMaxima, codigoComunicacao);
    }

    // Métodos obrigatórios da interface Interface.Acoes
    @Override
    public void ligar() {
        System.out.println(getModelo() + " está ligado.");
    }

    @Override
    public void desligar() {
        System.out.println(getModelo() + " foi desligado.");
    }

    @Override
    public void mover() {
        System.out.println(getModelo() + " está em movimento.");
    }

    @Override
    public void realizarComunicacao() {
        System.out.println("Carro " + getModelo() + " pronto para comunicação.");
    }


}