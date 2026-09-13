package br.com.rotta.models;

import java.time.LocalDateTime;

/**
 * Classe que representa o usuário do Rotta, seus dados de cadastro, acesso e status da conta.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

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
    /**
     * Cria um usuário e registra automaticamente a data de cadastro.
     *
     * @param id identificador do usuário
     * @param nome nome do usuário
     * @param email email do usuário
     * @param cpf CPF do usuário
     * @param senha senha do usuário
     * @param telefone telefone do usuário
     */
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

    // MÉTODOS
    /**
     * Simula a confirmação do cadastro do usuário.
     */
    public void cadastrar() {
        System.out.println("Usuário " + nome + " cadastrado com sucesso.");
    }

    /**
     * Verifica CPF, senha e status ativo antes de permitir o login.
     *
     * @param cpf CPF informado no login
     * @param senha senha informada no login
     * @return true se o login for válido; caso contrário, false
     */
    public boolean login(String cpf, String senha) {
        if (ativo && this.cpf.equals(cpf) && senhaHash.equals(senha)) {
            System.out.println("Login realizado com sucesso. Bem-vindo, " + nome + "!");
            return true;
        }
        System.out.println("CPF ou senha inválidos.");
        return false;
    }

    /**
     * Atualiza o email e a senha do usuário.
     *
     * @param novoEmail novo email do usuário
     * @param novaSenha nova senha do usuário
     */
    public void atualizarDados(String novoEmail, String novaSenha) {
        email = novoEmail;
        senhaHash = novaSenha;
        System.out.println("Dados atualizados com sucesso.");
    }

    /**
     * Desativa a conta do usuário.
     */
    public void desativarConta() {
        ativo = false;

        System.out.println("Conta desativada com sucesso.");
    }

    // GETTERS
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

    public String getSenha() {
        return senhaHash;
    }
}