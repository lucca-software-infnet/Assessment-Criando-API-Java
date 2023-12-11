


import org.example.dto.UsuarioDTOinput;
import org.example.services.UsuarioService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ServiceTest {

    @Test

    public void testInsercaoDeUsuario() {

        UsuarioService usuarioService = new UsuarioService();
        UsuarioDTOinput usuarioDTOInput = new UsuarioDTOinput();
        usuarioDTOInput.setId(1);
        usuarioDTOInput.setNome("John Doe");
        usuarioDTOInput.setSenha("password123");


        usuarioService.inserirUsuario(usuarioDTOInput);


        assertEquals(1, usuarioService.listar().size());
    }
}

