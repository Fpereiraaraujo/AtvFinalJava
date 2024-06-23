import java.io.Serializable;

public class Servico implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String descricao;
    private int duracao; // em minutos
    private double preco;

    // Construtor
    public Servico(String nome, String descricao, int duracao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.preco = preco;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", duracao=" + duracao +
                ", preco=" + preco +
                '}';
    }
}
