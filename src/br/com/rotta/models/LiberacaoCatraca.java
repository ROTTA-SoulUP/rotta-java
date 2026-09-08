package br.com.rotta.models;

import java.time.LocalDateTime;
import br.com.rotta.enums.MetodoLiberacao;
import br.com.rotta.enums.StatusLiberacao;

public class LiberacaoCatraca {

    private int id;
    private MetodoLiberacao tipoMetodo;
    private LocalDateTime dataLiberacao;
    private StatusLiberacao status;

    public LiberacaoCatraca(int id) {
        this.id = id;
    }

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

    public StatusLiberacao getStatus() {
        return status;
    }
}