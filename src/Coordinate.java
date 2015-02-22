import java.util.*;

public class Coordinate {
  public int x,y;

  Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    Coordinate c = (Coordinate)o;
    return x == c.x && y == c.y;
  }
}
