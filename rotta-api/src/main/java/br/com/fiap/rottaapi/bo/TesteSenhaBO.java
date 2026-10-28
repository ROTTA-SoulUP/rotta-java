package br.com.fiap.rottaapi.bo;

public class TesteSenhaBO {

    public static void main(String[] args) {
        SenhaBO senhaBO = new SenhaBO();

        String senhaOriginal = "MinhaSenha123!";

        String hash = senhaBO.gerarHash(senhaOriginal);

        System.out.println("Hash gerado: " + hash);

        boolean senhaCorreta = senhaBO.verificar(
                "MinhaSenha123!",
                hash
        );

        boolean senhaIncorreta = senhaBO.verificar(
                "SenhaErrada456!",
                hash
        );

        System.out.println("Senha correta: " + senhaCorreta);
        System.out.println("Senha incorreta: " + senhaIncorreta);
    }
}
