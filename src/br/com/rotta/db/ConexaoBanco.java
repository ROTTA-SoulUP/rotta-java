package br.com.rotta.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe responsável por abrir a conexão com o banco de dados MySQL.
// Os dados abaixo devem ser trocados pelos dados do seu banco
// (host, nome do banco, usuário e senha).
public class ConexaoBanco {

    // ===== ATRIBUTOS (dados de conexão) =====
    private static final String URL = "jdbc:mysql://localhost:3306/rotta_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    // ===== MÉTODOS =====

    // Abre e devolve uma conexão com o banco.
    // Quem chamar este método precisa fechar a conexão depois.
    // Nas classes DAO usamos try-with-resources para fazer isso automaticamente.
    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}