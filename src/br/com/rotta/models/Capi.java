package br.com.rotta.models;

public class Capi {

    //ATRIBUTO
    private int id;

    //CONSTRUTOR

    public Capi(int id) {
        this.id = id;
    }

    //METODOS - A Capi só orienta o usuário, não guarda nenhum dado dele.
    public String sugerirDica(String funcionalidade) {
        return "Dica da Capi sobre " + funcionalidade + ": explore essa funcionalidade para ganhar mais pontos!";
    }

    public void exibirNaTela() {
        System.out.println("Capi apareceu na tela para ajudar o usuário.");
    }
}
