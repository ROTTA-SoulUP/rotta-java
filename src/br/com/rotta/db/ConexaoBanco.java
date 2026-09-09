package br.com.rotta.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe responsavel por abrir a conexao com o banco de dados Oracle.
// Troque os valores abaixo pelos dados da SUA conexao (os mesmos que
// voce usou pra conectar no SQL Developer).
public class ConexaoBanco {

    // ===== ATRIBUTOS (dados de conexao) =====

    // Formato: jdbc:oracle:thin:@HOST:PORTA:SID  (ou use "/SERVICE_NAME" no lugar de ":SID")
    // Exemplo FIAP:  jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
    // Exemplo local: jdbc:oracle:thin:@localhost:1521:XE
    private static final String URL = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL";

    private static final String USUARIO = "RM571713";
    private static final String SENHA = "250807";


    // ===== METODOS =====

    // Abre e devolve uma conexao com o banco. Quem chamar esse metodo precisa
    // fechar a conexao depois (usamos try-with-resources nas classes DAO).
    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}