package tree;

import javax.swing.JFrame;

public class Window extends JFrame {
  public Window() {
    setTitle("Fractal Trees");
    setResizable(false);
    Surface s = new Surface();
    add(s);
    pack();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }
}
