package br.com.rotta.dao;

import br.com.rotta.db.ConexaoBanco;
import br.com.rotta.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo acesso aos dados dos usuários no banco de dados Oracle.
 * Realiza as operações de cadastro, consulta, atualização e exclusão de usuários.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */
public class UsuarioDAO {

    // MÉTODOS

    /**
     * Insere um novo usuário no banco de dados.
     *
     * @param usuario usuário que será cadastrado
     * @throws SQLException caso ocorra algum erro na comunicação com o banco
     */
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO USUARIO (ID, NOME, EMAIL, CPF, SENHA, TELEFONE, ATIVO) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, usuario.getId());
            comando.setString(2, usuario.getNome());
            comando.setString(3, usuario.getEmail());
            comando.setString(4, usuario.getCpf());
            comando.setString(5, usuario.getSenhaHash());
            comando.setString(6, usuario.getTelefone());
            comando.setInt(7, usuario.isAtivo() ? 1 : 0);

            comando.executeUpdate();
        }
    }

    /**
     * Busca o próximo identificador disponível para um novo usuário.
     *
     * @return próximo ID disponível
     * @throws SQLException caso ocorra algum erro na comunicação com o banco
     */
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

    /**
     * Busca um usuário pelo email informado.
     *
     * @param email email utilizado para realizar a busca
     * @return usuário encontrado ou null caso não exista
     * @throws SQLException caso ocorra algum erro na comunicação com o banco
     */
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

    /**
     * Busca um usuário pelo CPF informado.
     *
     * @param cpf CPF utilizado para realizar a busca
     * @return usuário encontrado ou null caso não exista
     * @throws SQLException caso ocorra algum erro na comunicação com o banco
     */
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

    /**
     * Retorna todos os usuários cadastrados no banco.
     *
     * @return lista com todos os usuários cadastrados
     * @throws SQLException caso ocorra algum erro na comunicação com o banco
     */
    public List<Usuario> listarTodos() throws SQLException {
        String sql = "SELECT * FROM USUARIO";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                usuarios.add(montarUsuario(resultado));
            }
        }

        return usuarios;
    }

    /**
     * Atualiza o email e a senha de um usuário existente.
     *
     * @param usuario usuário que terá os dados atualizados
     * @throws SQLException caso ocorra algum erro na comunicação com o banco
     */
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE USUARIO SET EMAIL = ?, SENHA = ? WHERE ID = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, usuario.getEmail());
            comando.setString(2, usuario.getSenhaHash());
            comando.setInt(3, usuario.getId());

            comando.executeUpdate();
        }
    }

    /**
     * Exclui um usuário do banco de dados pelo seu identificador.
     *
     * @param id identificador do usuário que será excluído
     * @throws SQLException caso ocorra algum erro na comunicação com o banco
     */
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE ID = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);
            comando.executeUpdate();
        }
    }

    /**
     * Converte o resultado de uma consulta do banco em um objeto Usuario.
     *
     * @param resultado resultado da consulta contendo os dados do usuário
     * @return objeto Usuario criado com os dados encontrados
     * @throws SQLException caso ocorra algum erro ao ler os dados do resultado
     */
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