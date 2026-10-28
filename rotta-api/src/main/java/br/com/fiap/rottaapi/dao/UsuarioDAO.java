package br.com.fiap.rottaapi.dao;

import br.com.fiap.rottaapi.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDAO {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeia os dados públicos do usuário, sem consultar a senha.
    private final RowMapper<Usuario> rowMapperPublico = (rs, rowNum) -> {
        Usuario usuario = new Usuario();

        usuario.setId(rs.getInt("ID"));
        usuario.setNome(rs.getString("NOME"));
        usuario.setEmail(rs.getString("EMAIL"));
        usuario.setCpf(rs.getString("CPF"));
        usuario.setTelefone(rs.getString("TELEFONE"));

        Timestamp dataCadastro = rs.getTimestamp("DATA_CADASTRO");
        if (dataCadastro != null) {
            usuario.setDataCadastro(dataCadastro.toLocalDateTime());
        }

        return usuario;
    };

    // Mapeia todos os dados, incluindo o hash da senha.
    // Usado internamente para autenticação.
    private final RowMapper<Usuario> rowMapperCompleto = (rs, rowNum) -> {
        Usuario usuario = new Usuario();

        usuario.setId(rs.getInt("ID"));
        usuario.setNome(rs.getString("NOME"));
        usuario.setEmail(rs.getString("EMAIL"));
        usuario.setCpf(rs.getString("CPF"));
        usuario.setSenhaHash(rs.getString("SENHA"));
        usuario.setTelefone(rs.getString("TELEFONE"));

        Timestamp dataCadastro = rs.getTimestamp("DATA_CADASTRO");
        if (dataCadastro != null) {
            usuario.setDataCadastro(dataCadastro.toLocalDateTime());
        }

        return usuario;
    };

    public List<Usuario> listarTodos() {
        String sql = """
                SELECT ID, NOME, EMAIL, CPF, TELEFONE, DATA_CADASTRO
                FROM USUARIO
                ORDER BY ID
                """;

        return jdbcTemplate.query(sql, rowMapperPublico);
    }

    public Optional<Usuario> buscarPorId(int id) {
        String sql = """
                SELECT ID, NOME, EMAIL, CPF, TELEFONE, DATA_CADASTRO
                FROM USUARIO
                WHERE ID = ?
                """;

        return jdbcTemplate.query(sql, rowMapperPublico, id)
                .stream()
                .findFirst();
    }

    public int cadastrar(Usuario usuario) {
        String sql = """
                INSERT INTO USUARIO
                    (ID, NOME, EMAIL, CPF, SENHA, TELEFONE)
                VALUES
                    (SEQ_USUARIO.NEXTVAL, ?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getSenhaHash(),
                usuario.getTelefone()
        );
    }

    public int atualizar(Usuario usuario) {
        String sql = """
                UPDATE USUARIO
                SET NOME = ?, EMAIL = ?, CPF = ?, SENHA = ?, TELEFONE = ?
                WHERE ID = ?
                """;

        return jdbcTemplate.update(
                sql,
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getSenhaHash(),
                usuario.getTelefone(),
                usuario.getId()
        );
    }

    public int atualizarSemSenha(Usuario usuario) {
        String sql = """
                UPDATE USUARIO
                SET NOME = ?, EMAIL = ?, CPF = ?, TELEFONE = ?
                WHERE ID = ?
                """;

        return jdbcTemplate.update(
                sql,
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getId()
        );
    }

    public int atualizarSenha(int id, String senhaHash) {
        String sql = """
                UPDATE USUARIO
                SET SENHA = ?
                WHERE ID = ?
                """;

        return jdbcTemplate.update(sql, senhaHash, id);
    }

    public int excluir(int id) {
        String sql = "DELETE FROM USUARIO WHERE ID = ?";

        return jdbcTemplate.update(sql, id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        String sql = """
                SELECT ID, NOME, EMAIL, CPF, SENHA, TELEFONE, DATA_CADASTRO
                FROM USUARIO
                WHERE LOWER(EMAIL) = LOWER(?)
                """;

        return jdbcTemplate.query(sql, rowMapperCompleto, email)
                .stream()
                .findFirst();
    }
}
