package br.com.rotta.models;

/**
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class PostagemVideo extends Midia {

    // ATRIBUTOS
    private int duracaoSegundos;
    private String qualidade;

    // CONSTRUTOR
    public PostagemVideo(int id, String nomeArquivo, Usuario usuario, ParticipacaoDesafio participacao, int duracaoSegundos, String qualidade) {
        super(id, nomeArquivo, usuario, participacao);
        this.duracaoSegundos = duracaoSegundos;
        this.qualidade = qualidade;
    }

    // MÉTODOS
    // Reaproveita o envio da classe mãe e complementa com uma mensagem específica de vídeo.
    @Override
    public void enviar() {
        super.enviar();
        System.out.println("Vídeo enviado para validação.");
    }

    // Verifica se a duração do vídeo está dentro do limite permitido pelo desafio.
    public boolean validarDuracao() {
        if (duracaoSegundos > 0 && duracaoSegundos <= 30) {
            System.out.println("Duração do vídeo válida.");
            return true;
        } else {
            System.out.println("Duração do vídeo inválida.");
            return false;
        }
    }

    // GETTERS
    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public String getQualidade() {
        return qualidade;
    }
}