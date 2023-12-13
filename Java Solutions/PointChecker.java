import java.util.ArrayList;

public class PointChecker {
    private ArrayList<Coordinate> points;

    public PointChecker() {
        points = new ArrayList<>();
    }

    public void addPoint(int x, int y) {
        points.add(new Coordinate(x, y));
    }

    public boolean containsPoint(int x, int y) {
        return points.contains(new Coordinate(x, y));
    }
}
