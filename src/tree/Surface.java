package tree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Surface extends JPanel {
  private final int WIDTH = 800;
  private final int HEIGHT = 800;

  private ArrayList<Branch> tree;

  public Surface() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    tree = new ArrayList<Branch>();
    Branch root = new Branch(WIDTH / 2, HEIGHT, WIDTH / 2, HEIGHT - 100);
    tree.add(root);
    tree.add(root.branchRight());
    tree.add(root.branchLeft());
    int count = 0;
    for(int i = 0; i < 31; i++) {
      if(tree.get(i).finished) continue;
      tree.add(tree.get(i).branchRight());
      tree.add(tree.get(i).branchLeft());
    }
  }

  public void doDrawing(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.setColor(Color.GRAY);
    g2d.fillRect(0, 0, WIDTH, HEIGHT);
    g2d.setColor(Color.WHITE);
    g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
    for(Branch b : tree) {
      g2d.draw(b.getLine());
    }
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
