// Multiple balls program 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// A JPanel that displays multiple bouncing balls.
public class MultipleBalls extends JPanel {

    private static final int WIDTH = 800;  // Width of the panel
    private static final int HEIGHT = 600;  // Height of the panel
    private static final int MAX_BALLS = 10;  // Maximum number of balls
    private static final Color[] COLORS = {  // Array of possible ball colors
        Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE,
        Color.MAGENTA, Color.PINK, Color.CYAN, Color.GRAY, Color.BLACK
    };

    private List<Ball> balls;  // List of all the balls

    // Constructor that creates the panel with the specified number of balls.
    public MultipleBalls (int numBalls) {
        balls = new ArrayList<>();
        List<Color> colors = new ArrayList<>(Arrays.asList(COLORS));
        Collections.shuffle(colors);
        for (int i = 0; i < numBalls && i < MAX_BALLS; i++) {
            Ball ball = Ball.createRandomBall(WIDTH, HEIGHT);  // Create a new ball with random position and velocity
            ball.setColor(COLORS[i % COLORS.length]);  // Set the ball color
            balls.add(ball);  // Add the ball to the list of balls
            new Thread(new BallRunner(ball, this)).start();  // Start a new thread to run the ball's animation
        }
        setPreferredSize(new Dimension(WIDTH, HEIGHT));  // Set the preferred size of the panel
        setBackground(Color.WHITE);  // Set the background color of the panel
    }

    // Override the paintComponent method to draw all the balls on the panel.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            g.setColor(ball.getColor());  // Set the color of the ball
            Point position = ball.getPosition();  // Get the position of the ball
            int radius = ball.getRadius();  // Get the radius of the ball
            g.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);  // Draw the ball
        }
    }

    // A Runnable class that runs the animation for a single ball.
    private static class BallRunner implements Runnable {

        private Ball ball;  // The ball to animate
        private MultipleBalls panel;  // The panel to draw the ball on

        // Constructor that sets the ball and panel to animate.
        public BallRunner(Ball ball, MultipleBalls panel) {
            this.ball = ball;
            this.panel = panel;
        }

        // Override the run method to animate the ball.
        @Override
        public void run() {
            while (true) {
                ball.move(panel.getWidth(), panel.getHeight());  // Move the ball
                panel.repaint();  // Repaint the panel to show the updated ball position
                try {
                    Thread.sleep(10);  // Pause the thread for 10 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // main method
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null, "Enter the number of balls:");
        int numBalls = 0;
        try {
            numBalls = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Defaulting to 1 ball.");
            numBalls = 1;
        }
        numBalls = Math.min(numBalls, MAX_BALLS);
        MultipleBalls panel = new MultipleBalls(numBalls); //create a new panel with specified num of balls
        JFrame frame = new JFrame("Multiple Balls"); // create JFrame to display the panel 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}

