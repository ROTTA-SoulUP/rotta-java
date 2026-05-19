package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;
import br.com.rotta.models.Midia;


// "extends Midia" = PostagemFoto herda tudo de Midia
public class PostagemFoto extends Midia {

    // ── Atributos específicos de foto ──────────────────────
    // Esses não existem em Midia — são exclusivos de PostagemFoto
    private String formato;  // JPG ou PNG
    private int resolucao;   // em pixels, ex: 1080

    // ── Construtor ─────────────────────────────────────────
    public PostagemFoto(int id, String urlArquivo, String descricao, int usuarioId, String formato, int resolucao) {
        // super() chama o construtor de Midia primeiro
        // Inicializa: id, urlArquivo, descricao, usuarioId, dataEnvio, status
        super(id, urlArquivo, descricao, usuarioId);

        // Depois inicializa o que é específico de foto
        this.formato = formato;
        this.resolucao = resolucao;
    }

    // ── Implementação dos métodos abstratos ────────────────
    // O @Override diz: estou implementando o metodo abstrato da mãe
    @Override
    public void enviar() {
        System.out.println("📸 Iniciando envio de foto...");
        System.out.println("Arquivo: " + getUrlArquivo());
        System.out.println("Formato: " + formato + " | Resolução: " + resolucao + "px");
        validarFormato();
        comprimirImagem();
        // setStatus é protected — filha pode usar!
        setStatus(StatusMidia.ENVIADO);
        System.out.println("Foto enviada com sucesso!");
    }

    @Override
    public void cancelar() {
        setStatus(StatusMidia.CANCELADO);
        System.out.println("Envio da foto " + getId() + " cancelado.");
    }

    // ── Métodos específicos de foto ────────────────────────
    public void validarFormato() {
        if (formato.equalsIgnoreCase("JPG") ||
                formato.equalsIgnoreCase("PNG")) {
            System.out.println("Formato " + formato + " válido.");
        } else {
            System.out.println("Formato inválido. Use JPG ou PNG.");
        }
    }

    public void comprimirImagem() {
        System.out.println("🗜️ Comprimindo imagem de " + resolucao +
                "px para otimizar upload...");
    }

    // ── Getters e Setters ──────────────────────────────────
    public String getFormato() { return formato; }
    public int getResolucao() { return resolucao; }
    public void setFormato(String formato) { this.formato = formato; }
    public void setResolucao(int resolucao) { this.resolucao = resolucao; }
}