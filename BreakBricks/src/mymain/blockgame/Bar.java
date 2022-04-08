package mymain.blockgame;

import java.awt.Graphics;

import myutil.MyConstant;

public class Bar extends item {
	
	int width;
	int height;
	
	int key_state = MyConstant.Key.NONE;
	
	int speed = 10;
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		
		g.fillRect(pos.x, pos.y, width, height);
	}	

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
		if(key_state == MyConstant.Key.LEFT) {
			pos.x -= speed;
			if(pos.x < 0)
				pos.x = 0;
		} else if(key_state == MyConstant.Key.RIGHT) {
			pos.x += speed;
			if(pos.x + width > MyConstant.BlockGame.GAMEPAN_WIDTH)
				pos.x = MyConstant.BlockGame.GAMEPAN_WIDTH - width;
		}
	}

}
