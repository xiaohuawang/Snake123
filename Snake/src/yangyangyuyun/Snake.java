package yangyangyuyun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Snake {

	private Node head = null;
	private Node tail = null;
	private int size = 0;
	
//	private Node n = new Node(20, 30);
	private Node n = new Node(20, 20, Dir.zuo);
//	private Node n = new Node(20, 30, Dir.you);
	
	public Snake() {
		head = n;
		tail = n;
		size = 1;
	}

	public void addToHead() {
		Node node=null;
		switch(head.dir){
		case zuo:
			node =new Node(head.row,head.col-1,head.dir);
			break;
		case shang:
			node =new Node(head.row-1,head.col,head.dir);
			break;
		case you:
			node =new Node(head.row,head.col+1,head.dir);
			break;
		case xia:
			node =new Node(head.row+1,head.col,head.dir);
			break;
		}
		node.next=head;
		head.pre=node;
        head=node;
        size++;
	}

	public void draw(Graphics g) {


//		Node m=new Node(10, 10, Dir.zuo);
//
//		n.draw(g);
//		m.draw(g);
//		System.out.println("size="+size);
		
//		if(size<=0)
//			return;
//		for(Node n=head;n!=null;n=n.next){
//			n.draw(g);
//		}
		
		if(size<=0)
			return;

		move();
		
		n=head;
		while(n!=null){
			n.draw(g);
			System.out.println("size="+size);
//			addToHead();
			n=n.next;
		}
	
	}

//	private void move(Egg egg) {
	private void move() {
		
		addToHead();
		deleteFromTail();
		
	}

	private void deleteFromTail() {
		if(size==0)
			return;
		tail=tail.pre;
		tail.next=null;
	}

	private class Node {

		int width = Yard.BLOCK_SIZE;
		int height = Yard.BLOCK_SIZE;
		int row = 0;
		int col = 0;
//		int row,col;
		Dir dir;
		Node next = null;
		Node pre=null;

//		private Node(int row,int col){
		private Node(int row, int col, Dir dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}

		void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.blue);
			g.fillRect(col * Yard.BLOCK_SIZE, row * Yard.BLOCK_SIZE, width, height);
			g.setColor(c);
		}

	}

	public void eat(Egg e){

		if(getRect().intersects(e.Rectangle())){
			e.appearAgain();
			addToHead();
		}
	}

	private Rectangle getRect() {
		return new Rectangle(head.col*Yard.BLOCK_SIZE,head.row*Yard.BLOCK_SIZE,head.width,head.height);
	}

	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			head.dir=Dir.zuo;
			break;
		case KeyEvent.VK_UP:
			head.dir=Dir.shang;
			break;
		case KeyEvent.VK_RIGHT:
			head.dir=Dir.you;
			break;
		case KeyEvent.VK_DOWN:	
			head.dir=Dir.xia;
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
//		case KeyEvent.VK_P:
//			sleep(20000);
		}
		
	}

}
