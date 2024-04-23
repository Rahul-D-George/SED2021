package ic.doc;

import ic.doc.movies.Movie;
import ic.doc.streaming.VideoStream;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VideoStreamerTest {

    @Test
    public void allowsUserToStreamSuggestedMovies() {

        VideoStreamer streamer = new VideoStreamer();
        User user = new User("Adam", 9);

        List<Movie> movies = streamer.getSuggestedMovies(user, MediaLibrary.getInstance());
        VideoStream stream = streamer.startStreaming(movies.get(0), user);

        // adam watches the movie

        streamer.stopStreaming(stream);
    }

    @Test
    public void playbackEventLogRecordsMoviesWatchedForOver15Minutes() {
        VideoStreamer streamer = new VideoStreamer();
        User user = new User("Adam", 9);

        List<Movie> movies = streamer.getSuggestedMovies(user, MediaLibrary.getInstance());

        VideoStream stream = streamer.startStreaming(movies.get(0), user);
        streamer.fastForward20(stream);
        streamer.stopStreaming(stream);

        assertTrue(streamer.watchedMovie(user, movies.get(0)));
    }
}
