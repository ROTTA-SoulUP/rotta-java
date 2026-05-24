package br.com.rotta;
import br.com.rotta.models.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // DADOS INICIAIS
        Usuario usuarioAtivo = new Usuario(
                1,
                "Guilherme Almeida",
                "guimmalmeidaads@gmail.com",
                "11223344556",
                "amoarotta123",
                "11998765654"
        );

        Carteira carteiraAtiva = new Carteira(1, usuarioAtivo.getId());
        Midia midiaAtiva = null;
        int desafioEscolhido = -1;

        // DESAFIOS DISPONIVEIS
        String[] titulosDesafios = {
                "Selfie no Transporte",
                "Reciclar Lixo",
                "Andar de Bicicleta",
                "Economia de Energia",
                "Uso de Ecobag (Teste o QR Code)"
        };

        int[] pontosDesafios = {
                50,  // Selfie no Transporte
                40,  // Reciclar Lixo
                35,  // Andar de Bicicleta
                30,  // Economia de Energia
                150   // Desafio teste para obter QR Code
        };

        System.out.println("\n--- BEM-VINDO AO ROTTA ---");

        int op = -1;

        while (op != 0) {

            System.out.println("\n--------- MENU ---------");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Fazer Login");
            System.out.println("3 - Enviar Foto");
            System.out.println("4 - Enviar Vídeo");
            System.out.println("5 - Validar postagem com IA");
            System.out.println("6 - Consultar carteira");
            System.out.println("7 - Resgatar crédito");
            System.out.println("8 - Ver desafios disponíveis");
            System.out.println("9 - Atualizar email e telefone");
            System.out.println("10 - Desativar conta");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Digite apenas números.");
                scanner.next();
            }

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {

                case 1 -> {
                    System.out.println("\n----- CADASTRO -----");

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    usuarioAtivo = new Usuario(
                            2, nome, email,
                            cpf, senha, telefone
                    );

                    usuarioAtivo.cadastrar();

                    carteiraAtiva = new Carteira(
                            2, usuarioAtivo.getId()
                    );

                    System.out.println("Carteira criada com sucesso.");
                    System.out.println(carteiraAtiva.consultarSaldo());
                }

                case 2 -> {
                    System.out.println("\n------ LOGIN ------");

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    if (cpf.equals(usuarioAtivo.getCpf()) &&
                            senha.equals(usuarioAtivo.getSenhaHash())) {
                        usuarioAtivo.login();
                    } else {
                        System.out.println("CPF ou senha inválidos.");
                    }
                }

                case 3 -> {
                    System.out.println("\n------- ENVIAR FOTO -------");

                    if (desafioEscolhido == -1) {
                        System.out.println("Escolha um desafio primeiro (opção 8).");
                        break;
                    }

                    System.out.print("Descrição da foto: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Onde foi tirada? (ex: Na faculdade): ");
                    String localizacao = scanner.nextLine();

                    PostagemFoto foto = new PostagemFoto(
                            1,
                            "foto_rotta.jpg",
                            descricao,
                            usuarioAtivo.getId(),
                            localizacao
                    );

                    foto.enviar();
                    midiaAtiva = foto;
                    System.out.println(foto.consultarStatus());
                }

                case 4 -> {
                    System.out.println("\n------ ENVIAR VÍDEO ------");

                    if (desafioEscolhido == -1) {
                        System.out.println("Escolha um desafio primeiro (opção 8).");
                        break;
                    }

                    System.out.print("Duração em segundos (máx. 60 segundos): ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Digite apenas números.");
                        scanner.next();
                    }

                    int duracao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Qualidade (HD/4K): ");
                    String qualidade = scanner.nextLine();

                    PostagemVideo video = new PostagemVideo(
                            2,
                            "video_rotta.mp4",
                            "Vídeo do desafio: " +
                                    titulosDesafios[desafioEscolhido],
                            usuarioAtivo.getId(),
                            duracao,
                            qualidade
                    );

                    video.enviar();
                    midiaAtiva = video;
                    System.out.println(video.consultarStatus());
                }

                case 5 -> {

                    System.out.println("\n------ VALIDAÇÃO DA IA ------");

                    if (midiaAtiva == null) {

                        System.out.println("Nenhuma mídia enviada. " +
                                "Envie uma foto ou vídeo primeiro.");
                        break;
                    }

                    if (desafioEscolhido == -1) {

                        System.out.println("Nenhum desafio selecionado. " +
                                "Escolha um desafio primeiro (opção 8).");
                        break;
                    }

                    System.out.println("Validando desafio: " +
                            titulosDesafios[desafioEscolhido]);

                    System.out.println("Recompensa: " +
                            pontosDesafios[desafioEscolhido] + " pontos");

                    ValidacaoIA validacao = new ValidacaoIA(
                            1, midiaAtiva.getId()
                    );

                    validacao.analisarMidia(midiaAtiva);

                    validacao.exibirResultado();

                    if (validacao.foiAprovado()) {

                        Pontuacao pontuacao = new Pontuacao(
                                1,
                                validacao.getScoreIA(),
                                midiaAtiva.getId(),
                                carteiraAtiva.getId(),
                                pontosDesafios[desafioEscolhido]
                        );

                        pontuacao.executar();
                        pontuacao.calcularPontos();
                        carteiraAtiva.creditarPontos(
                                pontuacao.getPontosAtribuidos()
                        );

                        desafioEscolhido = -1;
                        midiaAtiva = null;
                        System.out.println("Desafio concluído!");

                    } else {

                        System.out.println("Postagem rejeitada. " +
                                "Tente novamente!");

                        midiaAtiva = null;
                    }
                }

                case 6 -> {
                    System.out.println("\n----- CARTEIRA -----");

                    System.out.println("Usuário: " +
                            usuarioAtivo.getNome());
                    System.out.println(carteiraAtiva.consultarSaldo());
                    System.out.println("Última atualização: " +
                            carteiraAtiva.getUltimaAtualizacao()
                                    .toLocalDate());
                }

                case 7 -> {

                    System.out.println("\n------- RESGATE -------");

                    System.out.println(carteiraAtiva.consultarSaldo());

                    System.out.println("Custo: 150 pontos = 1 passagem (R$ 5,30)");

                    if (carteiraAtiva.verificarLimite(150)) {

                        // RESGATE COM CUSTO FIXO DE 150 PONTOS
                        Resgate resgate = new Resgate(
                                1,
                                150,
                                carteiraAtiva.getId()
                        );

                        resgate.executar();

                        if (resgate.getStatus().equals("CONCLUIDO")) {

                            resgate.validarQRCode();
                            resgate.verificarExpiracao();
                            carteiraAtiva.debitarPontos(150);
                        }

                        System.out.println(resgate.consultarStatus());

                    } else {

                        System.out.println("Saldo insuficiente.");
                        System.out.println("Você precisa de 150 pontos.");
                        System.out.println("Complete mais desafios!");
                    }
                }

                case 8 -> {
                    System.out.println("\n------ DESAFIOS DISPONÍVEIS ------");
                    System.out.println("Complete desafios para ganhar pontos!\n");

                    for (int i = 0; i < titulosDesafios.length; i++) {
                        System.out.println("[" + (i + 1) + "] " +
                                titulosDesafios[i] + " → " +
                                pontosDesafios[i] + " pontos");
                    }

                    System.out.print("\nEscolha o número do desafio: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Digite apenas números.");
                        scanner.next();
                    }

                    int escolha = scanner.nextInt();
                    scanner.nextLine();

                    if (escolha >= 1 &&
                            escolha <= titulosDesafios.length) {
                        desafioEscolhido = escolha - 1;
                        System.out.println("\nDesafio aceito: " +
                                titulosDesafios[desafioEscolhido]);
                        System.out.println("Recompensa: " +
                                pontosDesafios[desafioEscolhido] + " pontos");
                        System.out.println("Agora envie sua foto " +
                                "ou vídeo (opção 3 ou 4)!");
                    } else {
                        System.out.println("Desafio inválido.");
                    }
                }

                case 9 -> {
                    System.out.println("\n----- ATUALIZAR EMAIL E TELEFONE -----");

                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();

                    System.out.print("Novo telefone: ");
                    String novoTelefone = scanner.nextLine();

                    usuarioAtivo.atualizarDados(novoEmail, novoTelefone);
                    System.out.println("Email atual: " + usuarioAtivo.getEmail());
                    System.out.println("Telefone atual: " + usuarioAtivo.getTelefone());
                }

                case 10 -> {
                    System.out.println("\n----- DESATIVAR CONTA -----");
                    usuarioAtivo.desativarConta();
                }

                case 0 -> {
                    System.out.println("\nEncerrando o sistema...");
                }

                default -> {
                    System.out.println("Opção inválida.");
                }
            }
        }
        scanner.close();
    }
}
