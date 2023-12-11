package Controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.UsuarioDTOinput;
import org.example.services.UsuarioService;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;
import static spark.Spark.put;

import java.util.List;

public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();
    private final ObjectMapper objMapper = new ObjectMapper();

    public UsuarioController() {
        get("/usuarios", (request, response) -> {
            response.type("application/json");
            response.status(200);
            String json = objMapper.writeValueAsString(usuarioService.listar());
            return json;
        });

        get("/usuarios/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            Long id = Long.valueOf(idParam);
            String json = objMapper.writeValueAsString(usuarioService.buscarUsuarioPorId(id));
            response.status(200);
            return json;
        });

        delete("/usuarios/:id", (request, response) -> {
            response.type("application/json");
            String idParam = request.params("id");
            Long id = Long.valueOf(idParam);
            usuarioService.excluirUsuario(id);
            response.status(200);
            return "Usuário excluído com sucesso.";
        });

        post("/usuarios", (request, response) -> {
            UsuarioDTOinput usuarioDTOInput = objMapper.readValue(request.body(), UsuarioDTOinput.class);
            usuarioService.inserirUsuario(usuarioDTOInput);
            response.type("application/json");
            response.status(201);
            return "Usuário inserido com sucesso.";
        });

        put("/usuarios", (request, response) -> {
            UsuarioDTOinput usuarioDTOInput = objMapper.readValue(request.body(), UsuarioDTOinput.class);
            usuarioService.alterarUsuario(usuarioDTOInput);
            response.type("application/json");
            response.status(200);
            return "Usuário alterado com sucesso.";
        });
    }
}
