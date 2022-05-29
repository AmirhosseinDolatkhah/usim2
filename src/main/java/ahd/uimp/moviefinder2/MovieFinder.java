package ahd.uimp.moviefinder2;

import ahd.ulib.swingutils.MainFrame;
import ahd.ulib.utils.mapper.StringMapper;
import ahd.ulib.utils.supplier.StringSupplier;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Swing for webapp
 */
public class MovieFinder {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MainFrame() {{
            // ENTITY-start
            record Movie(
                    long id,
                    String name,
                    String type,
                    String description,
                    int year,
                    double rating,
                    double imdb,
                    String duration,
                    String classification,
                    Set<String> genres,
                    Set<String> countries,
                    String imageUrl,
                    String coverUrl,
                    Set<String> sources
            ) {
                @Override
                public boolean equals(Object obj) {
                    if (obj == this)
                        return true;
                    if (obj instanceof Movie movie)
                        return movie.id == this.id;
                    return false;
                }

                @Override
                public int hashCode() {
                    return Long.hashCode(id);
                }
            }
            // ENTITY-end

            // GLOBAL-INSTANCES-start
            var httpclient = HttpClient.newHttpClient();
            var allMovies = new HashSet<Movie>();


//
//            list.get(0).
            // GLOBAL-INSTANCES-end

            // API-IMPLEMENT-start

            // API-IMPLEMENT-end

            // UI-MAIN-start
            add(elementE("MainTabbedPain", new JTabbedPane() {{
                add("Search", new JPanel(new MigLayout()) {{
                    add(elementE("searchMovieTextFiled", new JTextField() {{
                        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
                        addActionListener(e -> {
                            var s = getText();
                            if (s.isBlank())
                                return;
                            try {
                                var results = new HashSet<Movie>();
                                setEnabled(false);
                                JsonParser.parseString(httpclient.
                                    send(HttpRequest.newBuilder().uri(URI.create(String.
                                    format("https://movieboxapp.ir/api/search/%s/a/", URLEncoder.encode(s, StandardCharsets.UTF_8)))).build(),
                                    HttpResponse.BodyHandlers.ofString()).body()).
                                    getAsJsonObject().get("posters").getAsJsonArray().
                                    forEach(je -> results.add(new Movie(
                                            je.getAsJsonObject().get("id").getAsLong(),
                                            je.getAsJsonObject().get("title").getAsString(),
                                            je.getAsJsonObject().get("type").getAsString(),
                                            je.getAsJsonObject().get("description").getAsString(),
                                            je.getAsJsonObject().get("year").getAsInt(),
                                            je.getAsJsonObject().get("rating").getAsDouble(),
                                            je.getAsJsonObject().get("imdb").getAsDouble(),
                                            je.getAsJsonObject().get("duration").getAsString(),
                                            je.getAsJsonObject().get("classification").getAsString(),
                                            new HashSet<>() {{
                                                je.getAsJsonObject().get("genres").getAsJsonArray().forEach(s ->
                                                        add(s.getAsJsonObject().get("title").getAsString()));
                                            }},
                                            new HashSet<>() {{
                                                je.getAsJsonObject().get("country").getAsJsonArray().forEach(s ->
                                                        add(s.getAsJsonObject().get("title").getAsString()));
                                            }},
                                            je.getAsJsonObject().get("image").getAsString(),
                                            je.getAsJsonObject().get("cover").getAsString(),
                                            new HashSet<>() {{
                                                je.getAsJsonObject().get("sources").getAsJsonArray().forEach(s ->
                                                        add(s.getAsJsonObject().get("url").getAsString()));
                                            }}
                                    )));
                                allMovies.addAll(results);
                            } catch (IOException | InterruptedException | JsonParseException ex) {
                                ex.printStackTrace();
                            } finally {
                                setEnabled(true);
                            }
                        });
                    }}), "wmin 320");

                }});
            }}), BorderLayout.CENTER);
            // UI-MAIN-end
        }});
    }
}
