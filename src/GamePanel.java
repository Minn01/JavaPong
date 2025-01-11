import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener {
    private GameWindow window;
    private Pad rightPad;
    private Pad leftPad;
    private PongBall pongBall;
    int player1Score = 0;
    int player2Score = 0;

    public GamePanel(GameWindow window) {
        this.window = window;

        this.rightPad = new Pad(window, window.getWidth() - 100, window.getHeight() - 200);
        this.leftPad = new Pad(window, 100, window.getHeight() - 200);

        this.pongBall = new PongBall(window, this, window.getWidth() / 2, window.getHeight() / 2);

        this.setOpaque(false);
        this.setFocusable(true);
        this.requestFocusInWindow(true);
        this.addKeyListener(this);
        Timer timer = new Timer(16, (e) -> {
            this.gameLoop();
        });
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(this.window.getWidth() / 2, 0, 1, this.window.getHeight());

        // right pad
        g2d.fillRoundRect(this.rightPad.getX(), this.rightPad.getY(), this.rightPad.getWidth(), this.rightPad.getHeight(), 5, 5);

        // left pad
        g2d.fillRoundRect(this.leftPad.getX(), this.leftPad.getY(), this.leftPad.getWidth(), this.leftPad.getHeight(), 5, 5);

        // the pong
        g2d.setColor(Color.RED);
        g2d.fillOval(this.pongBall.getX(), this.pongBall.getY(), this.pongBall.getWidth(), this.pongBall.getHeight());

        int centerX = this.window.getWidth() / 2;
        int offset = 150;
        int scoreYPosition = 120;

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 100));

        // score displays
        g.drawString(String.valueOf(this.player1Score), centerX - offset - g.getFontMetrics().stringWidth(String.valueOf(this.player1Score)), scoreYPosition);
        g.drawString(String.valueOf(this.player2Score), centerX + offset, scoreYPosition);
    }

    public void gameLoop() {
        if (this.rightPad.isUpPressed()) {
            this.rightPad.moveUp();
        }

        if (this.rightPad.isDownPressed()) {
            this.rightPad.moveDown();
        }

        if (this.leftPad.isUpPressed()) {
            this.leftPad.moveUp();
        }

        if (this.leftPad.isDownPressed()) {
            this.leftPad.moveDown();
        }

        this.checkCollisions();
        this.repaint();
    }

    public void checkCollisions() {
        Rectangle leftPadBounds = new Rectangle(this.leftPad.getX(), this.leftPad.getY(), this.leftPad.getWidth(), this.leftPad.getHeight());
        Rectangle rightPadBounds = new Rectangle(this.rightPad.getX(), this.rightPad.getY(), this.rightPad.getWidth(), this.rightPad.getHeight());
        Rectangle pongBallBounds = new Rectangle(this.pongBall.getX(), this.pongBall.getY(), this.pongBall.getWidth(), this.pongBall.getHeight());

        if (leftPadBounds.intersects(pongBallBounds)) {
            this.pongBall.setGoingLeft(false);
            this.pongBall.setGoingRight(true);
            this.pongBall.setBallDirectionForY();
            this.pongBall.increaseBallSpeed();
        }

        if (rightPadBounds.intersects(pongBallBounds)) {
            this.pongBall.setGoingRight(false);
            this.pongBall.setGoingLeft(true);
            this.pongBall.setBallDirectionForY();
            this.pongBall.increaseBallSpeed();
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38:
                this.rightPad.setUpPressed(true);
                break;
            case 40:
                this.rightPad.setDownPressed(true);
                break;
            case 83:
                this.leftPad.setDownPressed(true);
                break;
            case 87:
                this.leftPad.setUpPressed(true);
        }

    }

    public void incrementPlayer1Score() {
        ++this.player1Score;
    }

    public void incrementPlayer2score() {
        ++this.player2Score;
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38:
                this.rightPad.setUpPressed(false);
                break;
            case 40:
                this.rightPad.setDownPressed(false);
                break;
            case 83:
                this.leftPad.setDownPressed(false);
                break;
            case 87:
                this.leftPad.setUpPressed(false);
        }

    }
}
