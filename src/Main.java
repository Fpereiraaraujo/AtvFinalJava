import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static GerenciadorBarbearia gerenciadorBarbearia = new GerenciadorBarbearia();
    private static GerenciadorAgendamentos gerenciadorAgendamentos = new GerenciadorAgendamentos();
    private static RelatoriosEstatisticas relatorios;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Servico");
            System.out.println("3. Criar Agendamento");
            System.out.println("4. Listar Clientes");
            System.out.println("5. Listar Servicos");
            System.out.println("6. Listar Agendamentos");
            System.out.println("7. Gerar Relatorios");
            System.out.println("8. Gerar Estatisticas");
            System.out.println("9. Salvar Dados");
            System.out.println("10. Carregar Dados");
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
                    gerenciadorBarbearia.listarClientes();
                    break;
                case 5:
                    gerenciadorBarbearia.listarServicos();
                    break;
                case 6:
                    gerenciadorAgendamentos.listarAgendamentos();
                    break;
                case 7:
                    gerarRelatorios(scanner);
                    break;
                case 8:
                    gerarEstatisticas(scanner);
                    break;
                case 9:
                    salvarDados(scanner);
                    break;
                case 10:
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
        gerenciadorBarbearia.listarClientes();
        System.out.print("\nEscolha o numero do cliente: ");
        int numeroCliente = scanner.nextInt();
        Cliente cliente = gerenciadorBarbearia.getCliente(numeroCliente);

        gerenciadorBarbearia.listarServicos();
        System.out.print("\nEscolha o numero do servico: ");
        int numeroServico = scanner.nextInt();
        Servico servico = gerenciadorBarbearia.getServico(numeroServico);

        scanner.nextLine();  // Consume the newline
        System.out.print("\nData e Hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        gerenciadorAgendamentos.criarAgendamento(cliente, servico, dataHora);
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
        System.out.print("Escolha uma opção: ");
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
