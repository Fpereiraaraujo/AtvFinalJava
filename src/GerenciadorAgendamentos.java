import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorAgendamentos {
    List<Agendamento> agendamentos;

    public GerenciadorAgendamentos() {
        this.agendamentos = new ArrayList<>();
    }

    // Método para criar um agendamento
    public void criarAgendamento(Cliente cliente, Servico servico, LocalDateTime dataHora) {
        if (cliente == null || servico == null || dataHora == null) {
            System.out.println("Cliente, serviço ou data/hora inválidos.");
            return;
        }

        if (conflitoDeHorario(cliente, dataHora, servico.getDuracao())) {
            System.out.println("Conflito de horário detectado para o cliente " + cliente.getNome() + " no horário " + dataHora);
            return;
        }

        Agendamento agendamento = new Agendamento(cliente, servico, dataHora);
        this.agendamentos.add(agendamento);
        cliente.adicionarServico(servico);
        System.out.println("Agendamento criado com sucesso: " + agendamento);
    }

    // Método para listar agendamentos
    public void listarAgendamentos() {
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento cadastrado.");
        } else {
            System.out.println("Lista de Agendamentos:");
            for (Agendamento agendamento : agendamentos) {
                System.out.println(agendamento);
            }
        }
    }

    // Método para remover um agendamento
    public void removerAgendamento(Agendamento agendamento) {
        if (agendamentos.remove(agendamento)) {
            System.out.println("Agendamento removido com sucesso: " + agendamento);
        } else {
            System.out.println("Agendamento não encontrado.");
        }
    }

    // Método para verificar conflito de horário
    private boolean conflitoDeHorario(Cliente cliente, LocalDateTime dataHora, int duracao) {
        LocalDateTime dataHoraFim = dataHora.plusMinutes(duracao);
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getCliente().equals(cliente)) {
                LocalDateTime inicio = agendamento.getDataHora();
                LocalDateTime fim = inicio.plusMinutes(agendamento.getServico().getDuracao());
                if (dataHora.isBefore(fim) && dataHoraFim.isAfter(inicio)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Método para salvar agendamentos em um arquivo
    public void salvarAgendamentos(String arquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(agendamentos);
            System.out.println("Agendamentos salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar agendamentos: " + e.getMessage());
        }
    }

    // Método para carregar agendamentos de um arquivo
    @SuppressWarnings("unchecked")
    public void carregarAgendamentos(String arquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            agendamentos = (List<Agendamento>) ois.readObject();
            System.out.println("Agendamentos carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar agendamentos: " + e.getMessage());
        }
    }
}

