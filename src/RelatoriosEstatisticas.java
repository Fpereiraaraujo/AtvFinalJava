import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatoriosEstatisticas {
    private List<Cliente> clientes;
    private List<Servico> servicos;
    private List<Agendamento> agendamentos;

    public RelatoriosEstatisticas(List<Cliente> clientes, List<Servico> servicos, List<Agendamento> agendamentos) {
        this.clientes = clientes;
        this.servicos = servicos;
        this.agendamentos = agendamentos;
    }

    // Relatório de clientes
    public void gerarRelatorioClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("Relatório de Clientes:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    // Relatório de serviços
    public void gerarRelatorioServicos() {
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
        } else {
            System.out.println("Relatório de Serviços:");
            for (Servico servico : servicos) {
                System.out.println(servico);
            }
        }
    }

    // Relatório de agendamentos
    public void gerarRelatorioAgendamentos() {
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento cadastrado.");
        } else {
            System.out.println("Relatório de Agendamentos:");
            for (Agendamento agendamento : agendamentos) {
                System.out.println(agendamento);
            }
        }
    }

    // Estatísticas de clientes
    public void gerarEstatisticasClientes() {
        System.out.println("Estatísticas de Clientes:");
        System.out.println("Total de clientes: " + clientes.size());

        // Estatísticas sobre serviços dos clientes
        Map<String, Long> servicosPorCliente = agendamentos.stream()
                .collect(Collectors.groupingBy(agendamento -> agendamento.getCliente().getNome(),
                        Collectors.counting()));
        servicosPorCliente.forEach((cliente, quantidade) -> {
            System.out.println(cliente + " realizou " + quantidade + " agendamentos.");
        });
    }

    // Estatísticas de serviços
    public void gerarEstatisticasServicos() {
        System.out.println("Estatísticas de Serviços:");
        System.out.println("Total de serviços: " + servicos.size());

        // Estatísticas sobre popularidade dos serviços
        Map<String, Long> popularidadeServicos = agendamentos.stream()
                .collect(Collectors.groupingBy(agendamento -> agendamento.getServico().getNome(),
                        Collectors.counting()));
        popularidadeServicos.forEach((servico, quantidade) -> {
            System.out.println("Serviço " + servico + " foi agendado " + quantidade + " vezes.");
        });
    }

    // Estatísticas de agendamentos
    public void gerarEstatisticasAgendamentos() {
        System.out.println("Estatísticas de Agendamentos:");
        System.out.println("Total de agendamentos: " + agendamentos.size());

        // Estatísticas por cliente
        Map<String, Long> agendamentosPorCliente = agendamentos.stream()
                .collect(Collectors.groupingBy(agendamento -> agendamento.getCliente().getNome(),
                        Collectors.counting()));
        agendamentosPorCliente.forEach((cliente, quantidade) -> {
            System.out.println("Cliente " + cliente + " tem " + quantidade + " agendamentos.");
        });

        // Estatísticas por serviço
        Map<String, Long> agendamentosPorServico = agendamentos.stream()
                .collect(Collectors.groupingBy(agendamento -> agendamento.getServico().getNome(),
                        Collectors.counting()));
        agendamentosPorServico.forEach((servico, quantidade) -> {
            System.out.println("Serviço " + servico + " foi agendado " + quantidade + " vezes.");
        });
    }
}
