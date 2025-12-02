package veiculo;

import comunicacao.ModuloComunicacao;

public abstract class Veiculo {

    //atributos do veiculo
    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private int potencia;
    private int velocidadeMaxima;
    private String codigoComunicacao;
    protected ModuloComunicacao comunicador;

    //construtor
    public Veiculo(int id, String marca, String modelo, int ano, int potencia, int velocidadeMaxima, String codigoComunicacao) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.potencia = potencia;
        this.velocidadeMaxima = velocidadeMaxima;
        this.codigoComunicacao = codigoComunicacao;
        this.comunicador = new ModuloComunicacao(this);

    }

    public void enviarMensagem(String mensagem, Veiculo destino) {
        comunicador.transmitir(mensagem, destino);
    }

    //getters
    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public String getCodigoComunicacao() {
        return codigoComunicacao;
    }
}