package main;

import veiculo.*;
import comunicacao.*;
import arquivo.Arquivo;
import interfacee.Acoes;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Monitor global da rede
    public static MonitorRede monitor = new MonitorRede();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Arquivo arq = new Arquivo();

        // Carregar dados salvos nos arquivos
        ArrayList<Aviao> avioes = arq.carregarAvioes();
        ArrayList<Carro> carros = arq.carregarCarros();

        // Iniciar o monitor de rede
        monitor.start();

        boolean controle = true;

        while (controle) {

            System.out.println("\n==============================");
            System.out.println("     CONCESSIONÁRIA DO CHRIS ");
            System.out.println("==============================");
            System.out.println("1 - Adicionar Veículo");
            System.out.println("2 - Listar Veículos");
            System.out.println("3 - Ações nos Veículos");
            System.out.println("4 - Excluir Veículo");
            System.out.println("5 - Comunicação entre Veículos");
            System.out.println("6 - Relatórios de Produção / Telecom");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    adicionarVeiculo(sc, avioes, carros, arq);
                    break;

                case 2:
                    listarVeiculos(avioes, carros);
                    break;

                case 3:
                    acionarVeiculo(sc, avioes, carros);
                    break;

                case 4:
                    removerVeiculo(sc, avioes, carros, arq);
                    break;

                case 5:
                    menuComunicacao(sc, avioes, carros);
                    break;

                case 6:
                    menuRelatorios(avioes, carros);
                    break;

                case 0:
                    controle = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }

    // ==========================================================
    // 1. ADICIONAR VEÍCULO
    // ==========================================================

    public static void adicionarVeiculo(Scanner sc, ArrayList<Aviao> avioes, ArrayList<Carro> carros, Arquivo arq) {

        System.out.println("Tipo de veículo (1 - Avião | 2 - Carro): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Marca: ");
        String marca = sc.nextLine();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Ano: ");
        int ano = sc.nextInt();
        sc.nextLine();

        System.out.print("Potência (HP): ");
        int potencia = sc.nextInt();
        sc.nextLine();

        System.out.print("Velocidade Máxima (km/h): ");
        int velMax = sc.nextInt();
        sc.nextLine();

        System.out.print("Código de Comunicação: ");
        String codigoCom = sc.nextLine();

        if (tipo == 1) {
            Aviao a = new Aviao(id, marca, modelo, ano, potencia, velMax, codigoCom);
            avioes.add(a);
            arq.salvarAviao(a);
            monitor.notificarNovoVeiculo();
            System.out.println("Avião cadastrado com sucesso!");

        } else if (tipo == 2) {

            Carro c = new Carro(id, marca, modelo, ano, potencia, velMax, codigoCom);
            carros.add(c);
            arq.salvarCarro(c);
            monitor.notificarNovoVeiculo();
            System.out.println("Carro cadastrado com sucesso!");

        } else {
            System.out.println("Tipo inválido.");
        }
    }

    // ==========================================================
    // 2. LISTAR VEÍCULOS
    // ==========================================================

    public static void listarVeiculos(ArrayList<Aviao> avioes, ArrayList<Carro> carros) {

        System.out.println("\n--- Lista de Aviões ---");
        if (avioes.isEmpty()) System.out.println("(Nenhum avião cadastrado)");

        for (Aviao a : avioes) {
            System.out.println("[" + a.getId() + "] " + a.getMarca() + " " + a.getModelo());
        }

        System.out.println("\n--- Lista de Carros ---");
        if (carros.isEmpty()) System.out.println("(Nenhum carro cadastrado)");

        for (Carro c : carros) {
            System.out.println("[" + c.getId() + "] " + c.getMarca() + " " + c.getModelo());
        }
    }

    // ==========================================================
    // 3. AÇÕES NOS VEÍCULOS
    // ==========================================================

    public static void acionarVeiculo(Scanner sc, ArrayList<Aviao> avioes, ArrayList<Carro> carros) {

        System.out.println("Tipo (1 - Avião | 2 - Carro)");
        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo == 1 && !avioes.isEmpty()) {

            for (int i = 0; i < avioes.size(); i++) {
                System.out.println(i + " - " + avioes.get(i).getModelo());
            }

            System.out.print("Escolha: ");
            int es = sc.nextInt();
            sc.nextLine();

            executarAcoes(avioes.get(es));

        } else if (tipo == 2 && !carros.isEmpty()) {

            for (int i = 0; i < carros.size(); i++) {
                System.out.println(i + " - " + carros.get(i).getModelo());
            }

            System.out.print("Escolha: ");
            int es = sc.nextInt();
            sc.nextLine();

            executarAcoes(carros.get(es));

        } else {
            System.out.println("Lista vazia ou tipo errado!");
        }
    }

    // ==========================================================
    // 4. REMOVER VEÍCULO
    // ==========================================================

    public static void removerVeiculo(Scanner sc, ArrayList<Aviao> avioes, ArrayList<Carro> carros, Arquivo arq) {

        System.out.println("Tipo (1 - Avião | 2 - Carro)");
        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo == 1 && !avioes.isEmpty()) {

            for (int i = 0; i < avioes.size(); i++) {
                System.out.println(i + " - " + avioes.get(i).getModelo());
            }

            System.out.print("Qual remover? ");
            int r = sc.nextInt();
            sc.nextLine();

            avioes.remove(r);
            System.out.println("Avião removido!");

        } else if (tipo == 2 && !carros.isEmpty()) {

            for (int i = 0; i < carros.size(); i++) {
                System.out.println(i + " - " + carros.get(i).getModelo());
            }

            System.out.print("Qual remover? ");
            int r = sc.nextInt();
            sc.nextLine();

            carros.remove(r);
            System.out.println("Carro removido!");

        } else {
            System.out.println("Nenhum veículo disponível.");
        }
    }

    // ==========================================================
    // 5. COMUNICAÇÃO ENTRE VEÍCULOS
    // ==========================================================

    public static void menuComunicacao(Scanner sc, ArrayList<Aviao> avioes, ArrayList<Carro> carros) {

        ArrayList<Veiculo> todos = new ArrayList<>();
        todos.addAll(avioes);
        todos.addAll(carros);

        if (todos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado!");
            return;
        }

        System.out.println("\n--- VEÍCULOS DISPONÍVEIS ---");
        for (int i = 0; i < todos.size(); i++) {
            System.out.println(i + " - [" + todos.get(i).getCodigoComunicacao() + "] " + todos.get(i).getModelo());
        }

        System.out.print("\nSelecione o REMETENTE: ");
        int origem = sc.nextInt();
        sc.nextLine();

        System.out.print("Selecione o DESTINO: ");
        int destino = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite a mensagem: ");
        String msg = sc.nextLine();

        ThreadComunicacao t = new ThreadComunicacao(todos.get(origem), todos.get(destino), msg);
        t.start();
    }

    // ==========================================================
    // 6. RELATÓRIOS DE PRODUÇÃO / TELECOM
    // ==========================================================

    public static void menuRelatorios(ArrayList<Aviao> avioes, ArrayList<Carro> carros) {

        System.out.println("\n=========== RELATÓRIOS DE PRODUÇÃO / TELECOM ===========");

        int totalA = avioes.size();
        int totalC = carros.size();
        int total = totalA + totalC;

        System.out.println("→ Total de Aviões cadastrados: " + totalA);
        System.out.println("→ Total de Carros cadastrados: " + totalC);
        System.out.println("→ Total geral de veículos: " + total);

        System.out.println("\n→ Lista de Códigos de Comunicação:");
        for (Aviao a : avioes) {
            System.out.println("   Avião " + a.getModelo() + " → Código: " + a.getCodigoComunicacao());
        }
        for (Carro c : carros) {
            System.out.println("   Carro " + c.getModelo() + " → Código: " + c.getCodigoComunicacao());
        }

        System.out.println("\n→ Estado da rede:");
        System.out.println("   Rede operacional. Monitor ativo.");

        System.out.println("\n→ Total de mensagens enviadas: " + Main.monitor.getMensagens());

        System.out.println("=========================================================\n");
    }

    // ==========================================================
    // 7. EXECUTAR AÇÕES DO VEÍCULO
    // ==========================================================

    public static void executarAcoes(Acoes veiculo) {

        System.out.println("1 - Ligar");
        System.out.println("2 - Mover");
        System.out.println("3 - Realizar Comunicação");
        System.out.println("4 - Desligar");
        System.out.print("Escolha ação: ");

        Scanner sc = new Scanner(System.in);
        int acao = sc.nextInt();
        sc.nextLine();

        switch (acao) {
            case 1:
                veiculo.ligar();
                break;

            case 2:
                veiculo.mover();
                break;

            case 3:
                veiculo.realizarComunicacao();
                break;

            case 4:
                veiculo.desligar();
                break;

            default:
                System.out.println("Ação inválida.");
        }
    }
}