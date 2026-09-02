package br.com.rotta.enums;

public enum StatusMidia {

    // STATUS DA MÍDIA
    PENDENTE, // Acabou de ser criada, aguardando o envio (Sempre começa pendente)
    ENVIADO, // Foi enviada para analise da IA
    AGUARDANDO_SINCRONIZACAO // Aguardando a sincronização com o banco de dados
}