package br.com.rotta.models;

import java.time.LocalDateTime;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String senhaHash;
    private String telefone;
    private LocalDateTime dataCadastro;
    private boolean ativo;

    // Cria um usuário e registra automaticamente a data de cadastro.
    public Usuario(int id, String nome, String email, String cpf,
                   String senha, String telefone) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senhaHash = senha;
        this.telefone = telefone;
        this.dataCadastro = LocalDateTime.now();
        this.ativo = true;
    }

    // Simula o cadastro do usuário.
    public void cadastrar() {
        System.out.println("Usuário " + nome + " cadastrado com sucesso.");
    }

    // Verifica se a senha informada corresponde à senha cadastrada.
    public boolean login(String cpf, String senha) {
        if (ativo && this.cpf.equals(cpf) && senhaHash.equals(senha)) {
            System.out.println("Login realizado com sucesso. Bem-vindo, " + nome + "!");
            return true;
        }
        System.out.println("CPF ou senha inválidos.");
        return false;
    }

    // Atualiza os dados de contato do usuário.
    public void atualizarDados(String novoEmail, String novaSenha) {
        email = novoEmail;
        senhaHash = novaSenha;
        System.out.println("Dados atualizados com sucesso.");
    }

    // Desativa a conta do usuário.
    public void desativarConta() {
        ativo = false;

        System.out.println("Conta desativada com sucesso.");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSenhaHash() {
        return senhaHash;
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