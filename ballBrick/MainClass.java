import game.BallBrick;
import game.Brick;
import javax.swing.JFrame;



 public class MainClass extends JFrame{	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setTitle("BallBrick");
		frame.setSize(700,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setVisible(true);
		frame.setResizable(false);
		BallBrick obj = new BallBrick();
		frame.add(obj);
		
		
	}
}