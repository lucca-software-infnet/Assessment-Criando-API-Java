import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.example.dto.UsuarioDTOinput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIncercaoUsuario {

    private final String YOUR_API_BASE_URL = "http://0.0.0.0:4567/usuarios";

    @Test
   public void testUsuarioInsercao() throws Exception {

        UsuarioDTOinput usuarioDTOInput = generateUserDataFromExternalAPI();


        int responseCode = sendPostRequestToYourAPI(usuarioDTOInput);


        assertEquals(201, responseCode, "User insertion failed");
    }

    private UsuarioDTOinput generateUserDataFromExternalAPI() throws Exception {
        URL url = new URL("https://randomuser.me/api/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode, "Failed to fetch user data from external API");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.toString());
        JsonNode userNode = rootNode.path("results").get(0);

        UsuarioDTOinput usuarioDTOInput = new UsuarioDTOinput();
        usuarioDTOInput.setNome(userNode.path("name").path("first").asText());


        return usuarioDTOInput;
    }

    private int sendPostRequestToYourAPI(UsuarioDTOinput usuarioDTOInput) throws Exception {
        URL url = new URL(YOUR_API_BASE_URL + "/usuarios");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(usuarioDTOInput);

        connection.getOutputStream().write(requestBody.getBytes());

        return connection.getResponseCode();
    }
}
