package tree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Surface extends JPanel implements MouseListener {
  private final int WIDTH = 800;
  private final int HEIGHT = 800;

  private ArrayList<Branch> tree;

  public Surface() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    addMouseListener(this);
    tree = new ArrayList<Branch>();
    generateNewTree();
  }

  private void addTree() {
    Branch root = new Branch(WIDTH / 2, HEIGHT, WIDTH / 2, HEIGHT - 125);
    tree.add(root);
    for (int i = 0; i < 11; i++) {
      addLayer();
    }
  }

  private void addLayer() {
    for (int i = tree.size() - 1; i >= 0; i--) {
      if (tree.get(i).branches >= 2) continue;
      tree.add(tree.get(i).branchRight());
      tree.add(tree.get(i).branchLeft());
    }
  }

  public void generateNewTree() {
    tree.clear();
    addTree();
    render();
  }

  public void doDrawing(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.setColor(Color.GRAY);
    g2d.fillRect(0, 0, WIDTH, HEIGHT);
    g2d.setColor(Color.WHITE);
    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
    for (Branch b : tree) {
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

  @Override
  public void mousePressed(MouseEvent e) {
    generateNewTree();
  }
  @Override
  public void mouseClicked(MouseEvent e) {}
  @Override
  public void mouseEntered(MouseEvent e) {}
  @Override
  public void mouseExited(MouseEvent e) {}
  @Override
  public void mouseReleased(MouseEvent e) {}
}
