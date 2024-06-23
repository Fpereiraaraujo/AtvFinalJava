package view;

import java.time.LocalDateTime;
import java.util.Scanner;

import controller.GerenciadorAgendamentos;
import controller.GerenciadorBarbearia;
import model.Agendamento;
import model.Cliente;
import model.Servico;
import reports.RelatoriosEstatisticas;

public class Main {
    private static GerenciadorBarbearia gerenciadorBarbearia = new GerenciadorBarbearia();
    private static GerenciadorAgendamentos gerenciadorAgendamentos = new GerenciadorAgendamentos();
    private static RelatoriosEstatisticas relatorios;

    public static void main(String[] args) {

        // Exemplo: Cadastrando alguns serviços e clientes iniciais
        gerenciadorBarbearia.cadastrarServico("Corte Masculino", "Corte de cabelo para homens", 30, 50.0);
        gerenciadorBarbearia.cadastrarServico("Corte Feminino", "Corte de cabelo para mulheres", 45, 80.0);
        gerenciadorBarbearia.cadastrarCliente("Maria Silva", "99999-1234", "maria@gmail.com");
        gerenciadorBarbearia.cadastrarCliente("Joao Oliveira", "98888-5678", "joao@gmail.com");


        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Servico");
            System.out.println("3. Criar Agendamento");
            System.out.println("4. Cancelar Agendamento");
            System.out.println("5. Listar Clientes");
            System.out.println("6. Listar Servicos");
            System.out.println("7. Listar Agendamentos");
            System.out.println("8. Gerar Relatorios");
            System.out.println("9. Gerar Estatisticas");
            System.out.println("10. Salvar Dados");
            System.out.println("11. Carregar Dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    cadastrarServico(scanner);
                    break;
                case 3:
                    criarAgendamento(scanner);
                    break;
                case 4:
                    cancelarAgendamento(scanner);
                    break;
                case 5:
                    gerenciadorBarbearia.listarClientes();
                    break;
                case 6:
                    gerenciadorBarbearia.listarServicos();
                    break;
                case 7:
                    gerenciadorAgendamentos.listarAgendamentos();
                    break;
                case 8:
                    gerarRelatorios(scanner);
                    break;
                case 9:
                    gerarEstatisticas(scanner);
                    break;
                case 10:
                    salvarDados(scanner);
                    break;
                case 11:
                    carregarDados(scanner);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("\nOpcao invalida. Tente novamente.");
            }
        }
    }

    private static void cadastrarCliente(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        gerenciadorBarbearia.cadastrarCliente(nome, telefone, email);
    }

    private static void cadastrarServico(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();
        System.out.print("Duracao (minutos): ");
        int duracao = scanner.nextInt();
        System.out.print("Preco: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline
        gerenciadorBarbearia.cadastrarServico(nome, descricao, duracao, preco);
    }

    private static void criarAgendamento(Scanner scanner) {
        System.out.println("Clientes:");
        gerenciadorBarbearia.listarClientes();
        System.out.print("Escolha o cliente pelo numero: ");
        int numeroCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        System.out.println("Servicos:");
        gerenciadorBarbearia.listarServicos();
        System.out.print("Escolha o servico pelo numero: ");
        int numeroServico = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        Cliente cliente = gerenciadorBarbearia.getCliente(numeroCliente);
        Servico servico = gerenciadorBarbearia.getServico(numeroServico);

        if (cliente != null && servico != null) {
            LocalDateTime dataHora = LocalDateTime.now().plusDays(1); // Exemplo de agendamento para o dia seguinte
            gerenciadorAgendamentos.criarAgendamento(cliente, servico, dataHora);
            System.out.println("\nAgendamento criado para " + cliente.getNome() + " - Servico: " + servico.getNome() + " em " + dataHora);
        } else {
            System.out.println("Cliente ou servico nao encontrado.");
        }
    }


    private static void cancelarAgendamento(Scanner scanner) {
        gerenciadorAgendamentos.listarAgendamentos();
        System.out.print("Escolha o agendamento pelo número: ");
        int numeroAgendamento = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        if (numeroAgendamento >= 0 && numeroAgendamento < gerenciadorAgendamentos.agendamentos.size()) {
            Agendamento agendamento = gerenciadorAgendamentos.agendamentos.get(numeroAgendamento);
            gerenciadorBarbearia.cancelarAgendamento(agendamento, LocalDateTime.now());
            gerenciadorAgendamentos.cancelarAgendamento(agendamento);
        } else {
            System.out.println("Agendamento nao encontrado.");
        }
    }


    private static void gerarRelatorios(Scanner scanner) {
        if (relatorios == null) {
            relatorios = new RelatoriosEstatisticas(
                gerenciadorBarbearia.getClientes(),
                gerenciadorBarbearia.getServicos(),
                gerenciadorAgendamentos.getAgendamentos()
            );
        }
        System.out.println("\n--- Gerar Relatorios ---");
        System.out.println("1. Relatorio de Clientes");
        System.out.println("2. Relatorio de Servicos");
        System.out.println("3. Relatorio de Agendamentos");
        System.out.print("Escolha uma opcao: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        switch (opcao) {
            case 1:
                relatorios.gerarRelatorioClientes();
                break;
            case 2:
                relatorios.gerarRelatorioServicos();
                break;
            case 3:
                relatorios.gerarRelatorioAgendamentos();
                break;
            default:
                System.out.println("\nOpcao invalida.");
        }
    }

    private static void gerarEstatisticas(Scanner scanner) {
        if (relatorios == null) {
            relatorios = new RelatoriosEstatisticas(
                gerenciadorBarbearia.getClientes(),
                gerenciadorBarbearia.getServicos(),
                gerenciadorAgendamentos.getAgendamentos()
            );
        }
        System.out.println("\n--- Gerar Estatisticas ---");
        System.out.println("1. Estatisticas de Clientes");
        System.out.println("2. Estatisticas de Servicos");
        System.out.println("3. Estatisticas de Agendamentos");
        System.out.print("Escolha uma opcao: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        switch (opcao) {
            case 1:
                relatorios.gerarEstatisticasClientes();
                break;
            case 2:
                relatorios.gerarEstatisticasServicos();
                break;
            case 3:
                relatorios.gerarEstatisticasAgendamentos();
                break;
            default:
                System.out.println("\nOpcao invalida.");
        }
    }

    private static void salvarDados(Scanner scanner) {
        System.out.print("Nome do arquivo para salvar clientes: ");
        String arquivoClientes = scanner.nextLine();
        System.out.print("Nome do arquivo para salvar servicos: ");
        String arquivoServicos = scanner.nextLine();
        System.out.print("Nome do arquivo para salvar agendamentos: ");
        String arquivoAgendamentos = scanner.nextLine();

        gerenciadorBarbearia.salvarDados(arquivoClientes, arquivoServicos);
        gerenciadorAgendamentos.salvarAgendamentos(arquivoAgendamentos);
    }

    private static void carregarDados(Scanner scanner) {
        System.out.print("Nome do arquivo para carregar clientes: ");
        String arquivoClientes = scanner.nextLine();
        System.out.print("Nome do arquivo para carregar servicos: ");
        String arquivoServicos = scanner.nextLine();
        System.out.print("Nome do arquivo para carregar agendamentos: ");
        String arquivoAgendamentos = scanner.nextLine();

        gerenciadorBarbearia.carregarDados(arquivoClientes, arquivoServicos);
        gerenciadorAgendamentos.carregarAgendamentos(arquivoAgendamentos);

        // Atualizar o objeto de relatórios após carregar os dados
        relatorios = new RelatoriosEstatisticas(
            gerenciadorBarbearia.getClientes(),
            gerenciadorBarbearia.getServicos(),
            gerenciadorAgendamentos.getAgendamentos()
        );
    }
}
