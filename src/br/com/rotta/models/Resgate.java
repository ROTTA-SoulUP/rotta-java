package br.com.rotta.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import br.com.rotta.enums.StatusMovimentacao;

public class Resgate extends Movimentacao {

    private double pontosUtilizados;
    private String codigoQR;
    private LocalDateTime dataExpiracao;

    public Resgate(int id, double pontosUtilizados, String codigoQR) {
        super(id, pontosUtilizados);
        this.pontosUtilizados = pontosUtilizados;
        this.codigoQR = codigoQR;
    }

    @Override
    public void executar() {
        this.setStatus(StatusMovimentacao.CONCLUIDA);
    }

    public void gerarQRCode() {
        this.codigoQR = "ROTTA-QR-" + System.currentTimeMillis();
        this.dataExpiracao = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));

        System.out.println("QR Code gerado: " + codigoQR);
        System.out.println("Validade: hoje até " + dataExpiracao.toLocalTime());
    }

    public boolean validarQRCode() {
        return this.codigoQR != null && !verificarExpiracao();
    }

    public boolean verificarExpiracao() {
        return dataExpiracao == null || LocalDateTime.now().isAfter(dataExpiracao);
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }
}