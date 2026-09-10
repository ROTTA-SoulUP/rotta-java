package br.com.rotta.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import br.com.rotta.enums.StatusMovimentacao;

/**
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class Resgate extends Movimentacao {

    // ATRIBUTOS
    private double pontosUtilizados;
    private String codigoQR;
    private LocalDateTime dataExpiracao;

    // CONSTRUTOR
    public Resgate(int id, double pontosUtilizados, String codigoQR) {
        super(id, pontosUtilizados);
        this.pontosUtilizados = pontosUtilizados;
        this.codigoQR = codigoQR;
    }

    // MÉTODOS
    // Implementação da execução específica do resgate, marcando como concluída.
    @Override
    public void executar() {
        this.setStatus(StatusMovimentacao.CONCLUIDA);
    }

    // Gera um novo código de QR Code, válido somente até o fim do dia atual.
    public void gerarQRCode() {
        this.codigoQR = "ROTTA-QR-" + System.currentTimeMillis();
        this.dataExpiracao = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));

        System.out.println("QR Code gerado: " + codigoQR);
        System.out.println("Validade: hoje até " + dataExpiracao.toLocalTime());
    }

    // Verifica se o QR Code existe e ainda não expirou.
    public boolean validarQRCode() {
        return this.codigoQR != null && !verificarExpiracao();
    }

    // Verifica se a data de expiração já passou.
    public boolean verificarExpiracao() {
        return dataExpiracao == null || LocalDateTime.now().isAfter(dataExpiracao);
    }

    // GETTER
    public String getCodigoQR() {
        return codigoQR;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }
}