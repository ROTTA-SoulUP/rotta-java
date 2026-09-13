package br.com.rotta.models;

/**
 * Classe que representa um vídeo enviado como comprovação de uma ação sustentável.
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
    /**
     * Cria uma postagem de vídeo com duração e qualidade informadas.
     *
     * @param id identificador da mídia
     * @param nomeArquivo nome do arquivo do vídeo
     * @param usuario usuário que enviou o vídeo
     * @param participacao participação no desafio relacionada ao vídeo
     * @param duracaoSegundos duração do vídeo em segundos
     * @param qualidade qualidade informada para o vídeo
     */
    public PostagemVideo(int id, String nomeArquivo, Usuario usuario, ParticipacaoDesafio participacao, int duracaoSegundos, String qualidade) {
        super(id, nomeArquivo, usuario, participacao);
        this.duracaoSegundos = duracaoSegundos;
        this.qualidade = qualidade;
    }

    // MÉTODOS
    /**
     * Envia o vídeo para validação, reaproveitando o comportamento da classe Midia.
     */
    @Override
    public void enviar() {
        super.enviar();
        System.out.println("Vídeo enviado para validação.");
    }

    /**
     * Verifica se a duração do vídeo está dentro do limite de até 30 segundos.
     *
     * @return true se a duração for válida; caso contrário, false
     */
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