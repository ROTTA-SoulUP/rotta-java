package br.com.fiap.rottaapi.bo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SenhaBO {

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    public String gerarHash(String senha) {
        return encoder.encode(senha);
    }

    public boolean verificar(String senha, String hash) {
        if (senha == null || hash == null || hash.isBlank()) {
            return false;
        }

        if (hash.startsWith("$2a$")
                || hash.startsWith("$2b$")
                || hash.startsWith("$2y$")) {
            return encoder.matches(senha, hash);
        }

        // Compatibilidade temporária com senhas antigas em texto puro.
        return senha.equals(hash);
    }
}