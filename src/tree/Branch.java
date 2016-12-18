package tree;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.Random;

public class Branch {
  private float decreaseBy = 0.8f;
  private float angleChange = 30;
  private float angle;
  private float length;
  private Random r;
  private Point begin, end;
  public int branches;

  public Branch(Point begin, Point end) {
    this.r = new Random();
    this.begin = begin;
    this.end = end;
    this.angle = getAngle();
    this.length = getLength();
    this.branches = 0;
  }

  private float randAngle() {
    float change = r.nextFloat() + 0.5f;
    float angle = this.angleChange * change;
    return angle;
  }

  private float randDecrease() {
    float change = r.nextFloat() / 5.0f + 0.9f;
    float decrease = this.decreaseBy * change;
    return decrease;
  }

  public Branch(int x1, int y1, int x2, int y2) {
    this(new Point(x1, y1), new Point(x2, y2));
  }

  private float getAngle() {
    //    System.out.println("(" + begin.x + ", " + begin.y + ") (" + end.x + ", " + end.y + ")");
    float angle = (float) Math.toDegrees(Math.atan2(end.y - begin.y, end.x - begin.x));
    //    angle += 90;
    if (angle < 0) angle += 360;
    //    System.out.println(angle);
    return angle;
  }

  private float getLength() {
    //    System.out.println("(" + begin.x + ", " + begin.y + ") (" + end.x + ", " + end.y + ")");
    //    System.out.println(end.x - begin.x);
    int x = Math.abs(end.x - begin.x);
    int y = Math.abs(end.y - begin.y);
    return (float) Math.sqrt(x * x + y * y);
  }

  public Branch branchRight() {
    return branchOff(true);
  }

  public Branch branchLeft() {
    return branchOff(false);
  }

  public Branch branchOff(boolean right) {
    Point dir = new Point(
        (int) (Math.cos(Math.toRadians(angle + (right ? randAngle() : -randAngle()))) * length * randDecrease()),
        (int) (Math.sin(Math.toRadians(angle + (right ? randAngle() : -randAngle()))) * length * randDecrease()));
    int x2 = this.end.x + dir.x;
    int y2 = this.end.y + dir.y;
    this.branches += 1;
    return new Branch(this.end.x, this.end.y, x2, y2);
  }

  public Shape getLine() {
    Line2D l = new Line2D.Double();
    l.setLine(begin.x, begin.y, end.x, end.y);
    return l;
  }

  public void jitter() {
    end.x += r.nextInt(3) - 1;
  }
}
