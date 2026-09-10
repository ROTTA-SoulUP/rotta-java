package br.com.rotta.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

// Classe responsavel por abrir a conexao com o banco de dados Oracle.
// Troque os valores abaixo pelos dados da SUA conexao (os mesmos que
// voce usou pra conectar no SQL Developer).
public class ConexaoBanco {

    // ATRIBUTOS (dados de conexao)

    // Formato: jdbc:oracle:thin:@HOST:PORTA:SID  (ou use "/SERVICE_NAME" no lugar de ":SID")
    // Exemplo FIAP:  jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
    // Exemplo local: jdbc:oracle:thin:@localhost:1521:XE
    private static final String URL = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL";

    // Usuário e senha do banco Oracle, exigidos pela Sprint 3 diretamente no código.
    private static final String USUARIO = "RM571713";
    private static final String SENHA = "250807";


    // MÉTODOS

    /**
     * Abre e devolve uma conexão ativa com o banco Oracle, usando a URL,
     * usuário e senha definidos acima. Toda classe DAO chama esse método
     * sempre que precisa executar um comando SQL (inserir, buscar, atualizar
     * ou deletar). Quem chamar esse método precisa fechar a conexão depois,
     * e por isso as classes DAO usam try-with-resources: a conexão é fechada
     * automaticamente assim que o bloco try termina.
     */
    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}