package ic.doc;

import ic.doc.movies.Movie;
import ic.doc.streaming.PlaybackEventLog;
import ic.doc.streaming.StreamTracker;
import ic.doc.streaming.VideoStream;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class VideoStreamer {

  private final Map<VideoStream, StreamTracker> currentStreams = new HashMap<>();
  private final PlaybackEventLog playbackEvents = new PlaybackEventLog();

  public List<Movie> getSuggestedMovies(User user, Catalogue catalogue) {
    List<Movie> recommendations = MediaLibrary.getInstance().recommendedMoviesFor(user);

    // sort the list of suggestions in descending order of number of views
    List<Movie> suggestions =  new ArrayList<>(recommendations);
    suggestions.sort(Comparator.comparing(Movie::numberOfViews).reversed());
    return suggestions;
  }

  public boolean watchedMovie(User user, Movie movie) {
    return playbackEvents.watched(user, movie);
  }

  public VideoStream startStreaming(Movie movie, User user) {
    VideoStream stream = new VideoStream(movie);
    currentStreams.put(stream, new StreamTracker(user));
    return stream;
  }

  public void fastForward20(VideoStream stream) {
    StreamTracker streamTracker = currentStreams.get(stream);
    streamTracker.decTime();
  }

  public void stopStreaming(VideoStream stream) {
    StreamTracker streamTracker = currentStreams.remove(stream);
    LocalTime endTime = LocalTime.now();
    long minutesWatched = ChronoUnit.MINUTES.between(streamTracker.startTime(), endTime);
    if (minutesWatched > 15) {
      playbackEvents.logWatched(streamTracker.user(), stream.movie());
    }
  }

}
