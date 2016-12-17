package tree;

import java.awt.EventQueue;

public class Main {
  private void createAndShowGUI() {
    Window w = new Window();
    w.setVisible(true);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      Main m = new Main();
      m.createAndShowGUI();
    });
  }
}
