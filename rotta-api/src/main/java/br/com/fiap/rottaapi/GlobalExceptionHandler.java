package br.com.fiap.rottaapi;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> tratarErroDeValidacao(
            IllegalArgumentException erro) {

        Map<String, String> resposta = Map.of(
                "erro", erro.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resposta);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String, String>> tratarDuplicidade(
            DuplicateKeyException erro) {

        Map<String, String> resposta = Map.of(
                "erro", "Já existe um usuário com esses dados."
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(resposta);
    }
}
