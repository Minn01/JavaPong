// Source code is decompiled from a .class file using FernFlower decompiler.
public class Pad {
    private GameWindow window;
    private int x;
    private int y;
    private int speed = 10;
    private int width = 7;
    private int height = 75;
    private boolean upPressed;
    private boolean downPressed;

    public Pad(GameWindow window, int x, int y) {
        this.window = window;
        this.x = x;
        this.y = y;
    }

    public void moveUp() {
        if (this.y - this.speed >= 0) {
            this.y -= this.speed;
        }

    }

    public void moveDown() {
        if (this.y + this.speed < this.window.getHeight() - 100) {
            this.y += this.speed;
        }

    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isUpPressed() {
        return this.upPressed;
    }

    public boolean isDownPressed() {
        return this.downPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }
}

