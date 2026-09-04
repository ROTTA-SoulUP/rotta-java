package br.com.rotta.models;
import java.util.ArrayList;
import java.util.List;

public class ArmazenamentoOffline {

    //ATRIBUTO | LISTA
    private List<Midia> midiasPendentes = new ArrayList<>();

    //MÉTODOS
    public void salvarTemporariamente(Midia midia) {
        midiasPendentes.add(midia);
        System.out.println("Sem internet no momento! Sua mídia foi guardada com segurança no aparelho.");
        System.out.println("Ela sera enviada automaticamente assim que a conexão voltar.");
    }

    public void sincronizar() {
        if (midiasPendentes.isEmpty()) {
            System.out.println("Nenhuma mídia pendente para sincronizar.");
            return;
        }
        System.out.println("Conexão restabelecida! Sincronizando " + midiasPendentes.size() + " mídia(s) pendente(s)...");
        for (Midia midia : midiasPendentes) {
            midia.sincronizarMidia();
        }
        midiasPendentes.clear();
    }

    public void removerAposEnvio(Midia midia) {
        midiasPendentes.remove(midia);
    }
}
