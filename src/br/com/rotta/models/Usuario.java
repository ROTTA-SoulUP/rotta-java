package br.com.rotta.models;

import java.time.LocalDateTime;

public class Usuario {

    // ATRIBUTOS
    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String senhaHash;
    private String telefone;
    private LocalDateTime dataCadastro;
    private boolean ativo;

    // CONSTRUTOR
    public Usuario(int id, String nome, String email, String cpf, String senhaHash, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senhaHash = senhaHash;
        this.telefone = telefone;
        this.dataCadastro = LocalDateTime.now();
        this.ativo = true; // Sempre começa ativo
    }

    // METODOS
    public void cadastrar() {
        System.out.println("Usuário cadastrado!");
        System.out.println("\nNome: " + nome +
                            "\nEmail: " + email +
                            "\nData do cadastro: " + dataCadastro.toLocalDate() +
                            "\nBem-vindo a Rotta, " + nome + "! Sua jornada sustentável começa agora.");
    }

    public void login() {
        if (ativo) {
            System.out.println("Login realizado. Bem-vindo, " + nome + "!");
        } else {
            System.out.println("Nao foi possivel entrar. Sua conta esta desativada, contate o suporte.");
        }
    }

    public void atualizarDados(String novoEmail, String novoTelefone) {
        String emailAntigo = this.email;
        String telefoneAntigo = this.telefone;
        this.email = novoEmail;
        this.telefone = novoTelefone;
        System.out.println("Dados atualizados com sucesso!" +
                            "Email: " + emailAntigo + " - - " + novoEmail +
                            "Telefone: " + telefoneAntigo + " - - " + novoTelefone);
    }

    public void desativarConta() {
        this.ativo = false;
        System.out.println("Poxa, " + nome + "! Sua contra foi desativada. Sentiremos sua falta na Rotta, " + nome + ".");
    }

    // GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }
}

