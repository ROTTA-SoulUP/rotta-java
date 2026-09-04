package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;

public class PostagemFoto extends Midia {

    // ATRIBUTOS
    private String localizacao;

    // CONSTRUTOR
    public PostagemFoto(int id, String urlArquivo, Usuario usuario, ParticipacaoDesafio participacao, String localizacao) {
        super(id, urlArquivo, usuario, participacao);
        this.localizacao = localizacao;
    }

    // MÉTODOS
    @Override
    public void enviar() {
        System.out.println("Enviando foto tirada em: " + localizacao + "...");
        comprimirImagem();
        setStatus(StatusMidia.ENVIADO);
        System.out.println("Foto enviada com sucesso! Aguardando validação da IA.");
    }

    public void validarFoto() {
        System.out.println("Verificando se o arquivo é realmente uma imagem válida...");
    }

    public void comprimirImagem() {
        System.out.println("Comprimindo imagem para economizar dados do usuário...");
    }
}