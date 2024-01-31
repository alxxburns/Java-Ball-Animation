// Bouncing ball java program 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Ball {
    private static final int RADIUS = 20;
    private static final int MAX_SPEED = 40;

    private Point position;
    private Point velocity;

    public Ball(Point position, Point velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void move(int width, int height) {
        // Update position based on velocity
        position.x += velocity.x;
        position.y += velocity.y;

        // Bounce off walls
        if (position.x + RADIUS > width || position.x - RADIUS < 0) {
            velocity.x = -velocity.x;
        }
        if (position.y + RADIUS > height || position.y - RADIUS < 0) {
            velocity.y = -velocity.y;
        }
    }

    public Point getPosition() {
        return position;
    }

    public int getRadius() {
        return RADIUS;
    }

    public static Ball createRandomBall(int width, int height) {
        // Create a ball with a random position and velocity
        Random rand = new Random();
        Point position = new Point(rand.nextInt(width - RADIUS * 2) + RADIUS, rand.nextInt(height - RADIUS * 2) + RADIUS);
        Point velocity = new Point(rand.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED, rand.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED);
        return new Ball(position, velocity);
    }

	public void setColor(Color color) {
	}

	public Color getColor() {
		return null;
	}
}

class BallPanel extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Ball ball;

    public BallPanel() {
        // Create a random ball and set panel size and background color
        ball = Ball.createRandomBall(WIDTH, HEIGHT);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        // Create a timer to update the ball's position and repaint the panel
        Timer timer = new Timer(10, e -> {
            ball.move(getWidth(), getHeight());
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Draw the ball on the panel
        super.paintComponent(g);
        g.setColor(Color.RED);
        Point position = ball.getPosition();
        int radius = ball.getRadius();
        g.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);
    }
}


