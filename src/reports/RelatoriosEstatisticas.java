package reports;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Agendamento;
import model.Cliente;
import model.Servico;

public class RelatoriosEstatisticas {
    private List<Cliente> clientes;
    private List<Servico> servicos;
    private List<Agendamento> agendamentos;

    public RelatoriosEstatisticas(List<Cliente> clientes, List<Servico> servicos, List<Agendamento> agendamentos) {
        this.clientes = clientes;
        this.servicos = servicos;
        this.agendamentos = agendamentos;
    }

    public void gerarRelatorioClientes() {
        System.out.println("\nRelatório de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void gerarRelatorioServicos() {
        System.out.println("\nRelatório de Serviços:");
        for (Servico servico : servicos) {
            System.out.println(servico);
        }
    }

    public void gerarRelatorioAgendamentos() {
        System.out.println("\nRelatório de Agendamentos:");
        for (Agendamento agendamento : agendamentos) {
            System.out.println(agendamento);
        }
    }

    public void gerarEstatisticasClientes() {
        System.out.println("Estatísticas de Clientes:");
        System.out.println("\nTotal de clientes: " + clientes.size() + "\n");

        // Contagem de clientes por email (ou qualquer outra lógica necessária)
        Map<String, Long> clientesPorEmail = clientes.stream()
            .collect(Collectors.groupingBy(Cliente::getEmail, Collectors.counting()));
        System.out.println("\nClientes por email: " + clientesPorEmail + "\n");
    }

    public void gerarEstatisticasServicos() {
        System.out.println("Estatísticas de Serviços:");
        System.out.println("\nTotal de serviços: " + servicos.size() + "\n");

        // Contagem de serviços por nome (ou qualquer outra lógica necessária)
        Map<String, Long> servicosPorNome = servicos.stream()
            .collect(Collectors.groupingBy(Servico::getNome, Collectors.counting()));
        System.out.println("\nServiços por nome: " + servicosPorNome + "\n");
    }

    public void gerarEstatisticasAgendamentos() {
        System.out.println("Estatísticas de Agendamentos:");
        System.out.println("\nTotal de agendamentos: " + agendamentos.size() + "\n");

        // Contagem de agendamentos por serviço
        Map<String, Long> agendamentosPorServico = agendamentos.stream()
            .collect(Collectors.groupingBy(a -> a.getServico().getNome(), Collectors.counting()));
        System.out.println("\nAgendamentos por serviço: " + agendamentosPorServico + "\n");

        // Contagem de agendamentos por cliente
        Map<String, Long> agendamentosPorCliente = agendamentos.stream()
            .collect(Collectors.groupingBy(a -> a.getCliente().getNome(), Collectors.counting()));
        System.out.println("\nAgendamentos por cliente: " + agendamentosPorCliente + "\n");
    }
}
