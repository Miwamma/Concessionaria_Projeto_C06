package veiculo;

import interfacee.Acoes;

public class Aviao extends Veiculo implements Acoes {

    // Construtor
    public Aviao(int id, String marca, String modelo, int ano, int potencia, int velocidadeMaxima, String codigoComunicacao) {
        super(id, marca, modelo, ano, potencia, velocidadeMaxima, codigoComunicacao);
    }

    // Métodos obrigatórios da interface Interface.Acoes
    @Override
    public void ligar() {
        System.out.println("Avião " + getModelo() + " está ligado.");
    }

    @Override
    public void desligar() {
        System.out.println("Avião " + getModelo() + " foi desligado.");
    }

    @Override
    public void mover() {
        System.out.println("Avião " + getModelo() + " está taxiando ou voando.");
    }

    @Override
    public void realizarComunicacao() {
        System.out.println("Avião " + getModelo() + " pronto para comunicação.");}
}