package comunicacao;

import veiculo.Veiculo;

public class ModuloComunicacao {

    private Veiculo origem;

    public ModuloComunicacao(Veiculo origem) {
        this.origem = origem;
    }

    public void transmitir(String mensagem, Veiculo destino) {
        System.out.println("📡 Comunicando de [" + origem.getCodigoComunicacao() + "] "
                + origem.getModelo() + " para [" + destino.getCodigoComunicacao() + "] "
                + destino.getModelo() + ": \"" + mensagem + "\"");
    }
}