package br.com.fiap.rottaapi.resource;

import br.com.fiap.rottaapi.bo.UsuarioBO;
import br.com.fiap.rottaapi.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    private final UsuarioBO usuarioBO;

    public UsuarioResource(UsuarioBO usuarioBO) {
        this.usuarioBO = usuarioBO;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioBO.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @PathVariable int id
    ) {
        return usuarioBO.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(
            @RequestBody Usuario usuario
    ) {
        usuarioBO.cadastrar(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable int id,
            @RequestBody Usuario usuario
    ) {
        usuario.setId(id);

        if (usuarioBO.atualizar(usuario)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> atualizarSenha(
            @PathVariable int id,
            @RequestBody SenhaRequest dados
    ) {
        if (dados == null) {
            throw new IllegalArgumentException(
                    "A nova senha é obrigatória."
            );
        }

        if (usuarioBO.atualizarSenha(id, dados.senha())) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable int id
    ) {
        if (usuarioBO.excluir(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest dados
    ) {
        if (dados == null) {
            throw new IllegalArgumentException(
                    "E-mail e senha são obrigatórios."
            );
        }

        return usuarioBO.autenticar(dados.email(), dados.senha())
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .build()
                );
    }

    public record LoginRequest(String email, String senha) {}

    public record SenhaRequest(String senha) {}
}
