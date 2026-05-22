package br.com.rotta;
import br.com.rotta.models.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Usuario usuarioAtivo = new Usuario(
                1,
                "Guilherme Almeida",
                "guimmalmeidaads@gmail.com",
                "11223344556",
                "amoarotta123",
                "11998765654"
        );

        Carteira carteiraAtiva = new Carteira(1, usuarioAtivo.getId());

        System.out.println("BEM-VINDO AO ROTTA");

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

                    System.out.print("\nNome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    Usuario novoUsuario = new Usuario(
                            2,
                            nome,
                            email,
                            cpf,
                            "senha123",
                            telefone
                    );

                    novoUsuario.cadastrar();

                    Carteira novaCarteira = new Carteira(
                            2,
                            novoUsuario.getId()
                    );

                    System.out.println("Carteira criada com sucesso.");
                    System.out.println(novaCarteira.consultarSaldo());
                }

                case 2 -> {

                    System.out.println("\n------ LOGIN ------");

                    System.out.print("Digite o seu email: ");
                    String email = scanner.nextLine();

                    usuarioAtivo.login();
                }

                case 3 -> {

                    System.out.println("\n------- ENVIAR FOTO -------");

                    System.out.print("Formato (JPG/PNG): ");
                    String formato = scanner.nextLine();

                    System.out.print("Resolução da imagem: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Digite apenas números.");
                        scanner.next();
                    }

                    int resolucao = scanner.nextInt();
                    scanner.nextLine();

                    PostagemFoto foto = new PostagemFoto(
                            1,
                            "foto_sustentavel.jpg",
                            "Selfie reciclando lixo",
                            usuarioAtivo.getId(),
                            formato,
                            resolucao
                    );

                    foto.enviar();

                    System.out.println(foto.consultarStatus());
                }

                case 4 -> {

                    System.out.println("\n------ ENVIAR VÍDEO ------");

                    System.out.print("Duração em segundos: ");

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
                            "video_sustentavel.mp4",
                            "Vídeo evitando desperdício",
                            usuarioAtivo.getId(),
                            duracao,
                            qualidade
                    );

                    video.enviar();

                    System.out.println(video.consultarStatus());
                }

                case 5 -> {

                    System.out.println("\n------ VALIDAÇÃO DA IA ------");

                    // Desafio simulado com pontos fixos
                    int pontosDesafio = 50;
                    System.out.println("Desafio ativo: Selfie no transporte público");
                    System.out.println("Recompensa: " + pontosDesafio + " pontos");

                    System.out.print("Tipo da mídia (1 - Foto | 2 - Vídeo): ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Digite apenas 1 ou 2.");
                        scanner.next();
                    }

                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    Midia midia;

                    if (tipo == 1) {
                        midia = new PostagemFoto(
                                1, "foto.jpg", "Foto sustentável",
                                usuarioAtivo.getId(), "JPG", 1080
                        );
                    } else {
                        midia = new PostagemVideo(
                                2, "video.mp4", "Vídeo sustentável",
                                usuarioAtivo.getId(), 20, "HD"
                        );
                    }

                    ValidacaoIA validacao = new ValidacaoIA(1, midia.getId());
                    validacao.analisarMidia(midia);
                    validacao.exibirResultado();

                    if (validacao.foiAprovado()) {

                        Pontuacao pontuacao = new Pontuacao(
                                1,
                                validacao.getScoreIA(),
                                midia.getId(),
                                carteiraAtiva.getId(),
                                pontosDesafio  // pontos fixos do desafio
                        );

                        pontuacao.executar();
                        pontuacao.calcularPontos();
                        System.out.println(pontuacao.consultarStatus());
                        carteiraAtiva.creditarPontos(pontuacao.getPontosAtribuidos());

                    } else {
                        System.out.println("Postagem rejeitada. Nenhum ponto creditado.");
                    }

                    System.out.println(carteiraAtiva.consultarSaldo());
                }

                case 6 -> {

                    System.out.println("\n----- CARTEIRA -----");

                    System.out.println(
                            "Usuário: " + usuarioAtivo.getNome()
                    );

                    System.out.println(
                            carteiraAtiva.consultarSaldo()
                    );

                    System.out.println(
                            "Última atualização: " +
                                    carteiraAtiva.getUltimaAtualizacao()
                    );
                }

                case 7 -> {

                    System.out.println("\n------- RESGATE -------");

                    System.out.println(
                            carteiraAtiva.consultarSaldo()
                    );

                    System.out.print(
                            "Quantos pontos deseja utilizar?: "
                    );

                    while (!scanner.hasNextDouble()) {
                        System.out.println("Digite apenas números.");
                        scanner.next();
                    }

                    double pontos = scanner.nextDouble();
                    scanner.nextLine();

                    if (carteiraAtiva.verificarLimite(pontos)) {

                        Resgate resgate = new Resgate(
                                1,
                                pontos,
                                carteiraAtiva.getId()
                        );

                        resgate.executar();

                        resgate.validarVoucher();
                        resgate.verificarExpiracao();

                        System.out.println(
                                resgate.consultarStatus()
                        );

                        carteiraAtiva.debitarPontos(pontos);

                        System.out.println(
                                carteiraAtiva.consultarSaldo()
                        );

                    } else {

                        System.out.println("Saldo insuficiente.");
                    }
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