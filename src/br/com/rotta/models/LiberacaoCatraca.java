package br.com.rotta.models;

import java.time.LocalDateTime;
import br.com.rotta.enums.MetodoLiberacao;
import br.com.rotta.enums.StatusLiberacao;

/**
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class LiberacaoCatraca {

    // ATRIBUTOS
    private int id;
    private MetodoLiberacao tipoMetodo;
    private LocalDateTime dataLiberacao;
    private StatusLiberacao status;

    // CONSTRUTOR
    public LiberacaoCatraca(int id) {
        this.id = id;
    }

    // MÉTODOS
    // Tenta liberar a catraca usando o cartão NFC, validando se ele está ativo.
    public boolean liberarViaNFC(RottaCard cartao) {
        this.tipoMetodo = MetodoLiberacao.NFC;
        this.dataLiberacao = LocalDateTime.now();

        if (cartao.isAtivo()) {
            this.status = StatusLiberacao.VALIDADA;
            System.out.println("Catraca liberada via NFC! Código: " + cartao.identificar());
            return true;
        }

        this.status = StatusLiberacao.NEGADA;
        System.out.println("Não foi possível liberar a catraca via NFC.");
        return false;
    }

    // Tenta liberar a catraca usando o QR Code do resgate, validando sua autenticidade.
    public boolean liberarViaQRCode(Resgate resgate) {
        this.tipoMetodo = MetodoLiberacao.QRCODE;
        this.dataLiberacao = LocalDateTime.now();

        if (resgate.validarQRCode()) {
            this.status = StatusLiberacao.VALIDADA;
            System.out.println("Catraca liberada via QR Code!");
            return true;
        }

        this.status = StatusLiberacao.NEGADA;
        System.out.println("QR Code inválido ou expirado.");
        return false;
    }

    // GETTER
    public StatusLiberacao getStatus() {
        return status;
    }
}