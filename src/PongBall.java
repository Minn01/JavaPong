import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

public class PongBall {
    private GameWindow window;
    private GamePanel gamePanel;
    private int x;
    private int y;
    private final int width = 15;
    private final int height = 15;
    private double ballSpeed = 5.0;
    private boolean goingUpp;
    private boolean goingDown = true;
    private boolean goingRight = true;
    private boolean goingLeft;
    private final double speedVector;

    public PongBall(GameWindow window, GamePanel gamePanel, int x, int y) {
        this.speedVector = this.ballSpeed * 0.05;
        this.window = window;
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;

        Timer timer = new Timer(16, e -> movingBalls());
        timer.start();
    }

    public void movingBalls() {
        if (this.goingRight) {
            if ((double)this.x + this.ballSpeed < (double)this.window.getWidth()) {
                this.x = (int)((double)this.x + this.ballSpeed);
            } else {
                this.gamePanel.incrementPlayer1Score();
                this.resetBallSpeed();
                this.setBallMiddle();
            }
        }

        if (this.goingLeft) {
            if ((double)this.x - this.ballSpeed > 0.0) {
                this.x = (int)((double)this.x - this.ballSpeed);
            } else {
                this.gamePanel.incrementPlayer2score();
                this.resetBallSpeed();
                this.setBallMiddle();
            }
        }

        if (this.goingUpp) {
            if ((double)this.y - this.ballSpeed >= 0.0) {
                this.y = (int)((double)this.y - this.ballSpeed);
            } else {
                this.goingUpp = false;
                this.goingDown = true;
            }
        }

        if (this.goingDown) {
            if ((double)this.y + this.ballSpeed < (double)(this.window.getHeight() - 38)) {
                this.y = (int)((double)this.y + this.ballSpeed);
            } else {
                this.goingDown = false;
                this.goingUpp = true;
            }
        }

    }

    public void setBallMiddle() {
        this.x = this.window.getWidth() / 2;
        this.y = this.window.getHeight() / 2;
        this.setBallDirectionForX();
        this.setBallDirectionForY();
    }

    public void setBallDirectionForY() {
        Random random = new Random();
        if (random.nextBoolean()) {
            this.goingUpp = true;
            this.goingDown = false;
        } else {
            this.goingUpp = false;
            this.goingDown = true;
        }

    }

    public void setBallDirectionForX() {
        Random random = new Random();
        if (random.nextBoolean()) {
            this.goingRight = true;
            this.goingLeft = false;
        } else {
            this.goingRight = false;
            this.goingLeft = true;
        }

    }

    public void increaseBallSpeed() {
        this.ballSpeed += this.speedVector;
    }

    public void resetBallSpeed() {
        this.ballSpeed = 5.0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public double getBallSpeed() {
        return this.ballSpeed;
    }

    public void setBallSpeed(double ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    public boolean isGoingUpp() {
        return this.goingUpp;
    }

    public void setGoingUpp(boolean goingUpp) {
        this.goingUpp = goingUpp;
    }

    public boolean isGoingDown() {
        return this.goingDown;
    }

    public void setGoingDown(boolean goingDown) {
        this.goingDown = goingDown;
    }

    public boolean isGoingRight() {
        return this.goingRight;
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    public boolean isGoingLeft() {
        return this.goingLeft;
    }

    public void setGoingLeft(boolean goingLeft) {
        this.goingLeft = goingLeft;
    }
}
