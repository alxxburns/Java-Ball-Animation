// A simple class to test the MultiBallPanel
import javax.swing.JFrame;

public class MultiBallTest {
    public static void main(String[] args) {
        int numBalls = 10;
        MultipleBalls ballPanel = new MultipleBalls(numBalls);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(ballPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
