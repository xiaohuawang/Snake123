package yangyangyuyun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {

	int row,column;
	int w=Yard.BLOCK_SIZE;
	int h=Yard.BLOCK_SIZE;
	private static Random r=new Random();

	public Egg(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public Egg(){
		this(r.nextInt(Yard.ROWS-2),r.nextInt(Yard.COLS-2));
	}
	
	public void draw(Graphics g){
		Color c=g.getColor();
	    g.setColor(Color.GREEN);
		g.fillOval(Yard.BLOCK_SIZE*column,Yard.BLOCK_SIZE*row,w,h);
		g.setColor(c);
		
	}
	
	public Rectangle Rectangle(){
		return new Rectangle(this.column*Yard.BLOCK_SIZE,this.row*Yard.BLOCK_SIZE,this.w,this.h);
	}

	//have modified the range of the rows and the cols
	public void appearAgain(){
		this.row=r.nextInt(Yard.ROWS-2);
		this.column=r.nextInt(Yard.COLS-2);
	}

}
