import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorBarbearia {
    List<Cliente> clientes;
    List<Servico> servicos;

    public GerenciadorBarbearia() {
        this.clientes = new ArrayList<>();
        this.servicos = new ArrayList<>();
    }

    // Métodos para cadastro de clientes
    public void cadastrarCliente(String nome, String telefone, String email) {
        if (clienteExiste(email)) {
            System.out.println("Cliente com email " + email + " já existe.");
            return;
        }
        Cliente cliente = new Cliente(nome, telefone, email);
        this.clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso: " + cliente);
    }

    // Métodos para cadastro de serviços
    public void cadastrarServico(String nome, String descricao, int duracao, double preco) {
        if (servicoExiste(nome)) {
            System.out.println("Serviço com nome " + nome + " já existe.");
            return;
        }
        Servico servico = new Servico(nome, descricao, duracao, preco);
        this.servicos.add(servico);
        System.out.println("Serviço cadastrado com sucesso: " + servico);
    }

    // Método para listar clientes
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    // Método para listar serviços
    public void listarServicos() {
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
        } else {
            System.out.println("Lista de Serviços:");
            for (Servico servico : servicos) {
                System.out.println(servico);
            }
        }
    }

    // Método para acessar um cliente pelo índice na lista
    public Cliente getCliente(int numeroCliente) {
        if (numeroCliente >= 0 && numeroCliente < clientes.size()) {
            return clientes.get(numeroCliente);
        }
        return null;
    }

    // Método para acessar um serviço pelo índice na lista
    public Servico getServico(int numeroServico) {
        if (numeroServico >= 0 && numeroServico < servicos.size()) {
            return servicos.get(numeroServico);
        }
        return null;
    }

    // Verifica se um cliente com o mesmo email já existe
    private boolean clienteExiste(String email) {
        return clientes.stream().anyMatch(cliente -> cliente.getEmail().equals(email));
    }

    // Verifica se um serviço com o mesmo nome já existe
    private boolean servicoExiste(String nome) {
        return servicos.stream().anyMatch(servico -> servico.getNome().equals(nome));
    }

    // Método para salvar dados em um arquivo
    public void salvarDados(String arquivoClientes, String arquivoServicos) {
        try (ObjectOutputStream oosClientes = new ObjectOutputStream(new FileOutputStream(arquivoClientes));
                ObjectOutputStream oosServicos = new ObjectOutputStream(new FileOutputStream(arquivoServicos))) {
            oosClientes.writeObject(clientes);
            oosServicos.writeObject(servicos);
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    // Método para carregar dados de um arquivo
    @SuppressWarnings("unchecked")
    public void carregarDados(String arquivoClientes, String arquivoServicos) {
        try (ObjectInputStream oisClientes = new ObjectInputStream(new FileInputStream(arquivoClientes));
                ObjectInputStream oisServicos = new ObjectInputStream(new FileInputStream(arquivoServicos))) {
            clientes = (List<Cliente>) oisClientes.readObject();
            servicos = (List<Servico>) oisServicos.readObject();
            System.out.println("Dados carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }
}
