import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private String telefone;
    private String email;
    private List<Servico> historicoServicos;

    // Construtor padrão
    public Cliente() {
        this.historicoServicos = new ArrayList<>();
    }

    // Construtor com parâmetros
    public Cliente(String nome, String telefone, String email) {
        super(nome);
        this.telefone = telefone;
        this.email = email;
        this.historicoServicos = new ArrayList<>();
    }

    // Getters e Setters
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Servico> getHistoricoServicos() {
        return historicoServicos;
    }

    public void setHistoricoServicos(List<Servico> historicoServicos) {
        this.historicoServicos = historicoServicos;
    }

    // Método para adicionar um serviço ao histórico
    public void adicionarServico(Servico servico) {
        this.historicoServicos.add(servico);
    }

    // Método para retornar a quantidade de serviços realizados
    public int getQuantidadeServicos() {
        return this.historicoServicos.size();
    }

    // Método para retornar a despesa total do cliente
    public double getDespesaTotal() {
        double total = 0.0;
        for (Servico servico : historicoServicos) {
            total += servico.getPreco();
        }
        return total;
    }

    // Método para imprimir detalhes do cliente
    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + getNome() + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", historicoServicos=" + historicoServicos +
                '}';
    }
}
