package comunicacao;

import veiculo.Veiculo;


public class ThreadComunicacao extends Thread {

    private Veiculo origem;
    private Veiculo destino;
    private String mensagem;

    public ThreadComunicacao(Veiculo origem, Veiculo destino, String mensagem) {
        this.origem = origem;
        this.destino = destino;
        this.mensagem = mensagem;
    }

    @Override
    public void run() {
        try {
            System.out.println("🔄 Estabelecendo conexão...");
            Thread.sleep(1500);

            origem.enviarMensagem(mensagem, destino);

            main.Main.monitor.registrarMensagem();

            System.out.println("✔ Comunicação concluída.");
        } catch (InterruptedException e) {
            System.out.println("Erro na comunicação!");
        }
    }


}