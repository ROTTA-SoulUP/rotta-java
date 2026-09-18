package br.com.rotta.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por estabelecer a conexão entre o sistema Rotta e o banco de dados Oracle.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */
public class ConexaoBanco {

    // ATRIBUTOS
    private static final String URL = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL";
    private static final String USUARIO = "RM571713";
    private static final String SENHA = "250807";

    // MÉTODOS
    /**
     * Estabelece uma conexão com o banco de dados Oracle.
     *
     * @return conexão ativa com o banco de dados
     * @throws SQLException caso não seja possível estabelecer a conexão
     */
    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}