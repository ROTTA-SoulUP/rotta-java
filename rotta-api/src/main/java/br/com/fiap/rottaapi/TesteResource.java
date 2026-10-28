    package br.com.fiap.rottaapi;

    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class TesteResource {

        private final JdbcTemplate jdbcTemplate;

        public TesteResource(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @GetMapping("/")
        public String testar() {
            return "API Rotta funcionando!";
        }

        @GetMapping("/teste-banco")
        public String testarBanco() {
            jdbcTemplate.queryForObject(
                    "SELECT 1 FROM DUAL",
                    Integer.class
            );
            return "Conexão com Oracle funcionando!";
        }

        @GetMapping("/diagnostico-config")
        public String diagnosticarConfig() {
            String usuario = System.getenv("DB_USERNAME");
            String senha = System.getenv("DB_PASSWORD");

            System.out.println("Tamanho da senha recebida: "
                    + (senha == null ? -1 : senha.length()));

            return "DB_USERNAME definido: " + (usuario != null && !usuario.isBlank())
                    + " | Usuario esperado: " + "RM571713".equals(usuario)
                    + " | DB_PASSWORD definida: " + (senha != null && !senha.isBlank());
        }
    }
