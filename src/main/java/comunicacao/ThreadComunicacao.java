package comunicacao;

import veiculo.Veiculo;


public class ThreadComunicacao extends Thread {

    private Veiculo origem;
    private Veiculo destino;
    private String mensagem;
    private MonitorRede monitor;

    public ThreadComunicacao(Veiculo origem, Veiculo destino, String mensagem,  MonitorRede monitor) {
        this.origem = origem;
        this.destino = destino;
        this.mensagem = mensagem;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            System.out.println("🔄 Estabelecendo conexão...");
            Thread.sleep(1500);

            System.out.println("\n📡 Comunicando de [" + origem.getCodigoComunicacao() + "] " +
                    origem.getModelo() + " para [" + destino.getCodigoComunicacao() + "] " +
                    destino.getModelo() + ": \"" + mensagem + "\"");

            if (monitor != null) {
                monitor.registrarMensagem();
                System.out.println(" Mensagem registrada no monitor. Total: " + monitor.getMensagens());
            } else {
                System.out.println("Monitor não disponível para registrar mensagem");
            }

            System.out.println("✔ Comunicação concluída.");
        } catch (InterruptedException e) {
            System.out.println("Erro na comunicação!");
        }
    }


}