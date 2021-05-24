package pl.lublin.wsei.java.cwiczenia.lab5;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Kulka {

    private double radius;
    private static final double R = 10;
    protected double ySpeed;
    protected double xSpeed;
    protected double xPos;
    protected double yPos;
    protected Color color;

    Kulka(double xPos, double yPos, double xSpeed, double ySpeed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = Color.WHITESMOKE;
        this.radius = R;
    }

    Kulka(double xPos, double yPos, double xSpeed, double ySpeed, Color color) {
        this(xPos, yPos, xSpeed, ySpeed);
        this.color = color;
    }

    Kulka(double xPos, double yPos, double xSpeed, double ySpeed, double radius, Color color) {
        this(xPos, yPos, xSpeed, ySpeed);
        this.color = color;
        this.radius = radius;
    }

    public void checkBoundaryCollision(double xLeft, double yTop, double xRight, double yBottom) {
        if ((xPos - radius <= xLeft) || (xPos + radius >= xRight)) xSpeed = -xSpeed;
        if ((yPos - radius <= yTop) || (yPos + radius >= yBottom)) ySpeed = -ySpeed;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(xPos - radius, yPos - radius, 2 * radius, 2 * radius);
    }

    public void update() {
        xPos += xSpeed;
        yPos += ySpeed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}