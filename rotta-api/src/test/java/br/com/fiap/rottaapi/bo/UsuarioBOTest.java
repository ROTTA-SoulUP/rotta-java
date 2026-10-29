package br.com.fiap.rottaapi.bo;

import br.com.fiap.rottaapi.dao.UsuarioDAO;
import br.com.fiap.rottaapi.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioBOTest {

    @Mock
    private UsuarioDAO usuarioDAO;

    @Mock
    private SenhaBO senhaBO;

    @InjectMocks
    private UsuarioBO usuarioBO;

    private Usuario usuario;

    @BeforeEach
    void configurarUsuario() {
        usuario = new Usuario();
        usuario.setNome("Maria Silva");
        usuario.setEmail("maria@email.com");
        usuario.setCpf("52998224725");
        usuario.setSenhaHash("Senha123");
    }

    @Test
    void deveCadastrarUsuarioComDadosValidos() {
        when(senhaBO.gerarHash("Senha123"))
                .thenReturn("hash-da-senha");

        usuarioBO.cadastrar(usuario);

        assertEquals("hash-da-senha", usuario.getSenhaHash());
        assertNotNull(usuario.getDataCadastro());

        verify(usuarioDAO).cadastrar(usuario);
        verify(senhaBO).gerarHash("Senha123");
    }

    @Test
    void deveRejeitarUsuarioNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.cadastrar(null)
        );

        verifyNoInteractions(usuarioDAO, senhaBO);
    }

    @Test
    void deveRejeitarEmailInvalido() {
        usuario.setEmail("email-invalido");

        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.cadastrar(usuario)
        );

        verifyNoInteractions(usuarioDAO, senhaBO);
    }

    @Test
    void deveRejeitarCpfInvalido() {
        usuario.setCpf("11111111111");

        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.cadastrar(usuario)
        );

        verifyNoInteractions(usuarioDAO, senhaBO);
    }

    @Test
    void deveRejeitarSenhaVazia() {
        usuario.setSenhaHash(" ");

        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.cadastrar(usuario)
        );

        verifyNoInteractions(usuarioDAO, senhaBO);
    }

    @Test
    void deveRejeitarIdInvalidoNaBusca() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.buscarPorId(0)
        );

        verifyNoInteractions(usuarioDAO);
    }

    @Test
    void deveRetornarVazioQuandoUsuarioNaoExisteNoLogin() {
        when(usuarioDAO.buscarPorEmail("maria@email.com"))
                .thenReturn(Optional.empty());

        Optional<Usuario> resultado =
                usuarioBO.autenticar("maria@email.com", "Senha123");

        assertTrue(resultado.isEmpty());
        verify(usuarioDAO).buscarPorEmail("maria@email.com");
        verifyNoInteractions(senhaBO);
    }

    @Test
    void deveAutenticarUsuarioComSenhaCorreta() {
        when(usuarioDAO.buscarPorEmail("maria@email.com"))
                .thenReturn(Optional.of(usuario));
        when(senhaBO.verificar("Senha123", "Senha123"))
                .thenReturn(true);

        Optional<Usuario> resultado =
                usuarioBO.autenticar("maria@email.com", "Senha123");

        assertTrue(resultado.isPresent());
        assertEquals("Maria Silva", resultado.get().getNome());

        verify(usuarioDAO).buscarPorEmail("maria@email.com");
        verify(senhaBO).verificar("Senha123", "Senha123");
    }

    @Test
    void deveRetornarVazioQuandoSenhaEstiverIncorreta() {
        when(usuarioDAO.buscarPorEmail("maria@email.com"))
                .thenReturn(Optional.of(usuario));
        when(senhaBO.verificar("SenhaErrada", "Senha123"))
                .thenReturn(false);

        Optional<Usuario> resultado =
                usuarioBO.autenticar("maria@email.com", "SenhaErrada");

        assertTrue(resultado.isEmpty());
        verify(senhaBO).verificar("SenhaErrada", "Senha123");
    }

    @Test
    void deveRejeitarEmailVazioNoLogin() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.autenticar(" ", "Senha123")
        );

        verifyNoInteractions(usuarioDAO, senhaBO);
    }

    @Test
    void deveRejeitarSenhaVaziaNoLogin() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.autenticar("maria@email.com", " ")
        );

        verifyNoInteractions(usuarioDAO, senhaBO);
    }

    @Test
    void deveAtualizarUsuarioSemAlterarSenha() {
        usuario.setId(1);
        usuario.setSenhaHash(" ");

        when(usuarioDAO.atualizarSemSenha(usuario)).thenReturn(1);

        boolean resultado = usuarioBO.atualizar(usuario);

        assertTrue(resultado);
        verify(usuarioDAO).atualizarSemSenha(usuario);
        verifyNoInteractions(senhaBO);
    }

    @Test
    void deveAtualizarUsuarioComNovaSenha() {
        usuario.setId(1);
        usuario.setSenhaHash("NovaSenha123");

        when(senhaBO.gerarHash("NovaSenha123"))
                .thenReturn("hash-novo");
        when(usuarioDAO.atualizar(usuario)).thenReturn(1);

        boolean resultado = usuarioBO.atualizar(usuario);

        assertTrue(resultado);
        assertEquals("hash-novo", usuario.getSenhaHash());
        verify(usuarioDAO).atualizar(usuario);
    }

    @Test
    void deveRetornarFalsoQuandoUsuarioNaoForAtualizado() {
        usuario.setId(1);
        usuario.setSenhaHash("");

        when(usuarioDAO.atualizarSemSenha(usuario)).thenReturn(0);

        boolean resultado = usuarioBO.atualizar(usuario);

        assertFalse(resultado);
        verify(usuarioDAO).atualizarSemSenha(usuario);
    }

    @Test
    void deveRejeitarIdInvalidoNaAtualizacao() {
        usuario.setId(0);

        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.atualizar(usuario)
        );

        verifyNoInteractions(usuarioDAO, senhaBO);
    }

    @Test
    void deveAtualizarSenhaComSucesso() {
        when(senhaBO.gerarHash("NovaSenha123"))
                .thenReturn("hash-novo");
        when(usuarioDAO.atualizarSenha(1, "hash-novo"))
                .thenReturn(1);

        boolean resultado =
                usuarioBO.atualizarSenha(1, "NovaSenha123");

        assertTrue(resultado);
        verify(senhaBO).gerarHash("NovaSenha123");
        verify(usuarioDAO).atualizarSenha(1, "hash-novo");
    }

    @Test
    void deveRejeitarSenhaVaziaNaAtualizacaoDeSenha() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.atualizarSenha(1, " ")
        );

        verifyNoInteractions(usuarioDAO, senhaBO);
    }

    @Test
    void deveRejeitarIdInvalidoNaAtualizacaoDeSenha() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.atualizarSenha(0, "NovaSenha123")
        );

        verifyNoInteractions(usuarioDAO, senhaBO);
    }

    @Test
    void deveExcluirUsuarioComSucesso() {
        when(usuarioDAO.excluir(1)).thenReturn(1);

        boolean resultado = usuarioBO.excluir(1);

        assertTrue(resultado);
        verify(usuarioDAO).excluir(1);
    }

    @Test
    void deveRetornarFalsoQuandoUsuarioNaoForExcluido() {
        when(usuarioDAO.excluir(1)).thenReturn(0);

        boolean resultado = usuarioBO.excluir(1);

        assertFalse(resultado);
        verify(usuarioDAO).excluir(1);
    }

    @Test
    void deveRejeitarIdInvalidoNaExclusao() {
        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioBO.excluir(0)
        );

        verifyNoInteractions(usuarioDAO, senhaBO);
    }

    @Test
    void deveListarTodosOsUsuarios() {
        when(usuarioDAO.listarTodos()).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioBO.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Maria Silva", resultado.get(0).getNome());
        verify(usuarioDAO).listarTodos();
    }

    @Test
    void deveBuscarUsuarioPorIdValido() {
        usuario.setId(1);
        when(usuarioDAO.buscarPorId(1)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioBO.buscarPorId(1);

        assertTrue(resultado.isPresent());
        assertEquals("Maria Silva", resultado.get().getNome());
        verify(usuarioDAO).buscarPorId(1);
    }

    @Test
    void deveRetornarFalsoQuandoSenhaNaoForAtualizada() {
        when(senhaBO.gerarHash("NovaSenha123")).thenReturn("hash-novo");
        when(usuarioDAO.atualizarSenha(1, "hash-novo")).thenReturn(0);

        boolean resultado = usuarioBO.atualizarSenha(1, "NovaSenha123");

        assertFalse(resultado);
        verify(usuarioDAO).atualizarSenha(1, "hash-novo");
    }
}
