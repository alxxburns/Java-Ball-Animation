// Ball Test Program
import javax.swing.JFrame;
public class BallTest {
 public static void main(String[] args) {
     JFrame frame = new JFrame("Bouncing Ball");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.add(new BallPanel());
     frame.pack();
     frame.setVisible(true);
 }
}
