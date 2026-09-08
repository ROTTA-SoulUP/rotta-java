package br.com.rotta.models;

public class PostagemVideo extends Midia {
    private int duracaoSegundos;
    private String qualidade;

    public PostagemVideo(int id, String nomeArquivo, Usuario usuario, ParticipacaoDesafio participacao, int duracaoSegundos, String qualidade) {
        super(id, nomeArquivo, usuario, participacao);
        this.duracaoSegundos = duracaoSegundos;
        this.qualidade = qualidade;
    }

    @Override
    public void enviar() {
        super.enviar();
        System.out.println("Vídeo enviado para validação.");
    }

    public boolean validarDuracao() {
        if (duracaoSegundos > 0 && duracaoSegundos <= 30) {
            System.out.println("Duração do vídeo válida.");
            return true;
        } else {
            System.out.println("Duração do vídeo inválida.");
            return false;
        }
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public String getQualidade() {
        return qualidade;
    }
}
