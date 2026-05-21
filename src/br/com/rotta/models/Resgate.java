package br.com.rotta.models;

import java.util.Random;

public class Resgate extends Movimentacao {

    // ATRIBUTOS
    private double pontosUtilizados;
    private double valorCredito;
    private int diasRestantes;
    private String codigoVoucher;

    // CONSTRUTOR
    public Resgate(int id, double pontosUtilizados, int carteiraId) {

        super(id, pontosUtilizados, carteiraId);
        this.pontosUtilizados = pontosUtilizados;

        // CONVERSÃO:
        // 100 pontos = R$ 1,00
        this.valorCredito = pontosUtilizados * 0.01;

        // Voucher válido por 7 dias
        this.diasRestantes = 7;
    }

    // MÉTODOS OBRIGATÓRIOS
    @Override
    public void executar() {
        gerarVoucher();
        setStatus("CONCLUIDO");

        System.out.println("Resgate realizado!");
        System.out.println("Voucher: " + codigoVoucher +
                        "\nCrédito: R$ " + valorCredito +
                        "\nDias restantes: " + diasRestantes
        );
    }

    @Override
    public void cancelar() {
        setStatus("CANCELADO");
        System.out.println("Resgate " + getId() + " cancelado.");
    }

    // MÉTODOS ESPECÍFICOS
    public void gerarVoucher() {

        Random random = new Random();

        int numero = random.nextInt(1000);
        codigoVoucher = "ROTTA-" + numero;
        System.out.println("Voucher gerado: " + codigoVoucher);
    }

    public void validarVoucher() {

        if (diasRestantes > 0) {
            System.out.println("Voucher válido!");
        } else {
            System.out.println("Voucher expirado!");
        }
    }

    public void verificarExpiracao() {

        System.out.println("Dias restantes: " + diasRestantes);
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

    public String getCodigoVoucher() {
        return codigoVoucher;
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

    public void setCodigoVoucher(String codigoVoucher) {
        this.codigoVoucher = codigoVoucher;
    }
}