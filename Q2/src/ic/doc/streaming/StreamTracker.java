package ic.doc.streaming;

import ic.doc.User;

import java.time.LocalTime;

public class StreamTracker {
    private final User user;
    private LocalTime timestamp = LocalTime.now();

    public StreamTracker(User user) {
        this.user = user;
    }

    public LocalTime startTime() {
        return timestamp;
    }

    public User user() {
        return user;
    }

    public void decTime() {
        timestamp = timestamp.minusMinutes(20l);
    }

}
