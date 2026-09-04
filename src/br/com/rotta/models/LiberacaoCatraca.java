package br.com.rotta.models;

import br.com.rotta.enums.MetodoLiberacao;
import br.com.rotta.enums.StatusLiberacao;

import java.time.LocalDateTime;

public class LiberacaoCatraca {

    //ATRIBUTOS
    private int id;
    private MetodoLiberacao tipoMetodo;
    private LocalDateTime dataLiberacao;
    private StatusLiberacao status;

    //CONSTRUTOR
    public LiberacaoCatraca(int id) {
        this.id = id;
        this.dataLiberacao = LocalDateTime.now();
    }

    //MÉTODOS
    public boolean liberarViaNFC(RottaCard cartao) {
        this.tipoMetodo = MetodoLiberacao.NFC;
        System.out.println("Aproximando o Rotta Card no validador...");
        if (cartao.isAtivo() && cartao.getCarteira().consultarSaldo() > 0) {
            this.status = StatusLiberacao.VALIDADA;
            System.out.println("Catraca libereada via NFC! Boa viagem!");
            return true;
        }
        this.status = StatusLiberacao.NEGADA;
        System.out.println("Liberação negada. Verifique seu saldo ou o estado do cartão.");
        return false;
    }

    public boolean liberarViaQRCode(Resgate resgate) {
        this.tipoMetodo = MetodoLiberacao.QRCODE;
        System.out.println("Lendo o QR Code no validador...");
        if (resgate.validarQRCode()) {
            this.status = StatusLiberacao.VALIDADA;
            System.out.println("Catraca liberada via QR Code! Boa viagem!");
            return true;
        }
        this.status = StatusLiberacao.NEGADA;
        return false;
    }
    //GETTER
    public StatusLiberacao getStatus() {
        return status;
    }
}
