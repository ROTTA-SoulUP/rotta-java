package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;

public class PostagemFoto extends Midia {

    private String localizacao;

    public PostagemFoto(int id, String urlArquivo,
                        String descricao, int usuarioId,
                        String localizacao) {

        super(id, urlArquivo, descricao, usuarioId);

        this.localizacao = localizacao;
    }

    @Override
    public void enviar() {

        System.out.println("Iniciando envio de foto...");
        System.out.println("Arquivo: " + getUrlArquivo());

        validarFoto();
        comprimirImagem();

        setStatus(StatusMidia.ENVIADO);

        System.out.println("Foto enviada com sucesso!");
    }

    @Override
    public void cancelar() {
        setStatus(StatusMidia.CANCELADO);
        System.out.println("Envio da foto " + getId() + " cancelado.");
    }

    public void validarFoto() {
        System.out.println("Formato detectado automaticamente pelo sistema.");
    }

    public void comprimirImagem() {
        System.out.println("Imagem otimizada.");
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}