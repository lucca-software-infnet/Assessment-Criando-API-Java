


import org.example.dto.UsuarioDTOinput;
import org.example.services.UsuarioService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestIncercao {

    @Test

    public void testInsercaoDeUsuario() {

        UsuarioService usuarioService = new UsuarioService();
        UsuarioDTOinput usuarioDTOInput = new UsuarioDTOinput();
        usuarioDTOInput.setId(1);
        usuarioDTOInput.setNome("Lucca Nicassio");
        usuarioDTOInput.setSenha("Bleach");


        usuarioService.inserirUsuario(usuarioDTOInput);


        assertEquals(1, usuarioService.listar().size());
    }
}