package game;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;

public  class BallBrick extends JPanel implements ActionListener, KeyListener {
	private boolean play = false;
	private int totalBrick=21;
	private Timer timer;
	private int delay = 4;
	private int bollpositionX = 120;
	private int bollpositionY = 350;
	private int bollXdir = -1;
	private int bollYdir = -2;
	private int playerX = 350;
	private int score = 0;
	private Brick map;
	
	public BallBrick(){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer = new Timer(delay,this);
		timer.start();
		map=new Brick(3,7);
	}
	
	public void paint(Graphics g){
		//black backgroung
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		//border
		g.setColor(Color.cyan);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(0, 3, 3, 592);
		g.fillRect(681, 3, 3, 592);
		
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerX,550,100,8);
		
		//bricks
		map.draw((Graphics2D)g);
		
		//ball
		g.setColor(Color.green);
		g.fillOval(bollpositionX, bollpositionY, 20, 20);
		
		//Score
		g.setColor(Color.red);
		g.drawString("Score : "+score,500,30);
		g.setFont(new Font("serif",Font.BOLD,23));
		
		//GameOver
		if(bollpositionY>=570){
			play=false;
			bollXdir=0;
			bollYdir=0;
			g.setColor(Color.green);
			g.drawString("GameOver!!!, Score: "+score,200, 300);
			g.setFont(new Font("serif",Font.BOLD,23));
			g.drawString("press Enter to Restart!!",220,350);
			g.setFont(new Font("serif",Font.BOLD,23));
		}
		
		if(totalBrick<=0){
			play=false;
			bollXdir=0;
			bollYdir=0; 
			g.setColor(Color.green);
			g.drawString("You Won!!, Score : "+score,200,300);
            g.drawString("Press Enter to Restart!!",230,350);			
		}
		
	}
	private void moveLeft(){
		play = true;
		playerX-=20;
		repaint();
	}
	private void moveRight(){
		play = true;
		playerX+=20;
		repaint();
	}
	
	@Override
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX <= 0) {
				playerX = 0;
			} else{
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 600)
				playerX = 600;
			else
				moveRight();
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!play){
				score=0;
				totalBrick=21;
				bollpositionX=120;
				bollpositionY=350;
				bollXdir=-1;
			    bollYdir=-2;
				playerX=320;
				map = new Brick(3,7);
			}
		}
		
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (play) {
			if (bollpositionX <= 0) {
				bollXdir =-bollXdir;
			}
			if (bollpositionX >= 670) {
				bollXdir =-bollXdir;
			}
			if (bollpositionY <= 0) {
				bollYdir =-bollYdir;
			}
			Rectangle bollRect = new Rectangle(bollpositionX, bollpositionY, 20, 20);
			Rectangle paddleRect = new Rectangle(playerX, 550, 100, 8);
			if (bollRect.intersects(paddleRect)) {
				bollYdir =-bollYdir;
			}
			
			for(int i=0;i<map.map.length;i++){
				for(int j=0;j<map.map[0].length;j++){
					if(map.map[i][j]>0){
						int width = map.brickWidth;
						int height = map.brickHeight;
						int brickXpos=80+j*width;
						int brickYpos=50+i*height;
						Rectangle brickRect = new Rectangle(brickXpos,brickYpos,width,height);
						if(bollRect.intersects(brickRect)){
							map.setBrick(0,i,j);
							totalBrick--;
							score+=5;
							if(bollpositionX+19<=brickXpos || bollpositionX+1>=brickXpos+width){
								bollXdir=-bollXdir;
							}else{
								bollYdir=-bollYdir;
							}
						}
					}
				}
			}
			
			bollpositionX += bollXdir;
			bollpositionY += bollYdir;
		}
		
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	
		
	}