package br.com.fiap.rottaapi;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void deveRetornarBadRequestParaErroDeValidacao() {
        IllegalArgumentException erro =
                new IllegalArgumentException("E-mail inválido.");

        ResponseEntity<?> resposta =
                handler.tratarErroDeValidacao(erro);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertEquals("E-mail inválido.", ((java.util.Map<?, ?>) resposta.getBody()).get("erro"));
    }

    @Test
    void deveRetornarConflictParaDadosDuplicados() {
        DuplicateKeyException erro =
                new DuplicateKeyException("Chave duplicada.");

        ResponseEntity<?> resposta =
                handler.tratarDuplicidade(erro);

        assertEquals(HttpStatus.CONFLICT, resposta.getStatusCode());
        assertEquals(
                "Já existe um usuário com esses dados.",
                ((java.util.Map<?, ?>) resposta.getBody()).get("erro")
        );
    }
}