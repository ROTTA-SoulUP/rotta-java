package br.com.rotta.dao;

import br.com.rotta.db.ConexaoBanco;
import br.com.rotta.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // CREATE
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO USUARIO (ID, NOME, EMAIL, CPF, SENHA, TELEFONE, ATIVO) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, usuario.getId());
            comando.setString(2, usuario.getNome());
            comando.setString(3, usuario.getEmail());
            comando.setString(4, usuario.getCpf());
            comando.setString(5, usuario.getSenha());
            comando.setString(6, usuario.getTelefone());
            comando.setInt(7, usuario.isAtivo() ? 1 : 0);

            comando.executeUpdate();

            System.out.println("Usuário inserido no banco com sucesso.");
        }
    }

    // Busca o próximo ID disponível no banco
    public int buscarProximoId() throws SQLException {
        String sql = "SELECT NVL(MAX(ID), 0) + 1 AS PROXIMO_ID FROM USUARIO";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            if (resultado.next()) {
                return resultado.getInt("PROXIMO_ID");
            }
        }

        return 1;
    }

    // READ - busca por email
    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE EMAIL = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, email);

            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    return montarUsuario(resultado);
                }
            }
        }

        return null;
    }

    // READ - busca por CPF
    public Usuario buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE CPF = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, cpf);

            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    return montarUsuario(resultado);
                }
            }
        }

        return null;
    }

    // READ - lista todos os usuários
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                usuarios.add(montarUsuario(resultado));
            }
        }

        return usuarios;
    }

    // UPDATE
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE USUARIO SET EMAIL = ?, SENHA = ?, TELEFONE = ?, ATIVO = ? WHERE ID = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, usuario.getEmail());
            comando.setString(2, usuario.getSenha());
            comando.setString(3, usuario.getTelefone());
            comando.setInt(4, usuario.isAtivo() ? 1 : 0);
            comando.setInt(5, usuario.getId());

            comando.executeUpdate();

            System.out.println("Usuário atualizado no banco com sucesso.");
        }
    }

    // DELETE
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE ID = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            int linhasAfetadas = comando.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usuário removido do banco com sucesso.");
            } else {
                System.out.println("Usuário não encontrado no banco.");
            }
        }
    }

    // Converte o resultado do banco em um objeto Usuario
    private Usuario montarUsuario(ResultSet resultado) throws SQLException {
        return new Usuario(
                resultado.getInt("ID"),
                resultado.getString("NOME"),
                resultado.getString("EMAIL"),
                resultado.getString("CPF"),
                resultado.getString("SENHA"),
                resultado.getString("TELEFONE")
        );
    }
}