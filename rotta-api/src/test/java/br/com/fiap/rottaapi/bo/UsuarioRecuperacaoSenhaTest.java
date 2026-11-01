package br.com.fiap.rottaapi.bo;

import br.com.fiap.rottaapi.dao.UsuarioDAO;
import br.com.fiap.rottaapi.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioRecuperacaoSenhaTest {

    @Mock
    private UsuarioDAO usuarioDAO;

    private SenhaBO senhaBO;
    private UsuarioBO usuarioBO;
    private Usuario usuario;

    @BeforeEach
    void configurar() {
        senhaBO = new SenhaBO();
        usuarioBO = new UsuarioBO(usuarioDAO, senhaBO);

        usuario = new Usuario();
        usuario.setId(42);
        usuario.setNome("Maria Silva");
        usuario.setEmail("maria@email.com");
        usuario.setCpf("52998224725");
        usuario.setSenhaHash(senhaBO.gerarHash("SenhaAntiga123"));
    }

    @Test
    void deveConfirmarEmailExistente() {
        when(usuarioDAO.buscarPorEmail("maria@email.com"))
                .thenReturn(Optional.of(usuario));

        assertTrue(
                usuarioBO.verificarEmailRecuperacao("maria@email.com")
        );

        verify(usuarioDAO).buscarPorEmail("maria@email.com");
    }

    @Test
    void deveRetornarFalsoParaEmailInexistente() {
        when(usuarioDAO.buscarPorEmail("inexistente@email.com"))
                .thenReturn(Optional.empty());

        assertFalse(
                usuarioBO.verificarEmailRecuperacao(
                        "inexistente@email.com"
                )
        );
    }

    @Test
    void deveRedefinirSenhaEPermitirLoginComNovaSenha() {
        when(usuarioDAO.buscarPorEmail("maria@email.com"))
                .thenReturn(Optional.of(usuario));

        when(usuarioDAO.atualizarSenha(eq(42), anyString()))
                .thenAnswer(invocacao -> {
                    String novoHash = invocacao.getArgument(1);
                    usuario.setSenhaHash(novoHash);
                    return 1;
                });

        boolean resultado = usuarioBO.redefinirSenha(
                "maria@email.com",
                "NovaSenha123"
        );

        assertTrue(resultado);

        assertNotEquals("NovaSenha123", usuario.getSenhaHash());

        assertTrue(
                senhaBO.verificar("NovaSenha123", usuario.getSenhaHash())
        );

        assertTrue(
                usuarioBO.autenticar(
                        "maria@email.com",
                        "NovaSenha123"
                ).isPresent()
        );

        assertTrue(
                usuarioBO.autenticar(
                        "maria@email.com",
                        "SenhaAntiga123"
                ).isEmpty()
        );

        verify(usuarioDAO).atualizarSenha(eq(42), anyString());
    }

    @Test
    void deveRetornarFalsoAoRedefinirSenhaDeEmailInexistente() {
        when(usuarioDAO.buscarPorEmail("inexistente@email.com"))
                .thenReturn(Optional.empty());

        assertFalse(
                usuarioBO.redefinirSenha(
                        "inexistente@email.com",
                        "NovaSenha123"
                )
        );

        verify(usuarioDAO, never()).atualizarSenha(
                anyInt(),
                anyString()
        );
    }
}
