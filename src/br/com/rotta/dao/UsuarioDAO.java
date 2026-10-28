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

    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO USUARIO (ID, NOME, EMAIL, CPF, SENHA, TELEFONE) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getCpf());
            ps.setString(5, usuario.getSenhaHash());
            ps.setString(6, usuario.getTelefone());

            ps.executeUpdate();
        }
    }

    public int buscarProximoId() throws SQLException {
        String sql = "SELECT NVL(MAX(ID), 0) + 1 AS PROXIMO_ID FROM USUARIO";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet resultado = ps.executeQuery()) {

            if (resultado.next()) {
                return resultado.getInt("PROXIMO_ID");
            }

            return 1;
        }
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE EMAIL = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet resultado = ps.executeQuery()) {
                if (resultado.next()) {
                    return montarUsuario(resultado);
                }
            }
        }

        return null;
    }

    public Usuario buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE CPF = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, cpf);

            try (ResultSet resultado = ps.executeQuery()) {
                if (resultado.next()) {
                    return montarUsuario(resultado);
                }
            }
        }

        return null;
    }

    public List<Usuario> listarTodos() throws SQLException {
        String sql = "SELECT * FROM USUARIO";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet resultado = ps.executeQuery()) {

            while (resultado.next()) {
                usuarios.add(montarUsuario(resultado));
            }
        }

        return usuarios;
    }

    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE USUARIO SET EMAIL = ?, SENHA = ? WHERE ID = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenhaHash());
            ps.setInt(3, usuario.getId());

            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE ID = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

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
