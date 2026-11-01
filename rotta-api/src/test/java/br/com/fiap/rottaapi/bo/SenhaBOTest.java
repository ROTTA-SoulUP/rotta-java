package br.com.fiap.rottaapi.bo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SenhaBOTest {

    private final SenhaBO senhaBO = new SenhaBO();

    @Test
    void deveGerarHashDaSenha() {
        String senha = "Teste@123";

        String hash = senhaBO.gerarHash(senha);

        assertNotNull(hash);
        assertNotEquals(senha, hash);
        assertTrue(hash.startsWith("$2"));
    }

    @Test
    void deveVerificarSenhaCorreta() {
        String senha = "Teste@123";
        String hash = senhaBO.gerarHash(senha);

        assertTrue(senhaBO.verificar(senha, hash));
    }

    @Test
    void naoDeveVerificarSenhaIncorreta() {
        String hash = senhaBO.gerarHash("Teste@123");

        assertFalse(senhaBO.verificar("SenhaErrada@123", hash));
    }

    @Test
    void deveRetornarFalseQuandoSenhaForNula() {
        String hash = senhaBO.gerarHash("Teste@123");

        assertFalse(senhaBO.verificar(null, hash));
    }

    @Test
    void deveRetornarFalseQuandoHashForNulo() {
        assertFalse(senhaBO.verificar("Teste@123", null));
    }

    @Test
    void deveRetornarFalseQuandoHashForInvalido() {
        assertFalse(senhaBO.verificar("Teste@123", "hash-invalido"));
    }
}