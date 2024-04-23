package ic.doc.streaming;

import ic.doc.User;
import ic.doc.movies.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaybackEventLog {

    private final Map<User, ArrayList<Movie>> likedMovies = new HashMap<>();

    public void logWatched(User user, Movie movie) {
        System.out.printf("%s enjoyed %s %n", user, movie.title());

        if (!likedMovies.containsKey(user)) {
            ArrayList<Movie> liked = new ArrayList<>();
            liked.add(movie);
            likedMovies.put(user, liked);
        } else {
            ArrayList<Movie> liked = likedMovies.get(user);
            liked.add(movie);
        }
    }

    public boolean watched(User user, Movie movie) {
        return likedMovies.get(user).contains(movie);
    }

    public void logRejection(User user, Movie movie) {
        System.out.printf("%s did not like %s %n", user, movie.title());
    }
}



