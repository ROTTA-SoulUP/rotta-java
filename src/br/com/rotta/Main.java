package br.com.rotta;

import br.com.rotta.enums.*;
import br.com.rotta.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.rotta.dao.UsuarioDAO;
import java.sql.SQLException;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Usuario> usuariosCadastrados = new ArrayList<>();
    private static final double PONTOS_POR_PASSAGEM = 150.0;

    private static Usuario usuarioLogado;
    private static Carteira carteiraUsuario;
    private static Streak streakUsuario;
    private static Capi capi;
    private static ArmazenamentoMidia armazenamento;
    private static Midia ultimaMidiaEnviada;
    private static ParticipacaoDesafio participacaoAtual;
    private static RottaCard cartaoUsuario;
    private static Resgate ultimoResgate;

    private static final Desafio[] desafios = {
            new Desafio(1, "1 - Caminhada no Parque", FormatoMidia.VIDEO, 30, 60),
            new Desafio(2, "2 - Descarte Inteligente", FormatoMidia.FOTO, 0, 50),
            new Desafio(3, "3 - Almoço Saudável", FormatoMidia.FOTO, 0, 40),
            new Desafio(4, "4 - Evitando Sacolas Plásticas", FormatoMidia.FOTO, 0, 30)
    };

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n========== ROTTA ==========");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    fazerLogin();
                    break;
                case 0:
                    System.out.println("Encerrando o Rotta...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarUsuario() {
        System.out.println("\n========== CADASTRO ==========");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        String email;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();

            if (!email.contains("@")) {
                System.out.println("Email inválido. Digite novamente.");
            }
        } while (!email.contains("@"));

        String cpf;
        do {
            System.out.print("CPF: ");
            cpf = scanner.nextLine();

            if (cpf.length() != 11) {
                System.out.println("CPF inválido. Digite novamente.");
            }
        } while (cpf.length() != 11);

        String senha;
        do {
            System.out.print("Senha: ");
            senha = scanner.nextLine();

            if (senha.length() < 6) {
                System.out.println("A senha deve ter pelo menos 6 caracteres. Digite novamente.");
            }
        } while (senha.length() < 6);

        String telefone;
        do {
            System.out.print("Telefone: ");
            telefone = scanner.nextLine();

            if (telefone.length() != 11) {
                System.out.println("Telefone inválido. Digite novamente.");
            }
        } while (telefone.length() != 11);

        for (Usuario usuario : usuariosCadastrados) {
            if (usuario.getCpf().equals(cpf)) {
                System.out.println("Já existe um usuário cadastrado com esse CPF.");
                return;
            }

            if (usuario.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Já existe um usuário cadastrado com esse email.");
                return;
            }
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int proximoId;

        try {
            proximoId = usuarioDAO.buscarProximoId();
        } catch (SQLException e) {
            proximoId = usuariosCadastrados.size() + 1; // se o banco estiver fora do ar, usa a memória mesmo
        }

        Usuario novoUsuario = new Usuario(proximoId, nome, email, cpf, senha, telefone);

        novoUsuario.cadastrar();

        try {
            usuarioDAO.inserir(novoUsuario);
        } catch (SQLException e) {
            System.out.println("(Nao foi possível salvar no banco agora, mas o cadastro na simulação continua)");
            System.out.println("Erro real: " + e.getMessage());
        }

        usuariosCadastrados.add(novoUsuario);

        System.out.println("Cadastro realizado.");
        System.out.println("Agora faça login para acessar sua conta.");
    }

    private static void fazerLogin() {
        System.out.println("\n========== LOGIN ==========");

        if (usuarioLogado != null) {
            System.out.println("Você já está logado como " + usuarioLogado.getNome() + ".");
            return;
        }

        if (usuariosCadastrados.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            System.out.println("Faça o cadastro primeiro.");
            return;
        }

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuarioEncontrado = null;

        for (Usuario usuario : usuariosCadastrados) {
            if (usuario.getCpf().equals(cpf)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println("CPF não encontrado.");
            System.out.println("Faça o cadastro primeiro.");
            return;
        }

        if (!usuarioEncontrado.login(cpf, senha)) {
            return;
        }

        usuarioLogado = usuarioEncontrado;
        carteiraUsuario = new Carteira(usuarioLogado.getId(), 0, LocalDateTime.now(), "Uso exclusivo para transporte público");
        streakUsuario = new Streak(usuarioLogado.getId(), 0, null);
        capi = new Capi(1, "Capi", "Iniciante", "Capivara mascote do Rotta");
        armazenamento = new ArmazenamentoMidia();
        cartaoUsuario = new RottaCard(usuarioLogado.getId(), "NFC-" + usuarioLogado.getId(), true);

        cartaoUsuario.vincularCartao(carteiraUsuario);

        menuLogado();
    }

    private static void menuLogado() {
        int opcao;

        do {
            System.out.println("\n========== ROTTA ==========");
            System.out.println("Usuário: " + usuarioLogado.getNome());
            System.out.println("----------------------------");
            System.out.println("1 - Ver Capi e Streak");
            System.out.println("2 - Ver Desafios");
            System.out.println("3 - Ver Carteira");
            System.out.println("4 - Resgatar Passagem");
            System.out.println("5 - Atualizar Dados");
            System.out.println("6 - Desativar Conta");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    System.out.println("\n========== CAPI ==========");
                    capi.exibirNaTela();
                    System.out.println(capi.sugerirDica("streak"));
                    System.out.println("Dias consecutivos (Streak): " + streakUsuario.getDiasConsecutivos());
                    System.out.println("Nível atual: " + capi.verificarNivel(streakUsuario.getDiasConsecutivos()));
                    break;

                case 2:
                    System.out.println("\n========== DESAFIOS ==========");

                    for (Desafio desafio : desafios) {
                        desafio.exibirDesafio();
                        System.out.println();
                    }

                    System.out.print("Digite o número do desafio: ");
                    int escolhaDesafio = lerOpcao();

                    Desafio desafioEscolhido = null;

                    for (Desafio desafio : desafios) {
                        if (desafio.getId() == escolhaDesafio) {
                            desafioEscolhido = desafio;
                            break;
                        }
                    }

                    if (desafioEscolhido == null) {
                        System.out.println("Desafio não encontrado.");
                        break;
                    }

                    participacaoAtual = new ParticipacaoDesafio(escolhaDesafio, usuarioLogado, desafioEscolhido);
                    participacaoAtual.iniciar();


                    System.out.println("\n========== ENVIO DE MÍDIA ==========");
                    System.out.println("Desafio: " + participacaoAtual.getDesafio().getNome());
                    System.out.println("A captura e o envio da mídia devem ser realizados pelo aplicativo mobile.");
                    System.out.println("1 - Enviar nova mídia");
                    System.out.println("2 - Usar mídia armazenada");
                    System.out.println("0 - Voltar");
                    System.out.print("Escolha uma opção: ");

                    int tipoEnvio = lerOpcao();

                    switch (tipoEnvio) {
                        case 1:
                            ultimaMidiaEnviada = null;

                            if (participacaoAtual.getDesafio().getTipoFormato() == FormatoMidia.FOTO) {
                                System.out.print("Nome do arquivo da foto: ");
                                String nomeFoto = scanner.nextLine();

                                System.out.print("Localização: ");
                                String localizacao = scanner.nextLine();

                                PostagemFoto foto = new PostagemFoto(1, nomeFoto, usuarioLogado, participacaoAtual, localizacao);

                                if (foto.validarFoto()) {
                                    foto.comprimirImagem();
                                    ultimaMidiaEnviada = foto;
                                }
                            } else {
                                System.out.print("Nome do arquivo do vídeo: ");
                                String nomeVideo = scanner.nextLine();

                                System.out.print("Duração do vídeo em segundos: ");
                                int duracao = lerOpcao();

                                PostagemVideo video = new PostagemVideo(1, nomeVideo, usuarioLogado, participacaoAtual, duracao, "HD");

                                if (video.validarDuracao()) {
                                    ultimaMidiaEnviada = video;
                                }
                            }

                            if (ultimaMidiaEnviada == null) {
                                System.out.println("A mídia não pôde ser utilizada.");
                                break;
                            }

                            System.out.print("Deseja salvar a mídia no armazenamento? (S/N): ");
                            String salvar = scanner.nextLine();

                            if (salvar.equalsIgnoreCase("S")) {
                                armazenamento.salvar(ultimaMidiaEnviada);
                                System.out.println("Mídia armazenada para uso posterior.");
                            } else {
                                ultimaMidiaEnviada.enviar();
                                validarMidia(ultimaMidiaEnviada);
                            }
                            break;

                        case 2:
                            if (ultimaMidiaEnviada == null) {
                                System.out.println("Nenhuma mídia salva no armazenamento.");
                                break;
                            }

                            armazenamento.listarMidias();
                            System.out.print("Digite o nome da mídia que deseja utilizar: ");
                            String nomeMidia = scanner.nextLine();

                            ultimaMidiaEnviada = armazenamento.recuperar(nomeMidia);

                            if (ultimaMidiaEnviada != null) {
                                System.out.println("Mídia selecionada: " + ultimaMidiaEnviada.getNomeArquivo());

                                if (ultimaMidiaEnviada.getParticipacao() == null || ultimaMidiaEnviada.getParticipacao().getDesafio().getId() != participacaoAtual.getDesafio().getId()) {
                                    System.out.println("Essa mídia pertence a outro desafio.");
                                    System.out.println("Selecione uma mídia correspondente ao desafio atual.");
                                    break;
                                }

                                if (participacaoAtual.getDesafio().getTipoFormato() == FormatoMidia.VIDEO && !(ultimaMidiaEnviada instanceof PostagemVideo)) {
                                    System.out.println("Essa mídia não é compatível com o formato deste desafio.");
                                    break;
                                }

                                if (participacaoAtual.getDesafio().getTipoFormato() == FormatoMidia.FOTO && !(ultimaMidiaEnviada instanceof PostagemFoto)) {
                                    System.out.println("Essa mídia não é compatível com o formato deste desafio.");
                                    break;
                                }

                                ultimaMidiaEnviada.enviar();

                                if (validarMidia(ultimaMidiaEnviada)) {
                                    armazenamento.remover(ultimaMidiaEnviada);
                                }
                            }
                            break;

                        case 0:
                            break;

                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("\n========== CARTEIRA ==========");
                    System.out.println("Saldo de pontos: " + carteiraUsuario.consultarSaldo());
                    System.out.println("Tipo de uso: " + carteiraUsuario.getTipoUso());
                    System.out.println("Rotta Card: " + cartaoUsuario.identificar());
                    break;

                case 4:
                    System.out.println("\n========== RESGATE ==========");
                    System.out.println("Saldo disponível: " + carteiraUsuario.consultarSaldo());
                    System.out.println("1 passagem = 150 pontos");
                    System.out.print("Quantidade de pontos para resgatar: ");

                    double pontosResgate;

                    try {
                        pontosResgate = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido.");
                        break;
                    }

                    if (pontosResgate <= 0) {
                        System.out.println("A quantidade de pontos deve ser maior que zero.");
                        break;
                    }

                    if (pontosResgate % PONTOS_POR_PASSAGEM != 0) {
                        System.out.println("O resgate deve ser um múltiplo de 150 pontos.");
                        System.out.println("1 passagem = 150 pontos.");
                        break;
                    }

                    if (!carteiraUsuario.verificarSaldo(pontosResgate)) {
                        System.out.println("Saldo insuficiente.");
                        break;
                    }

                    int quantidadePassagens = (int) (pontosResgate / PONTOS_POR_PASSAGEM);

                    System.out.println("Você está resgatando " + quantidadePassagens + " passagem(ns).");
                    System.out.println("\nComo deseja utilizar a passagem?");
                    System.out.println("1 - Rotta Card (NFC)");
                    System.out.println("2 - QR Code");
                    System.out.println("0 - Voltar");
                    System.out.print("Escolha uma opção: ");

                    int metodo = lerOpcao();

                    switch (metodo) {
                        case 1:
                            carteiraUsuario.debitarPontos(pontosResgate);

                            ultimoResgate = new Resgate(1, pontosResgate, null);
                            ultimoResgate.executar();

                            LiberacaoCatraca liberacaoNFC = new LiberacaoCatraca(1);
                            liberacaoNFC.liberarViaNFC(cartaoUsuario);
                            break;

                        case 2:
                            carteiraUsuario.debitarPontos(pontosResgate);

                            ultimoResgate = new Resgate(1, pontosResgate, null);
                            ultimoResgate.executar();
                            ultimoResgate.gerarQRCode();

                            break;

                        case 0:
                            System.out.println("Resgate cancelado.");
                            break;

                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                    break;

                case 5:
                    System.out.println("\n========== ATUALIZAR DADOS ==========");
                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();

                    System.out.print("Nova senha: ");
                    String novaSenha = scanner.nextLine();

                    usuarioLogado.atualizarDados(novoEmail, novaSenha);

                    try {
                        UsuarioDAO usuarioDAO = new UsuarioDAO();
                        usuarioDAO.atualizar(usuarioLogado);
                    } catch (SQLException e) {
                        System.out.println("(Não foi possível atualizar no banco agora)");
                        System.out.println("Erro real: " + e.getMessage());
                    }

                    break;

                case 6:
                    System.out.println("\n========== DESATIVAR CONTA ==========");
                    System.out.print("Tem certeza que deseja desativar sua conta? (S/N): ");

                    String confirmacao = scanner.nextLine();

                    if (confirmacao.equalsIgnoreCase("S")) {
                        usuarioLogado.desativarConta();
                        usuarioLogado = null;
                    }
                    break;

                case 0:
                    System.out.println("Logout realizado.");
                    usuarioLogado = null;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0 && usuarioLogado != null);
    }

    private static boolean validarMidia(Midia midia) {
        System.out.println("\n========== VALIDAÇÃO IA ==========");

        ValidacaoIA validacao = new ValidacaoIA(1, 0.0, ResultadoValidacao.REPROVADO, "Aguardando análise.");

        validacao.analisarMidia(midia, participacaoAtual.getDesafio());
        validacao.exibirResultado();

        if (validacao.foiAprovado()) {
            Pontuacao pontuacao = new Pontuacao(1, participacaoAtual.getDesafio().getPontosDesafio());
            double pontos = pontuacao.calcularPontos();

            streakUsuario.atualizarStreak();
            participacaoAtual.concluir();
            carteiraUsuario.creditarPontos(pontos);

            return true;
        }

        participacaoAtual.cancelar();
        System.out.println("A mídia não foi aprovada. Nenhum ponto foi recebido.");
        return false;
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}