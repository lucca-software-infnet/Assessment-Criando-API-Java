import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDeListagem  {


    private static final String API_URL = "http://127.0.0.1:4567/usuarios";

    @Test
    public void testListarUsuarios() throws IOException {
        URL url = new URL(API_URL + "/usuarios");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            assertEquals(200, responseCode, "Esperado código de resposta HTTP 200");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } finally {
            connection.disconnect();
        }
    }
}





