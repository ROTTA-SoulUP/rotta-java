package br.com.rotta.models;
import java.util.HashMap;
import java.util.Map;

public class ArmazenamentoMidia {

    //ATRIBUTO | HASHMAP
    private Map<String, Midia> midiasArmazenadas = new HashMap<>();

    //MÉTODOS
    public String salvar(Midia midia) {
        String url = "storage/" + midia.getId() + ".dat";
        midiasArmazenadas.put(url, midia);
        System.out.println("Mídia armazenada com segurança em: " + url);
        return url;
    }

    public Midia recuperar(String url) {
        System.out.println("Buscando mídia em: " + url);
        return midiasArmazenadas.get(url);
    }

    public void excluir(String url) {
        midiasArmazenadas.remove(url);
        System.out.println("Mídia removida do armazenamento.");
    }
}
