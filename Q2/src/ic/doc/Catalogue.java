package ic.doc;

import ic.doc.movies.Movie;
import java.util.List;

public interface Catalogue {
    public List<Movie> recommendedMoviesFor(User user);
}
