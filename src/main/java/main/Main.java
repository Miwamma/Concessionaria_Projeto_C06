package main;

import veiculo.*;
import comunicacao.*;
import arquivo.Arquivo;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Arquivo arq = new Arquivo();

        MonitorRede monitor = new MonitorRede(); // instanciando o monitor

        // abrindo os arqs
        ArrayList<Aviao> avioes = arq.carregarAvioes();
        ArrayList<Carro> carros = arq.carregarCarros();

        // iniciar o monitor
        monitor.start();

        boolean controle = true;

        while (controle) {
            System.out.println("==============================");
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
                    // add veiculo
                    System.out.print("Tipo de veículo (1 - Avião | 2 - Carro): ");
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
                        // instância de Aviao
                        Aviao aviao = new Aviao(id, marca, modelo, ano, potencia, velMax, codigoCom);
                        avioes.add(aviao);
                        arq.salvarAviao(aviao);
                        monitor.notificarNovoVeiculo(); // Usar método do monitor
                        System.out.println("Avião cadastrado com sucesso!");
                    } else if (tipo == 2) {
                        // instância de Carro
                        Carro carro = new Carro(id, marca, modelo, ano, potencia, velMax, codigoCom);
                        carros.add(carro);
                        arq.salvarCarro(carro);
                        monitor.notificarNovoVeiculo(); // Usar método do monitor
                        System.out.println("Carro cadastrado com sucesso!");
                    }
                    break;

                case 2:
                    // listando com os getters
                    System.out.println("--- Lista de Aviões ---");
                    if (avioes.isEmpty()) {
                        System.out.println("(Nenhum avião cadastrado)");
                    }
                    for (Aviao a : avioes) {
                        System.out.println("ID: " + a.getId() +
                                " | " + a.getMarca() +
                                " " + a.getModelo() +
                                " | Código: " + a.getCodigoComunicacao());
                    }

                    System.out.println("--- Lista de Carros ---");
                    if (carros.isEmpty()) {
                        System.out.println("(Nenhum carro cadastrado)");
                    }
                    for (Carro c : carros) {
                        System.out.println("ID: " + c.getId() +
                                " | " + c.getMarca() +
                                " " + c.getModelo() +
                                " | Código: " + c.getCodigoComunicacao());
                    }
                    break;

                case 3:
                    // acoes nos veículos usando interface Acoes
                    System.out.print("Tipo (1 - Avião | 2 - Carro): ");
                    int tipoAcao = sc.nextInt();
                    sc.nextLine();

                    if (tipoAcao == 1) {
                        System.out.println("Aviões disponíveis:");
                        for (int i = 0; i < avioes.size(); i++) {
                            System.out.println(i + " - " + avioes.get(i).getModelo());
                        }

                        System.out.print("Escolha: ");
                        int escolha = sc.nextInt();
                        sc.nextLine();

                        if (escolha >= 0 && escolha < avioes.size()) {
                            System.out.println("1 - Ligar");
                            System.out.println("2 - Mover");
                            System.out.println("3 - Realizar Comunicação");
                            System.out.println("4 - Desligar");
                            System.out.print("Escolha ação: ");
                            int acao = sc.nextInt();
                            sc.nextLine();

                            Aviao aviaoSelecionado = avioes.get(escolha);
                            switch (acao) {
                                case 1:
                                    aviaoSelecionado.ligar();
                                    break;
                                case 2:
                                    aviaoSelecionado.mover();
                                    break;
                                case 3:
                                    aviaoSelecionado.realizarComunicacao();
                                    break;
                                case 4:
                                    aviaoSelecionado.desligar();
                                    break;
                                default:
                                    System.out.println("Ação inválida");
                            }
                        }
                    } else if (tipoAcao == 2) {
                        System.out.println("Carros disponíveis:");
                        for (int i = 0; i < carros.size(); i++) {
                            System.out.println(i + " - " + carros.get(i).getModelo());
                        }

                        System.out.print("Escolha: ");
                        int escolha = sc.nextInt();
                        sc.nextLine();

                        if (escolha >= 0 && escolha < carros.size()) {
                            System.out.println("1 - Ligar");
                            System.out.println("2 - Mover");
                            System.out.println("3 - Realizar Comunicação");
                            System.out.println("4 - Desligar");
                            System.out.print("Escolha ação: ");
                            int acao = sc.nextInt();
                            sc.nextLine();

                            Carro carroSelecionado = carros.get(escolha);
                            switch (acao) {
                                case 1:
                                    carroSelecionado.ligar();
                                    break;
                                case 2:
                                    carroSelecionado.mover();
                                    break;
                                case 3:
                                    carroSelecionado.realizarComunicacao();
                                    break;
                                case 4:
                                    carroSelecionado.desligar();
                                    break;
                                default:
                                    System.out.println("Ação inválida");
                            }
                        }
                    }
                    break;

                case 4:
                    // removendo veículo usando o método que já atualiza o arquivo
                    System.out.print("Tipo (1 - Avião | 2 - Carro): ");
                    int tipoRemover = sc.nextInt();
                    sc.nextLine();

                    if (tipoRemover == 1 && !avioes.isEmpty()) {
                        System.out.println("\n--- Aviões Disponíveis ---");
                        for (int i = 0; i < avioes.size(); i++) {
                            System.out.println(i + " - " + avioes.get(i).getModelo());
                        }

                        System.out.print("Qual remover? ");
                        int remover = sc.nextInt();
                        sc.nextLine();

                        arq.removerAviao(avioes, remover);

                    } else if (tipoRemover == 2 && !carros.isEmpty()) {
                        System.out.println("\n--- Carros Disponíveis ---");
                        for (int i = 0; i < carros.size(); i++) {
                            System.out.println(i + " - " + carros.get(i).getModelo());
                        }

                        System.out.print("Qual remover? ");
                        int remover = sc.nextInt();
                        sc.nextLine();

                        arq.removerCarro(carros, remover);

                    } else {
                        System.out.println("Nenhum veículo disponível ou tipo inválido.");
                    }
                    break;

                case 5:
                    // comunicação entre veículos
                    ArrayList<Veiculo> todosVeiculos = new ArrayList<>();
                    todosVeiculos.addAll(avioes);
                    todosVeiculos.addAll(carros);

                    if (todosVeiculos.size() >= 2) {
                        System.out.println("Veículos disponíveis:");
                        for (int i = 0; i < todosVeiculos.size(); i++) {
                            Veiculo v = todosVeiculos.get(i);
                            System.out.println(i + " - " + v.getModelo() +
                                    " (" + (v instanceof Aviao ? "Avião" : "Carro") +
                                    ") - Código: " + v.getCodigoComunicacao());
                        }

                        System.out.print("Selecione o REMETENTE: ");
                        int origem = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Selecione o DESTINO: ");
                        int destino = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Digite a mensagem: ");
                        String msg = sc.nextLine();

                        // criar thread de comunicação
                        ThreadComunicacao thread = new ThreadComunicacao(
                                todosVeiculos.get(origem),
                                todosVeiculos.get(destino),
                                msg,
                                monitor
                        );
                        thread.start();

                    } else {
                        System.out.println("É necessário pelo menos 2 veículos para comunicação!");
                    }
                    break;

                case 6:
                    // relatórios usando o monitor
                    System.out.println("=========== RELATÓRIOS ===========");
                    System.out.println("Total de Aviões: " + avioes.size());
                    System.out.println("Total de Carros: " + carros.size());
                    System.out.println("Total de Veículos: " + (avioes.size() + carros.size()));

                    System.out.println("Códigos de comunicação:");
                    for (Aviao a : avioes) {
                        System.out.println("Avião " + a.getModelo() + ": " + a.getCodigoComunicacao());
                    }
                    for (Carro c : carros) {
                        System.out.println("Carro " + c.getModelo() + ": " + c.getCodigoComunicacao());
                    }
                    break;

                case 0:
                    controle = false;
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}