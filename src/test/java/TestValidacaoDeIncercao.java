import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.dto.UsuarioDTOinput;

import org.junit.Test;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



import static org.junit.Assert.assertEquals;

public class TestValidacaoDeIncercao {


    @Test
    public void testeValidacao() throws Exception {

        URL urlApiPublica = new URL("https://randomuser.me/api/");
        HttpURLConnection connection = (HttpURLConnection) urlApiPublica.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String jsonResponse = reader.readLine();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);

        String nomeUsuario = jsonNode.at("/results/0/name/first").asText();
        String senhaUsuario = "ksjdkajsdl";

        UsuarioDTOinput usuarioDTOInput = new UsuarioDTOinput();
        usuarioDTOInput.setNome(nomeUsuario);
        usuarioDTOInput.setSenha(senhaUsuario);

        URL urlSuaApi = new URL("http://localhost:4567/usuarios");
        HttpURLConnection connectionSuaApi = (HttpURLConnection) urlSuaApi.openConnection();
        connectionSuaApi.setRequestMethod("POST");
        connectionSuaApi.setRequestProperty("Content-Type", "application/json");
        connectionSuaApi.setDoOutput(true);

        String jsonInputString = objectMapper.writeValueAsString(usuarioDTOInput);

        connectionSuaApi.getOutputStream().write(jsonInputString.getBytes());

        int responseCodeSuaApi = connectionSuaApi.getResponseCode();
        assertEquals(201, responseCodeSuaApi);
    }
}