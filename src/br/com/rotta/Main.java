package br.com.rotta;

import br.com.rotta.enums.*;
import br.com.rotta.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.rotta.dao.UsuarioDAO;
import java.sql.SQLException;

/**
 * Classe principal do projeto, responsável por executar o sistema, apresentar os menus e testar o fluxo das funcionalidades.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class Main {

    // ATRIBUTOS
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Usuario> usuariosCadastrados = new ArrayList<>();
    private static final double PONTOS_POR_PASSAGEM = 150.0;

    // Guarda o estado do usuário logado no momento, usado pelos menus depois do login.
    private static Usuario usuarioLogado;
    private static Carteira carteiraUsuario;
    private static Streak streakUsuario;
    private static Capi capi;
    private static ArmazenamentoMidia armazenamento;
    private static Midia ultimaMidiaEnviada;
    private static ParticipacaoDesafio participacaoAtual;
    private static RottaCard cartaoUsuario;
    private static Resgate ultimoResgate;

    // Lista fixa de desafios disponíveis no app, usada no menu de desafios.
    private static final Desafio[] desafios = {
            new Desafio(1, "1 - Caminhada no Parque", FormatoMidia.VIDEO, 30, 60),
            new Desafio(2, "2 - Descarte Inteligente", FormatoMidia.FOTO, 0, 50),
            new Desafio(3, "3 - Almoço Saudável", FormatoMidia.FOTO, 0, 40),
            new Desafio(4, "4 - Evitando Sacolas Plásticas", FormatoMidia.FOTO, 0, 30)
    };

    // METODO PRINCIPAL
    /**
     * Inicia a execução do sistema Rotta e apresenta o menu inicial.
     *
     * @param args argumentos recebidos na execução do programa
     */
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

    // MÉTODOS
    /**
     * Coleta, valida e cadastra um novo usuário no banco de dados.
     */
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

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            // Verifica no banco se já existe usuário com o mesmo CPF ou email antes de inserir.
            if (usuarioDAO.buscarPorCpf(cpf) != null) {
                System.out.println("Já existe um usuário cadastrado com esse CPF.");
                return;
            }

            if (usuarioDAO.buscarPorEmail(email) != null) {
                System.out.println("Já existe um usuário cadastrado com esse email.");
                return;
            }

            int proximoId = usuarioDAO.buscarProximoId();

            Usuario novoUsuario = new Usuario(proximoId, nome, email, cpf, senha, telefone);

            novoUsuario.cadastrar();
            usuarioDAO.inserir(novoUsuario);

            usuariosCadastrados.add(novoUsuario);

            System.out.println("Cadastro realizado.");
            System.out.println("Agora faça login para acessar sua conta.");

        } catch (SQLException e) {
            System.out.println("Não foi possível realizar o cadastro no banco.");
            System.out.println("Erro real: " + e.getMessage());
        }
    }

    /**
     * Realiza o login pelo CPF e senha e inicializa os dados necessários para o menu logado.
     */
    private static void fazerLogin() {
        System.out.println("\n========== LOGIN ==========");

        if (usuarioLogado != null) {
            System.out.println("Você já está logado como " + usuarioLogado.getNome() + ".");
            return;
        }

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioEncontrado;

        try {
            usuarioEncontrado = usuarioDAO.buscarPorCpf(cpf);
        } catch (SQLException e) {
            System.out.println("Não foi possível consultar o usuário no banco.");
            return;
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

    /**
     * Exibe o menu principal do usuário após o login.
     */
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
            System.out.println("7 - Excluir Conta");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    // Mostra o mascote Capi, sua dica atual, e o progresso de streak do usuário.
                    System.out.println("\n========== CAPI ==========");
                    capi.exibirNaTela();
                    System.out.println(capi.sugerirDica("streak"));
                    System.out.println("Dias consecutivos (Streak): " + streakUsuario.getDiasConsecutivos());
                    System.out.println("Nível atual: " + capi.verificarNivel(streakUsuario.getDiasConsecutivos()));
                    break;

                case 2:
                    // Lista os desafios disponíveis e inicia a participação no desafio escolhido.
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
                            // Cria a mídia (foto ou vídeo) de acordo com o formato exigido pelo desafio.
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

                            // Simula a galeria offline: o usuário pode salvar a mídia para enviar depois,
                            // ou enviar e validar imediatamente.
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
                            // Recupera uma mídia previamente salva no armazenamento offline e valida
                            // se ela é compatível com o desafio atual antes de enviar.
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
                    // Exibe o saldo da carteira, o tipo de uso exclusivo e o código do Rotta Card.
                    System.out.println("\n========== CARTEIRA ==========");
                    System.out.println("Saldo de pontos: " + carteiraUsuario.consultarSaldo());
                    System.out.println("Tipo de uso: " + carteiraUsuario.getTipoUso());
                    System.out.println("Rotta Card: " + cartaoUsuario.identificar());
                    break;

                case 4:
                    // Permite resgatar passagens em múltiplos de 150 pontos, escolhendo entre
                    // liberação via NFC (Rotta Card) ou geração de QR Code.
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
                    // Atualiza email e senha do usuário logado, tanto no objeto em memória quanto no banco.
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
                        System.out.println("Não foi possível atualizar no banco agora.");
                        System.out.println("Erro real: " + e.getMessage());
                    }

                    break;

                case 6:
                    // Exclui a conta permanentemente do banco de dados (DELETE real).
                    System.out.println("\n========== EXCLUIR CONTA ==========");
                    System.out.print("Tem certeza que deseja excluir sua conta permanentemente? (S/N): ");

                    String confirmacaoExclusao = scanner.nextLine();

                    if (confirmacaoExclusao.equalsIgnoreCase("S")) {
                        try {
                            UsuarioDAO usuarioDAO = new UsuarioDAO();
                            usuarioDAO.deletar(usuarioLogado.getId());

                            usuariosCadastrados.removeIf(usuario -> usuario.getId() == usuarioLogado.getId());
                            usuarioLogado = null;

                            System.out.println("Conta excluída.");
                        } catch (SQLException e) {
                            System.out.println("Não foi possível excluir a conta no banco.");
                            System.out.println("Erro real: " + e.getMessage());
                        }
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

    /**
     * Valida a mídia enviada, atualiza a participação e credita os pontos quando a validação é aprovada.
     *
     * @param midia mídia que será validada
     * @return true quando a mídia é aprovada; caso contrário, false
     */
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

    /**
     * Lê uma opção numérica informada pelo usuário.
     *
     * @return número informado ou -1 quando a entrada não é válida
     */
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}