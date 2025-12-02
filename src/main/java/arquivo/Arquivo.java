package arquivo;

import veiculo.Aviao;
import veiculo.Carro;

import java.io.*;
import java.util.ArrayList;

public class Arquivo {

    // persistencia arquivo path
    private final String AVIAO_PATH = "src/data/veiculos_aviao.txt";
    private final String CARRO_PATH = "src/data/veiculos_carro.txt";

    public Arquivo() {
        criarArquivosSeNaoExistir();
    }

    // criando arquivo
    private void criarArquivosSeNaoExistir() {
        try {
            File aviaoFile = new File(AVIAO_PATH);
            File carroFile = new File(CARRO_PATH);

            if (!aviaoFile.exists()) {
                aviaoFile.getParentFile().mkdirs();
                aviaoFile.createNewFile();
            }

            if (!carroFile.exists()) {
                carroFile.getParentFile().mkdirs();
                carroFile.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("Erro ao criar arquivos de persistência.");
        }
    }

    // salvando aviao
    public void salvarAviao(Aviao a) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AVIAO_PATH, true))) {

            bw.write(
                    a.getId() + ";" +
                            a.getMarca() + ";" +
                            a.getModelo() + ";" +
                            a.getAno() + ";" +
                            a.getPotencia() + ";" +
                            a.getVelocidadeMaxima() + ";" +
                            a.getCodigoComunicacao()
            );
            bw.newLine();

        } catch (Exception e) {
            System.out.println("[ERRO] Não foi possível salvar o avião!");
        }
    }

    // removendo por indicie aviao e salvando
    public void removerAviao(ArrayList<Aviao> avioes, int indice) {
        if (indice >= 0 && indice < avioes.size()) {
            // Remove da lista
            avioes.remove(indice);

            // reescreve o arquivo completo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(AVIAO_PATH, false))) {
                for (Aviao a : avioes) {
                    bw.write(
                            a.getId() + ";" +
                                    a.getMarca() + ";" +
                                    a.getModelo() + ";" +
                                    a.getAno() + ";" +
                                    a.getPotencia() + ";" +
                                    a.getVelocidadeMaxima() + ";" +
                                    a.getCodigoComunicacao()
                    );
                    bw.newLine();
                }
                System.out.println("✓ Avião removido e arquivo atualizado!");
            } catch (Exception e) {
                System.out.println("[ERRO] Não foi possível atualizar o arquivo de aviões!");
            }
        }
    }

    // removendo carro por indice e salvando
    public void removerCarro(ArrayList<Carro> carros, int indice) {
        if (indice >= 0 && indice < carros.size()) {
            //removendo lista
            carros.remove(indice);

            // reescreve o arquivo completo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(CARRO_PATH, false))) {
                for (Carro c : carros) {
                    bw.write(
                            c.getId() + ";" +
                                    c.getMarca() + ";" +
                                    c.getModelo() + ";" +
                                    c.getAno() + ";" +
                                    c.getPotencia() + ";" +
                                    c.getVelocidadeMaxima() + ";" +
                                    c.getCodigoComunicacao()
                    );
                    bw.newLine();
                }
                System.out.println("✓ Carro removido e arquivo atualizado!");
            } catch (Exception e) {
                System.out.println("[ERRO] Não foi possível atualizar o arquivo de carros!");
            }
        }
    }

    // salvando carro
    public void salvarCarro(Carro c) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CARRO_PATH, true))) {

            bw.write(
                    c.getId() + ";" +
                            c.getMarca() + ";" +
                            c.getModelo() + ";" +
                            c.getAno() + ";" +
                            c.getPotencia() + ";" +
                            c.getVelocidadeMaxima() + ";" +
                            c.getCodigoComunicacao()
            );
            bw.newLine();

        } catch (Exception e) {
            System.out.println("[ERRO] Não foi possível salvar o carro!");
        }
    }

    // carregando aviao
    public ArrayList<Aviao> carregarAvioes() {
        ArrayList<Aviao> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(AVIAO_PATH))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                Aviao a = new Aviao(
                        Integer.parseInt(d[0]),
                        d[1], d[2],
                        Integer.parseInt(d[3]),
                        Integer.parseInt(d[4]),
                        Integer.parseInt(d[5]),
                        d[6]
                );

                lista.add(a);
            }

        } catch (Exception e) {
            System.out.println("[AVISO] Nenhum avião encontrado.");
        }

        return lista;
    }

    //carregando carro
    public ArrayList<Carro> carregarCarros() {
        ArrayList<Carro> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CARRO_PATH))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");

                Carro c = new Carro(
                        Integer.parseInt(d[0]),
                        d[1], d[2],
                        Integer.parseInt(d[3]),
                        Integer.parseInt(d[4]),
                        Integer.parseInt(d[5]),
                        d[6]
                );

                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("[AVISO] Nenhum carro encontrado.");
        }

        return lista;
    }
}