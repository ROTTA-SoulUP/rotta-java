package br.com.rotta.models;

public class Capi {

    //ATRIBUTO
    private int id;

    //CONSTRUTOR
    public Capi(int id) {
        this.id = id;
    }

    //MÉTODOS - A Capi só orienta o usuário, não guarda nenhum dado dele.
    public String sugerirDica(String funcionalidade) { //Aqui a Capi aparece para direcionar o usuário com balões explicativos
        String dica;
        switch (funcionalidade) { //Foi utilizado o switch, pois para essa funcionalidade pensamos que seria melhor usar essa estrutura de repetição pela organização
            case "desafio":
                dica = "Escolha um desafio sustentável e envie a comprovação para ganhar pontos!";
                break;
            case "carteira":
                dica = "Acompanhe seu saldo aqui e resgate créditos de transporte quando atingir o valor necessário!";
            case "streak":
                dica = "Mantenha sua sequência diária para eu evoluir junto com você!";
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
