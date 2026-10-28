package br.com.fiap.rottaapi.bo;

import br.com.fiap.rottaapi.dao.UsuarioDAO;
import br.com.fiap.rottaapi.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioBO {

    private final UsuarioDAO usuarioDAO;
    private final SenhaBO senhaBO;

    public UsuarioBO(UsuarioDAO usuarioDAO, SenhaBO senhaBO) {
        this.usuarioDAO = usuarioDAO;
        this.senhaBO = senhaBO;
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.listarTodos();
    }

    public Optional<Usuario> buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "ID deve ser maior que zero."
            );
        }

        return usuarioDAO.buscarPorId(id);
    }

    public void cadastrar(Usuario usuario) {
        validarUsuario(usuario);

        if (usuario.getSenhaHash() == null
                || usuario.getSenhaHash().isBlank()) {
            throw new IllegalArgumentException(
                    "Senha é obrigatória."
            );
        }

        usuario.setSenhaHash(
                senhaBO.gerarHash(usuario.getSenhaHash())
        );

        if (usuario.getDataCadastro() == null) {
            usuario.setDataCadastro(LocalDateTime.now());
        }

        usuarioDAO.cadastrar(usuario);
    }

    public boolean atualizar(Usuario usuario) {
        validarUsuario(usuario);

        if (usuario.getId() <= 0) {
            throw new IllegalArgumentException(
                    "ID deve ser maior que zero."
            );
        }

        if (usuario.getSenhaHash() == null
                || usuario.getSenhaHash().isBlank()) {
            return usuarioDAO.atualizarSemSenha(usuario) > 0;
        }

        usuario.setSenhaHash(
                senhaBO.gerarHash(usuario.getSenhaHash())
        );

        return usuarioDAO.atualizar(usuario) > 0;
    }

    public boolean atualizarSenha(int id, String novaSenha) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "ID deve ser maior que zero."
            );
        }

        if (novaSenha == null || novaSenha.isBlank()) {
            throw new IllegalArgumentException(
                    "A nova senha é obrigatória."
            );
        }

        String senhaHash = senhaBO.gerarHash(novaSenha);

        return usuarioDAO.atualizarSenha(id, senhaHash) > 0;
    }

    public boolean excluir(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "ID deve ser maior que zero."
            );
        }

        return usuarioDAO.excluir(id) > 0;
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException(
                    "Usuário não pode ser nulo."
            );
        }

        if (usuario.getNome() == null
                || usuario.getNome().isBlank()) {
            throw new IllegalArgumentException(
                    "Nome é obrigatório."
            );
        }

        if (usuario.getEmail() == null
                || usuario.getEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "E-mail é obrigatório."
            );
        }

        String email = usuario.getEmail().trim();

        if (!email.matches(
                "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        )) {
            throw new IllegalArgumentException(
                    "E-mail inválido."
            );
        }

        if (usuario.getCpf() == null
                || usuario.getCpf().isBlank()) {
            throw new IllegalArgumentException(
                    "CPF é obrigatório."
            );
        }

        String cpf = usuario.getCpf().replaceAll("\\D", "");

        if (!validarCPF(cpf)) {
            throw new IllegalArgumentException(
                    "CPF inválido."
            );
        }
    }

    private boolean validarCPF(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;

        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }

        int primeiroDigito = (soma * 10) % 11;

        if (primeiroDigito == 10) {
            primeiroDigito = 0;
        }

        if (primeiroDigito != cpf.charAt(9) - '0') {
            return false;
        }

        soma = 0;

        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }

        int segundoDigito = (soma * 10) % 11;

        if (segundoDigito == 10) {
            segundoDigito = 0;
        }

        return segundoDigito == cpf.charAt(10) - '0';
    }

    public Optional<Usuario> autenticar(String email, String senha) {
        if (email == null || email.isBlank()
                || senha == null || senha.isBlank()) {
            throw new IllegalArgumentException(
                    "E-mail e senha são obrigatórios."
            );
        }

        Optional<Usuario> usuarioEncontrado =
                usuarioDAO.buscarPorEmail(email.trim());

        if (usuarioEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Usuario usuario = usuarioEncontrado.get();

        if (!senhaBO.verificar(senha, usuario.getSenhaHash())) {
            return Optional.empty();
        }

        return usuarioEncontrado;
    }
}
