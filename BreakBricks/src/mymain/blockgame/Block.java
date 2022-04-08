package mymain.blockgame;

import java.awt.Color;
import java.awt.Graphics;

public class Block extends item {
	
	static int width;
	static int height;
	boolean bShow = true; //±úÁ³³Ä À¯¹«?
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		
		g.fillRect(pos.x+1, pos.y+1, width-2, height-2);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

}
