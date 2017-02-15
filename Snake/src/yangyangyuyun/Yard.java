package yangyangyuyun;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {


	public static final int ROWS = 30;
	public static final int COLS = 30;
	public static final int BLOCK_SIZE = 15;
	Snake snake=new Snake();
	Egg egg=new Egg();
//	Egg egg=new Egg(30,30);
	
	Image offscreenImage = null;

	public void launch() {
		this.setLocation(150, 150);
		this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {

				System.exit(0);
			}

		});
		this.setVisible(true);
		
		KeyMonitor l = new KeyMonitor();
		addKeyListener(l);
		
		new Thread(new PaintThread()).start();
		
	}

	public static void main(String[] args) {
		Yard yard = new Yard();
	
		yard.launch();
	}
	
	@Override
	public void paint(Graphics g) {
//	public void paintComponents(Graphics g) {
		Color c=g.getColor();
		g.setColor(Color.gray);	
		g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		g.setColor(Color.black);
		for(int i=0;i<ROWS-1;i++){
			g.drawLine(0, i*BLOCK_SIZE, COLS*BLOCK_SIZE, i*BLOCK_SIZE);
		}
		for(int i=0;i<COLS-1;i++){
			g.drawLine(i*BLOCK_SIZE, 0,i*BLOCK_SIZE, ROWS*BLOCK_SIZE);
		}
		g.setColor(c);
		
		snake.eat(egg);
		egg.draw(g);
		snake.draw(g);
	}
	

//	@Override
//	public void update(Graphics g) {
//		if(offscreenImage==null){
//			offscreenImage=this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
//		}
//		Graphics gOff= offscreenImage.getGraphics();
//		paint(g);
//		g.drawImage(offscreenImage, 0, 0, null);
//	}

	private class PaintThread implements Runnable{
		
		public void run() {
			while(true){
				repaint();
				try{
					Thread.sleep(180);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			snake.keyPressed(e);
		}
		
	}
	
	
	
}
