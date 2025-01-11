import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

public class GameWindow extends JFrame {
    private final int width = 1000;
    private final int height = (int)Math.floor(555.555555);

    public GameWindow() {
        this.setTitle("JavaPong");
        this.setSize(width, height);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel(this);
        this.add(gamePanel, "Center");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
