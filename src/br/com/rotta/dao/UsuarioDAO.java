package br.com.rotta.dao;

import br.com.rotta.db.ConexaoBanco;
import br.com.rotta.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Camada DAO (Data Access Object): é a classe que conversa diretamente
// com o banco de dados.
//
// Ela é responsável pelo CRUD da tabela de usuários:
// Create  -> inserir
// Read    -> buscar e listar
// Update  -> atualizar
// Delete  -> deletar
//
// SQL usado para criar a tabela no banco:
//
// CREATE TABLE usuario (
//     id INT AUTO_INCREMENT PRIMARY KEY,
//     nome VARCHAR(100),
//     email VARCHAR(100) UNIQUE,
//     cpf VARCHAR(20),
//     senha VARCHAR(50),
//     telefone VARCHAR(20),
//     ativo BOOLEAN
// );
public class UsuarioDAO {

    // ===== CREATE =====

    // Insere um novo usuário no banco de dados.
    public void inserir(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO usuario "
                + "(nome, email, cpf, senha, telefone, ativo) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCpf());

            // A classe Usuario utiliza o atributo senhaHash.
            stmt.setString(4, usuario.getSenhaHash());

            stmt.setString(5, usuario.getTelefone());
            stmt.setBoolean(6, usuario.isAtivo());

            stmt.executeUpdate();

            System.out.println("Usuário inserido no banco com sucesso!");
        }
    }

    // ===== READ =====

    // Busca um usuário pelo e-mail.
    // Esse método pode ser utilizado durante o processo de login.
    public Usuario buscarPorEmail(String email) throws SQLException {

        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {

                // Se encontrar um usuário, transforma a linha do banco
                // em um objeto Usuario.
                if (rs.next()) {
                    return montarUsuario(rs);
                }
            }
        }

        // Retorna null quando nenhum usuário é encontrado.
        return null;
    }

    // Lista todos os usuários cadastrados no banco.
    public List<Usuario> listarTodos() throws SQLException {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuario";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Percorre todas as linhas encontradas no banco.
            while (rs.next()) {
                usuarios.add(montarUsuario(rs));
            }
        }

        return usuarios;
    }

    // ===== UPDATE =====

    // Atualiza o e-mail e o telefone de um usuário já existente.
    public void atualizar(Usuario usuario) throws SQLException {

        String sql = "UPDATE usuario "
                + "SET email = ?, telefone = ? "
                + "WHERE id = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getTelefone());
            stmt.setInt(3, usuario.getId());

            stmt.executeUpdate();

            System.out.println("Usuário atualizado no banco com sucesso!");
        }
    }

    // ===== DELETE =====

    // Remove um usuário do banco utilizando seu ID.
    public void deletar(int id) throws SQLException {

        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Usuário removido do banco com sucesso!");
        }
    }

    // ===== MÉTODO AUXILIAR =====

    // Transforma uma linha do ResultSet em um objeto Usuario.
    // Isso facilita o reaproveitamento desse processo nos métodos de consulta.
    private Usuario montarUsuario(ResultSet rs) throws SQLException {

        return new Usuario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("cpf"),
                rs.getString("senha"),
                rs.getString("telefone")
        );
    }
}