package br.com.fiap.rottaapi.dao;

import br.com.fiap.rottaapi.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioDAOTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UsuarioDAO usuarioDAO;

    @Test
    void deveListarTodosOsUsuarios() {
        when(jdbcTemplate.query(
                anyString(),
                any(org.springframework.jdbc.core.RowMapper.class)
        )).thenReturn(List.of(new Usuario()));

        List<Usuario> resultado = usuarioDAO.listarTodos();

        assertEquals(1, resultado.size());
        verify(jdbcTemplate).query(
                contains("ORDER BY ID"),
                any(org.springframework.jdbc.core.RowMapper.class)
        );
    }

    @Test
    void deveRetornarUsuarioQuandoBuscarPorId() {
        when(jdbcTemplate.query(
                anyString(),
                any(org.springframework.jdbc.core.RowMapper.class),
                eq(1)
        )).thenReturn(List.of(new Usuario()));

        Optional<Usuario> resultado = usuarioDAO.buscarPorId(1);

        assertTrue(resultado.isPresent());
        verify(jdbcTemplate).query(
                contains("WHERE ID = ?"),
                any(org.springframework.jdbc.core.RowMapper.class),
                eq(1)
        );
    }

    @Test
    void deveRetornarVazioQuandoUsuarioNaoExiste() {
        when(jdbcTemplate.query(
                anyString(),
                any(org.springframework.jdbc.core.RowMapper.class),
                eq(999)
        )).thenReturn(List.of());

        Optional<Usuario> resultado = usuarioDAO.buscarPorId(999);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveCadastrarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria Silva");
        usuario.setEmail("maria@email.com");
        usuario.setCpf("52998224725");
        usuario.setSenhaHash("hash");
        usuario.setTelefone("11999999999");

        when(jdbcTemplate.update(
                anyString(), any(), any(), any(), any(), any()
        )).thenReturn(1);

        int resultado = usuarioDAO.cadastrar(usuario);

        assertEquals(1, resultado);
        verify(jdbcTemplate).update(
                contains("SEQ_USUARIO.NEXTVAL"),
                eq("Maria Silva"),
                eq("maria@email.com"),
                eq("52998224725"),
                eq("hash"),
                eq("11999999999")
        );
    }

    @Test
    void deveAtualizarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Maria Silva");
        usuario.setEmail("maria@email.com");
        usuario.setCpf("52998224725");
        usuario.setSenhaHash("hash-novo");
        usuario.setTelefone("11999999999");

        when(jdbcTemplate.update(
                anyString(), any(), any(), any(), any(), any(), any()
        )).thenReturn(1);

        int resultado = usuarioDAO.atualizar(usuario);

        assertEquals(1, resultado);
        verify(jdbcTemplate).update(
                contains("SET NOME = ?, EMAIL = ?, CPF = ?, SENHA = ?, TELEFONE = ?"),
                eq("Maria Silva"),
                eq("maria@email.com"),
                eq("52998224725"),
                eq("hash-novo"),
                eq("11999999999"),
                eq(1)
        );
    }

    @Test
    void deveAtualizarUsuarioSemAlterarSenha() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Maria Silva");
        usuario.setEmail("maria@email.com");
        usuario.setCpf("52998224725");
        usuario.setTelefone("11999999999");

        when(jdbcTemplate.update(
                anyString(), any(), any(), any(), any(), any()
        )).thenReturn(1);

        int resultado = usuarioDAO.atualizarSemSenha(usuario);

        assertEquals(1, resultado);
        verify(jdbcTemplate).update(
                contains("SET NOME = ?, EMAIL = ?, CPF = ?, TELEFONE = ?"),
                eq("Maria Silva"),
                eq("maria@email.com"),
                eq("52998224725"),
                eq("11999999999"),
                eq(1)
        );
    }

    @Test
    void deveAtualizarSenha() {
        when(jdbcTemplate.update(
                anyString(), eq("hash-novo"), eq(1)
        )).thenReturn(1);

        int resultado = usuarioDAO.atualizarSenha(1, "hash-novo");

        assertEquals(1, resultado);
        verify(jdbcTemplate).update(
                contains("SET SENHA = ?"),
                eq("hash-novo"),
                eq(1)
        );
    }

    @Test
    void deveExcluirUsuario() {
        when(jdbcTemplate.update(
                anyString(), eq(1)
        )).thenReturn(1);

        int resultado = usuarioDAO.excluir(1);

        assertEquals(1, resultado);
        verify(jdbcTemplate).update(
                eq("DELETE FROM USUARIO WHERE ID = ?"),
                eq(1)
        );
    }

    @Test
    void deveBuscarUsuarioPorEmail() {
        when(jdbcTemplate.query(
                anyString(),
                any(org.springframework.jdbc.core.RowMapper.class),
                eq("maria@email.com")
        )).thenReturn(List.of(new Usuario()));

        Optional<Usuario> resultado =
                usuarioDAO.buscarPorEmail("maria@email.com");

        assertTrue(resultado.isPresent());
        verify(jdbcTemplate).query(
                contains("WHERE LOWER(EMAIL) = LOWER(?)"),
                any(org.springframework.jdbc.core.RowMapper.class),
                eq("maria@email.com")
        );
    }

    @Test
    void deveRetornarVazioQuandoEmailNaoExiste() {
        when(jdbcTemplate.query(
                anyString(),
                any(org.springframework.jdbc.core.RowMapper.class),
                eq("inexistente@email.com")
        )).thenReturn(List.of());

        Optional<Usuario> resultado =
                usuarioDAO.buscarPorEmail("inexistente@email.com");

        assertTrue(resultado.isEmpty());
    }
}
