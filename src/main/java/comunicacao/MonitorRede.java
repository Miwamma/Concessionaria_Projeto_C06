package comunicacao;

public class MonitorRede extends Thread {

    private boolean ativo = true;
    private boolean novoVeiculo = false;

    public void notificarNovoVeiculo() {
        novoVeiculo = true;
    }

    @Override
    public void run() {
        while (ativo) {
            if (novoVeiculo) {
                System.out.println("📡 [Monitor] Rede atualizada! Novo veículo integrado ao sistema.");
                novoVeiculo = false;
            }

            try {
                Thread.sleep(500); // pequeno intervalo para não travar CPU
            } catch (InterruptedException e) {
                System.out.println("Erro no monitor da rede!");
            }
        }
    }

    private int mensagens = 0;

    public synchronized void registrarMensagem() {
        mensagens++;
    }

    public int getMensagens() {
        return mensagens;
    }


    public void desligar() {
        ativo = false;
    }
}