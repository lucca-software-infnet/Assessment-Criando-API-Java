

import org.example.Controllers.UsuarioController;

import static spark.Spark.get;
import static spark.Spark.port;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        UsuarioController usuarioController = new UsuarioController();
        port(4567);

        // Define uma rota de exemplo para listar usuários
        get("/usuarios", (request, response) -> {
            // Adicione aqui a lógica para obter a lista de usuários e convertê-la para JSON
            // Exemplo de retorno JSON: [{"id": 1, "nome": "Usuário 1"}, {"id": 2, "nome": "Usuário 2"}]
            return "[{\"id\": 1, \"nome\": \"Usuário 1\"}, {\"id\": 2, \"nome\": \"Usuário 2\"}]";
        });
    }
}
