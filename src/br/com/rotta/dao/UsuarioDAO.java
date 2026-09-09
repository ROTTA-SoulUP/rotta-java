package br.com.rotta.dao;

import br.com.rotta.db.ConexaoBanco;
import br.com.rotta.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Camada DAO (Data Access Object): e a classe que conversa diretamente
// com o banco de dados Oracle.
//
//
// Script SQL usado para criar a tabela (rode no SQL Developer):
//
// CREATE TABLE USUARIO (
//     ID       NUMBER PRIMARY KEY,
//     NOME     VARCHAR2(100) NOT NULL,
//     EMAIL    VARCHAR2(100) NOT NULL UNIQUE,
//     CPF      VARCHAR2(20)  NOT NULL UNIQUE,
//     SENHA    VARCHAR2(50)  NOT NULL,
//     TELEFONE VARCHAR2(20),
//     ATIVO    NUMBER(1) DEFAULT 1 NOT NULL
// );

public class UsuarioDAO {

    // ===== CREATE =====

    // Insere um novo usuario no banco de dados (o ID ja vem definido no objeto)
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO USUARIO (ID, NOME, EMAIL, CPF, SENHA, TELEFONE, ATIVO) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getSenhaHash());
            stmt.setString(6, usuario.getTelefone());
            stmt.setInt(7, usuario.isAtivo() ? 1 : 0);

            stmt.executeUpdate();
            System.out.println("Usuário inserido no banco com sucesso.");
        }
    }

    // Descobre qual o proximo ID disponivel, olhando o maior ID já usado no banco
    public int buscarProximoId() throws SQLException {
        String sql = "SELECT NVL(MAX(ID), 0) + 1 AS PROXIMO_ID FROM USUARIO";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("PROXIMO_ID");
            }
            return 1;
        }
    }

    // ===== READ =====

    // Busca um usuario pelo email (usado no login)
    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE EMAIL = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montarUsuario(rs);
                }
            }
        }
        return null;
    }

    // Lista todos os usuarios cadastrados no banco
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(montarUsuario(rs));
            }
        }
        return usuarios;
    }

    // ===== UPDATE =====

    // Atualiza email e senha de um usuario ja existente
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE USUARIO SET EMAIL = ?, SENHA = ? WHERE ID = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenhaHash());
            stmt.setInt(3, usuario.getId());

            stmt.executeUpdate();
            System.out.println("Usuário atualizado no banco com sucesso!");
        }
    }

    // ===== DELETE =====

    // Remove um usuario do banco pelo id
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE ID = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Usuário removido do banco com sucesso!");
        }
    }

    // ===== METODO AUXILIAR =====

    // Transforma uma linha do ResultSet em um objeto Usuario
    private Usuario montarUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("ID"),
                rs.getString("NOME"),
                rs.getString("EMAIL"),
                rs.getString("CPF"),
                rs.getString("SENHA"),
                rs.getString("TELEFONE")
        );
    }
}