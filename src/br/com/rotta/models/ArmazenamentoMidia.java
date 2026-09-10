package br.com.rotta.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class ArmazenamentoMidia {

    // ATRIBUTOS
    // Lista que simula a galeria offline do dispositivo, guardando as mídias antes da sincronização.
    private List<Midia> midiasArmazenadas = new ArrayList<>();

    // MÉTODOS
    // Salva a mídia na lista, evitando duplicidade, e retorna o nome do arquivo salvo.
    public String salvar(Midia midia) {
        if (!midiasArmazenadas.contains(midia)) {
            midiasArmazenadas.add(midia);
        }

        System.out.println("Mídia salva no armazenamento do dispositivo: " + midia.getNomeArquivo());
        return midia.getNomeArquivo();
    }

    // Procura e retorna uma mídia armazenada pelo nome do arquivo.
    public Midia recuperar(String nomeArquivo) {
        for (Midia midia : midiasArmazenadas) {
            if (midia.getNomeArquivo().equalsIgnoreCase(nomeArquivo)) {
                return midia;
            }
        }

        System.out.println("Mídia não encontrada no armazenamento.");
        return null;
    }

    // Lista todas as mídias atualmente armazenadas.
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

    // Remove uma mídia específica do armazenamento.
    public void remover(Midia midia) {
        if (midiasArmazenadas.remove(midia)) {
            System.out.println("Mídia removida do armazenamento.");
        }
    }
}