import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;

@SuppressWarnings("unused")
public class Main {

    private static GerenciadorBarbearia gerenciadorBarbearia = new GerenciadorBarbearia();
    private static GerenciadorAgendamentos gerenciadorAgendamentos = new GerenciadorAgendamentos();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Exemplo: Cadastrando alguns serviços e clientes iniciais
        gerenciadorBarbearia.cadastrarServico("Corte Masculino", "Corte de cabelo para homens", 30, 50.0);
        gerenciadorBarbearia.cadastrarServico("Corte Feminino", "Corte de cabelo para mulheres", 45, 80.0);
        gerenciadorBarbearia.cadastrarCliente("Maria Silva", "99999-1234", "maria@gmail.com");
        gerenciadorBarbearia.cadastrarCliente("João Oliveira", "98888-5678", "joao@gmail.com");

        int opcao;
        do {
            System.out.println("=== Menu ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Serviço");
            System.out.println("3. Agendar Serviço");
            System.out.println("4. Listar Clientes");
            System.out.println("5. Listar Serviços");
            System.out.println("6. Listar Agendamentos");
            System.out.println("7. Gerar Relatórios e Estatísticas");
            System.out.println("8. Salvar Dados");
            System.out.println("9. Carregar Dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha pendente

            switch (opcao) {
                case 1:
                    cadastrarNovoCliente(scanner);
                    break;
                case 2:
                    cadastrarNovoServico(scanner);
                    break;
                case 3:
                    agendarNovoServico(scanner);
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
                    gerarRelatoriosEstatisticas();
                    break;
                case 8:
                    salvarDados();
                    break;
                case 9:
                    carregarDados();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }

            System.out.println(); // Linha em branco para melhorar a legibilidade
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarNovoCliente(Scanner scanner) {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone do cliente: ");
        String telefone = scanner.nextLine();
        System.out.print("E-mail do cliente: ");
        String email = scanner.nextLine();
        gerenciadorBarbearia.cadastrarCliente(nome, telefone, email);
    }

    private static void cadastrarNovoServico(Scanner scanner) {
        System.out.print("Nome do serviço: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição do serviço: ");
        String descricao = scanner.nextLine();
        System.out.print("Duração do serviço (em minutos): ");
        int duracao = scanner.nextInt();
        System.out.print("Preço do serviço: ");
        double preco = scanner.nextDouble();
        gerenciadorBarbearia.cadastrarServico(nome, descricao, duracao, preco);
        scanner.nextLine(); // Consumir a quebra de linha pendente
    }

    private static void agendarNovoServico(Scanner scanner) {
        gerenciadorBarbearia.listarClientes();
        System.out.print("Escolha o cliente pelo número: ");
        int numeroCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        gerenciadorBarbearia.listarServicos();
        System.out.print("Escolha o serviço pelo número: ");
        int numeroServico = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        Cliente cliente = gerenciadorBarbearia.getCliente(numeroCliente);
        Servico servico = gerenciadorBarbearia.getServico(numeroServico);

        if (cliente != null && servico != null) {
            LocalDateTime dataHora = LocalDateTime.now(); // Poderia ser mais sofisticado na prática
            gerenciadorAgendamentos.criarAgendamento(cliente, servico, dataHora);
        } else {
            System.out.println("Cliente ou serviço não encontrado.");
        }
    }

    private static void gerarRelatoriosEstatisticas() {
        RelatoriosEstatisticas relatorios = new RelatoriosEstatisticas(
                gerenciadorBarbearia.clientes,
                gerenciadorBarbearia.servicos,
                gerenciadorAgendamentos.agendamentos);
        System.out.println("=== Relatórios e Estatísticas ===");
        System.out.println("1. Relatório de Clientes");
        System.out.println("2. Relatório de Serviços");
        System.out.println("3. Relatório de Agendamentos");
        System.out.println("4. Estatísticas de Clientes");
        System.out.println("5. Estatísticas de Serviços");
        System.out.println("6. Estatísticas de Agendamentos");
        System.out.print("Escolha uma opção: ");

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

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
            case 4:
                relatorios.gerarEstatisticasClientes();
                break;
            case 5:
                relatorios.gerarEstatisticasServicos();
                break;
            case 6:
                relatorios.gerarEstatisticasAgendamentos();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void salvarDados() {
        gerenciadorBarbearia.salvarDados("clientes.dat", "servicos.dat");
        gerenciadorAgendamentos.salvarAgendamentos("agendamentos.dat");
    }

    private static void carregarDados() {
        gerenciadorBarbearia.carregarDados("clientes.dat", "servicos.dat");
        gerenciadorAgendamentos.carregarAgendamentos("agendamentos.dat");
    }
}
