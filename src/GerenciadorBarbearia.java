import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorBarbearia {
    List<Cliente> clientes = new ArrayList<>();
    List<Servico> servicos = new ArrayList<>();

    public void cadastrarCliente(String nome, String telefone, String email) {
        clientes.add(new Cliente(nome, telefone, email));
    }

    public void cadastrarServico(String nome, String descricao, int duracao, double preco) {
        servicos.add(new Servico(nome, descricao, duracao, preco));
    }

    public void listarClientes() {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.println(i + ". " + cliente.getNome() + " - " + cliente.getTelefone() + " - " + cliente.getEmail());
        }
    }

    public void listarServicos() {
        for (int i = 0; i < servicos.size(); i++) {
            Servico servico = servicos.get(i);
            System.out.println(i + ". " + servico.getNome() + " - " + servico.getDescricao() + " - " + servico.getDuracao() + "min - R$" + servico.getPreco());
        }
    }

    public Cliente getCliente(int index) {
        if (index >= 0 && index < clientes.size()) {
            return clientes.get(index);
        }
        return null;
    }

    public Servico getServico(int index) {
        if (index >= 0 && index < servicos.size()) {
            return servicos.get(index);
        }
        return null;
    }

    public void cancelarAgendamento(Agendamento agendamento, LocalDateTime cancelamentoSolicitado) {
        long hoursDifference = ChronoUnit.HOURS.between(cancelamentoSolicitado, agendamento.getDataHora());
        if (hoursDifference >= 24) {
            System.out.println("Cancelamento sem custo.");
        } else {
            double taxaCancelamento = agendamento.getServico().getPreco() * 0.5;
            System.out.println("Cancelamento com taxa de: R$" + taxaCancelamento);
        }
    }
}
