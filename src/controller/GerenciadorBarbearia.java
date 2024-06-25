package controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import model.Agendamento;
import model.Cliente;
import model.Servico;

public class GerenciadorBarbearia {
    private List<Cliente> clientes;
    private List<Servico> servicos;

    public GerenciadorBarbearia() {
        this.clientes = new ArrayList<>();
        this.servicos = new ArrayList<>();
    }

    // Métodos para cadastro de clientes
    public void cadastrarCliente(String nome, String telefone, String email) {
        if (clienteExiste(email)) {
            System.out.println("\nCliente com email " + email + " ja existe.\n");
            return;
        }
        Cliente cliente = new Cliente(nome, telefone, email);
        this.clientes.add(cliente);
        System.out.println("\nCliente cadastrado com sucesso: " + cliente + "\n");
    }

    // Métodos para cadastro de serviços
    public void cadastrarServico(String nome, String descricao, int duracao, double preco) {
        if (servicoExiste(nome)) {
            System.out.println("\nServiço com nome " + nome + " já existe.\n");
            return;
        }
        Servico servico = new Servico(nome, descricao, duracao, preco);
        this.servicos.add(servico);
        System.out.println("\nServico cadastrado com sucesso: " + servico + "\n");
    }

    // Método para listar clientes com índice
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado.");
        } else {
            System.out.println("\nLista de Clientes:");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println("[" + i + "] " + clientes.get(i));
            }
        }
    }

    // Método para listar serviços
    public void listarServicos() {
        if (servicos.isEmpty()) {
            System.out.println("\nNenhum serviço cadastrado.");
        } else {
            System.out.println("\nLista de Serviços:");
            
            for (int i = 0; i < servicos.size(); i++) {
                System.out.println("[" + i + "] " + servicos.get(i));
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

    public void cancelarAgendamento(Agendamento agendamento, LocalDateTime cancelamentoSolicitado) {
        long hoursDifference = ChronoUnit.HOURS.between(cancelamentoSolicitado, agendamento.getDataHora());
        if (hoursDifference >= 24) {
            System.out.println("Cancelamento sem custo.");
        } else {
            double taxaCancelamento = agendamento.getServico().getPreco() * 0.5;
            System.out.println("Cancelamento com taxa de: R$" + taxaCancelamento);
        }
    }

    // Método para obter a lista de clientes
    public List<Cliente> getClientes() {
        return clientes;
    }

    // Método para obter a lista de serviços
    public List<Servico> getServicos() {
        return servicos;
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

        try (ObjectOutputStream oosClientes = new ObjectOutputStream(
                new FileOutputStream(arquivoClientes));
                ObjectOutputStream oosServicos = new ObjectOutputStream(
                        new FileOutputStream(arquivoServicos))) {
            oosClientes.writeObject(clientes);
            oosServicos.writeObject(servicos);
            System.out.println("\nDados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("\nErro ao salvar dados: " + e.getMessage());
        }
    }

    // Método para carregar dados de um arquivo
    @SuppressWarnings("unchecked")
    public void carregarDados(String arquivoClientes, String arquivoServicos) {
        try (ObjectInputStream oisClientes = new ObjectInputStream(new FileInputStream(arquivoClientes));
                ObjectInputStream oisServicos = new ObjectInputStream(new FileInputStream(arquivoServicos))) {
            clientes = (List<Cliente>) oisClientes.readObject();
            servicos = (List<Servico>) oisServicos.readObject();
            System.out.println("\nDados carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\nErro ao carregar dados: " + e.getMessage());
        }
    }
}
