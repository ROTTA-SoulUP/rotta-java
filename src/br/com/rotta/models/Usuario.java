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

    public void cadastrar() {
        System.out.println("Usuário cadastrado!");
        System.out.println("\nNome: " + nome +
                            "\nEmail: " + email +
                            "\nData: " + dataCadastro.toLocalDate());
    }

    public void login() {
        if (ativo) {
            System.out.println("Login realizado. Bem-vindo, " + nome + "!");
        } else {
            System.out.println("Conta desativada. Contate o suporte.");
        }
    }

    public void atualizaDados(String novoEmail, String novoTelefone) {
        this.email = novoEmail;
        this.telefone = novoTelefone;
        System.out.println("Dados atualizados com sucesso!");
    }

    public void desativarConta() {
        this.ativo = false;
        System.out.println("Conta de " + nome + "desativada.");
    }

    //GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
