package tree;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.Random;

public class Branch {
  private float decreaseBy = 0.67f;
  private float angleChange = 30;
  private float angle;
  private Random r;
  private Point begin, end;
  public boolean finished = false;

  public Branch(Point begin, Point end) {
    this.r = new Random();
    this.begin = begin;
    this.end = end;
    this.angle = getAngle();
  }

  private float getAngle() {
    System.out.println("(" + begin.x + ", " + begin.y + ") (" + end.x + ", " + end.y + ")");
    float angle = (float) Math.toDegrees(Math.atan2(end.y - begin.y, end.x - begin.x));
//    angle += 90;
    if(angle < 0) angle += 360;
    System.out.println(angle);
    return angle;
  }

  public Branch(int x1, int y1, int x2, int y2) {
    this(new Point(x1, y1), new Point(x2, y2));
  }

  public Branch branchRight() {
    int x2 = this.end.x + (int) (Math.cos(Math.toRadians(angle + angleChange)) * 60.0);
    int y2 = this.end.y + (int) (Math.sin(Math.toRadians(angle + angleChange)) * 60.0);
    this.finished = true;
    return new Branch(this.end.x, this.end.y, x2, y2);
  }

  public Branch branchLeft() {
    int x2 = this.end.x + (int) (Math.cos(Math.toRadians(angle - angleChange)) * 60.0);
    int y2 = this.end.y + (int) (Math.sin(Math.toRadians(angle - angleChange)) * 60.0);
    this.finished = true;
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
