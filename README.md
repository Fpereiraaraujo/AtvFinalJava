# AtividadeFinalJava


Regras de Negócios
1 - Gerenciamento de Serviços:
 [] A barbearia oferece diferentes tipos de serviços (corte de cabelo, barba, coloração, etc.), cada um com seu preço e duração.
 [] Cada serviço deve ser registrado com nome, descrição, duração e preço.


2 - Agendamento de Horários:
 [] Os clientes podem agendar serviços em horários específicos.
 [] O sistema deve verificar a disponibilidade de horários antes de confirmar o agendamento.
 [] Cada horário deve ser associado a um barbeiro específico.


3- Gerenciamento de Clientes:
 [] O sistema deve armazenar informações dos clientes, como nome, telefone e histórico de agendamentos.
 [] Clientes devem poder se cadastrar e acessar seus históricos de agendamentos.


4 - Gerenciamento de Barbeiros:
 [] Cada barbeiro tem uma agenda específica e só pode atender um cliente por vez.
 [] O sistema deve permitir a criação, edição e exclusão de barbeiros, bem como a visualização de suas agendas.


# AtividadeFinalJava

# Sistema de Gerenciamento de Barbearia

Este é um sistema simples para gerenciamento de uma barbearia, permitindo cadastrar clientes, serviços, agendamentos e gerar relatórios e estatísticas.

## Estrutura do Projeto

- `Agendamento.java`: Classe que representa um agendamento de serviço para um cliente.
- `Cliente.java`: Classe que representa um cliente da barbearia.
- `GerenciadorAgendamentos.java`: Classe responsável por gerenciar os agendamentos, incluindo criação, listagem, remoção e verificação de conflitos.
- `GerenciadorBarbearia.java`: Classe responsável por gerenciar os clientes e serviços da barbearia, incluindo cadastro, listagem e armazenamento.
- `Main.java`: Classe principal que contém a interface de linha de comando para interação com o sistema.
- `Pessoa.java`: Classe abstrata que representa uma pessoa, sendo estendida por `Cliente`.
- `RelatoriosEstatisticas.java`: Classe responsável por gerar relatórios e estatísticas sobre clientes, serviços e agendamentos.
- `Servico.java`: Classe que representa um serviço oferecido pela barbearia.

## Funcionalidades

### Cadastro de Clientes

Permite o cadastro de novos clientes com nome, telefone e email.

### Cadastro de Serviços

Permite o cadastro de novos serviços com nome, descrição, duração e preço.

### Agendamento de Serviços

Permite agendar serviços para clientes em horários específicos, verificando conflitos de horário.

### Listagem

- Listar Clientes
- Listar Serviços
- Listar Agendamentos

### Relatórios e Estatísticas

Geração de relatórios e estatísticas sobre clientes, serviços e agendamentos.

### Persistência de Dados

Salva e carrega dados de clientes, serviços e agendamentos a partir de arquivos.

## Como Executar

1. Compile o projeto:
    ```sh
    javac *.java
    ```

2. Execute a aplicação:
    ```sh
    java Main
    ```

## Uso

Ao executar a aplicação, um menu será apresentado com as seguintes opções:

1. **Cadastrar Cliente**: Solicita informações e cadastra um novo cliente.
2. **Cadastrar Serviço**: Solicita informações e cadastra um novo serviço.
3. **Agendar Serviço**: Permite agendar um serviço para um cliente.
4. **Listar Clientes**: Lista todos os clientes cadastrados.
5. **Listar Serviços**: Lista todos os serviços cadastrados.
6. **Listar Agendamentos**: Lista todos os agendamentos realizados.
7. **Gerar Relatórios e Estatísticas**: Permite gerar diversos relatórios e estatísticas.
8. **Salvar Dados**: Salva os dados de clientes, serviços e agendamentos em arquivos.
9. **Carregar Dados**: Carrega os dados de clientes, serviços e agendamentos a partir de arquivos.
0. **Sair**: Encerra a aplicação.

## Estrutura do Código

### Agendamento.java

Classe que representa um agendamento com cliente, serviço e data/hora.

### Cliente.java

Classe que representa um cliente com nome, telefone, email e histórico de serviços.

### GerenciadorAgendamentos.java

Classe que gerencia os agendamentos, permitindo criar, listar, remover e salvar em arquivo.

### GerenciadorBarbearia.java

Classe que gerencia clientes e serviços, permitindo cadastrar, listar e salvar em arquivo.

### Main.java

Classe principal que contém o menu interativo para o usuário.

### Pessoa.java

Classe abstrata que serve de base para a classe Cliente.

### RelatoriosEstatisticas.java

Classe que gera relatórios e estatísticas a partir dos dados de clientes, serviços e agendamentos.

### Servico.java

Classe que representa um serviço oferecido pela barbearia.

## Contribuição

Sinta-se à vontade para contribuir com este projeto. Sugestões, correções e melhorias são bem-vindas.

## GPT e Referências

O Chat GPT foi usado principalmente para tirar dúvidas e para geração do readme. Outras fontes de pesquisa incluem o site StackOverFlow para tirar dúvidas / resolver alguns problemas.