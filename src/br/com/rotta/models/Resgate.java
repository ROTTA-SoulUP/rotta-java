package br.com.rotta.models;

import java.util.Random;

public class Resgate extends Movimentacao {

    // ATRIBUTOS
    private double pontosUtilizados;
    private double valorCredito;
    private int diasRestantes;
    private String codigoQR;

    // CONSTRUTOR
    public Resgate(int id, double pontosUtilizados, int carteiraId) {

        super(id, pontosUtilizados, carteiraId);

        this.pontosUtilizados = pontosUtilizados;
        this.valorCredito = 5.30;
        this.diasRestantes = 7;
    }

    // METODOS
    @Override
    public void executar() {

        if (pontosUtilizados < 150) {

            setStatus("PENDENTE");

            System.out.println("Pontos insuficientes para resgate.");

            System.out.println("Você precisa de 150 pontos.");

            return;
        }

        gerarQRCode();

        setStatus("CONCLUIDO");

        System.out.println("Resgate realizado!");
    }

    public void gerarQRCode() {

        Random random = new Random();

        int numero = random.nextInt(9000) + 1000;

        codigoQR = "QR-ROTTA-" + numero;

        System.out.println("QR Code gerado: " + codigoQR);
    }

    public void validarQRCode() {
        System.out.println("QR Code validado.");
    }

    public void verificarExpiracao() {
        System.out.println("Validade confirmada.");
    }

    @Override
    public String consultarStatus() {
        return "Status do resgate: " + getStatus();
    }

    // GETTERS E SETTERS
    public double getPontosUtilizados() {
        return pontosUtilizados;
    }

    public double getValorCredito() {
        return valorCredito;
    }

    public int getDiasRestantes() {
        return diasRestantes;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public void setPontosUtilizados(double pontosUtilizados) {
        this.pontosUtilizados = pontosUtilizados;
    }

    public void setValorCredito(double valorCredito) {
        this.valorCredito = valorCredito;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }
}
