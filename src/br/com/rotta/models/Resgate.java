package br.com.rotta.models;

import br.com.rotta.enums.StatusMovimentacao;

import java.time.LocalDateTime;
import java.util.UUID;

public class Resgate extends Movimentacao { //Herança

    // ATRIBUTOS
    private double pontosUtilizados;
    private double valorCredito;
    private String codigoQR;
    private LocalDateTime dataExpiracao;

    // CONSTRUTOR
    public Resgate(int id, double pontosUtilizados, double valorCredito, Carteira carteira) {
        super(id, pontosUtilizados, carteira);
        this.pontosUtilizados = pontosUtilizados;
        this.valorCredito = valorCredito;
    }

    // METODOS
    @Override
    public void executar() {
        System.out.println("Processando resgate de " + pontosUtilizados + " pontos...");
        getCarteira().debitarPontos(this.pontosUtilizados);
        gerarQRCode();
        setStatus(StatusMovimentacao.CONCLUIDA);
        System.out.println("Resgate concluído! Sua passagem está pronta.");
        }

    public void gerarQRCode() {
        this.codigoQR = UUID.randomUUID().toString();
        this.dataExpiracao = LocalDateTime.now().plusHours(24);
        System.out.println("QR Code gerado: " + codigoQR);
        System.out.println("Válido até: " + dataExpiracao);
    }

    public boolean validarQRCode() {
        boolean valido = !verificarExpiracao();
        if (valido) {
            System.out.println("QR Code válido! Catraca liberada.");
        } else {
            System.out.println("QR Code expirado. Gere um novo resgate.");
        }
        return valido;
    }

    public boolean verificarExpiracao() {
        return LocalDateTime.now().isAfter(this.dataExpiracao);
    }

    public String getCodigoQR() {
        return codigoQR;
    }
}