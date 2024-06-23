import java.io.Serializable;
import java.time.LocalDateTime;

public class Agendamento implements Serializable {
    private static final long serialVersionUID = 1L;


    

    private Cliente cliente;
    private Servico servico;
    private LocalDateTime dataHora;

    // Construtor padrão
    public Agendamento() {
    }

    // Construtor com parâmetros
    public Agendamento(Cliente cliente, Servico servico, LocalDateTime dataHora) {
        if (dataHora.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Data/hora inválida");
        this.cliente = cliente;
        this.servico = servico;
        this.dataHora = dataHora;
    }

    // Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    // Método para imprimir detalhes do agendamento
    @Override
    public String toString() {
        return "Agendamento{" +
                "cliente=" + cliente.getNome() +
                ", servico=" + servico.getNome() +
                ", dataHora=" + dataHora +
                '}';
    }
}
