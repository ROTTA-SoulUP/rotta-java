package br.com.rotta.models;

import java.util.ArrayList;
import java.util.List;

public class ArmazenamentoMidia {
    private List<Midia> midiasArmazenadas = new ArrayList<>();

    public String salvar(Midia midia) {
        if (!midiasArmazenadas.contains(midia)) {
            midiasArmazenadas.add(midia);
        }

        System.out.println("Mídia salva no armazenamento do dispositivo: " + midia.getNomeArquivo());
        return midia.getNomeArquivo();
    }

    public Midia recuperar(String nomeArquivo) {
        for (Midia midia : midiasArmazenadas) {
            if (midia.getNomeArquivo().equalsIgnoreCase(nomeArquivo)) {
                return midia;
            }
        }

        System.out.println("Mídia não encontrada no armazenamento.");
        return null;
    }

    public void listarMidias() {
        if (midiasArmazenadas.isEmpty()) {
            System.out.println("Nenhuma mídia salva no armazenamento.");
            return;
        }

        System.out.println("\n===== MÍDIAS SALVAS =====");

        for (Midia midia : midiasArmazenadas) {
            System.out.println("- " + midia.getNomeArquivo());
        }
    }

    public void remover(Midia midia) {
        if (midiasArmazenadas.remove(midia)) {
            System.out.println("Mídia removida do armazenamento.");
        }
    }
}