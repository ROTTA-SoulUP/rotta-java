package br.com.rotta.models;

public class PostagemFoto extends Midia {
    private String localizacao;

    public PostagemFoto(int id, String nomeArquivo, Usuario usuario,
                        ParticipacaoDesafio participacao, String localizacao) {
        super(id, nomeArquivo, usuario, participacao);
        this.localizacao = localizacao;
    }

    @Override
    public void enviar() {
        super.enviar();
        System.out.println("Foto enviada para validação.");
    }

    public boolean validarFoto() {
        System.out.println("Foto validada.");
        return true;
    }

    public void comprimirImagem() {
        System.out.println("Imagem comprimida para o envio.");
    }

    public String getLocalizacao() {
        return localizacao;
    }
}