package tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Surface extends JPanel {
  private final int WIDTH = 400;
  private final int HEIGHT = 400;
  public Surface() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
  }

  public void doDrawing(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setColor(Color.GRAY);
    g2d.fillRect(0, 0, WIDTH, HEIGHT);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    doDrawing(g);
  }

  public void render() {
    paintImmediately(new Rectangle(0, 0, WIDTH, HEIGHT));
  }
}
