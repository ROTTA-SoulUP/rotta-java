package br.com.rotta.models;

public class Capi {

    //ATRIBUTO
    private int id;

    //CONSTRUTOR
    public Capi(int id) {
        this.id = id;
    }

    //MÉTODOS - A Capi só orienta o usuário, não guarda nenhum dado dele.
    public String sugerirDica(String funcionalidade) {
        String dica;
        switch (funcionalidade) {
            case "desafio":
                dica = "Escolha um desafio sustentavel e envie a comprovacao para ganhar pontos!";
                break;
            case "carteira":
                dica = "Acompanhe seu saldo aqui e resgate creditos de transporte quando atingir o valor necessario!";
                break;
            case "streak":
                dica = "Mantenha sua sequencia diaria para eu evoluir junto com voce!";
                break;
            default:
                dica = "Estou aqui para te ajudar, mande uma mensagem se precisar, ta bom?";
        }
        System.out.println("Capi diz: " + dica);
        return dica;
    }

    public void exibirNaTela() {
        System.out.println("Capi apareceu na tela para ajudar o usuário.");
    }
}
