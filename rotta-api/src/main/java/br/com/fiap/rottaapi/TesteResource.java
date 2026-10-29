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

    }
