package br.com.rotta;

import br.com.rotta.models.*;
import br.com.rotta.enums.*;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Main {

    // ATRIBUTOS  --------------------
    private Scanner scanner;
    private Usuario usuarioLogado;
    private Carteira carteiraAtual;
    private boolean appAberto;

    // CONSTANTES DE VALIDAÇÃO  --------------------
    private static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$"; //^ e $ abrem e fecham a linha.
    private static final String REGEX_TELEFONE = "^d{2} \\d{4,5}-\\d{4}$";
    private static final int TAMANHO_CPF = 11;
    private static final int TAMANHO_MINIMO_SENHA = 6;
    private static final int TAMANHO_MINIMO_NOME = 3;

    // CONSTRUTOR  --------------------
    public Main() {
        this.scanner = new Scanner(System.in);
        this.appAberto = true;
    }

    // METODO PRINCIPAL --------------------
    public static void main(String[] args) {
        Main app = new Main();
        app.iniciarApp();
    }

    // FLUXO PRINCIPAL DO APLICATIVO  -----
    /**
     * Inicia o aplicativo, exibe tela inicial e controla o loop principal
     */
    private void iniciarApp() {
        exibirTelaInicial();

        while (appAberto) {
            if (usuarioLogado == null) {
                telaAutenticacao();
            } else {
                telaAppPrincipal();
            }
        }
        scanner.close();
    }


    // TELA INICIAL  ----------------------
    /**
     * Exibe a tela de boas-vindas do aplicativo
     */
    private void exibirTelaInicial() {
        System.out.println("\n");
        System.out.println("===================================");
        System.out.println("        BEM-VINDO AO ROTTA");
        System.out.println("           from SoulUp");
        System.out.println("===================================");
        System.out.println("     Seu caminho, nossa rotta.");
        System.out.println("===================================");
    }

    // TELA DE AUTENTICAÇÃO  --------------------
    /**
     * Exibe menu de autenticação (Cadastro, Login ou Sair)
     */
    private void telaAutenticacao() {
        System.out.println("\n--- AUTENTICAÇÃO ---");
        System.out.println("1. Novo Cadastro");
        System.out.println("2. Login");
        System.out.println("3. Sair do App");
        System.out.print("Escolha: ");

        int opcao = lerInteiro();

        switch (opcao) {
            case 1 -> realizarCadastro();
            case 2 -> realizarLogin();
            case 3 -> appAberto = false;
            default -> System.out.println("Opção inválida.");
        }
    }

    /**
     * Realiza o cadastro de novo usuário com validações de segurança
     * Valida: nome (tamanho mínimo), email (formato), CPF (11 dígitos),
     * telefone (formato brasileiro), senha (tamanho mínimo)
     */
    private void realizarCadastro() {
        System.out.println("\n--- NOVO CADASTRO ---");

        // VALIDAR NOME --------------------
        String nome;
        while (true) {
            System.out.print("Nome completo: ");
            nome = scanner.nextLine().trim();

            if (validarNome(nome)) {
                break;
            }
            System.out.println("Nome deve ter no mínimo " + TAMANHO_MINIMO_NOME + " caracteres.");
        }

        // VALIDAR EMAIL  --------------------
        String email;
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine().trim();

            if (validarEmail(email)) {
                break;
            }
            System.out.println("Email inválido. Use o formato: usuario@dominio.com");
        }

        // VALIDAR CPF  --------------------
        String cpf;
        while (true) {
            System.out.print("CPF (somente números): ");
            cpf = scanner.nextLine().trim();

            if (validarCPF(cpf)) {
                break;
            }
            System.out.println("CPF inválido (deve conter 11 dígitos).");
        }

        // VALIDAR TELEFONE  --------------------
        String telefone;
        while (true) {
            System.out.print("Telefone (formato: (XX) XXXXX-XXXX): ");
            telefone = scanner.nextLine().trim();

            if (validarTelefone(telefone)) {
                break;
            }
            System.out.println("Formato inválido. Use: (XX) XXXXX-XXXX (ex: (11) 98765-4321)");
        }

        // VALIDAR SENHA  --------------------
        String senha;
        while (true) {
            System.out.print("Crie uma senha (mínimo " + TAMANHO_MINIMO_SENHA + " caracteres): ");
            senha = scanner.nextLine();

            if (validarSenha(senha)) {
                break;
            }
            System.out.println("Senha deve ter no mínimo " + TAMANHO_MINIMO_SENHA + " caracteres.");
        }

        usuarioLogado = new Usuario(1, nome, email, cpf, senha, telefone);
        carteiraAtual = new Carteira(1, usuarioLogado.getId());

        usuarioLogado.cadastrar();
        System.out.println("Cadastro realizado com sucesso! Você já está logado.");
    }

    /**
     * Realiza o login do usuário com validações
     * Valida: email (formato) e senha (tamanho mínimo)
     */
    private void realizarLogin() {
        System.out.println("\n--- LOGIN ---");

        // VALIDAR EMAIL  --------------------
        String email;
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine().trim();

            if (validarEmail(email)) {
                break;
            }
            System.out.println("Email inválido. Use o formato: usuario@dominio.com");
        }

        // VALIDAR SENHA  --------------------
        String senha;
        while (true) {
            System.out.print("Senha: ");
            senha = scanner.nextLine();

            if (validarSenha(senha)) {
                break;
            }
            System.out.println("Senha deve ter no mínimo " + TAMANHO_MINIMO_SENHA + " caracteres.");
        }

        usuarioLogado = new Usuario(1, "Usuario", email, "12345678900", senha, "11999999999");
        carteiraAtual = new Carteira(1, usuarioLogado.getId());

        usuarioLogado.login();
    }


    // MENU PRINCIPAL DO APLICATIVO  ---------------
    /**
     * Exibe o menu principal com as 7 opções de funcionalidades
     */
    private void telaAppPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ROTTA ---");
        System.out.println("1. Ver Desafios Disponíveis");
        System.out.println("2. Minha Carteira");
        System.out.println("3. Meu Progresso (Streak e Capi)");
        System.out.println("4. Enviar Comprovação de Desafio");
        System.out.println("5. Resgatar Passagem");
        System.out.println("6. Meu Perfil");
        System.out.println("7. Logout");
        System.out.print("Escolha: ");

        int opcao = lerInteiro();

        switch (opcao) {
            case 1 -> menuDesafios();
            case 2 -> menuCarteira();
            case 3 -> menuProgresso();
            case 4 -> menuEnviarMidia();
            case 5 -> menuResgate();
            case 6 -> menuPerfil();
            case 7 -> logout();
            default -> System.out.println("Opção inválida.");
        }
    }


    // MENU DE DESAFIOS  --------------------
    /**
     * Exibe desafios disponíveis e permite ao usuário iniciar um desafio
     * Atualiza streak e nível ao iniciar
     */
    private void menuDesafios() {
        System.out.println("\n--- DESAFIOS DISPONÍVEIS ---\n");

        Parceiro parceiro1 = new Parceiro(1, "Prefeitura Municipal", TipoParceiro.INSTITUICAO_PUBLICA, "00000000000191", true);
        Desafio desafio1 = new Desafio(1, "Passos no Bairro", FormatoMidia.FOTO, 50, parceiro1, true);

        System.out.println("Desafio encontrado:");
        desafio1.exibirDesafio();

        Capi capi = new Capi(1);
        System.out.println();
        capi.exibirNaTela();
        capi.sugerirDica("desafio");

        System.out.print("\nVocê deseja iniciar este desafio? (s/n) ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            ParticipacaoDesafio participacao = new ParticipacaoDesafio(1, usuarioLogado, desafio1);
            participacao.iniciar();

            Streak streak = new Streak(1, usuarioLogado);
            streak.atualizarStreak();

            Nivel nivel = new Nivel(1, "Iniciante", 1);
            nivel.verificarNivelAtual(streak.getDiasConsecutivos());

            Avatar avatar = new Avatar(1, "Capi em sua forma natural", nivel);
            avatar.exibirAvatar();
        }
    }


    // MENU DE CARTEIRA  --------------------
    /**
     * Exibe saldo da carteira digital e informações do Rotta Card (NFC)
     */
    private void menuCarteira() {
        System.out.println("\n--- MINHA CARTEIRA DIGITAL ---\n");

        System.out.println("Titular: " + usuarioLogado.getNome());
        carteiraAtual.consultarSaldo();

        RottaCard cartao = new RottaCard(1, "NFC-0001-ROTTA", carteiraAtual);
        String codigoCartao = cartao.identificar();
        System.out.println("Seu Rotta Card: " + codigoCartao);
        System.out.println("Status: " + (cartao.isAtivo() ? "Ativo e pronto para usar" : "Inativo"));

        System.out.println("\nO que deseja fazer?");
        System.out.println("1. Ver extrato completo");
        System.out.println("2. Voltar ao menu");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.println("\n--- EXTRATO ---");
            System.out.println("Data: " + LocalDateTime.now());
            System.out.println("Saldo Disponível: " + carteiraAtual.getSaldoPontos() + " pontos");
            System.out.println("Status: Ativo");
        }
    }


    // MENU DE PROGRESSO (STREAK E CAPI)  --------------------
    /**
     * Exibe o progresso do usuário: dias consecutivos, nível e avatar (Capi)
     */
    private void menuProgresso() {
        System.out.println("\n--- MEU PROGRESSO ---\n");

        Streak streak = new Streak(1, usuarioLogado);
        System.out.println("Verificando sua sequência...");
        streak.atualizarStreak();
        streak.verificarQuebrarStreak();

        int dias = streak.getDiasConsecutivos();
        System.out.println("\nDias consecutivos: " + dias);

        String nomeNivel = obterNomeNivel(dias);
        String descricaoAvatar = obterDescricaoAvatar(dias);

        Nivel nivel = new Nivel(dias, nomeNivel, dias);
        nivel.verificarNivelAtual(dias);

        Avatar avatar = new Avatar(1, descricaoAvatar, nivel);
        System.out.println();
        avatar.exibirAvatar();

        Capi capi = new Capi(1);
        capi.exibirNaTela();
        capi.sugerirDica("streak");
    }


    // MENU DE COMPROVAÇÃO DE DESAFIO  --------------------
    /**
     * Permite ao usuário enviar foto/vídeo como comprovação de desafio
     * Simula validação por IA, armazenamento offline se sem internet,
     * e creditação automática de pontos se aprovado
     */
    private void menuEnviarMidia() {
        System.out.println("\n--- ENVIAR COMPROVAÇÃO ---\n");

        Parceiro parceiro = new Parceiro(1, "Prefeitura Municipal", TipoParceiro.INSTITUICAO_PUBLICA, "00000000000191", true);
        Desafio desafio = new Desafio(1, "Passos no Bairro", FormatoMidia.FOTO, 50, parceiro, true);
        ParticipacaoDesafio participacao = new ParticipacaoDesafio(1, usuarioLogado, desafio);

        System.out.print("Digite o nome do arquivo (ex: foto_parque.jpg): ");
        String nomeArquivo = scanner.nextLine();

        System.out.print("Digite a localização (ex: Parque Villa-Lobos): ");
        String localizacao = scanner.nextLine();

        PostagemFoto foto = new PostagemFoto(1, nomeArquivo, usuarioLogado, participacao, localizacao);

        System.out.println("\nVerificando conexão com internet...");
        System.out.print("Você está em área com internet? (s/n) ");
        String internet = scanner.nextLine();

        if (internet.equalsIgnoreCase("n")) {
            System.out.println("\nSem internet detectada!");
            ArmazenamentoOffline armazem = new ArmazenamentoOffline();
            armazem.salvarTemporariamente(foto);
            System.out.println("\n(Simulando reconexão...)");
            System.out.println("Internet reestabelecida!");
            armazem.sincronizar();
        }

        foto.enviar();

        System.out.println("\nSubmetendo para validação...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ValidacaoIA validacao = new ValidacaoIA(1);
        validacao.analisarMidia(foto);

        if (validacao.foiAprovado()) {
            System.out.println("\nParabéns! Sua comprovação foi aprovada!");

            Pontuacao pontuacao = new Pontuacao(1, validacao);
            pontuacao.executar();

            CreditoPontos credito = new CreditoPontos(pontuacao, carteiraAtual);
            credito.executar();

            participacao.concluir();
        } else {
            System.out.println("\nInfelizmente sua comprovação foi rejeitada.");
            System.out.println("Tente novamente com uma imagem mais clara.");
            participacao.cancelar();
        }
    }


    // MENU DE RESGATE DE PASSAGEM  --------------------
    /**
     * Permite ao usuário resgatar passagem usando pontos acumulados
     * Oferece dois métodos: Rotta Card (NFC) ou QR Code
     */
    private void menuResgate() {
        System.out.println("\n--- RESGATAR PASSAGEM ---\n");

        System.out.println("Saldo disponível:");
        double saldo = carteiraAtual.consultarSaldo();

        if (saldo < 1) {
            System.out.println("\nVocê não tem pontos suficientes para resgatar uma passagem.");
            System.out.println("Complete mais desafios para ganhar pontos!");
            return;
        }

        System.out.print("\nDeseja resgatar uma passagem? (s/n) ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            Resgate resgate = new Resgate(1, 1.0, 1, carteiraAtual);
            resgate.executar();

            System.out.println("\nEscolha como deseja usar sua passagem:");
            System.out.println("1. Rotta Card (aproximar no validador)");
            System.out.println("2. QR Code (exibir no celular)");
            System.out.print("Escolha: ");

            int metodo = scanner.nextInt();
            scanner.nextLine();

            LiberacaoCatraca liberacao = new LiberacaoCatraca(1);
            RottaCard cartao = new RottaCard(1, "NFC-0001-ROTTA", carteiraAtual);

            if (metodo == 1) {
                liberacao.liberarViaNFC(cartao);
            } else if (metodo == 2) {
                liberacao.liberarViaQRCode(resgate);
            }
        }
    }


    // MENU DE PERFIL  --------------------
    /**
     * Exibe informações do usuário (nome, email, CPF, telefone, data de cadastro)
     * Permite atualizar email e telefone
     */
    private void menuPerfil() {
        System.out.println("\n--- MEU PERFIL ---\n");

        System.out.println("Nome: " + usuarioLogado.getNome());
        System.out.println("Email: " + usuarioLogado.getEmail());
        System.out.println("CPF: " + usuarioLogado.getCpf());
        System.out.println("Telefone: " + usuarioLogado.getTelefone());
        System.out.println("Data de Cadastro: " + usuarioLogado.getDataCadastro().toLocalDate());
        System.out.println("Status: " + (usuarioLogado.isAtivo() ? "Ativo" : "Inativo"));

        System.out.println("\nO que deseja fazer?");
        System.out.println("1. Atualizar dados");
        System.out.println("2. Voltar ao menu");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.print("Novo email: ");
            String novoEmail = scanner.nextLine();
            System.out.print("Novo telefone: ");
            String novoTelefone = scanner.nextLine();
            usuarioLogado.atualizarDados(novoEmail, novoTelefone);
        }
    }


    // LOGOUT  --------------------
    /**
     * Realiza logout do usuário e retorna à tela de autenticação
     */
    private void logout() {
        System.out.println("\nRealizando logout...");
        System.out.println("Até logo, " + usuarioLogado.getNome() + "!");
        usuarioLogado = null;
        carteiraAtual = null;
    }


    // MÉTODOS AUXILIARES  --------------------
    /**
     * Obtém o nome do nível baseado em dias consecutivos
     * Níveis: Iniciante, Comprometido, Inspirador, Transformador, Catalisador
     */
    private String obterNomeNivel(int diasConsecutivos) {
        if (diasConsecutivos >= 30) return "Catalisador - Lenda da Mobilidade";
        if (diasConsecutivos >= 15) return "Transformador";
        if (diasConsecutivos >= 7) return "Inspirador";
        if (diasConsecutivos >= 3) return "Comprometido";
        return "Iniciante";
    }

    /**
     * Obtém a descrição visual do avatar (Capi) baseado em dias consecutivos
     * Descreve roupas, acessórios e aura da Capi conforme nível progride
     */
    private String obterDescricaoAvatar(int diasConsecutivos) {
        if (diasConsecutivos >= 30) return "Capi com óculos futuristas de tecnologia (HUD), fones de ouvido e coroa dourada - cercada por uma energia vibrante";
        if (diasConsecutivos >= 15) return "Capi com aura roxa brilhante - transformando ativamente a rotina e infraestrutura da cidade";
        if (diasConsecutivos >= 7) return "Capi com mochila verde de sustentabilidade e elementos visuais de folhas - um exemplo que movimenta pessoas";
        if (diasConsecutivos >= 3) return "Capi com moletom oficial da Rotta - simbolizando que você entrou na rota e está criando constância";
        return "Capi em sua forma natural e amigável - marcando seu primeiro passo em direção às atitudes sustentáveis";
    }


    // MÉTODOS DE VALIDAÇÃO DE ENTRADA (SEGURANÇA)  -----------
    /**
     * Lê um inteiro da entrada do usuário com tratamento de exceção
     * Se a entrada não for um número válido, retorna -1
     * @return inteiro lido ou -1 se inválido
     */
    private int lerInteiro() {
        try {
            int valor = scanner.nextInt();
            scanner.nextLine();
            return valor;
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Entrada inválida. Digite um número.");
            return -1;
        }
    }

    /**
     * Valida se o nome tem tamanho mínimo de 3 caracteres
     * @param nome Nome a ser validado
     * @return true se válido, false caso contrário
     */
    private boolean validarNome(String nome) {
        return nome != null && nome.length() >= TAMANHO_MINIMO_NOME;
    }

    /**
     * Valida se o email está em formato correto usando regex
     * Padrão: usuario@dominio.extensão
     * @param email Email a ser validado
     * @return true se válido, false caso contrário
     */
    private boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        return pattern.matcher(email).matches();
    }

    /**
     * Valida se o CPF contém exatamente 11 dígitos numéricos
     * Remove não-dígitos e valida o tamanho resultante
     * @param cpf CPF a ser validado (pode conter formatação)
     * @return true se contiver 11 dígitos, false caso contrário
     */
    private boolean validarCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return false;
        }
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        return cpfLimpo.length() == TAMANHO_CPF;
    }

    /**
     * Valida se o telefone está no formato brasileiro: (XX) XXXXX-XXXX
     * Aceita 9 ou 10 dígitos após o DDD
     * @param telefone Telefone a ser validado
     * @return true se válido, false caso contrário
     */
    private boolean validarTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(REGEX_TELEFONE);
        return pattern.matcher(telefone).matches();
    }

    /**
     * Valida se a senha tem tamanho mínimo de 6 caracteres
     * @param senha Senha a ser validada
     * @return true se válido, false caso contrário
     */
    private boolean validarSenha(String senha) {
        return senha != null && senha.length() >= TAMANHO_MINIMO_SENHA;
    }
}