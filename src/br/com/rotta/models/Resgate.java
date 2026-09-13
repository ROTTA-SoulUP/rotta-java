package br.com.rotta.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import br.com.rotta.enums.StatusMovimentacao;

/**
 * Classe que representa o resgate de pontos para uma passagem de transporte público e controla o QR Code gerado.
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
    /**
     * Cria um resgate de pontos para uma passagem.
     *
     * @param id identificador do resgate
     * @param pontosUtilizados quantidade de pontos utilizada no resgate
     * @param codigoQR código QR associado ao resgate
     */
    public Resgate(int id, double pontosUtilizados, String codigoQR) {
        super(id, pontosUtilizados);
        this.pontosUtilizados = pontosUtilizados;
        this.codigoQR = codigoQR;
    }

    // MÉTODOS
    /**
     * Conclui a movimentação do resgate.
     */
    @Override
    public void executar() {
        this.setStatus(StatusMovimentacao.CONCLUIDA);
    }

    /**
     * Gera um QR Code para o resgate, válido até o fim do dia.
     */
    public void gerarQRCode() {
        this.codigoQR = "ROTTA-QR-" + System.currentTimeMillis();
        this.dataExpiracao = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));

        System.out.println("QR Code gerado: " + codigoQR);
        System.out.println("Validade: hoje até " + dataExpiracao.toLocalTime());
    }

    /**
     * Verifica se existe um QR Code e se ele ainda não expirou.
     *
     * @return true se o QR Code for válido; caso contrário, false
     */
    public boolean validarQRCode() {
        return this.codigoQR != null && !verificarExpiracao();
    }

    /**
     * Verifica se a data de expiração do QR Code já passou.
     *
     * @return true se o QR Code estiver expirado; caso contrário, false
     */
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