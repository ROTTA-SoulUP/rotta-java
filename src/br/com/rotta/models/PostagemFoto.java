package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;

public class PostagemFoto extends Midia {

    // ATRIBUTOS
    private String localizacao;

    // CONSTRUTOR
    public PostagemFoto(int id, String urlArquivo,
                        String descricao, int usuarioId,
                        String localizacao) {

        super(id, urlArquivo, descricao, usuarioId);

        this.localizacao = localizacao;
    }

    // METODOS
    @Override
    public void enviar() {

        System.out.println("Iniciando envio de foto...");
        System.out.println("Arquivo: " + getUrlArquivo());

        validarFoto();
        comprimirImagem();

        setStatus(StatusMidia.ENVIADO);

        System.out.println("Foto enviada com sucesso!");
    }

    public void validarFoto() {
        System.out.println("Formato detectado automaticamente pelo sistema.");
    }

    public void comprimirImagem() {
        System.out.println("Imagem otimizada.");
    }

    // GETTERS E SETTERS
    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}