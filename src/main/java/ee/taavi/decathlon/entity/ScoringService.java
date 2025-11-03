package ee.taavi.decathlon.entity;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;

@Service
public class ScoringService {
    public static int calculatePoints(String event, double performance){
        double points = 0;

        if (event.equalsIgnoreCase("100m")) {
            points = 25.4347 * Math.pow(18 - performance, 1.81);
        } else if (event.equalsIgnoreCase("Long jump")) {
            points = 0.14354 * Math.pow(performance * 100 - 220, 1.4); // performance in meters → cm
        } else if (event.equalsIgnoreCase("Shot put")) {
            points = 51.39 * Math.pow(performance - 1.5, 1.05);
        } else if (event.equalsIgnoreCase("High jump")) {
            points = 0.8465 * Math.pow(performance * 100 - 75, 1.42); // meters → cm
        } else if (event.equalsIgnoreCase("400m")) {
            points = 1.53775 * Math.pow(82 - performance, 1.81);
        } else if (event.equalsIgnoreCase("110m hurdles")) {
            points = 5.74352 * Math.pow(28.5 - performance, 1.92);
        } else if (event.equalsIgnoreCase("Discus throw")) {
            points = 12.91 * Math.pow(performance - 4, 1.1);
        } else if (event.equalsIgnoreCase("Pole vault")) {
            points = 0.2797 * Math.pow(performance * 100 - 100, 1.35); // meters → cm
        } else if (event.equalsIgnoreCase("Javelin throw")) {
            points = 10.14 * Math.pow(performance - 7, 1.08);
        } else if (event.equalsIgnoreCase("1500m")) {
            points = 0.03768 * Math.pow(480 - performance, 1.85);
        } else {
            throw new IllegalArgumentException("Unknown event: " + event);
        }

        return (int) Math.floor(points);
    }
}
