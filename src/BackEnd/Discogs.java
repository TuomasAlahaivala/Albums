package BackEnd;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Discogs {
    final String TOKEN = "pIzvEWLINzeagDrJfvwNSxzYbBRMaVfNsJsxCUOB";

    public void getAlbumsFromDiscogs() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .headers("Authorization: `Discogs token="+TOKEN)
                .uri(URI.create("https://api.scryfall.com/catalog/land-types"))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

        //String[] landTypes = response.body();
    }
}
