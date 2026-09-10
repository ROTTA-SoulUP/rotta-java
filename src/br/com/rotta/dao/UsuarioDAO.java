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
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

// Classe DAO (Data Access Object) responsável por toda a comunicação entre
// o objeto Usuario e a tabela USUARIO no banco de dados. Implementa o CRUD
// completo: Create (inserir), Read (buscarPorEmail, buscarPorCpf, listarTodos),
// Update (atualizar) e Delete (deletar).
public class UsuarioDAO {

    // CREATE
    // Insere um novo usuário na tabela USUARIO, usando os dados do objeto recebido.
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

    // Busca o próximo ID disponível no banco, somando 1 ao maior ID já cadastrado.
    // Usado no cadastro pra gerar o ID do novo usuário antes de inserir.
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
    // Procura um usuário pelo email e retorna o objeto montado, ou null se não encontrar.
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
    // Procura um usuário pelo CPF e retorna o objeto montado, ou null se não encontrar.
    // Usado tanto no cadastro (verificar duplicidade) quanto no login.
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
    // Retorna todos os usuários cadastrados na tabela USUARIO.
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
    // Atualiza email, senha, telefone e status ativo do usuário, localizando pelo ID.
    // Usado tanto na atualização de dados normal quanto na desativação de conta.
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
    // Remove definitivamente o usuário da tabela USUARIO, localizando pelo ID.
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

    // Converte o resultado do banco (ResultSet) em um objeto Usuario.
    // Método auxiliar usado internamente por buscarPorEmail, buscarPorCpf e listarTodos,
    // pra não repetir esse código de montagem em cada método de busca.
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